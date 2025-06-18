package xyz.dedsecm.icar.dto;

import org.junit.jupiter.api.Test;
import xyz.dedsecm.icar.model.StatutCovoiturage;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CovoiturageDTOTest {
    @Test
    void testConstructeurEtGettersSetters() {
        LocalDateTime depart = LocalDateTime.of(2025, 6, 18, 8, 0);
        LocalDateTime arrivee = LocalDateTime.of(2025, 6, 18, 10, 0);
        String adresseDepart = "Paris";
        String adresseArrivee = "Lyon";
        Integer nbPlaces = 4;
        Integer nbRestant = 2;
        Integer distance = 450;
        StatutCovoiturage statut = StatutCovoiturage.RESERVABLE;

        CovoiturageDTO dto = new CovoiturageDTO(depart, arrivee, adresseDepart, adresseArrivee, nbPlaces, nbRestant, distance, statut);

        assertEquals(depart, dto.getDateHeureDepart());
        assertEquals(arrivee, dto.getDateHeureArrivee());
        assertEquals(adresseDepart, dto.getAdresseDepart());
        assertEquals(adresseArrivee, dto.getAdresseArrivee());
        assertEquals(nbPlaces, dto.getNbPlaces());
        assertEquals(nbRestant, dto.getNbRestant());
        assertEquals(distance, dto.getDistance());
        assertEquals(statut, dto.getStatut());

        // Test setters
        LocalDateTime newDepart = LocalDateTime.of(2025, 6, 19, 9, 0);
        dto.setDateHeureDepart(newDepart);
        assertEquals(newDepart, dto.getDateHeureDepart());
    }

    @Test
    void testNoArgsConstructor() {
        CovoiturageDTO dto = new CovoiturageDTO();
        assertNotNull(dto);
    }
}
