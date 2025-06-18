package xyz.dedsecm.icar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.dedsecm.icar.model.Covoiturage;

/**
 * Repository Spring Data JPA pour l'entité Covoiturage.
 * <p>
 * Permet d'effectuer des opérations CRUD et des requêtes personnalisées sur les covoiturages.
 * Hérite des méthodes standards de JpaRepository.
 * </p>
 */
public interface CovoiturageRepository extends JpaRepository<Covoiturage, Long> {
}
