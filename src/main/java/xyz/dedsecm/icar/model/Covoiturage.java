package xyz.dedsecm.icar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Entité représentant un covoiturage dans le système.
 * <p>
 * Cette classe contient toutes les informations nécessaires à la gestion d'un trajet de covoiturage :
 * <ul>
 *   <li>date et heure de départ/arrivée</li>
 *   <li>adresses de départ et d'arrivée</li>
 *   <li>nombre de places totales et restantes</li>
 *   <li>distance du trajet</li>
 *   <li>statut du covoiturage</li>
 * </ul>
 * </p>
 */
@Entity
@Table(name = "covoiturage")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Covoiturage {

    /** Identifiant unique du covoiturage. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Date et heure de départ du covoiturage. */
    @Column(name = "date_heure_depart", nullable = false)
    private LocalDateTime dateHeureDepart;

    /** Date et heure d'arrivée du covoiturage. */
    @Column(name = "date_heure_arrivee", nullable = false)
    private LocalDateTime dateHeureArrivee;

    /** Adresse de départ du covoiturage. */
    @Column(name = "adresse_depart", nullable = false)
    private String adresseDepart;

    /** Adresse d'arrivée du covoiturage. */
    @Column(name = "adresse_arrivee", nullable = false)
    private String adresseArrivee;

    /** Nombre total de places disponibles dans le véhicule. */
    @Column(name = "nb_places")
    private Integer nbPlaces;

    /** Nombre de places restantes disponibles. */
    @Column(name = "nb_restant")
    private Integer nbRestant;

    /** Distance du trajet en kilomètres. */
    @Column(name = "distance")
    private Integer distance;

    /** Statut actuel du covoiturage. */
    @Enumerated
    private StatutCovoiturage statut;

    public Covoiturage(LocalDateTime dateHeureDepart, LocalDateTime dateHeureArrivee, String adresseDepart, String adresseArrivee, Integer nbPlaces, Integer nbRestant, Integer distance, StatutCovoiturage statut) {
        this.dateHeureDepart = dateHeureDepart;
        this.dateHeureArrivee = dateHeureArrivee;
        this.adresseDepart = adresseDepart;
        this.adresseArrivee = adresseArrivee;
        this.nbPlaces = nbPlaces;
        this.nbRestant = nbRestant;
        this.distance = distance;
        this.statut = statut;
    }

    /**
     * Constructeur de confort pour Covoiturage.
     * Utilisez un builder ou un objet de paramètres pour éviter d'avoir plus de 7 paramètres.
     */
    public static class Builder {
        private LocalDateTime dateHeureDepart;
        private LocalDateTime dateHeureArrivee;
        private String adresseDepart;
        private String adresseArrivee;
        private Integer nbPlaces;
        private Integer nbRestant;
        private Integer distance;
        private StatutCovoiturage statut;

        public Builder dateHeureDepart(LocalDateTime dateHeureDepart) { this.dateHeureDepart = dateHeureDepart; return this; }
        public Builder dateHeureArrivee(LocalDateTime dateHeureArrivee) { this.dateHeureArrivee = dateHeureArrivee; return this; }
        public Builder adresseDepart(String adresseDepart) { this.adresseDepart = adresseDepart; return this; }
        public Builder adresseArrivee(String adresseArrivee) { this.adresseArrivee = adresseArrivee; return this; }
        public Builder nbPlaces(Integer nbPlaces) { this.nbPlaces = nbPlaces; return this; }
        public Builder nbRestant(Integer nbRestant) { this.nbRestant = nbRestant; return this; }
        public Builder distance(Integer distance) { this.distance = distance; return this; }
        public Builder statut(StatutCovoiturage statut) { this.statut = statut; return this; }
        public Covoiturage build() {
            return new Covoiturage(dateHeureDepart, dateHeureArrivee, adresseDepart, adresseArrivee, nbPlaces, nbRestant, distance, statut);
        }
    }

    /***
     * Méthode calculée pour la durée du trajet
     * @return null
     */
    public Duration getDuree() {
        if (dateHeureDepart != null && dateHeureArrivee != null) {
            return Duration.between(dateHeureDepart, dateHeureArrivee);
        }
        return null;
    }

    /***
     * Méthode pour obtenir la durée en heures et en minutes
     * @return xh xxm
     */
    public String getDureeFormatee() {
        Duration duree =getDuree();
        if (duree == null) return "0h 0m";

        long heures = duree.toHours();
        long minutes = duree.toMinutesPart();

        return heures + "h" + minutes + "m";
    }

}
