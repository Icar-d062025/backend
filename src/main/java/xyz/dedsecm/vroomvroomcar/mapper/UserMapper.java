package xyz.dedsecm.vroomvroomcar.mapper;

import xyz.dedsecm.vroomvroomcar.dto.UserDTO;
import xyz.dedsecm.vroomvroomcar.model.User;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        return new UserDTO(user.getNom(), user.getPrenom(), user.getMail(), user.getPassword());
    }

    public static User toEntity(UserDTO dto) {
        return new User(dto.getNom(), dto.getPrenom(), dto.getMail());
    }
}
