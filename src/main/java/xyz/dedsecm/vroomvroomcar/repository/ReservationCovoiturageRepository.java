package xyz.dedsecm.vroomvroomcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import xyz.dedsecm.vroomvroomcar.model.ReservationCovoiturage;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationCovoiturageRepository extends JpaRepository<ReservationCovoiturage, Integer> {

    // Trouver les réservations par utilisateur
    List<ReservationCovoiturage> findByUtilisateurId(Integer utilisateurId);

    // Trouver les réservations par statut
    List<ReservationCovoiturage> findByStatut(Integer statut);

    // Trouver les réservations par utilisateur et statut
    List<ReservationCovoiturage> findByUtilisateurIdAndStatut(Integer utilisateurId, Integer statut);

    // Trouver les réservations par date
    List<ReservationCovoiturage> findByDateReservation(LocalDate dateReservation);

    // Trouver les réservations entre deux dates
    List<ReservationCovoiturage> findByDateReservationBetween(LocalDate dateDebut, LocalDate dateFin);

    // Compter les réservations par utilisateur
    Long countByUtilisateurId(Integer utilisateurId);

    // Vérifier si une réservation existe pour un utilisateur avec un statut donné
    boolean existsByUtilisateurIdAndStatut(Integer utilisateurId, Integer statut);
}
