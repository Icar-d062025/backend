package xyz.dedsecm.vroomvroomcar.mapper;

import xyz.dedsecm.vroomvroomcar.dto.UserDTO;
import xyz.dedsecm.vroomvroomcar.model.User;

public class UserMapper {

    // Constructeur privé pour empêcher l'instanciation
    private UserMapper() {

    }

    public static UserDTO toDTO(User user) {
        return new UserDTO(
                user.getUsername(),
                user.getNom(),
                user.getPrenom(),
                user.getMail(),
                user.getPassword(),
                user.getRole()
        );
    }

    public static User toEntity(UserDTO dto) {
        return new User(
                dto.getUsername(),
                dto.getNom(),
                dto.getPrenom(),
                dto.getMail(),
                dto.getPassword(),
                dto.getRole()
        );
    }
}