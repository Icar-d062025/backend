package xyz.dedsecm.icar.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import xyz.dedsecm.icar.dto.UserDTO;
import xyz.dedsecm.icar.model.Role;
import xyz.dedsecm.icar.model.User;
import xyz.dedsecm.icar.repository.UserRepository;
import xyz.dedsecm.icar.service.TokenService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests unitaires pour le contrôleur AuthController.
 * Vérifie l'authentification et la génération de token JWT.
 */
class AuthControllerTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private TokenService tokenService;

    @InjectMocks
    private AuthController authController;

    public AuthControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Vérifie que la méthode login retourne un token JWT si les identifiants sont valides.
     */
    @Test
    void login_shouldReturnToken_whenCredentialsAreValid() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("encodedPassword");
        user.setRole(Role.USER);
        UserDTO loginDto = new UserDTO();
        loginDto.setUsername("testuser");
        loginDto.setPassword("password");

        when(userRepository.findAll()).thenReturn(List.of(user));
        when(passwordEncoder.matches("password", "encodedPassword")).thenReturn(true);
        when(tokenService.generateToken("testuser", "USER", null)).thenReturn("jwt-token");

        String result = authController.login(loginDto);
        assertEquals("jwt-token", result);
    }

    /**
     * Vérifie que la méthode login retourne "Invalid credentials" si l'utilisateur n'existe pas ou si le mot de passe est incorrect.
     */
    @Test
    void login_shouldReturnInvalidCredentials_whenUserNotFoundOrPasswordIncorrect() {
        UserDTO loginDto = new UserDTO();
        loginDto.setUsername("unknown");
        loginDto.setPassword("wrong");

        when(userRepository.findAll()).thenReturn(List.of());

        String result = authController.login(loginDto);
        assertEquals("Invalid credentials", result);
    }

    /**
     * Vérifie que la méthode register retourne un message de succès si l'inscription est correcte.
     */
    @Test
    void register_shouldReturnSuccess_whenRegistrationIsValid() {
        UserDTO userDto = new UserDTO();
        userDto.setEmail("test@email.com");
        userDto.setUsername("testuser");
        userDto.setPassword("password");
        userDto.setNom("Test");
        userDto.setPrenom("User");

        when(userRepository.existsByEmail("test@email.com")).thenReturn(false);
        when(userRepository.existsByUsername("testuser")).thenReturn(false);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        String result = authController.register(userDto);
        assertEquals("Registration successful", result);
        verify(userRepository).save(any(User.class));
    }

    /**
     * Vérifie que la méthode register retourne une erreur si l'email existe déjà.
     */
    @Test
    void register_shouldReturnError_whenEmailExists() {
        UserDTO userDto = new UserDTO();
        userDto.setEmail("test@email.com");
        userDto.setUsername("testuser");
        userDto.setPassword("password");

        when(userRepository.existsByEmail("test@email.com")).thenReturn(true);

        String result = authController.register(userDto);
        assertEquals("Email already in use", result);
        verify(userRepository, never()).save(any(User.class));
    }

    /**
     * Vérifie que la méthode register retourne une erreur si le username existe déjà.
     */
    @Test
    void register_shouldReturnError_whenUsernameExists() {
        UserDTO userDto = new UserDTO();
        userDto.setEmail("test@email.com");
        userDto.setUsername("testuser");
        userDto.setPassword("password");

        when(userRepository.existsByEmail("test@email.com")).thenReturn(false);
        when(userRepository.existsByUsername("testuser")).thenReturn(true);

        String result = authController.register(userDto);
        assertEquals("Username already in use", result);
        verify(userRepository, never()).save(any(User.class));
    }
}
