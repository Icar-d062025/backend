package xyz.dedsecm.vroomvroomcar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    private String nom;
    private String prenom;
    private String mail;
    private String password;

    private String role; // admin, chauffeur, utilisateur

    public User(String username, String nom, String prenom, String mail, String password, String role) {
        this.username = username;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.password = password;
        this.role = role;
    }
}