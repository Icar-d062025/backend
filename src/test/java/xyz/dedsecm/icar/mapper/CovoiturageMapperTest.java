package xyz.dedsecm.icar.mapper;

import org.junit.jupiter.api.Test;
import xyz.dedsecm.icar.dto.CovoiturageDTO;
import xyz.dedsecm.icar.model.Covoiturage;
import xyz.dedsecm.icar.model.StatutCovoiturage;

import java.time.LocalDateTime;
import java.util.Optional;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test unitaire pour CovoiturageMapper.
 * <p>
 * Vérifie la conversion entre l'entité Covoiturage et le DTO CovoiturageDTO.
 * </p>
 */
class CovoiturageMapperTest {

    /**
     * Teste la conversion d'une entité Covoiturage en DTO CovoiturageDTO.
     */
    @Test
    void testToDTO() {
        LocalDateTime depart = LocalDateTime.now();
        LocalDateTime arrivee = depart.plusHours(2);
        Covoiturage covoiturage = new Covoiturage(depart, arrivee, "Paris", "Lyon", 4, 2, (int) 450.0, StatutCovoiturage.EN_COURS);
        CovoiturageDTO dto = CovoiturageMapper.toDTO(covoiturage);
        assertEquals(depart, dto.getDateHeureDepart());
        assertEquals(arrivee, dto.getDateHeureArrivee());
        assertEquals("Paris", dto.getAdresseDepart());
        assertEquals("Lyon", dto.getAdresseArrivee());
        assertEquals(4, dto.getNbPlaces());
        assertEquals(2, dto.getNbRestant());
        assertEquals(450, dto.getDistance());
        assertEquals(StatutCovoiturage.EN_COURS, dto.getStatut());
    }

    /**
     * Teste la conversion d'un DTO CovoiturageDTO en entité Covoiturage.
     */
    @Test
    void testToEntity() {
        LocalDateTime depart = LocalDateTime.now();
        LocalDateTime arrivee = depart.plusHours(2);
        CovoiturageDTO dto = new CovoiturageDTO(depart, arrivee, "Paris", "Lyon", 4, 2, (int) 450.0, StatutCovoiturage.EN_COURS);
        Covoiturage covoiturage = CovoiturageMapper.toEntity(dto);
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
     * Teste le constructeur privé de CovoiturageMapper.
     */
    @Test
    void testPrivateConstructor() throws Exception {
        var constructor = CovoiturageMapper.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Exception exception = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertNotNull(exception.getCause());
        assertTrue(exception.getCause() instanceof UnsupportedOperationException);
        assertEquals("Cette classe utilitaire ne doit pas être instanciée", exception.getCause().getMessage());
    }
}
