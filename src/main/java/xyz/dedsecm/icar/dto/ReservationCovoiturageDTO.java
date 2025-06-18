package xyz.dedsecm.icar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

/**
 * DTO (Data Transfer Object) pour la réservation d'un covoiturage.
 * <p>
 * Permet de transférer les informations relatives à une réservation de covoiturage entre les différentes couches de l'application.
 * Contient :
 * <ul>
 *   <li>l'identifiant de la réservation</li>
 *   <li>le statut de la réservation</li>
 *   <li>la date de réservation</li>
 *   <li>l'identifiant de l'utilisateur</li>
 * </ul>
 * </p>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationCovoiturageDTO {

    /** Identifiant unique de la réservation. */
    private Integer id;

    /** Statut de la réservation (ex : 1 = confirmée, 0 = en attente, etc.). */
    private Integer statut;

    /** Date à laquelle la réservation a été effectuée. */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateReservation;

    /** Identifiant de l'utilisateur ayant effectué la réservation. */
    private Integer utilisateurId;

    // Constructeur pour création d'une nouvelle réservation (sans ID)
    /**
     * Constructeur pour création d'une nouvelle réservation (sans ID).
     * @param statut Statut de la réservation
     * @param utilisateurId Identifiant de l'utilisateur
     */
    public ReservationCovoiturageDTO(Integer statut, Integer utilisateurId) {
        this.statut = statut;
        this.utilisateurId = utilisateurId;
        this.dateReservation = LocalDate.now();
    }

    /**
     * Constructeur avec date personnalisée.
     * @param statut Statut de la réservation
     * @param dateReservation Date de la réservation
     * @param utilisateurId Identifiant de l'utilisateur
     */
    public ReservationCovoiturageDTO(Integer statut, LocalDate dateReservation, Integer utilisateurId) {
        this.statut = statut;
        this.dateReservation = dateReservation;
        this.utilisateurId = utilisateurId;
    }

    /**
     * Vérifie si la réservation est confirmée.
     * @return true si le statut indique une réservation confirmée (statut == 1)
     */
    public boolean isConfirmee() {
        return statut != null && statut == 1;
    }

    /**
     * Vérifie si la réservation est en attente
     * @return true si le statut indique une réservation en attente
     */
    public boolean isEnAttente() {
        return statut != null && statut == 0;
    }

    /**
     * Vérifie si la réservation est annulée
     * @return true si le statut indique une réservation annulée
     */
    public boolean isAnnulee() {
        return statut != null && statut == 2;
    }

    /**
     * Retourne le libellé du statut de la réservation.
     * <ul>
     *   <li>0 : En attente</li>
     *   <li>1 : Confirmée</li>
     *   <li>2 : Annulée</li>
     *   <li>3 : Terminée</li>
     *   <li>autre ou null : Inconnu</li>
     * </ul>
     * @return le libellé correspondant au statut, ou "Inconnu" si le statut n'est pas reconnu ou null
     */
    public String getStatutLibelle() {
        if (statut == null) {
            return "Inconnu";
        }
        switch (statut) {
            case 0:
                return "En attente";
            case 1:
                return "Confirmée";
            case 2:
                return "Annulée";
            case 3:
                return "Terminée";
            default:
                return "Inconnu";
        }
    }

    /**
     * Vérifie si la réservation est récente (moins de 7 jours)
     * @return true si la réservation a été faite il y a moins de 7 jours
     */
    public boolean isRecente() {
        if (dateReservation == null) {
            return false;
        }
        return dateReservation.isAfter(LocalDate.now().minusDays(7));
    }

    @Override
    public String toString() {
        return "ReservationCovoituragetDTO{" +
                "id=" + id +
                ", statut=" + statut +
                ", dateReservation=" + dateReservation +
                ", utilisateurId=" + utilisateurId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationCovoiturageDTO that = (ReservationCovoiturageDTO) o;
        return java.util.Objects.equals(id, that.id)
                && java.util.Objects.equals(statut, that.statut)
                && java.util.Objects.equals(dateReservation, that.dateReservation)
                && java.util.Objects.equals(utilisateurId, that.utilisateurId);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, statut, dateReservation, utilisateurId);
    }
}