package xyz.dedsecm.icar.config;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test de la configuration du WebClient pour OSRM.
 */
class WebClientConfigTest {

    private static MockWebServer mockWebServer;

    @BeforeAll
    static void startServer() throws Exception {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterAll
    static void stopServer() throws Exception {
        mockWebServer.shutdown();
    }

    @Test
    void testOsrmWebClientBaseUrl() throws Exception {
        // Arrange: stub response
        mockWebServer.enqueue(new MockResponse().setBody("ok").addHeader("Content-Type", "text/plain"));
        String baseUrl = mockWebServer.url("/").toString();
        MockEnvironment env = new MockEnvironment().withProperty("routing.osrm.base-url", baseUrl);

        // Act: create WebClient via config and perform request
        WebClient client = new WebClientConfig().osrmWebClient(env);
        String response = client.get().uri("/test").retrieve().bodyToMono(String.class).block();

        // Assert: response body and requested path
        assertEquals("ok", response);
        String path = mockWebServer.takeRequest().getPath();
        assertEquals("/test", path);
    }
}
