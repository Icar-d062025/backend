package xyz.dedsecm.icar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entité représentant un véhicule dans le système.
 * <p>
 * Cette classe contient toutes les informations nécessaires à la gestion d'un véhicule :
 * <ul>
 *   <li>identifiant, immatriculation, marque, modèle, catégorie</li>
 *   <li>motorisation, photo, date de création, revenu annuel prévisionnel</li>
 * </ul>
 * </p>
 */
@Entity
@Table(name = "vehicules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicule {

    /** Identifiant unique du véhicule. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Numéro d'immatriculation du véhicule (unique). */
    @Column(unique = true, nullable = false)
    private String immatricule;

    /** Marque du véhicule. */
    private String marque;
    /** Modèle du véhicule. */
    private String modele;
    /** Catégorie du véhicule (ex : citadine, SUV, etc.). */
    private String categorie;
    /** Type de motorisation (ex : essence, diesel, électrique, etc.). */
    private Integer motorisation;
    /** URL de la photo du véhicule. */
    private String photoUrl;
    /** Année de création du véhicule. */
    private Integer dateCreation;
    /** Revenu annuel prévisionnel généré par le véhicule. */
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