package xyz.dedsecm.icar.service;

import org.junit.jupiter.api.Test;
import xyz.dedsecm.icar.dto.CovoiturageDTO;
import xyz.dedsecm.icar.model.StatutCovoiturage;
import xyz.dedsecm.icar.exception.CovoiturageException;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test unitaire pour la méthode validateCovoiturageDTO du service CovoiturageService.
 * <p>
 * Vérifie la validation des règles métier sur un CovoiturageDTO.
 * </p>
 */
class CovoiturageServiceTest {

    /**
     * Teste la validation d'un DTO correct (aucune exception attendue).
     */
    @Test
    void testValidationValide() {
        CovoiturageService service = new CovoiturageService();
        CovoiturageDTO dto = new CovoiturageDTO(
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(1).plusHours(2),
                "Paris", "Lyon", 4, 2, 450, StatutCovoiturage.EN_COURS
        );
        assertDoesNotThrow(() -> service.validateCovoiturageDTO(dto));
    }

    /**
     * Teste la validation avec une date de départ dans le passé.
     */
    @Test
    void testDateDepartDansLePasse() {
        CovoiturageService service = new CovoiturageService();
        CovoiturageDTO dto = new CovoiturageDTO(
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now().plusDays(1),
                "Paris", "Lyon", 4, 2, 450, StatutCovoiturage.EN_COURS
        );
        Exception e = assertThrows(CovoiturageException.class, () -> service.validateCovoiturageDTO(dto));
        assertTrue(e.getMessage().contains("départ ne peut pas être dans le passé"));
    }

    /**
     * Teste la validation avec une date d'arrivée avant la date de départ.
     */
    @Test
    void testDateArriveeAvantDepart() {
        CovoiturageService service = new CovoiturageService();
        CovoiturageDTO dto = new CovoiturageDTO(
                LocalDateTime.now().plusDays(2),
                LocalDateTime.now().plusDays(1),
                "Paris", "Lyon", 4, 2, 450, StatutCovoiturage.EN_COURS
        );
        Exception e = assertThrows(CovoiturageException.class, () -> service.validateCovoiturageDTO(dto));
        assertTrue(e.getMessage().contains("après la date de départ"));
    }

    /**
     * Teste la validation avec des adresses identiques.
     */
    @Test
    void testAdressesIdentiques() {
        CovoiturageService service = new CovoiturageService();
        CovoiturageDTO dto = new CovoiturageDTO(
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(1).plusHours(2),
                "Paris", "Paris", 4, 2, 450, StatutCovoiturage.EN_COURS
        );
        Exception e = assertThrows(CovoiturageException.class, () -> service.validateCovoiturageDTO(dto));
        assertTrue(e.getMessage().contains("doit être différente"));
    }

    /**
     * Teste la validation avec un nombre de places négatif.
     */
    @Test
    void testNbPlacesNegatif() {
        CovoiturageService service = new CovoiturageService();
        CovoiturageDTO dto = new CovoiturageDTO(
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(1).plusHours(2),
                "Paris", "Lyon", -1, 0, 450, StatutCovoiturage.EN_COURS
        );
        Exception e = assertThrows(CovoiturageException.class, () -> service.validateCovoiturageDTO(dto));
        assertTrue(e.getMessage().contains("nombre de places"));
    }
}
