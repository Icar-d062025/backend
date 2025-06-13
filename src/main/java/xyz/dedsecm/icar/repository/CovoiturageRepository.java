package xyz.dedsecm.icar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.dedsecm.icar.model.Covoiturage;

public interface CovoiturageRepository extends JpaRepository<Covoiturage, Long> {
}
