package xyz.dedsecm.icar.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * DTO representation of OSRM routing response.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OsrmRouteResponse {

    private Route[] routes;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Route {
        private double distance; // en mètres
        private double duration; // en secondes
    }
}
