package xyz.dedsecm.icar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.dedsecm.icar.model.Vehicule;

public interface VehiculeRepository extends JpaRepository<Vehicule, Integer> {
}

