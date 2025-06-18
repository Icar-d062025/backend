package xyz.dedsecm.icar.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ReservationCovoiturageTest {

    @Test
    void testConstructeursEtGettersSetters() {
        // Constructeur par défaut
        ReservationCovoiturage res1 = new ReservationCovoiturage();
        res1.setId(1);
        res1.setStatut(2);
        res1.setDateReservation(LocalDate.of(2024, 6, 18));
        res1.setUtilisateurId(10);
        assertEquals(1, res1.getId());
        assertEquals(2, res1.getStatut());
        assertEquals(LocalDate.of(2024, 6, 18), res1.getDateReservation());
        assertEquals(10, res1.getUtilisateurId());

        // Constructeur avec statut et utilisateurId
        ReservationCovoiturage res2 = new ReservationCovoiturage(3, 20);
        assertEquals(3, res2.getStatut());
        assertEquals(20, res2.getUtilisateurId());
        assertNotNull(res2.getDateReservation());

        // Constructeur avec statut, date, utilisateurId
        LocalDate date = LocalDate.of(2025, 1, 1);
        ReservationCovoiturage res3 = new ReservationCovoiturage(4, date, 30);
        assertEquals(4, res3.getStatut());
        assertEquals(date, res3.getDateReservation());
        assertEquals(30, res3.getUtilisateurId());
    }

    @Test
    void testToString() {
        ReservationCovoiturage res = new ReservationCovoiturage(1, 2);
        res.setId(99);
        String str = res.toString();
        assertTrue(str.contains("ReservationCovoiturage"));
        assertTrue(str.contains("id=99"));
    }

    @Test
    void testEqualsAndHashCode() {
        ReservationCovoiturage res1 = new ReservationCovoiturage();
        res1.setId(1);
        ReservationCovoiturage res2 = new ReservationCovoiturage();
        res2.setId(1);
        ReservationCovoiturage res3 = new ReservationCovoiturage();
        res3.setId(2);
        ReservationCovoiturage resNullId = new ReservationCovoiturage();

        // Même id
        assertEquals(res1, res2);
        assertEquals(res1.hashCode(), res2.hashCode());
        // Id différents
        assertNotEquals(res1, res3);
        // Comparaison avec null
        assertNotEquals(res1, null);
        // Comparaison avec autre classe
        assertNotEquals(res1, "string");
        // Comparaison avec id null
        assertNotEquals(res1, resNullId);
        // Réflexivité
        assertEquals(res1, res1);
    }
}

