package xyz.dedsecm.icar.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test unitaire pour l'entité ReservationVehicule.
 * <p>
 * Vérifie la création, l'accès aux attributs et les méthodes utilitaires de la classe ReservationVehicule.
 * </p>
 */
class ReservationVehiculeTest {

    /**
     * Teste la création d'une réservation de véhicule et l'accès à ses attributs.
     */
    @Test
    void testCreationEtAccesseurs() {
        LocalDate debut = LocalDate.of(2025, 6, 20);
        LocalDate fin = LocalDate.of(2025, 6, 25);
        Integer utilisateurId = 5;
        Integer vehiculeId = 10;
        ReservationVehicule reservation = new ReservationVehicule(debut, fin, utilisateurId, vehiculeId);
        assertEquals(debut, reservation.getDateDebut());
        assertEquals(fin, reservation.getDateFin());
        assertEquals(utilisateurId, reservation.getUtilisateurId());
        assertEquals(vehiculeId, reservation.getVehiculeId());
        assertNotNull(reservation.getDateCreation());
    }

    /**
     * Teste la modification des attributs après création.
     */
    @Test
    void testSetters() {
        ReservationVehicule reservation = new ReservationVehicule();
        reservation.setId(1);
        reservation.setDateDebut(LocalDate.of(2025, 7, 1));
        reservation.setDateFin(LocalDate.of(2025, 7, 5));
        reservation.setDateCreation(LocalDate.of(2025, 6, 18));
        reservation.setUtilisateurId(7);
        reservation.setVehiculeId(3);
        assertEquals(1, reservation.getId());
        assertEquals(LocalDate.of(2025, 7, 1), reservation.getDateDebut());
        assertEquals(LocalDate.of(2025, 7, 5), reservation.getDateFin());
        assertEquals(LocalDate.of(2025, 6, 18), reservation.getDateCreation());
        assertEquals(7, reservation.getUtilisateurId());
        assertEquals(3, reservation.getVehiculeId());
    }

    /**
     * Teste la méthode toString pour s'assurer qu'elle ne retourne pas null.
     */
    @Test
    void testToString() {
        ReservationVehicule reservation = new ReservationVehicule(LocalDate.now(), LocalDate.now().plusDays(2), 1, 2);
        assertNotNull(reservation.toString());
    }
}

