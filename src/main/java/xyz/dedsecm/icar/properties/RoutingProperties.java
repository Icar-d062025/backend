package xyz.dedsecm.icar.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration properties for routing services.
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "routing")
public class RoutingProperties {
    private Osrm osrm;
    private Co2 co2;

    @Getter
    @Setter
    public static class Osrm {
        private String baseUrl;
        private int timeout;
    }

    @Getter
    @Setter
    public static class Co2 {
        private double factor;
    }
}
