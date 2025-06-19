package xyz.dedsecm.icar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.dedsecm.icar.model.Coordinate;
import xyz.dedsecm.icar.dto.RouteInfo;
import xyz.dedsecm.icar.service.RoutingService;

/**
 * Controller REST pour l'exposition du calcul d'itinéraires et émissions de CO2.
 */
@RestController
@RequestMapping("/api/routes")
public class RoutingController {

    private final RoutingService routingService;

    public RoutingController(RoutingService routingService) {
        this.routingService = routingService;
    }

    /**
     * Calcule un itinéraire et les émissions de CO2 entre deux points géographiques.
     * @param startLat latitude du point de départ
     * @param startLng longitude du point de départ
     * @param endLat latitude du point d'arrivée
     * @param endLng longitude du point d'arrivée
     * @return informations sur la distance (m), durée (s) et CO2 (kg)
     */
    @GetMapping
    public RouteInfo getRoute(
            @RequestParam double startLat,
            @RequestParam double startLng,
            @RequestParam double endLat,
            @RequestParam double endLng) {
        Coordinate start = new Coordinate(startLat, startLng);
        Coordinate end = new Coordinate(endLat, endLng);
        return routingService.getRoute(start, end);
    }
}
