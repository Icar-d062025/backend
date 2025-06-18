package xyz.dedsecm.icar.mapper;

import xyz.dedsecm.icar.dto.ReservationCovoiturageDTO;
import xyz.dedsecm.icar.model.ReservationCovoiturage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper Spring pour convertir entre les entités ReservationCovoiturage et les DTO ReservationCovoiturageDTO.
 * <p>
 * Fournit des méthodes pour transformer un objet métier (entité) en DTO et inversement.
 * Permet de séparer la logique de conversion du reste de l'application.
 * </p>
 */
@Component
public class ReservationCovoiturageMapper {

    /**
     * Convertit une entité ReservationCovoiturage en DTO
     * @param entity l'entité à convertir
     * @return le DTO correspondant
     */
    public ReservationCovoiturageDTO toDTO(ReservationCovoiturage entity) {
        if (entity == null) {
            return null;
        }

        ReservationCovoiturageDTO dto = new ReservationCovoiturageDTO();
        dto.setId(entity.getId());
        dto.setStatut(entity.getStatut());
        dto.setDateReservation(entity.getDateReservation());
        dto.setUtilisateurId(entity.getUtilisateurId());

        return dto;
    }

    /**
     * Convertit un DTO ReservationCovoituragetDTO en entité
     * @param dto le DTO à convertir
     * @return l'entité correspondante
     */
    public ReservationCovoiturage toEntity(ReservationCovoiturageDTO dto) {
        if (dto == null) {
            return null;
        }

        ReservationCovoiturage entity = new ReservationCovoiturage();
        entity.setId(dto.getId());
        entity.setStatut(dto.getStatut());
        entity.setDateReservation(dto.getDateReservation());
        entity.setUtilisateurId(dto.getUtilisateurId());

        return entity;
    }

    /**
     * Convertit une liste d'entités en liste de DTOs
     * @param entities la liste d'entités à convertir
     * @return la liste de DTOs correspondante
     */
    public List<ReservationCovoiturageDTO> toDTOList(List<ReservationCovoiturage> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Convertit une liste de DTOs en liste d'entités
     * @param dtos la liste de DTOs à convertir
     * @return la liste d'entités correspondante
     */
    public List<ReservationCovoiturage> toEntityList(List<ReservationCovoiturageDTO> dtos) {
        if (dtos == null) {
            return null;
        }

        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    /**
     * Met à jour une entité existante avec les données d'un DTO
     * @param entity l'entité à mettre à jour
     * @param dto le DTO contenant les nouvelles données
     */
    public void updateEntityFromDTO(ReservationCovoiturage entity, ReservationCovoiturageDTO dto) {
        if (entity == null || dto == null) {
            return;
        }

        // On ne met pas à jour l'ID
        entity.setStatut(dto.getStatut());
        entity.setDateReservation(dto.getDateReservation());
        entity.setUtilisateurId(dto.getUtilisateurId());
    }

    /**
     * Met à jour uniquement le statut d'une entité
     * @param entity l'entité à mettre à jour
     * @param nouveauStatut le nouveau statut
     */
    public void updateStatut(ReservationCovoiturage entity, Integer nouveauStatut) {
        if (entity != null) {
            entity.setStatut(nouveauStatut);
        }
    }

    /**
     * Crée un DTO avec les informations essentielles pour une nouvelle réservation
     * @param statut le statut initial
     * @param utilisateurId l'ID de l'utilisateur
     * @return le DTO créé
     */
    public ReservationCovoiturageDTO createNewReservationDTO(Integer statut, Integer utilisateurId) {
        return new ReservationCovoiturageDTO(statut, utilisateurId);
    }

    /**
     * Convertit une entité en DTO avec informations enrichies
     * @param entity l'entité à convertir
     * @return le DTO avec les informations supplémentaires
     */
    public ReservationCovoiturageDTO toDTOWithDetails(ReservationCovoiturage entity) {
        ReservationCovoiturageDTO dto = toDTO(entity);
        if (dto != null) {
            // Le DTO contient déjà les méthodes utilitaires comme getStatutLibelle(), isRecente(), etc.
            // Elles seront automatiquement disponibles
        }
        return dto;
    }
}