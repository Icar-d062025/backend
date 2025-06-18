package xyz.dedsecm.icar.mapper;

import org.junit.jupiter.api.Test;
import xyz.dedsecm.icar.dto.ReservationVehiculeDTO;
import xyz.dedsecm.icar.model.ReservationVehicule;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test unitaire pour ReservationVehiculeMapper.
 * <p>
 * Vérifie la conversion entre l'entité ReservationVehicule et le DTO ReservationVehiculeDTO.
 * </p>
 */
class ReservationVehiculeMapperTest {

    private final ReservationVehiculeMapper mapper = new ReservationVehiculeMapper();

    /**
     * Teste la conversion d'une entité ReservationVehicule en DTO ReservationVehiculeDTO.
     */
    @Test
    void testToDTO() {
        ReservationVehicule entity = new ReservationVehicule();
        entity.setId(1);
        entity.setDateDebut(LocalDate.of(2025, 6, 18));
        entity.setDateFin(LocalDate.of(2025, 6, 20));
        entity.setDateCreation(LocalDate.of(2025, 6, 10));
        entity.setUtilisateurId(42);
        entity.setVehiculeId(7);

        ReservationVehiculeDTO dto = mapper.toDTO(entity);
        assertNotNull(dto);
        assertEquals(1, dto.getId());
        assertEquals(LocalDate.of(2025, 6, 18), dto.getDateDebut());
        assertEquals(LocalDate.of(2025, 6, 20), dto.getDateFin());
        assertEquals(LocalDate.of(2025, 6, 10), dto.getDateCreation());
        assertEquals(42, dto.getUtilisateurId());
        assertEquals(7, dto.getVehiculeId());
    }

    /**
     * Teste la conversion d'un DTO ReservationVehiculeDTO en entité ReservationVehicule.
     */
    @Test
    void testToEntity() {
        ReservationVehiculeDTO dto = new ReservationVehiculeDTO();
        dto.setId(2);
        dto.setDateDebut(LocalDate.of(2025, 7, 1));
        dto.setDateFin(LocalDate.of(2025, 7, 5));
        dto.setDateCreation(LocalDate.of(2025, 6, 15));
        dto.setUtilisateurId(99);
        dto.setVehiculeId(3);

        ReservationVehicule entity = mapper.toEntity(dto);
        assertNotNull(entity);
        assertEquals(2, entity.getId());
        assertEquals(LocalDate.of(2025, 7, 1), entity.getDateDebut());
        assertEquals(LocalDate.of(2025, 7, 5), entity.getDateFin());
        assertEquals(LocalDate.of(2025, 6, 15), entity.getDateCreation());
        assertEquals(99, entity.getUtilisateurId());
        assertEquals(3, entity.getVehiculeId());
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
        ReservationVehicule entity = new ReservationVehicule();
        entity.setId(1);
        entity.setDateDebut(java.time.LocalDate.now());
        entity.setDateFin(java.time.LocalDate.now().plusDays(1));
        entity.setDateCreation(java.time.LocalDate.now());
        entity.setUtilisateurId(42);
        entity.setVehiculeId(7);
        List<ReservationVehicule> entities = List.of(entity);
        List<ReservationVehiculeDTO> dtos = mapper.toDTOList(entities);
        assertEquals(1, dtos.size());
        assertEquals(1, dtos.get(0).getId());
        // Cas liste vide
        assertTrue(mapper.toDTOList(List.of()).isEmpty());
        // Cas liste nulle
        assertTrue(mapper.toDTOList(null).isEmpty());
    }

    @Test
    void testToEntityList() {
        ReservationVehiculeDTO dto = new ReservationVehiculeDTO();
        dto.setId(2);
        dto.setDateDebut(java.time.LocalDate.now());
        dto.setDateFin(java.time.LocalDate.now().plusDays(1));
        dto.setDateCreation(java.time.LocalDate.now());
        dto.setUtilisateurId(99);
        dto.setVehiculeId(3);
        List<ReservationVehiculeDTO> dtos = List.of(dto);
        List<ReservationVehicule> entities = mapper.toEntityList(dtos);
        assertEquals(1, entities.size());
        assertEquals(2, entities.get(0).getId());
        // Cas liste vide
        assertTrue(mapper.toEntityList(List.of()).isEmpty());
        // Cas liste nulle
        assertTrue(mapper.toEntityList(null).isEmpty());
    }

    @Test
    void testUpdateEntityFromDTO() {
        ReservationVehicule entity = new ReservationVehicule();
        entity.setId(1);
        entity.setDateDebut(java.time.LocalDate.of(2025, 1, 1));
        entity.setDateFin(java.time.LocalDate.of(2025, 1, 2));
        entity.setDateCreation(java.time.LocalDate.of(2025, 1, 1));
        entity.setUtilisateurId(42);
        entity.setVehiculeId(7);
        ReservationVehiculeDTO dto = new ReservationVehiculeDTO();
        dto.setDateDebut(java.time.LocalDate.of(2025, 2, 2));
        dto.setDateFin(java.time.LocalDate.of(2025, 2, 3));
        dto.setUtilisateurId(99);
        dto.setVehiculeId(3);
        mapper.updateEntityFromDTO(entity, dto);
        assertEquals(java.time.LocalDate.of(2025, 2, 2), entity.getDateDebut());
        assertEquals(java.time.LocalDate.of(2025, 2, 3), entity.getDateFin());
        assertEquals(99, entity.getUtilisateurId());
        assertEquals(3, entity.getVehiculeId());
        // Cas entity null
        mapper.updateEntityFromDTO(null, dto); // ne doit pas planter
        // Cas dto null
        mapper.updateEntityFromDTO(entity, null); // ne doit pas planter
    }
}
