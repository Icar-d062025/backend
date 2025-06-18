package xyz.dedsecm.icar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.dedsecm.icar.model.User;

import java.util.Optional;

/**
 * Repository Spring Data JPA pour l'entité User.
 * <p>
 * Permet d'effectuer des opérations CRUD et des requêtes personnalisées sur les utilisateurs.
 * Hérite des méthodes standards de JpaRepository.
 * </p>
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Recherche un utilisateur par son adresse email.
     * @param email l'adresse email à rechercher
     * @return un Optional contenant l'utilisateur s'il existe
     */
    Optional<User> findByEmail(String email);

    /**
     * Recherche un utilisateur par son nom d'utilisateur.
     * @param username le nom d'utilisateur à rechercher
     * @return un Optional contenant l'utilisateur s'il existe
     */
    Optional<User> findByUsername(String username);

    /**
     * Vérifie l'existence d'un utilisateur par son adresse email.
     * @param email l'adresse email à vérifier
     * @return true si un utilisateur existe avec cet email
     */
    boolean existsByEmail(String email);

    /**
     * Vérifie l'existence d'un utilisateur par son nom d'utilisateur.
     * @param username le nom d'utilisateur à vérifier
     * @return true si un utilisateur existe avec ce nom d'utilisateur
     */
    boolean existsByUsername(String username);
}