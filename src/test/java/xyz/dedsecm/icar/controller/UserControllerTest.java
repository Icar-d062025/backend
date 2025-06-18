package xyz.dedsecm.icar.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import xyz.dedsecm.icar.dto.UserDTO;
import xyz.dedsecm.icar.service.UserService;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Tests unitaires pour le contrôleur UserController.
 * Vérifie les endpoints de gestion des utilisateurs.
 */
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    public UserControllerTest() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    /**
     * Vérifie que l'endpoint POST /api/users/{userId}/assign-vehicle/{vehicleId} attribue un véhicule à un utilisateur.
     */
    @Test
    void assignVehicle_shouldReturnUserWithVehicle() throws Exception {
        UserDTO userDTO = new UserDTO();
        when(userService.assignVehicle(anyLong(), anyLong())).thenReturn(userDTO);

        mockMvc.perform(post("/api/users/1/assign-vehicle/2"))
                .andExpect(status().isOk());
        verify(userService, times(1)).assignVehicle(1L, 2L);
    }

    /**
     * Vérifie que l'endpoint GET /api/users/check/email retourne true si l'email existe.
     */
    @Test
    void checkEmailExists_shouldReturnTrueIfEmailExists() throws Exception {
        when(userService.existsByEmail(anyString())).thenReturn(true);

        mockMvc.perform(get("/api/users/check/email?email=test@example.com"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        verify(userService, times(1)).existsByEmail("test@example.com");
    }

    /**
     * Vérifie que l'endpoint GET /api/users/check/username retourne true si le nom d'utilisateur existe.
     */
    @Test
    void checkUsernameExists_shouldReturnTrueIfUsernameExists() throws Exception {
        when(userService.existsByUsername(anyString())).thenReturn(true);

        mockMvc.perform(get("/api/users/check/username?username=testuser"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        verify(userService, times(1)).existsByUsername("testuser");
    }
}

