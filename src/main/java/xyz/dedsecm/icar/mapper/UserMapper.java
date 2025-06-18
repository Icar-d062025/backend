package xyz.dedsecm.icar.mapper;

import xyz.dedsecm.icar.dto.UserDTO;
import xyz.dedsecm.icar.model.User;

/**
 * Mapper utilitaire pour convertir entre les entités User et les DTO UserDTO.
 * <p>
 * Fournit des méthodes statiques pour transformer un objet métier (entité) en DTO et inversement.
 * Permet de séparer la logique de conversion du reste de l'application.
 * </p>
 */
public class UserMapper {
    /**
     * Constructeur privé pour empêcher l'instanciation de la classe utilitaire.
     */
    private UserMapper() {}

    /**
     * Convertit une entité User en DTO UserDTO.
     * @param user l'entité à convertir
     * @return le DTO correspondant
     */
    public static UserDTO toDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getNom(),
                user.getPrenom(),
                user.getUsername(),
                user.getPassword(),
                user.getAdresse(),
                user.getRole(),
                user.getBanni(),
                user.getRaisonBanni(),
                user.getDureeBanni(),
                user.getVehiculePerso(),
                user.getVehiculeId()
        );
    }

    /**
     * Convertit un DTO UserDTO en entité User.
     * @param dto le DTO à convertir
     * @return l'entité correspondante
     */
    public static User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setEmail(dto.getEmail());
        user.setNom(dto.getNom());
        user.setPrenom(dto.getPrenom());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setAdresse(dto.getAdresse());
        user.setRole(dto.getRole());
        user.setBanni(dto.getBanni());
        user.setRaisonBanni(dto.getRaisonBanni());
        user.setDureeBanni(dto.getDureeBanni());
        user.setVehiculePerso(dto.getVehiculePerso());
        user.setVehiculeId(dto.getVehiculeId());
        return user;
    }
}