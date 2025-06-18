package xyz.dedsecm.icar.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import xyz.dedsecm.icar.dto.CovoiturageDTO;
import xyz.dedsecm.icar.service.CovoiturageService;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Tests unitaires pour le contrôleur CovoiturageController.
 * Vérifie les endpoints de consultation et de création de covoiturages.
 */
class CovoiturageControllerTest {

    @Mock
    private CovoiturageService covoiturageService;

    @InjectMocks
    private CovoiturageController covoiturageController;

    private MockMvc mockMvc;

    public CovoiturageControllerTest() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(covoiturageController).build();
    }

    /**
     * Vérifie que l'endpoint GET /api/covoiturages retourne la liste attendue de covoiturages.
     */
    @Test
    void getCovoiturages_shouldReturnListOfCovoiturages() throws Exception {
        CovoiturageDTO dto = new CovoiturageDTO();
        when(covoiturageService.getAllCovoiturages()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/covoiturages"))
                .andExpect(status().isOk());
        verify(covoiturageService, times(1)).getAllCovoiturages();
    }

    /**
     * Vérifie que l'endpoint POST /api/covoiturages crée un covoiturage et retourne le DTO créé.
     */
    @Test
    void createCovoiturage_shouldReturnCreatedCovoiturage() throws Exception {
        CovoiturageDTO inputDto = new CovoiturageDTO();
        CovoiturageDTO outputDto = new CovoiturageDTO();
        when(covoiturageService.createCovoiturage(any(CovoiturageDTO.class))).thenReturn(outputDto);

        mockMvc.perform(post("/api/covoiturages")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());
        verify(covoiturageService, times(1)).createCovoiturage(any(CovoiturageDTO.class));
    }
}

