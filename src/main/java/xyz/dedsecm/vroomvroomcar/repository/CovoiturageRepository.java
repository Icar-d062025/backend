package xyz.dedsecm.vroomvroomcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.dedsecm.vroomvroomcar.model.Covoiturage;

public interface CovoiturageRepository extends JpaRepository<Covoiturage, Long> {
}
