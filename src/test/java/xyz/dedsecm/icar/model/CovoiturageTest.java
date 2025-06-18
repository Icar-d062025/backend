package xyz.dedsecm.icar.model;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test unitaire pour l'entité Covoiturage.
 * <p>
 * Vérifie la création, l'accès aux attributs et les méthodes utilitaires de la classe Covoiturage.
 * </p>
 */
class CovoiturageTest {

    /**
     * Teste la création d'un covoiturage et l'accès à ses attributs.
     */
    @Test
    void testCreationEtAccesseurs() {
        LocalDateTime depart = LocalDateTime.of(2025, 6, 18, 8, 0);
        LocalDateTime arrivee = LocalDateTime.of(2025, 6, 18, 10, 30);
        Covoiturage covoiturage = new Covoiturage(depart, arrivee, "Paris", "Lyon", 4, 2, 450, StatutCovoiturage.EN_COURS);
        assertEquals(depart, covoiturage.getDateHeureDepart());
        assertEquals(arrivee, covoiturage.getDateHeureArrivee());
        assertEquals("Paris", covoiturage.getAdresseDepart());
        assertEquals("Lyon", covoiturage.getAdresseArrivee());
        assertEquals(4, covoiturage.getNbPlaces());
        assertEquals(2, covoiturage.getNbRestant());
        assertEquals(450, covoiturage.getDistance());
        assertEquals(StatutCovoiturage.EN_COURS, covoiturage.getStatut());
    }

    /**
     * Teste la méthode getDuree() pour le calcul de la durée du trajet.
     */
    @Test
    void testGetDuree() {
        LocalDateTime depart = LocalDateTime.of(2025, 6, 18, 8, 0);
        LocalDateTime arrivee = LocalDateTime.of(2025, 6, 18, 10, 30);
        Covoiturage covoiturage = new Covoiturage(depart, arrivee, "Paris", "Lyon", 4, 2, 450, StatutCovoiturage.EN_COURS);
        Duration duree = covoiturage.getDuree();
        assertNotNull(duree);
        assertEquals(2, duree.toHours());
        assertEquals(30, duree.toMinutesPart());
    }

    /**
     * Teste la méthode getDureeFormatee() pour l'affichage de la durée.
     */
    @Test
    void testGetDureeFormatee() {
        LocalDateTime depart = LocalDateTime.of(2025, 6, 18, 8, 0);
        LocalDateTime arrivee = LocalDateTime.of(2025, 6, 18, 10, 45);
        Covoiturage covoiturage = new Covoiturage(depart, arrivee, "Paris", "Lyon", 4, 2, 450, StatutCovoiturage.EN_COURS);
        String dureeFormatee = covoiturage.getDureeFormatee();
        assertEquals("2h45m", dureeFormatee);
    }

    /**
     * Teste la gestion des cas où les dates sont nulles.
     */
    @Test
    void testDureeAvecDatesNulles() {
        Covoiturage covoiturage = new Covoiturage(null, null, "Paris", "Lyon", 4, 2, 450, StatutCovoiturage.EN_COURS);
        assertNull(covoiturage.getDuree());
        assertEquals("0h 0m", covoiturage.getDureeFormatee());
    }
}

