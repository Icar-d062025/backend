package xyz.dedsecm.vroomvroomcar.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

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

    @Column(unique = true, nullable = false)
    private String email;

    private String nom;
    private String prenom;

    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    private String adresse;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean banni;

    @Column(name = "raisonBanni")
    private String raisonBanni;

    @Column(name = "dureeBanni")
    private LocalTime dureeBanni;

    private Boolean vehiculePerso;

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
