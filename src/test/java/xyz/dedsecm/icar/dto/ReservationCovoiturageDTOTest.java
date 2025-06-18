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
        assertEquals("En attente", new ReservationCovoiturageDTO(0, 1).getStatutLibelle());
        assertEquals("Confirmée", new ReservationCovoiturageDTO(1, 1).getStatutLibelle());
        assertEquals("Annulée", new ReservationCovoiturageDTO(2, 1).getStatutLibelle());
        assertEquals("Terminée", new ReservationCovoiturageDTO(3, 1).getStatutLibelle());
        assertEquals("Statut inconnu", new ReservationCovoiturageDTO(99, 1).getStatutLibelle());
        ReservationCovoiturageDTO inconnu = new ReservationCovoiturageDTO(null, 1);
        assertEquals("Statut inconnu", inconnu.getStatutLibelle());
    }

    /**
     * Teste la méthode isRecente().
     */
    @Test
    void testIsRecente() {
        LocalDate now = LocalDate.now();
        ReservationCovoiturageDTO recente = new ReservationCovoiturageDTO(1, now, 1);
        ReservationCovoiturageDTO ancienne = new ReservationCovoiturageDTO(1, now.minusDays(10), 1);
        assertTrue(recente.isRecente());
        assertFalse(ancienne.isRecente());
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
}

