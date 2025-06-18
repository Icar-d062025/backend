package xyz.dedsecm.icar.model;

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

    /**
     * Retourne une représentation textuelle de la réservation de véhicule.
     * @return une chaîne contenant les valeurs de tous les champs principaux
     */
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

    /**
     * Vérifie l'égalité de deux objets ReservationVehicule.
     * Deux réservations sont considérées comme égales si tous leurs champs principaux sont identiques :
     * <ul>
     *   <li>id</li>
     *   <li>dateDebut</li>
     *   <li>dateFin</li>
     *   <li>dateCreation</li>
     *   <li>utilisateurId</li>
     *   <li>vehiculeId</li>
     * </ul>
     * @param o l'objet à comparer
     * @return true si tous les champs sont identiques, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationVehicule that = (ReservationVehicule) o;
        return java.util.Objects.equals(id, that.id)
                && java.util.Objects.equals(dateDebut, that.dateDebut)
                && java.util.Objects.equals(dateFin, that.dateFin)
                && java.util.Objects.equals(dateCreation, that.dateCreation)
                && java.util.Objects.equals(utilisateurId, that.utilisateurId)
                && java.util.Objects.equals(vehiculeId, that.vehiculeId);
    }

    /**
     * Calcule le hashCode de la réservation à partir de tous les champs principaux.
     * @return le hashCode basé sur id, dateDebut, dateFin, dateCreation, utilisateurId et vehiculeId
     */
    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, dateDebut, dateFin, dateCreation, utilisateurId, vehiculeId);
    }
}
