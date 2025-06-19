package xyz.dedsecm.icar.service;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import xyz.dedsecm.icar.properties.RoutingProperties;
import xyz.dedsecm.icar.dto.RouteInfo;
import xyz.dedsecm.icar.model.Coordinate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour le service de routage, couvrant calcul de distance, durée et CO2.
 */
class RoutingServiceTest {

    private static MockWebServer mockWebServer;
    private RoutingService routingService;
    private RoutingProperties properties;

    @BeforeAll
    static void startServer() throws Exception {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterAll
    static void stopServer() throws Exception {
        mockWebServer.shutdown();
    }

    @BeforeEach
    void setUp() {
        String baseUrl = mockWebServer.url("/").toString();
        WebClient client = WebClient.builder().baseUrl(baseUrl).build();
        properties = new RoutingProperties();
        RoutingProperties.Co2 co2 = new RoutingProperties.Co2();
        co2.setFactor(0.1); // 0.1 kg CO2 per km
        properties.setCo2(co2);
        routingService = new RoutingService(client, properties);
    }

    /**
     * Vérifie que le service retourne les bonnes valeurs pour distance, durée et CO2.
     */
    @Test
    void testGetRouteSuccess() {
        String json = "{\"routes\":[{\"distance\":1000.0,\"duration\":60.0}]}";
        mockWebServer.enqueue(new MockResponse()
                .setBody(json)
                .addHeader("Content-Type", "application/json"));

        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(0, 0);
        RouteInfo info = routingService.getRoute(start, end);

        assertEquals(1000.0, info.getDistance(), 0.0001);
        assertEquals(60.0, info.getDuration(), 0.0001);
        // distance 1000m => 1km * 0.1 = 0.1kg CO2
        assertEquals(0.1, info.getCo2(), 0.0001);
    }

    /**
     * Vérifie que le service lève une exception si aucune route n'est trouvée.
     */
    @Test
    void testGetRouteNoRoutes() {
        String json = "{\"routes\":[]}";
        mockWebServer.enqueue(new MockResponse()
                .setBody(json)
                .addHeader("Content-Type", "application/json"));

        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(0, 0);
        IllegalStateException ex = assertThrows(IllegalStateException.class,
                () -> routingService.getRoute(start, end));
        assertTrue(ex.getMessage().contains("Aucune route trouvée"));
    }
}
