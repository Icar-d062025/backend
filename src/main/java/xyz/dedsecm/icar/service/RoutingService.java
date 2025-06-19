package xyz.dedsecm.icar.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import xyz.dedsecm.icar.model.Coordinate;
import xyz.dedsecm.icar.model.NominatimResponse;
import xyz.dedsecm.icar.model.OSRMResponse;
import xyz.dedsecm.icar.model.RouteInfo;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RoutingService {

    private final WebClient webClient;

    @Value("${routing.osrm.base-url:http://router.project-osrm.org}")
    private String osrmBaseUrl;

    @Value("${routing.nominatim.base-url:https://nominatim.openstreetmap.org}")
    private String nominatimBaseUrl;

    @Value("${routing.osrm.timeout:30000}")
    private int osrmTimeout;

    @Value("${routing.nominatim.timeout:10000}")
    private int nominatimTimeout;

    public RoutingService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    /**
     * Géocode une adresse en coordonnées GPS
     */
    @Cacheable("geocoding")
    public Optional<Coordinate> geocodeAddress(String address) {
        try {
            log.info("Géocodage de l'adresse : {}", address);

            NominatimResponse[] responses = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .scheme("https")
                            .host("nominatim.openstreetmap.org")
                            .path("/search")
                            .queryParam("q", address)
                            .queryParam("format", "json")
                            .queryParam("limit", "1")
                            .build())
                    .header("User-Agent", "Icar")
                    .retrieve()
                    .bodyToMono(NominatimResponse[].class)
                    .timeout(Duration.ofMillis(nominatimTimeout))
                    .block();

            if (responses != null && responses.length > 0) {
                NominatimResponse response = responses[0];
                return Optional.of(new Coordinate(
                        Double.parseDouble(response.getLat()),
                        Double.parseDouble(response.getLon())));
            }

            return Optional.empty();

        } catch (Exception e) {
            log.error("Erreur lors du géocodage de l'adresse : {}", address, e);
            return Optional.empty();
        }
    }

    /**
     * Calcule la route entre deux coordonnées
     */
    @Cacheable("routing")
    public Optional<RouteInfo> calculateRoute(Coordinate start, Coordinate end) {
        try {
            log.info("Calcul de route de {} vers {}", start, end);

            String url = String.format("%s/route/v1/driving/%s;%s?overview=false&alternatives=false&steps=false",
                    osrmBaseUrl, start.toString(), end.toString());

            OSRMResponse response = webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(OSRMResponse.class)
                    .timeout(Duration.ofMillis(osrmTimeout))
                    .block();

            if (response != null && "Ok".equals(response.getCode()) &&
                    response.getRoutes() != null && response.getRoutes().length > 0) {

                OSRMResponse.Route route = response.getRoutes()[0];
                double distanceKm = route.getDistance() / 1000.0;
                int durationMinutes = (int) Math.round(route.getDuration() / 60.0);

                return Optional.of(new RouteInfo(distanceKm, durationMinutes));
            }

            return Optional.empty();

        } catch (WebClientResponseException e) {
            log.error("Erreur HTTP lors du calcul de route: {}", e.getMessage());
            return Optional.empty();
        } catch (Exception e) {
            log.error("Erreur lors du calcul de route", e);
            return Optional.empty();
        }
    }

    /**
     * Calcule la route entre deux adresses
     */
    public Optional<RouteInfo> calculateRouteFromAddresses(String startAddress, String endAddress) {
        log.info("Calcul de route de '{}' vers '{}'", startAddress, endAddress);

        Optional<Coordinate> startCoord = geocodeAddress(startAddress);
        if (startCoord.isEmpty()) {
            log.warn("Impossible de géocoder l'adresse de départ: {}", startAddress);
            return Optional.empty();
        }

        Optional<Coordinate> endCoord = geocodeAddress(endAddress);
        if (endCoord.isEmpty()) {
            log.warn("Impossible de géocoder l'adresse d'arrivée: {}", endAddress);
            return Optional.empty();
        }

        return calculateRoute(startCoord.get(), endCoord.get());
    }

    /**
     * Calcule la distance à vol d'oiseau (approximation rapide)
     */
    public double calculateHaversineDistance(Coordinate start, Coordinate end) {
        final double R = 6371; // Rayon de la Terre en km

        double latDistance = Math.toRadians(end.getLatitude() - start.getLatitude());
        double lonDistance = Math.toRadians(end.getLongitude() - start.getLongitude());

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(start.getLatitude())) * Math.cos(Math.toRadians(end.getLatitude()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }

    /**
     * Calcule les routes pour un trajet multi-étapes
     */
    public Optional<RouteInfo> calculateMultiStepRoute(List<Coordinate> waypoints) {
        if (waypoints.size() < 2) {
            return Optional.empty();
        }

        double totalDistance = 0;
        int totalDuration = 0;

        for (int i = 0; i < waypoints.size() - 1; i++) {
            Optional<RouteInfo> segment = calculateRoute(waypoints.get(i), waypoints.get(i + 1));
            if (segment.isEmpty()) {
                log.warn("Impossible de calculer le segment {} -> {}", i, i + 1);
                return Optional.empty();
            }

            totalDistance += segment.get().getDistanceKm();
            totalDuration += segment.get().getDurationMinutes();
        }

        return Optional.of(new RouteInfo(totalDistance, totalDuration));
    }
}
