package xyz.dedsecm.vroomvroomcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.dedsecm.vroomvroomcar.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}