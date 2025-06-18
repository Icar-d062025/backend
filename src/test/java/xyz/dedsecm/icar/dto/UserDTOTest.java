package xyz.dedsecm.icar.dto;

import org.junit.jupiter.api.Test;
import xyz.dedsecm.icar.model.Role;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test unitaire pour UserDTO.
 * <p>
 * Vérifie la création, l'accès aux attributs et l'égalité de la classe UserDTO.
 * </p>
 */
class UserDTOTest {

    /**
     * Teste la création d'un UserDTO et l'accès à ses attributs.
     */
    @Test
    void testCreationEtAccesseurs() {
        UserDTO dto = new UserDTO(1L, "test@mail.com", "Dupont", "Jean", "jdupont", "secret", "1 rue de Paris", Role.USER, false, null, LocalTime.of(0,0), true, 10L);
        assertEquals(1L, dto.getId());
        assertEquals("test@mail.com", dto.getEmail());
        assertEquals("Dupont", dto.getNom());
        assertEquals("Jean", dto.getPrenom());
        assertEquals("jdupont", dto.getUsername());
        assertEquals("secret", dto.getPassword());
        assertEquals("1 rue de Paris", dto.getAdresse());
        assertEquals(Role.USER, dto.getRole());
        assertFalse(dto.getBanni());
        assertNull(dto.getRaisonBanni());
        assertEquals(LocalTime.of(0,0), dto.getDureeBanni());
        assertTrue(dto.getVehiculePerso());
        assertEquals(10L, dto.getVehiculeId());
    }

    /**
     * Teste la modification des attributs après création.
     */
    @Test
    void testSetters() {
        UserDTO dto = new UserDTO();
        dto.setId(2L);
        dto.setEmail("user2@mail.com");
        dto.setNom("Martin");
        dto.setPrenom("Paul");
        dto.setUsername("pmartin");
        dto.setPassword("pass2");
        dto.setAdresse("2 rue de Lyon");
        dto.setRole(Role.ADMIN);
        dto.setBanni(true);
        dto.setRaisonBanni("fraude");
        dto.setDureeBanni(LocalTime.of(1,30));
        dto.setVehiculePerso(false);
        dto.setVehiculeId(null);
        assertEquals(2L, dto.getId());
        assertEquals("user2@mail.com", dto.getEmail());
        assertEquals("Martin", dto.getNom());
        assertEquals("Paul", dto.getPrenom());
        assertEquals("pmartin", dto.getUsername());
        assertEquals("pass2", dto.getPassword());
        assertEquals("2 rue de Lyon", dto.getAdresse());
        assertEquals(Role.ADMIN, dto.getRole());
        assertTrue(dto.getBanni());
        assertEquals("fraude", dto.getRaisonBanni());
        assertEquals(LocalTime.of(1,30), dto.getDureeBanni());
        assertFalse(dto.getVehiculePerso());
        assertNull(dto.getVehiculeId());
    }

    /**
     * Teste l'égalité entre deux UserDTO ayant le même id.
     */
    @Test
    void testEqualsAndHashCode() {
        UserDTO dto1 = new UserDTO(3L, "a@mail.com", "A", "B", "ab", "pw", "adr", Role.USER, false, null, LocalTime.of(0,0), false, null);
        UserDTO dto2 = new UserDTO(3L, "a@mail.com", "A", "B", "ab", "pw", "adr", Role.USER, false, null, LocalTime.of(0,0), false, null);
        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    /**
     * Teste les branches non couvertes de equals (null, autre classe, self).
     */
    @Test
    void testEqualsBranches() {
        UserDTO dto = new UserDTO(5L, "b@mail.com", "B", "C", "bc", "pw2", "adr2", Role.USER, false, null, LocalTime.of(0,0), false, null);
        // Comparaison avec null
        assertNotEquals(null, dto);
        // Comparaison avec une autre classe
        assertNotEquals("une string", dto);
        // Comparaison avec lui-même
        assertEquals(dto, dto);
    }
}
