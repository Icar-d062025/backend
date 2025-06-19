package xyz.dedsecm.icar.model;

import lombok.Data;

@Data
public class OSRMResponse {
    private String code;
    private Route[] routes;

    @Data
    public static class Route {
        private double distance;
        private double duration;
        private String geometry;
    }
}
