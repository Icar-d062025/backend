package xyz.dedsecm.icar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "covoiturage")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Covoiturage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_heure_depart", nullable = false)
    private LocalDateTime dateHeureDepart;

    @Column(name = "date_heure_arrivee", nullable = false)
    private LocalDateTime dateHeureArrivee;

    @Column(name = "adresse_depart", nullable = false)
    private String adresseDepart;

    @Column(name = "adresse_arrivee", nullable = false)
    private String adresseArrivee;

    @Column(name = "nb_places")
    private Integer nbPlaces;

    @Column(name = "nb_restant")
    private Integer nbRestant;

    @Column(name = "distance")
    private Integer distance;

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
