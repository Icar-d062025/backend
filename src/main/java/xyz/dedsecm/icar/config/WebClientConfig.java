package xyz.dedsecm.icar.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    /**
     * WebClient dédié aux appels OSRM pour le calcul d'itinéraires.
     * @param baseUrl URL de base configurée pour OSRM
     * @return un bean WebClient configuré.
     */
    @Bean
    @Qualifier("osrmClient")
    public WebClient osrmWebClient(org.springframework.core.env.Environment env) {
        String url = env.getProperty("routing.osrm.base-url");
        return WebClient.builder()
                .baseUrl(url)
                .build();
    }
}
