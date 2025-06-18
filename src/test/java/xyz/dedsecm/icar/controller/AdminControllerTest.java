package xyz.dedsecm.icar.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.security.test.context.support.WithMockUser;

/**
 * Tests unitaires pour le contrôleur AdminController.
 * Vérifie que les endpoints d'administration répondent correctement.
 */
@WebMvcTest(AdminController.class)
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Vérifie que l'endpoint /api/admin/hello retourne bien le message attendu.
     */
    @Test
    @WithMockUser(roles = "ADMIN")
    void helloAdmin_shouldReturnHelloAdminMessage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/hello"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Hello Admin!"));
    }
}
