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

    /**
     * Retourne une représentation textuelle du véhicule avec tous ses champs principaux.
     * @return une chaîne contenant les valeurs de tous les champs du véhicule
     */
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

    /**
     * Vérifie l'égalité de deux objets Vehicule.
     * Deux véhicules sont considérés comme égaux si tous leurs champs principaux sont identiques :
     * <ul>
     *   <li>id</li>
     *   <li>immatricule</li>
     *   <li>marque</li>
     *   <li>modele</li>
     *   <li>categorie</li>
     *   <li>motorisation</li>
     *   <li>photoUrl</li>
     *   <li>dateCreation</li>
     *   <li>revenueAnnuelDePrevision</li>
     * </ul>
     * @param o l'objet à comparer
     * @return true si tous les champs sont identiques, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicule vehicule = (Vehicule) o;
        return java.util.Objects.equals(id, vehicule.id)
                && java.util.Objects.equals(immatricule, vehicule.immatricule)
                && java.util.Objects.equals(marque, vehicule.marque)
                && java.util.Objects.equals(modele, vehicule.modele)
                && java.util.Objects.equals(categorie, vehicule.categorie)
                && java.util.Objects.equals(motorisation, vehicule.motorisation)
                && java.util.Objects.equals(photoUrl, vehicule.photoUrl)
                && java.util.Objects.equals(dateCreation, vehicule.dateCreation)
                && java.util.Objects.equals(revenueAnnuelDePrevision, vehicule.revenueAnnuelDePrevision);
    }

    /**
     * Calcule le hashCode du véhicule à partir de tous les champs principaux.
     * @return le hashCode basé sur id, immatricule, marque, modele, categorie, motorisation, photoUrl, dateCreation et revenueAnnuelDePrevision
     */
    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, immatricule, marque, modele, categorie, motorisation, photoUrl, dateCreation, revenueAnnuelDePrevision);
    }
}