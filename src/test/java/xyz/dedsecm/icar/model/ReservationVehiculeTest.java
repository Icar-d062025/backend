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
     * Teste la méthode equals et hashCode.
     */
    @Test
    void testEqualsAndHashCode() {
        ReservationVehicule r1 = new ReservationVehicule(LocalDate.of(2025, 6, 20), LocalDate.of(2025, 6, 25), 5, 10);
        r1.setId(1);
        ReservationVehicule r2 = new ReservationVehicule(LocalDate.of(2025, 6, 20), LocalDate.of(2025, 6, 25), 5, 10);
        r2.setId(1);
        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
        assertEquals(r1, r1);
        assertNotEquals(r1, null);
        assertNotEquals(r1, "string");
        // Champs différents
        ReservationVehicule diff = new ReservationVehicule(LocalDate.of(2025, 6, 21), LocalDate.of(2025, 6, 25), 5, 10);
        diff.setId(1);
        assertNotEquals(r1, diff);
        diff = new ReservationVehicule(LocalDate.of(2025, 6, 20), LocalDate.of(2025, 6, 26), 5, 10);
        diff.setId(1);
        assertNotEquals(r1, diff);
        diff = new ReservationVehicule(LocalDate.of(2025, 6, 20), LocalDate.of(2025, 6, 25), 6, 10);
        diff.setId(1);
        assertNotEquals(r1, diff);
        diff = new ReservationVehicule(LocalDate.of(2025, 6, 20), LocalDate.of(2025, 6, 25), 5, 11);
        diff.setId(1);
        assertNotEquals(r1, diff);
        diff = new ReservationVehicule(LocalDate.of(2025, 6, 20), LocalDate.of(2025, 6, 25), 5, 10);
        diff.setId(2);
        assertNotEquals(r1, diff);
    }

    /**
     * Teste la méthode toString pour s'assurer qu'elle ne retourne pas null et qu'elle contient les bonnes informations.
     */
    @Test
    void testToString() {
        ReservationVehicule r = new ReservationVehicule(LocalDate.of(2025, 6, 20), LocalDate.of(2025, 6, 25), 5, 10);
        r.setId(1);
        String str = r.toString();
        assertTrue(str.contains("id=1"));
        assertTrue(str.contains("dateDebut=2025-06-20"));
        assertTrue(str.contains("dateFin=2025-06-25"));
        assertTrue(str.contains("utilisateurId=5"));
        assertTrue(str.contains("vehiculeId=10"));
    }
}
