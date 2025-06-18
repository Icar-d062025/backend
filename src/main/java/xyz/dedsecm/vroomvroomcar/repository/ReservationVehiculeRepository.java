package xyz.dedsecm.vroomvroomcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import xyz.dedsecm.vroomvroomcar.model.ReservationVehicule;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationVehiculeRepository extends JpaRepository<ReservationVehicule, Integer> {

    // Trouver les réservations par utilisateur
    List<ReservationVehicule> findByUtilisateurId(Integer utilisateurId);

    // Trouver les réservations par véhicule
    List<ReservationVehicule> findByVehiculeId(Integer vehiculeId);

    // Trouver les réservations par utilisateur et véhicule
    List<ReservationVehicule> findByUtilisateurIdAndVehiculeId(Integer utilisateurId, Integer vehiculeId);

    // Trouver les réservations qui se chevauchent avec une période donnée
    @Query("SELECT r FROM ReservationVehicule r WHERE r.vehiculeId = :vehiculeId AND " +
            "(r.dateDebut <= :dateFin AND r.dateFin >= :dateDebut)")
    List<ReservationVehicule> findConflictingReservations(
            @Param("vehiculeId") Integer vehiculeId,
            @Param("dateDebut") LocalDate dateDebut,
            @Param("dateFin") LocalDate dateFin
    );

    // Trouver les réservations actives (en cours)
    @Query("SELECT r FROM ReservationVehicule r WHERE r.dateDebut <= :today AND r.dateFin >= :today")
    List<ReservationVehicule> findActiveReservations(@Param("today") LocalDate today);

    // Trouver les réservations futures
    List<ReservationVehicule> findByDateDebutAfter(LocalDate date);

    // Trouver les réservations passées
    List<ReservationVehicule> findByDateFinBefore(LocalDate date);

    // Compter les réservations par véhicule
    Long countByVehiculeId(Integer vehiculeId);

    // Vérifier si un véhicule est disponible pour une période
    @Query("SELECT COUNT(r) = 0 FROM ReservationVehicule r WHERE r.vehiculeId = :vehiculeId AND " +
            "(r.dateDebut <= :dateFin AND r.dateFin >= :dateDebut)")
    boolean isVehiculeAvailable(
            @Param("vehiculeId") Integer vehiculeId,
            @Param("dateDebut") LocalDate dateDebut,
            @Param("dateFin") LocalDate dateFin
    );
}