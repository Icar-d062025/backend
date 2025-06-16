package xyz.dedsecm.icar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import xyz.dedsecm.icar.dto.CovoiturageDTO;
import xyz.dedsecm.icar.model.StatutCovoiturage;
import xyz.dedsecm.icar.repository.CovoiturageRepository;
import xyz.dedsecm.icar.service.CovoiturageService;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CovoiturageTest {

    @MockitoBean
    private CovoiturageRepository covoiturageRepository;

    @Autowired
    private CovoiturageService covoiturageService;

    @Test
    void createCovoiturageDateArriveeAvantDateDepartVerifErreur() {
        LocalDateTime dateDepart = LocalDateTime.of(2025, 12, 21, 10, 0);
        LocalDateTime dateArrivee = LocalDateTime.of(2025, 12, 21, 9, 0);

        CovoiturageDTO invalidDTO = new CovoiturageDTO(
                dateDepart,
                dateArrivee,
                "Narbonne",
                "Carcassonne",
                4, 4, 100,
                StatutCovoiturage.RESERVABLE
        );

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> covoiturageService.createCovoiturage(invalidDTO)
        );

        assertTrue(exception.getMessage().contains("La date d'arrivée doit être après la date de départ"));
    }

    @Test
    void createCovoiturageNbRestantSuperieurNbPlacesVerifErreur() {
        CovoiturageDTO invalidDTO = new CovoiturageDTO(
                LocalDateTime.now().plusHours(2),
                LocalDateTime.now().plusHours(4),
                "2 Rue Hoche 11100 Narbonne",
                "1 Rue Viollet le Duc, 11000 Carcassonne",
                4, 6, 100,
                StatutCovoiturage.RESERVABLE
        );

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> covoiturageService.createCovoiturage(invalidDTO)
        );

        assertTrue(exception.getMessage().contains("Le nombre de places restantes doit être inférieur ou égal au nombre de places du véhicule"));

    }

    @Test
    void createCovoiturageDateDepartDansLePasseVerifErreur() {
        LocalDateTime datePassee = LocalDateTime.of(1999, 1, 1, 0, 1, 0);

        CovoiturageDTO invalidDTO = new CovoiturageDTO(
                datePassee,
                datePassee.plusYears(26),
                "1 place Martin Bastard 79027 Niort",
                "2 avenue des Roches Blanches 44300 Nantes",
                4, 4, 200,
                StatutCovoiturage.RESERVABLE
        );

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> covoiturageService.createCovoiturage(invalidDTO)
        );

        assertTrue(exception.getMessage().contains("La date de départ ne peut pas être dans le passé"));
    }

    @Test
    void createCovoiturageMemeNomDeVilleVerifErreur() {
        CovoiturageDTO invalidDTO = new CovoiturageDTO(
                LocalDateTime.now().plusHours(1),
                LocalDateTime.now().plusHours(3),
                "2 avenue Tanne 34500 Béziers",
                "2 avenue Tanne 34500 Béziers",
                4, 4, 200,
                StatutCovoiturage.RESERVABLE
        );

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> covoiturageService.createCovoiturage(invalidDTO)
        );

        assertTrue(exception.getMessage().contains("L'adresse de départ doit être différente de l'adresse d'arrivée et inversement"));
    }

}
