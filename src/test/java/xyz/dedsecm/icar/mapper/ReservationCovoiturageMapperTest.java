package xyz.dedsecm.icar.mapper;

import org.junit.jupiter.api.Test;
import xyz.dedsecm.icar.dto.ReservationCovoiturageDTO;
import xyz.dedsecm.icar.model.ReservationCovoiturage;

import java.time.LocalDate;
import java.util.List;

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

    @Test
    void testToDTOList() {
        ReservationCovoiturage entity = new ReservationCovoiturage();
        entity.setId(1);
        entity.setStatut(1);
        entity.setDateReservation(java.time.LocalDate.now());
        entity.setUtilisateurId(42);
        List<ReservationCovoiturage> entities = List.of(entity);
        List<ReservationCovoiturageDTO> dtos = mapper.toDTOList(entities);
        assertEquals(1, dtos.size());
        assertEquals(1, dtos.get(0).getId());
        // Cas liste vide
        assertTrue(mapper.toDTOList(List.of()).isEmpty());
        // Cas liste nulle
        assertTrue(mapper.toDTOList(null).isEmpty());
    }

    @Test
    void testToEntityList() {
        ReservationCovoiturageDTO dto = new ReservationCovoiturageDTO();
        dto.setId(2);
        dto.setStatut(0);
        dto.setDateReservation(java.time.LocalDate.now());
        dto.setUtilisateurId(99);
        List<ReservationCovoiturageDTO> dtos = List.of(dto);
        List<ReservationCovoiturage> entities = mapper.toEntityList(dtos);
        assertEquals(1, entities.size());
        assertEquals(2, entities.get(0).getId());
        // Cas liste vide
        assertTrue(mapper.toEntityList(List.of()).isEmpty());
        // Cas liste nulle
        assertTrue(mapper.toEntityList(null).isEmpty());
    }

    @Test
    void testUpdateEntityFromDTO() {
        ReservationCovoiturage entity = new ReservationCovoiturage();
        entity.setId(1);
        entity.setStatut(1);
        entity.setDateReservation(java.time.LocalDate.of(2025, 1, 1));
        entity.setUtilisateurId(42);
        ReservationCovoiturageDTO dto = new ReservationCovoiturageDTO();
        dto.setStatut(2);
        dto.setDateReservation(java.time.LocalDate.of(2025, 2, 2));
        dto.setUtilisateurId(99);
        mapper.updateEntityFromDTO(entity, dto);
        assertEquals(2, entity.getStatut());
        assertEquals(java.time.LocalDate.of(2025, 2, 2), entity.getDateReservation());
        assertEquals(99, entity.getUtilisateurId());
        // Cas entity null
        mapper.updateEntityFromDTO(null, dto); // ne doit pas planter
        // Cas dto null
        mapper.updateEntityFromDTO(entity, null); // ne doit pas planter
    }

    @Test
    void testUpdateStatut() {
        ReservationCovoiturage entity = new ReservationCovoiturage();
        entity.setStatut(1);
        mapper.updateStatut(entity, 3);
        assertEquals(3, entity.getStatut());
        // Cas entity null
        mapper.updateStatut(null, 4); // ne doit pas planter
    }

    @Test
    void testCreateNewReservationDTO() {
        ReservationCovoiturageDTO dto = mapper.createNewReservationDTO(5, 123);
        assertEquals(5, dto.getStatut());
        assertEquals(123, dto.getUtilisateurId());
    }

    @Test
    void testToDTOWithDetails() {
        ReservationCovoiturage entity = new ReservationCovoiturage();
        entity.setId(7);
        entity.setStatut(1);
        entity.setDateReservation(java.time.LocalDate.now());
        entity.setUtilisateurId(42);
        ReservationCovoiturageDTO dto = mapper.toDTOWithDetails(entity);
        assertNotNull(dto);
        assertEquals(7, dto.getId());
        // Cas null
        assertNull(mapper.toDTOWithDetails(null));
    }
}
