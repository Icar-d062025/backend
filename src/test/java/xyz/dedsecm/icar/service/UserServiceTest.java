package xyz.dedsecm.icar.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;
import xyz.dedsecm.icar.dto.UserDTO;
import xyz.dedsecm.icar.mapper.UserMapper;
import xyz.dedsecm.icar.model.Role;
import xyz.dedsecm.icar.model.User;
import xyz.dedsecm.icar.repository.UserRepository;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Classe de test unitaire pour UserService.
 * <p>
 * Vérifie la création d'utilisateur, la récupération de tous les utilisateurs et l'encodage du mot de passe.
 * </p>
 */
class UserServiceTest {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        userService = new UserService();
        // Injection manuelle des mocks
        userService.userRepository = userRepository;
        userService.passwordEncoder = passwordEncoder;
    }

    /**
     * Teste la récupération de tous les utilisateurs (getAllUsers).
     */
    @Test
    void testGetAllUsers() {
        User user = new User(1L, "mail@test.com", "Nom", "Prenom", "user", "pass", "adr", Role.USER, false, null, null, false, null);
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));
        List<UserDTO> dtos = userService.getAllUsers();
        assertEquals(1, dtos.size());
        assertEquals(user.getEmail(), dtos.get(0).getEmail());
    }

    /**
     * Teste la création d'un utilisateur (createUser) et l'encodage du mot de passe.
     */
    @Test
    void testCreateUser() {
        UserDTO dto = new UserDTO(2L, "mail2@test.com", "Nom2", "Prenom2", "user2", "motdepasse", "adr2", Role.ADMIN, false, null, null, false, null);
        User user = UserMapper.toEntity(dto);
        when(passwordEncoder.encode(dto.getPassword())).thenReturn("encoded");
        when(userRepository.save(any(User.class))).thenReturn(user);
        UserDTO result = userService.createUser(dto);
        assertEquals(dto.getEmail(), result.getEmail());
        verify(passwordEncoder).encode(dto.getPassword());
        verify(userRepository).save(any(User.class));
    }
}

