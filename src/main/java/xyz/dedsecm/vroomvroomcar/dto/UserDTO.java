package xyz.dedsecm.vroomvroomcar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.dedsecm.vroomvroomcar.model.Role;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private String nom;
    private String prenom;
    private String username;
    private String password;
    private String adresse;
    private Role role;
    private Boolean banni;
    private String raisonBanni;
    private LocalTime dureeBanni;
    private Boolean vehiculePerso;
    private Long vehiculeId;
}