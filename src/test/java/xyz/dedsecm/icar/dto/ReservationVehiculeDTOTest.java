package xyz.dedsecm.icar.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour la classe ReservationVehiculeDTO.
 * Vérifie la création, l'intégrité et les méthodes utilitaires du DTO de réservation de véhicule.
 */
class ReservationVehiculeDTOTest {

    /**
     * Vérifie que le constructeur et les accesseurs fonctionnent correctement.
     */
    @Test
    void constructorAndGetters_shouldSetAndReturnFields() {
        Integer id = 1;
        LocalDate dateDebut = LocalDate.of(2025, 6, 18);
        LocalDate dateFin = LocalDate.of(2025, 6, 20);
        LocalDate dateCreation = LocalDate.of(2025, 6, 10);
        Integer utilisateurId = 42;
        Integer vehiculeId = 99;

        ReservationVehiculeDTO dto = new ReservationVehiculeDTO();
        dto.setId(id);
        dto.setDateDebut(dateDebut);
        dto.setDateFin(dateFin);
        dto.setDateCreation(dateCreation);
        dto.setUtilisateurId(utilisateurId);
        dto.setVehiculeId(vehiculeId);

        assertEquals(id, dto.getId());
        assertEquals(dateDebut, dto.getDateDebut());
        assertEquals(dateFin, dto.getDateFin());
        assertEquals(dateCreation, dto.getDateCreation());
        assertEquals(utilisateurId, dto.getUtilisateurId());
        assertEquals(vehiculeId, dto.getVehiculeId());
    }

    /**
     * Vérifie que le constructeur partiel initialise correctement les champs.
     */
    @Test
    void constructorPartial_shouldSetFields() {
        LocalDate debut = LocalDate.of(2025, 6, 18);
        LocalDate fin = LocalDate.of(2025, 6, 20);
        ReservationVehiculeDTO dto = new ReservationVehiculeDTO(debut, fin, 42, 99);
        assertEquals(debut, dto.getDateDebut());
        assertEquals(fin, dto.getDateFin());
        assertEquals(42, dto.getUtilisateurId());
        assertEquals(99, dto.getVehiculeId());
    }

    /**
     * Vérifie que la méthode isDateRangeValid retourne true si la date de fin est après la date de début.
     */
    @Test
    void isDateRangeValid_shouldReturnTrueIfEndAfterStart() {
        ReservationVehiculeDTO dto = new ReservationVehiculeDTO();
        dto.setDateDebut(LocalDate.of(2025, 6, 18));
        dto.setDateFin(LocalDate.of(2025, 6, 20));
        assertTrue(dto.isDateRangeValid());
    }

    /**
     * Vérifie que la méthode isDateRangeValid retourne false si la date de fin est avant la date de début.
     */
    @Test
    void isDateRangeValid_shouldReturnFalseIfEndBeforeStart() {
        ReservationVehiculeDTO dto = new ReservationVehiculeDTO();
        dto.setDateDebut(LocalDate.of(2025, 6, 20));
        dto.setDateFin(LocalDate.of(2025, 6, 18));
        assertFalse(dto.isDateRangeValid());
    }

    /**
     * Vérifie que la méthode isDateRangeValid retourne false si les dates sont nulles ou incohérentes.
     */
    @Test
    void isDateRangeValid_shouldReturnFalseIfNullsOrEndBeforeStart() {
        ReservationVehiculeDTO dto = new ReservationVehiculeDTO();
        assertFalse(dto.isDateRangeValid());
        dto.setDateDebut(LocalDate.of(2025, 6, 20));
        dto.setDateFin(null);
        assertFalse(dto.isDateRangeValid());
        dto.setDateDebut(null);
        dto.setDateFin(LocalDate.of(2025, 6, 21));
        assertFalse(dto.isDateRangeValid());
        dto.setDateDebut(LocalDate.of(2025, 6, 22));
        dto.setDateFin(LocalDate.of(2025, 6, 21));
        assertFalse(dto.isDateRangeValid());
    }

