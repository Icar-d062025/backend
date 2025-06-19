package xyz.dedsecm.icar.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import xyz.dedsecm.icar.dto.RouteInfo;
import xyz.dedsecm.icar.model.Coordinate;
import xyz.dedsecm.icar.service.RoutingService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@WebMvcTest(RoutingController.class)
class RoutingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoutingService routingService;

    @Test
    @WithMockUser
    @DisplayName("GET /api/routes returns RouteInfo as JSON")
    void testGetRoute() throws Exception {
        // précondition: le service renvoie un RouteInfo donné
        given(routingService.getRoute(any(Coordinate.class), any(Coordinate.class)))
                .willReturn(new RouteInfo(1500.0, 120.0, 0.18));

        mockMvc.perform(get("/api/routes")
                .param("startLat", "1.0")
                .param("startLng", "2.0")
                .param("endLat", "3.0")
                .param("endLng", "4.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.distance", is(1500.0)))
                .andExpect(jsonPath("$.duration", is(120.0)))
                .andExpect(jsonPath("$.co2", is(0.18)));
    }
}
