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

    @Test
    void testGetUserByIdFound() {
        User user = new User(1L, "mail@test.com", "Nom", "Prenom", "user", "pass", "adr", Role.USER, false, null, null, false, null);
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));
        UserDTO dto = userService.getUserById(1L);
        assertEquals(user.getEmail(), dto.getEmail());
    }

    @Test
    void testGetUserByIdNotFound() {
        when(userRepository.findById(2L)).thenReturn(java.util.Optional.empty());
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userService.getUserById(2L));
        assertTrue(ex.getMessage().contains("Utilisateur non trouvé"));
    }

    @Test
    void testGetUserByEmailFound() {
        User user = new User(1L, "mail@test.com", "Nom", "Prenom", "user", "pass", "adr", Role.USER, false, null, null, false, null);
        when(userRepository.findByEmail("mail@test.com")).thenReturn(java.util.Optional.of(user));
        UserDTO dto = userService.getUserByEmail("mail@test.com");
        assertEquals(user.getEmail(), dto.getEmail());
    }

    @Test
    void testGetUserByEmailNotFound() {
        when(userRepository.findByEmail("notfound@mail.com")).thenReturn(java.util.Optional.empty());
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userService.getUserByEmail("notfound@mail.com"));
        assertTrue(ex.getMessage().contains("Utilisateur non trouvé"));
    }

    @Test
    void testGetUserByUsernameFound() {
        User user = new User(1L, "mail@test.com", "Nom", "Prenom", "user", "pass", "adr", Role.USER, false, null, null, false, null);
        when(userRepository.findByUsername("user")).thenReturn(java.util.Optional.of(user));
        UserDTO dto = userService.getUserByUsername("user");
        assertEquals(user.getUsername(), dto.getUsername());
    }

    @Test
    void testGetUserByUsernameNotFound() {
        when(userRepository.findByUsername("notfound")).thenReturn(java.util.Optional.empty());
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userService.getUserByUsername("notfound"));
        assertTrue(ex.getMessage().contains("Utilisateur non trouvé"));
    }

    @Test
    void testUpdateUserFoundWithPassword() {
        User user = new User(1L, "mail@test.com", "Nom", "Prenom", "user", "pass", "adr", Role.USER, false, null, null, false, null);
        UserDTO dto = new UserDTO(1L, "new@mail.com", "Nom2", "Prenom2", "user2", "newpass", "adr2", Role.ADMIN, true, "raison", java.time.LocalTime.NOON, true, 2L);
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));
        when(passwordEncoder.encode("newpass")).thenReturn("encoded");
        when(userRepository.save(any())).thenReturn(user);
        UserDTO result = userService.updateUser(1L, dto);
        assertEquals("new@mail.com", result.getEmail());
    }

    @Test
    void testUpdateUserFoundWithoutPassword() {
        User user = new User(1L, "mail@test.com", "Nom", "Prenom", "user", "pass", "adr", Role.USER, false, null, null, false, null);
        UserDTO dto = new UserDTO(1L, "new@mail.com", "Nom2", "Prenom2", "user2", "", "adr2", Role.ADMIN, true, "raison", java.time.LocalTime.NOON, true, 2L);
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));
        when(userRepository.save(any())).thenReturn(user);
        UserDTO result = userService.updateUser(1L, dto);
        assertEquals("new@mail.com", result.getEmail());
    }

    @Test
    void testUpdateUserNotFound() {
        when(userRepository.findById(2L)).thenReturn(java.util.Optional.empty());
        UserDTO dto = new UserDTO();
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userService.updateUser(2L, dto));
        assertTrue(ex.getMessage().contains("Utilisateur non trouvé"));
    }

    @Test
    void testDeleteUser() {
        userService.deleteUser(1L);
        verify(userRepository).deleteById(1L);
    }

    @Test
    void testBanUserFound() {
        User user = new User(1L, "mail@test.com", "Nom", "Prenom", "user", "pass", "adr", Role.USER, false, null, null, false, null);
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));
        when(userRepository.save(any())).thenReturn(user);
        UserDTO result = userService.banUser(1L, "raison", java.time.LocalTime.NOON);
        assertTrue(result.getBanni());
    }

    @Test
    void testBanUserNotFound() {
        when(userRepository.findById(2L)).thenReturn(java.util.Optional.empty());
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userService.banUser(2L, "raison", java.time.LocalTime.NOON));
        assertTrue(ex.getMessage().contains("Utilisateur non trouvé"));
    }

    @Test
    void testUnbanUserFound() {
        User user = new User(1L, "mail@test.com", "Nom", "Prenom", "user", "pass", "adr", Role.USER, true, "raison", java.time.LocalTime.NOON, false, null);
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));
        when(userRepository.save(any())).thenReturn(user);
        UserDTO result = userService.unbanUser(1L);
        assertFalse(result.getBanni());
        assertNull(result.getRaisonBanni());
        assertNull(result.getDureeBanni());
    }

    @Test
    void testUnbanUserNotFound() {
        when(userRepository.findById(2L)).thenReturn(java.util.Optional.empty());
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userService.unbanUser(2L));
        assertTrue(ex.getMessage().contains("Utilisateur non trouvé"));
    }

    @Test
    void testAssignVehicleFound() {
        User user = new User(1L, "mail@test.com", "Nom", "Prenom", "user", "pass", "adr", Role.USER, false, null, null, false, null);
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));
        when(userRepository.save(any())).thenReturn(user);
        UserDTO result = userService.assignVehicle(1L, 99L);
        assertEquals(99L, result.getVehiculeId());
    }

    @Test
    void testAssignVehicleNotFound() {
        when(userRepository.findById(2L)).thenReturn(java.util.Optional.empty());
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userService.assignVehicle(2L, 99L));
        assertTrue(ex.getMessage().contains("Utilisateur non trouvé"));
    }

    @Test
    void testExistsByEmail() {
        when(userRepository.existsByEmail("mail@test.com")).thenReturn(true);
        assertTrue(userService.existsByEmail("mail@test.com"));
        when(userRepository.existsByEmail("notfound@mail.com")).thenReturn(false);
        assertFalse(userService.existsByEmail("notfound@mail.com"));
    }

    @Test
    void testExistsByUsername() {
        when(userRepository.existsByUsername("user")).thenReturn(true);
        assertTrue(userService.existsByUsername("user"));
        when(userRepository.existsByUsername("notfound")).thenReturn(false);
        assertFalse(userService.existsByUsername("notfound"));
    }
}
