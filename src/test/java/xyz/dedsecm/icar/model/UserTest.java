package xyz.dedsecm.icar.model;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test unitaire pour l'entité User.
 * <p>
 * Vérifie la création, l'accès aux attributs et les méthodes utilitaires de la classe User.
 * </p>
 */
class UserTest {

    /**
     * Teste la création d'un utilisateur et l'accès à ses attributs.
     */
    @Test
    void testCreationEtAccesseurs() {
        User user = new User(1L, "test@mail.com", "Dupont", "Jean", "jdupont", "secret", "1 rue de Paris", Role.USER, false, null, LocalTime.of(0,0), true, 10L);
        assertEquals(1L, user.getId());
        assertEquals("test@mail.com", user.getEmail());
        assertEquals("Dupont", user.getNom());
        assertEquals("Jean", user.getPrenom());
        assertEquals("jdupont", user.getUsername());
        assertEquals("secret", user.getPassword());
        assertEquals("1 rue de Paris", user.getAdresse());
        assertEquals(Role.USER, user.getRole());
        assertFalse(user.getBanni());
        assertNull(user.getRaisonBanni());
        assertEquals(LocalTime.of(0,0), user.getDureeBanni());
        assertTrue(user.getVehiculePerso());
        assertEquals(10L, user.getVehiculeId());
    }

    /**
     * Teste la modification des attributs après création.
     */
    @Test
    void testSetters() {
        User user = new User();
        user.setId(2L);
        user.setEmail("user2@mail.com");
        user.setNom("Martin");
        user.setPrenom("Paul");
        user.setUsername("pmartin");
        user.setPassword("pass2");
        user.setAdresse("2 rue de Lyon");
        user.setRole(Role.ADMIN);
        user.setBanni(true);
        user.setRaisonBanni("fraude");
        user.setDureeBanni(LocalTime.of(1,30));
        user.setVehiculePerso(false);
        user.setVehiculeId(null);
        assertEquals(2L, user.getId());
        assertEquals("user2@mail.com", user.getEmail());
        assertEquals("Martin", user.getNom());
        assertEquals("Paul", user.getPrenom());
        assertEquals("pmartin", user.getUsername());
        assertEquals("pass2", user.getPassword());
        assertEquals("2 rue de Lyon", user.getAdresse());
        assertEquals(Role.ADMIN, user.getRole());
        assertTrue(user.getBanni());
        assertEquals("fraude", user.getRaisonBanni());
        assertEquals(LocalTime.of(1,30), user.getDureeBanni());
        assertFalse(user.getVehiculePerso());
        assertNull(user.getVehiculeId());
    }
}

