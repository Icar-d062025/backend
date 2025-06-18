package xyz.dedsecm.icar.mapper;

import xyz.dedsecm.icar.dto.ReservationVehiculeDTO;
import xyz.dedsecm.icar.model.ReservationVehicule;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper Spring pour convertir entre les entités ReservationVehicule et les DTO ReservationVehiculeDTO.
 * <p>
 * Fournit des méthodes pour transformer un objet métier (entité) en DTO et inversement.
 * Permet de séparer la logique de conversion du reste de l'application.
 * </p>
 */
@Component
public class ReservationVehiculeMapper {

    /**
     * Convertit une entité ReservationVehicule en DTO
     * @param entity l'entité à convertir
     * @return le DTO correspondant
     */
    public ReservationVehiculeDTO toDTO(ReservationVehicule entity) {
        if (entity == null) {
            return null;
        }

        ReservationVehiculeDTO dto = new ReservationVehiculeDTO();
        dto.setId(entity.getId());
        dto.setDateDebut(entity.getDateDebut());
        dto.setDateFin(entity.getDateFin());
        dto.setDateCreation(entity.getDateCreation());
        dto.setUtilisateurId(entity.getUtilisateurId());
        dto.setVehiculeId(entity.getVehiculeId());

        return dto;
    }

    /**
     * Convertit un DTO ReservationVehiculeDTO en entité
     * @param dto le DTO à convertir
     * @return l'entité correspondante
     */
    public ReservationVehicule toEntity(ReservationVehiculeDTO dto) {
        if (dto == null) {
            return null;
        }

        ReservationVehicule entity = new ReservationVehicule();
        entity.setId(dto.getId());
        entity.setDateDebut(dto.getDateDebut());
        entity.setDateFin(dto.getDateFin());
        entity.setDateCreation(dto.getDateCreation());
        entity.setUtilisateurId(dto.getUtilisateurId());
        entity.setVehiculeId(dto.getVehiculeId());

        return entity;
    }

    /**
     * Convertit une liste d'entités en liste de DTOs
     * @param entities la liste d'entités à convertir
     * @return la liste de DTOs correspondante
     */
    public List<ReservationVehiculeDTO> toDTOList(List<ReservationVehicule> entities) {
        if (entities == null) {
            return List.of();
        }

        return entities.stream()
                .map(this::toDTO)
                .toList();
    }

    /**
     * Convertit une liste de DTOs en liste d'entités
     * @param dtos la liste de DTOs à convertir
     * @return la liste d'entités correspondante
     */
    public List<ReservationVehicule> toEntityList(List<ReservationVehiculeDTO> dtos) {
        if (dtos == null) {
            return List.of();
        }

        return dtos.stream()
                .map(this::toEntity)
                .toList();
    }

    /**
     * Met à jour une entité existante avec les données d'un DTO
     * @param entity l'entité à mettre à jour
     * @param dto le DTO contenant les nouvelles données
     */
    public void updateEntityFromDTO(ReservationVehicule entity, ReservationVehiculeDTO dto) {
        if (entity == null || dto == null) {
            return;
        }

        // On ne met pas à jour l'ID ni la date de création
        entity.setDateDebut(dto.getDateDebut());
        entity.setDateFin(dto.getDateFin());
        entity.setUtilisateurId(dto.getUtilisateurId());
        entity.setVehiculeId(dto.getVehiculeId());
    }
}