package xyz.dedsecm.icar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vehicules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String immatricule;

    private String marque;
    private String modele;
    private String categorie;
    private Integer motorisation;
    private String photoUrl;
    private Integer dateCreation;
    private Integer revenueAnnuelDePrevision;

    public Vehicule(Integer id, String immatricule, String marque, String modele, String categorie) {
        this.id = id;
        this.immatricule = immatricule;
        this.marque = marque;
        this.modele = modele;
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Vehicule{" +
                "id=" + id +
                ", immatricule='" + immatricule + '\'' +
                ", marque='" + marque + '\'' +
                ", modele='" + modele + '\'' +
                ", categorie='" + categorie + '\'' +
                ", motorisation=" + motorisation +
                ", photoUrl='" + photoUrl + '\'' +
                ", dateCreation=" + dateCreation +
                ", revenueAnnuelDePrevision=" + revenueAnnuelDePrevision +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicule vehicule = (Vehicule) o;
        return id.equals(vehicule.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}