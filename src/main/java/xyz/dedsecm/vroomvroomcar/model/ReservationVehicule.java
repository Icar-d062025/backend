package xyz.dedsecm.vroomvroomcar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "reservations_vehicules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationVehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate dateDebut;
    private LocalDate dateFin;
    private LocalDate dateCreation;

    @Column(name = "utilisateur_id")
    private Integer utilisateurId;

    @Column(name = "vehicule_id")
    private Integer vehiculeId;

    // Constructeur avec paramètres essentiels
    public ReservationVehicule(LocalDate dateDebut, LocalDate dateFin, Integer utilisateurId, Integer vehiculeId) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.utilisateurId = utilisateurId;
        this.vehiculeId = vehiculeId;
        this.dateCreation = LocalDate.now();
    }

    @Override
    public String toString() {
        return "ReservationVehicule{" +
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
        ReservationVehicule that = (ReservationVehicule) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
