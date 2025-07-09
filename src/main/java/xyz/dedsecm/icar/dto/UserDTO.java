package xyz.dedsecm.icar.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.dedsecm.icar.model.Role;

import java.time.LocalTime;

/**
 * DTO (Data Transfer Object) pour les utilisateurs.
 * <p>
 * Permet de transférer les informations relatives aux utilisateurs entre les différentes couches de l'application.
 * Contient toutes les informations d'un utilisateur :
 * <ul>
 *   <li>informations personnelles (nom, prénom, adresse, etc.)</li>
 *   <li>informations d'authentification (email, username, password)</li>
 *   <li>informations de rôle et d'autorisation</li>
 *   <li>informations sur les sanctions éventuelles (bannissement)</li>
 *   <li>informations sur le véhicule personnel</li>
 * </ul>
 * </p>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    /** Identifiant unique de l'utilisateur. */
    private Long id;

    /** Adresse email de l'utilisateur, utilisée pour les communications. */
    private String email;

    /** Nom de famille de l'utilisateur. */
    private String nom;

    /** Prénom de l'utilisateur. */
    private String prenom;

    /** Nom d'utilisateur choisi pour l'authentification. */
    private String username;

    /** Mot de passe utilisé pour l'authentification (devrait être hashé lors du stockage). */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /** Adresse physique de l'utilisateur. */
    private String adresse;

    /** Rôle de l'utilisateur dans le système (ex: ADMIN, USER, etc.). */
    private Role role;

    /** Indique si l'utilisateur est banni du système. */
    private Boolean banni;

    /** Raison pour laquelle l'utilisateur a été banni, si applicable. */
    private String raisonBanni;

    /** Durée du bannissement, si applicable. */
    private LocalTime dureeBanni;

    /** Indique si l'utilisateur possède un véhicule personnel enregistré dans le système. */
    private Boolean vehiculePerso;

    /** Identifiant du véhicule personnel de l'utilisateur, si applicable. */
    private Long vehiculeId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return java.util.Objects.equals(id, userDTO.id)
                && java.util.Objects.equals(email, userDTO.email)
                && java.util.Objects.equals(nom, userDTO.nom)
                && java.util.Objects.equals(prenom, userDTO.prenom)
                && java.util.Objects.equals(username, userDTO.username)
                && java.util.Objects.equals(password, userDTO.password)
                && java.util.Objects.equals(adresse, userDTO.adresse)
                && role == userDTO.role
                && java.util.Objects.equals(banni, userDTO.banni)
                && java.util.Objects.equals(raisonBanni, userDTO.raisonBanni)
                && java.util.Objects.equals(dureeBanni, userDTO.dureeBanni)
                && java.util.Objects.equals(vehiculePerso, userDTO.vehiculePerso)
                && java.util.Objects.equals(vehiculeId, userDTO.vehiculeId);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, email, nom, prenom, username, password, adresse, role, banni, raisonBanni, dureeBanni, vehiculePerso, vehiculeId);
    }
}