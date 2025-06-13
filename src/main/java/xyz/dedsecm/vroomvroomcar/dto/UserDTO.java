package xyz.dedsecm.vroomvroomcar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.dedsecm.vroomvroomcar.model.Role;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String username;
    private String nom;
    private String prenom;
    private String mail;
    private String password;
    private Role role;
}