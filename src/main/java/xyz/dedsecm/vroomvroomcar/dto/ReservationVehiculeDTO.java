package xyz.dedsecm.vroomvroomcar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// import jakarta.validation.constraints.NotNull;
// import jakarta.validation.constraints.Future;
// import jakarta.validation.constraints.Positive;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationVehiculeDTO {

    private Integer id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateDebut;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFin;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateCreation;

    private Integer utilisateurId;

    private Integer vehiculeId;

    // Constructeur pour création d'une nouvelle réservation (sans ID)
    public ReservationVehiculeDTO(LocalDate dateDebut, LocalDate dateFin, Integer utilisateurId, Integer vehiculeId) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.utilisateurId = utilisateurId;
        this.vehiculeId = vehiculeId;
    }

    /**
     * Vérifie si la date de fin est après la date de début
     * @return true si les dates sont cohérentes
     */
    public boolean isDateRangeValid() {
        if (dateDebut == null || dateFin == null) {
            return false;
        }
        return dateFin.isAfter(dateDebut);
    }

    /**
     * Calcule la durée de la réservation en jours
     * @return le nombre de jours de réservation
     */
    public long getDureeEnJours() {
        if (dateDebut == null || dateFin == null) {
            return 0;
        }
        return java.time.temporal.ChronoUnit.DAYS.between(dateDebut, dateFin);
    }

    @Override
    public String toString() {
        return "ReservationVehiculeDTO{" +
                "id=" + id +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", dateCreation=" + dateCreation +
                ", utilisateurId=" + utilisateurId +
                ", vehiculeId=" + vehiculeId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationVehiculeDTO that = (ReservationVehiculeDTO) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}