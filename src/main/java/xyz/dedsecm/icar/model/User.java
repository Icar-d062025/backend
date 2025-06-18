package xyz.dedsecm.icar.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

/**
 * Entité représentant un utilisateur dans le système.
 * <p>
 * Cette classe contient toutes les informations nécessaires à la gestion d'un utilisateur :
 * <ul>
 *   <li>informations personnelles (nom, prénom, adresse, email, etc.)</li>
 *   <li>informations d'authentification (username, password)</li>
 *   <li>rôle et autorisations</li>
 *   <li>état de bannissement</li>
 *   <li>informations sur le véhicule personnel</li>
 * </ul>
 * </p>
 */
@Entity
@Table(name = "Utilisateur")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utilisateur")
    private Long id;

    /** Adresse email de l'utilisateur (doit être unique). */
    @Column(unique = true, nullable = false)
    private String email;

    /** Nom de l'utilisateur. */
    private String nom;

    /** Prénom de l'utilisateur. */
    private String prenom;

    /** Nom d'utilisateur pour l'authentification. */
    private String username;

    /** Mot de passe utilisé pour l'authentification (stocké de façon sécurisée). */
    @Column(name = "password", nullable = false)
    private String password;

    /** Adresse physique de l'utilisateur. */
    private String adresse;

    /** Rôle de l'utilisateur dans le système (ex : ADMIN, USER, etc.). */
    @Enumerated(EnumType.STRING)
    private Role role;

    /** Indique si l'utilisateur est banni du système. */
    private Boolean banni;

    /** Raison pour laquelle l'utilisateur a été banni, si applicable. */
    @Column(name = "raisonBanni")
    private String raisonBanni;

    /** Durée du bannissement, si applicable. */
    @Column(name = "dureeBanni")
    private LocalTime dureeBanni;

    /** Indique si l'utilisateur possède un véhicule personnel enregistré dans le système. */
    private Boolean vehiculePerso;

    /** Identifiant du véhicule personnel de l'utilisateur, si applicable. */
    @Column(name = "Vehiculeid")
    private Long vehiculeId;

    // constructeur personnalisé
    public User(String email, String nom, String prenom, String password, String username) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.username = username;
        this.role = Role.USER;
        this.banni = false;
        this.vehiculePerso = false;
    }
}
