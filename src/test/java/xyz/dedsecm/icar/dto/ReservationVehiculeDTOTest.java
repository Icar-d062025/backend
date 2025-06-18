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
     * Vérifie que la méthode getDureeEnJours retourne le nombre de jours correct.
     */
    @Test
    void getDureeEnJours_shouldReturnCorrectNumberOfDays() {
        ReservationVehiculeDTO dto = new ReservationVehiculeDTO();
        dto.setDateDebut(LocalDate.of(2025, 6, 18));
        dto.setDateFin(LocalDate.of(2025, 6, 20));
        assertEquals(2, dto.getDureeEnJours());
    }
}

