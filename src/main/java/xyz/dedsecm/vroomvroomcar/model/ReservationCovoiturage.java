package xyz.dedsecm.vroomvroomcar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "reservations_covoiturage")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationCovoiturage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer statut;

    private LocalDate dateReservation;

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