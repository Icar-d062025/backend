package xyz.dedsecm.icar.model;

import org.junit.jupiter.api.Test;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testAllArgsConstructorAndGettersSetters() {
        User user = new User(1L, "test@mail.com", "Doe", "John", "johndoe", "password123", "1 rue de Paris", Role.ADMIN, true, "Spam", LocalTime.of(1, 30), true, 42L);
        assertEquals(1L, user.getId());
        assertEquals("test@mail.com", user.getEmail());
        assertEquals("Doe", user.getNom());
        assertEquals("John", user.getPrenom());
        assertEquals("johndoe", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals("1 rue de Paris", user.getAdresse());
        assertEquals(Role.ADMIN, user.getRole());
        assertTrue(user.getBanni());
        assertEquals("Spam", user.getRaisonBanni());
        assertEquals(LocalTime.of(1, 30), user.getDureeBanni());
        assertTrue(user.getVehiculePerso());
        assertEquals(42L, user.getVehiculeId());
    }

    @Test
    void testNoArgsConstructor() {
        User user = new User();
        assertNull(user.getId());
        assertNull(user.getEmail());
        assertNull(user.getNom());
        assertNull(user.getPrenom());
        assertNull(user.getUsername());
        assertNull(user.getPassword());
        assertNull(user.getAdresse());
        assertNull(user.getRole());
        assertNull(user.getBanni());
        assertNull(user.getRaisonBanni());
        assertNull(user.getDureeBanni());
        assertNull(user.getVehiculePerso());
        assertNull(user.getVehiculeId());
    }

    @Test
    void testCustomConstructor() {
        User user = new User("mail@mail.com", "Nom", "Prenom", "pass", "userName");
        assertEquals("mail@mail.com", user.getEmail());
        assertEquals("Nom", user.getNom());
        assertEquals("Prenom", user.getPrenom());
        assertEquals("pass", user.getPassword());
        assertEquals("userName", user.getUsername());
        assertEquals(Role.USER, user.getRole());
        assertFalse(user.getBanni());
        assertFalse(user.getVehiculePerso());
    }

    @Test
    void testSetters() {
        User user = new User();
        user.setId(2L);
        user.setEmail("a@b.com");
        user.setNom("N");
        user.setPrenom("P");
        user.setUsername("u");
        user.setPassword("pw");
        user.setAdresse("adr");
        user.setRole(Role.USER);
        user.setBanni(false);
        user.setRaisonBanni("none");
        user.setDureeBanni(LocalTime.of(0, 0));
        user.setVehiculePerso(false);
        user.setVehiculeId(99L);
        assertEquals(2L, user.getId());
        assertEquals("a@b.com", user.getEmail());
        assertEquals("N", user.getNom());
        assertEquals("P", user.getPrenom());
        assertEquals("u", user.getUsername());
        assertEquals("pw", user.getPassword());
        assertEquals("adr", user.getAdresse());
        assertEquals(Role.USER, user.getRole());
        assertFalse(user.getBanni());
        assertEquals("none", user.getRaisonBanni());
        assertEquals(LocalTime.of(0, 0), user.getDureeBanni());
        assertFalse(user.getVehiculePerso());
        assertEquals(99L, user.getVehiculeId());
    }
}