    /**
     * Vérifie que la méthode getDureeEnJours retourne le nombre de jours correct.
     */
    @Test
    void getDureeEnJours_shouldReturnCorrectNumberOfDays() {
        ReservationVehiculeDTO dto = new ReservationVehiculeDTO();
        dto.setDateDebut(LocalDate.of(2025, 6, 18));
        dto.setDateFin(LocalDate.of(2025, 6, 20));
        assertEquals(2, dto.getDureeEnJours());
    }

    /**
     * Vérifie que la méthode getDureeEnJours retourne 0 ou une valeur correcte selon les dates.
     */
    @Test
    void getDureeEnJours_shouldReturnCorrectValueOrZero() {
        ReservationVehiculeDTO dto = new ReservationVehiculeDTO();
        assertEquals(0, dto.getDureeEnJours());
        dto.setDateDebut(LocalDate.of(2025, 6, 18));
        dto.setDateFin(null);
        assertEquals(0, dto.getDureeEnJours());
        dto.setDateDebut(null);
        dto.setDateFin(LocalDate.of(2025, 6, 20));
        assertEquals(0, dto.getDureeEnJours());
        dto.setDateDebut(LocalDate.of(2025, 6, 18));
        dto.setDateFin(LocalDate.of(2025, 6, 20));
        assertEquals(2, dto.getDureeEnJours());
        dto.setDateFin(LocalDate.of(2025, 6, 17));
        assertEquals(-1, dto.getDureeEnJours());
    }

    /**
     * Vérifie que les méthodes equals et hashCode fonctionnent correctement.
     */
    @Test
    void testEqualsAndHashCode() {
        ReservationVehiculeDTO dto1 = new ReservationVehiculeDTO(1, LocalDate.of(2025,6,18), LocalDate.of(2025,6,20), LocalDate.of(2025,6,10), 42, 99);
        ReservationVehiculeDTO dto2 = new ReservationVehiculeDTO(1, LocalDate.of(2025,6,18), LocalDate.of(2025,6,20), LocalDate.of(2025,6,10), 42, 99);
        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertEquals(dto1, dto1);
        assertNotEquals(dto1, null);
        assertNotEquals(dto1, "string");
        // Champs différents
        assertNotEquals(dto1, new ReservationVehiculeDTO(2, LocalDate.of(2025,6,18), LocalDate.of(2025,6,20), LocalDate.of(2025,6,10), 42, 99));
        assertNotEquals(dto1, new ReservationVehiculeDTO(1, LocalDate.of(2025,6,19), LocalDate.of(2025,6,20), LocalDate.of(2025,6,10), 42, 99));
        assertNotEquals(dto1, new ReservationVehiculeDTO(1, LocalDate.of(2025,6,18), LocalDate.of(2025,6,21), LocalDate.of(2025,6,10), 42, 99));
        assertNotEquals(dto1, new ReservationVehiculeDTO(1, LocalDate.of(2025,6,18), LocalDate.of(2025,6,20), LocalDate.of(2025,6,11), 42, 99));
        assertNotEquals(dto1, new ReservationVehiculeDTO(1, LocalDate.of(2025,6,18), LocalDate.of(2025,6,20), LocalDate.of(2025,6,10), 43, 99));
        assertNotEquals(dto1, new ReservationVehiculeDTO(1, LocalDate.of(2025,6,18), LocalDate.of(2025,6,20), LocalDate.of(2025,6,10), 42, 100));
    }

    /**
     * Vérifie que la méthode toString retourne une chaîne contenant les informations du DTO.
     */
    @Test
    void testToString() {
        ReservationVehiculeDTO dto = new ReservationVehiculeDTO(1, LocalDate.of(2025,6,18), LocalDate.of(2025,6,20), LocalDate.of(2025,6,10), 42, 99);
        String str = dto.toString();
        assertTrue(str.contains("id=1"));
        assertTrue(str.contains("dateDebut=2025-06-18"));
        assertTrue(str.contains("dateFin=2025-06-20"));
        assertTrue(str.contains("dateCreation=2025-06-10"));
        assertTrue(str.contains("utilisateurId=42"));
        assertTrue(str.contains("vehiculeId=99"));
    }
}
