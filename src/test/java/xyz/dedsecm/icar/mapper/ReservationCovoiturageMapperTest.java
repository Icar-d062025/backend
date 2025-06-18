package xyz.dedsecm.icar.mapper;

import org.junit.jupiter.api.Test;
import xyz.dedsecm.icar.dto.ReservationCovoiturageDTO;
import xyz.dedsecm.icar.model.ReservationCovoiturage;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test unitaire pour ReservationCovoiturageMapper.
 * <p>
 * Vérifie la conversion entre l'entité ReservationCovoiturage et le DTO ReservationCovoiturageDTO.
 * </p>
 */
class ReservationCovoiturageMapperTest {

    private final ReservationCovoiturageMapper mapper = new ReservationCovoiturageMapper();

    /**
     * Teste la conversion d'une entité ReservationCovoiturage en DTO ReservationCovoiturageDTO.
     */
    @Test
    void testToDTO() {
        ReservationCovoiturage entity = new ReservationCovoiturage();
        entity.setId(1);
        entity.setStatut(1);
        entity.setDateReservation(LocalDate.of(2025, 6, 18));
        entity.setUtilisateurId(42);

        ReservationCovoiturageDTO dto = mapper.toDTO(entity);
        assertNotNull(dto);
        assertEquals(1, dto.getId());
        assertEquals(1, dto.getStatut());
        assertEquals(LocalDate.of(2025, 6, 18), dto.getDateReservation());
        assertEquals(42, dto.getUtilisateurId());
    }

    /**
     * Teste la conversion d'un DTO ReservationCovoiturageDTO en entité ReservationCovoiturage.
     */
    @Test
    void testToEntity() {
        ReservationCovoiturageDTO dto = new ReservationCovoiturageDTO();
        dto.setId(2);
        dto.setStatut(0);
        dto.setDateReservation(LocalDate.of(2025, 6, 19));
        dto.setUtilisateurId(99);

        ReservationCovoiturage entity = mapper.toEntity(dto);
        assertNotNull(entity);
        assertEquals(2, entity.getId());
        assertEquals(0, entity.getStatut());
        assertEquals(LocalDate.of(2025, 6, 19), entity.getDateReservation());
        assertEquals(99, entity.getUtilisateurId());
    }

    @Test
    void testToDTONull() {
        assertNull(mapper.toDTO(null));
    }

    @Test
    void testToEntityNull() {
        assertNull(mapper.toEntity(null));
    }
}
