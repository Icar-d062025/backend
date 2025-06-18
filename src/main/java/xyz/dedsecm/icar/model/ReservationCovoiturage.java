package xyz.dedsecm.icar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Entité représentant une réservation de covoiturage dans le système.
 * <p>
 * Cette classe contient toutes les informations nécessaires à la gestion d'une réservation de covoiturage :
 * <ul>
 *   <li>statut de la réservation</li>
 *   <li>date de réservation</li>
 *   <li>identifiant de l'utilisateur</li>
 * </ul>
 * </p>
 */
@Entity
@Table(name = "reservations_covoiturage")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationCovoiturage {

    /** Identifiant unique de la réservation. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Statut de la réservation (ex : 1 = confirmée, 0 = en attente, etc.). */
    private Integer statut;

    /** Date à laquelle la réservation a été effectuée. */
    private LocalDate dateReservation;

    /** Identifiant de l'utilisateur ayant effectué la réservation. */
    @Column(name = "utilisateur_id")
    private Integer utilisateurId;

    // Constructeur avec paramètres essentiels
    public ReservationCovoiturage(Integer statut, Integer utilisateurId) {
        this.statut = statut;
        this.utilisateurId = utilisateurId;
        this.dateReservation = LocalDate.now();
    }

    // Constructeur avec date personnalisée
    public ReservationCovoiturage(Integer statut, LocalDate dateReservation, Integer utilisateurId) {
        this.statut = statut;
        this.dateReservation = dateReservation;
        this.utilisateurId = utilisateurId;
    }

    @Override
    public String toString() {
        return "ReservationCovoiturage{" +
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
        ReservationCovoiturage that = (ReservationCovoiturage) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}