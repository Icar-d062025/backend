package xyz.dedsecm.icar.mapper;

import xyz.dedsecm.icar.dto.UserDTO;
import xyz.dedsecm.icar.model.User;

public class UserMapper {

    // Constructeur privé pour empêcher l'instanciation
    private UserMapper() {
    }

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