package xyz.dedsecm.vroomvroomcar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String role;
}