package xyz.dedsecm.icar.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import xyz.dedsecm.icar.properties.RoutingProperties;
import xyz.dedsecm.icar.dto.OsrmRouteResponse;
import xyz.dedsecm.icar.dto.RouteInfo;
import xyz.dedsecm.icar.model.Coordinate;

/**
 * Service pour le calcul d'itinéraires via OSRM et le calcul d'émissions de CO2.
 */
@Service
public class RoutingService {

    private final WebClient osrmClient;
    private final RoutingProperties properties;

    public RoutingService(@Qualifier("osrmClient") WebClient osrmClient,
                          RoutingProperties properties) {
        this.osrmClient = osrmClient;
        this.properties = properties;
    }

    /**
     * Calcule l'itinéraire entre deux coordonnées.
     *
     * @param start point de départ
     * @param end   point d'arrivée
     * @return informations sur l'itinéraire et émissions de CO2
     */
    public RouteInfo getRoute(Coordinate start, Coordinate end) {
        String uri = String.format("/route/v1/driving/%.6f,%.6f;%.6f,%.6f?overview=false",
                start.getLongitude(), start.getLatitude(),
                end.getLongitude(), end.getLatitude());
        OsrmRouteResponse response = osrmClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(OsrmRouteResponse.class)
                .block();
        if (response == null || response.getRoutes() == null || response.getRoutes().length == 0) {
            throw new IllegalStateException("Aucune route trouvée");
        }
        OsrmRouteResponse.Route route = response.getRoutes()[0];
        double distance = route.getDistance();
        double duration = route.getDuration();
        double co2 = (distance / 1000.0) * properties.getCo2().getFactor();
        return new RouteInfo(distance, duration, co2);
    }
}
