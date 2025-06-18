package xyz.dedsecm.icar.dto;

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
}