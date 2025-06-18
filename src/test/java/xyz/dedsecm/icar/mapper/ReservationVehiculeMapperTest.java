package xyz.dedsecm.icar.mapper;

import org.junit.jupiter.api.Test;
import xyz.dedsecm.icar.dto.ReservationVehiculeDTO;
import xyz.dedsecm.icar.model.ReservationVehicule;

import java.time.LocalDate;

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
}
