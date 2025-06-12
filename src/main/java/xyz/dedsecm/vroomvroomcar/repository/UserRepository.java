package xyz.dedsecm.vroomvroomcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.dedsecm.vroomvroomcar.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
