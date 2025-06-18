package xyz.dedsecm.icar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.dedsecm.icar.model.StatutCovoiturage;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) pour le covoiturage.
 * Permet de transférer les informations d'un covoiturage entre les différentes couches de l'application.
 * <p>
 * Contient les informations principales d'un trajet de covoiturage :
 * - date et heure de départ/arrivée
 * - adresses de départ et d'arrivée
 * - nombre de places totales et restantes
 * - distance du trajet
 * - statut du covoiturage
 * </p>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CovoiturageDTO {
    /** Date et heure de départ du covoiturage. */
    private LocalDateTime dateHeureDepart;
    /** Date et heure d'arrivée du covoiturage. */
    private LocalDateTime dateHeureArrivee;
    /** Adresse de départ du covoiturage. */
    private String adresseDepart;
    /** Adresse d'arrivée du covoiturage. */
    private String adresseArrivee;
    /** Nombre total de places disponibles dans le véhicule. */
    private Integer nbPlaces;
    /** Nombre de places restantes disponibles. */
    private Integer nbRestant;
    /** Distance du trajet en kilomètres. */
    private Integer distance;
    /** Statut actuel du covoiturage. */
    private StatutCovoiturage statut;
}
