package xyz.dedsecm.icar.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test unitaire pour ReservationCovoiturageDTO.
 * <p>
 * Vérifie la création, l'accès aux attributs et les méthodes utilitaires de la classe ReservationCovoiturageDTO.
 * </p>
 */
class ReservationCovoiturageDTOTest {

    /**
     * Teste la création d'un DTO et l'accès à ses attributs.
     */
    @Test
    void testCreationEtAccesseurs() {
        LocalDate date = LocalDate.of(2025, 6, 18);
        ReservationCovoiturageDTO dto = new ReservationCovoiturageDTO(1, date, 42);
        dto.setId(10);
        assertEquals(10, dto.getId());
        assertEquals(1, dto.getStatut());
        assertEquals(date, dto.getDateReservation());
        assertEquals(42, dto.getUtilisateurId());
    }

    /**
     * Teste les méthodes de statut (isConfirmee, isEnAttente, isAnnulee).
     */
    @Test
    void testStatuts() {
        ReservationCovoiturageDTO conf = new ReservationCovoiturageDTO(1, 1);
        ReservationCovoiturageDTO attente = new ReservationCovoiturageDTO(0, 2);
        ReservationCovoiturageDTO annulee = new ReservationCovoiturageDTO(2, 3);
        assertTrue(conf.isConfirmee());
        assertFalse(conf.isEnAttente());
        assertFalse(conf.isAnnulee());
        assertFalse(attente.isConfirmee());
        assertTrue(attente.isEnAttente());
        assertFalse(attente.isAnnulee());
        assertFalse(annulee.isConfirmee());
        assertFalse(annulee.isEnAttente());
        assertTrue(annulee.isAnnulee());
    }

    /**
     * Teste la méthode getStatutLibelle().
     */
    @Test
    void testGetStatutLibelle() {
        ReservationCovoiturageDTO attente = new ReservationCovoiturageDTO(0, 1);
        ReservationCovoiturageDTO conf = new ReservationCovoiturageDTO(1, 2);
        ReservationCovoiturageDTO annulee = new ReservationCovoiturageDTO(2, 3);
        ReservationCovoiturageDTO inconnu = new ReservationCovoiturageDTO(99, 4);
        assertEquals("En attente", attente.getStatutLibelle());
        assertEquals("Confirmée", conf.getStatutLibelle());
        assertEquals("Annulée", annulee.getStatutLibelle());
        assertEquals("Inconnu", inconnu.getStatutLibelle());
    }

    /**
     * Teste la méthode isRecente().
     */
    @Test
    void testIsRecente() {
        ReservationCovoiturageDTO recente = new ReservationCovoiturageDTO(1, LocalDate.now(), 1);
        ReservationCovoiturageDTO pasRecente = new ReservationCovoiturageDTO(1, LocalDate.now().minusDays(10), 1);
        assertTrue(recente.isRecente());
        assertFalse(pasRecente.isRecente());
        ReservationCovoiturageDTO nullDate = new ReservationCovoiturageDTO(1, null, 1);
        assertFalse(nullDate.isRecente());
    }

    /**
     * Teste la méthode toString() et l'égalité.
     */
    @Test
    void testToStringEtEquals() {
        ReservationCovoiturageDTO dto1 = new ReservationCovoiturageDTO(1, LocalDate.now(), 1);
        dto1.setId(100);
        ReservationCovoiturageDTO dto2 = new ReservationCovoiturageDTO(1, LocalDate.now(), 1);
        dto2.setId(100);
        assertEquals(dto1, dto2);
        assertNotNull(dto1.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        ReservationCovoiturageDTO dto1 = new ReservationCovoiturageDTO(1, LocalDate.of(2025,6,18), 42);
        dto1.setId(10);
        ReservationCovoiturageDTO dto2 = new ReservationCovoiturageDTO(1, LocalDate.of(2025,6,18), 42);
        dto2.setId(10);
        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertEquals(dto1, dto1);
        assertNotEquals(dto1, null);
        assertNotEquals(dto1, "string");
        // Champs différents
        ReservationCovoiturageDTO diff = new ReservationCovoiturageDTO(2, LocalDate.of(2025,6,18), 42);
        diff.setId(10);
        assertNotEquals(dto1, diff);
        diff = new ReservationCovoiturageDTO(1, LocalDate.of(2025,6,19), 42);
        diff.setId(10);
        assertNotEquals(dto1, diff);
        diff = new ReservationCovoiturageDTO(1, LocalDate.of(2025,6,18), 43);
        diff.setId(10);
        assertNotEquals(dto1, diff);
        diff = new ReservationCovoiturageDTO(1, LocalDate.of(2025,6,18), 42);
        diff.setId(11);
        assertNotEquals(dto1, diff);
    }

    @Test
    void testToString() {
        ReservationCovoiturageDTO dto = new ReservationCovoiturageDTO(1, LocalDate.of(2025,6,18), 42);
        dto.setId(10);
        String str = dto.toString();
        assertTrue(str.contains("id=10"));
        assertTrue(str.contains("statut=1"));
        assertTrue(str.contains("dateReservation=2025-06-18"));
        assertTrue(str.contains("utilisateurId=42"));
    }
}
