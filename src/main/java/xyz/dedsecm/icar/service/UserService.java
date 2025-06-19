package xyz.dedsecm.icar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.dedsecm.icar.dto.UserDTO;
import xyz.dedsecm.icar.mapper.UserMapper;
import xyz.dedsecm.icar.model.User;
import xyz.dedsecm.icar.repository.UserRepository;

import java.time.LocalTime;
import java.util.List;

/**
 * Service pour la gestion des utilisateurs dans l'application.
 * <p>
 * Permet de gérer la création, la récupération et la recherche d'utilisateurs,
 * ainsi que l'encodage sécurisé des mots de passe.
 * </p>
 */
@Service
public class UserService {

    /** Repository pour l'accès aux données des utilisateurs. */
    @Autowired
    UserRepository userRepository;

    /** Service d'encodage des mots de passe. */
    @Autowired
    PasswordEncoder passwordEncoder;

    private static final String USER_NOT_FOUND_ID = "Utilisateur non trouvé avec l'id: ";

    /**
     * Récupère la liste de tous les utilisateurs sous forme de DTO.
     * @return la liste des utilisateurs
     */
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::toDTO)
                .toList();
    }

    /**
     * Crée un nouvel utilisateur à partir d'un DTO, encode le mot de passe et sauvegarde l'utilisateur.
     * @param dto le DTO de l'utilisateur à créer
     * @return le DTO de l'utilisateur créé
     */
    public UserDTO createUser(UserDTO dto) {
        User user = UserMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        User saved = userRepository.save(user);
        return UserMapper.toDTO(saved);
    }

    /**
     * Recherche un utilisateur par son identifiant.
     * @param id l'identifiant de l'utilisateur
     * @return le DTO de l'utilisateur trouvé
     * @throws RuntimeException si l'utilisateur n'est pas trouvé
     */
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::toDTO)
                .orElseThrow(() -> new RuntimeException(USER_NOT_FOUND_ID + id));
    }

    /**
     * Recherche un utilisateur par son adresse email.
     * @param email l'adresse email de l'utilisateur
     * @return le DTO de l'utilisateur trouvé
     * @throws RuntimeException si l'utilisateur n'est pas trouvé
     */
    public UserDTO getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'email: " + email));
    }

    /**
     * Recherche un utilisateur par son nom d'utilisateur.
     * @param username le nom d'utilisateur de l'utilisateur
     * @return le DTO de l'utilisateur trouvé
     * @throws RuntimeException si l'utilisateur n'est pas trouvé
     */
    public UserDTO getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(UserMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec le username: " + username));
    }

    /**
     * Met à jour les informations d'un utilisateur.
     * @param id l'identifiant de l'utilisateur à mettre à jour
     * @param userDTO le DTO contenant les nouvelles informations de l'utilisateur
     * @return le DTO de l'utilisateur mis à jour
     * @throws RuntimeException si l'utilisateur n'est pas trouvé
     */
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setEmail(userDTO.getEmail());
                    user.setNom(userDTO.getNom());
                    user.setPrenom(userDTO.getPrenom());
                    user.setUsername(userDTO.getUsername());
                    user.setAdresse(userDTO.getAdresse());
                    user.setRole(userDTO.getRole());
                    user.setBanni(userDTO.getBanni());
                    user.setRaisonBanni(userDTO.getRaisonBanni());
                    user.setDureeBanni(userDTO.getDureeBanni());
                    user.setVehiculePerso(userDTO.getVehiculePerso());
                    user.setVehiculeId(userDTO.getVehiculeId());

                    if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
                        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
                    }

                    return UserMapper.toDTO(userRepository.save(user));
                })
                .orElseThrow(() -> new RuntimeException(USER_NOT_FOUND_ID + id));
    }

    /**
     * Supprime un utilisateur par son identifiant.
     * @param id l'identifiant de l'utilisateur à supprimer
     */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * Banni un utilisateur pour une raison et une durée spécifiées.
     * @param id l'identifiant de l'utilisateur à bannir
     * @param raison la raison du bannissement
     * @param duree la durée du bannissement
     * @return le DTO de l'utilisateur banni
     * @throws RuntimeException si l'utilisateur n'est pas trouvé
     */
    public UserDTO banUser(Long id, String raison, LocalTime duree) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setBanni(true);
                    user.setRaisonBanni(raison);
                    user.setDureeBanni(duree);
                    return UserMapper.toDTO(userRepository.save(user));
                })
                .orElseThrow(() -> new RuntimeException(USER_NOT_FOUND_ID + id));
    }

    /**
     * Lève le bannissement d'un utilisateur.
     * @param id l'identifiant de l'utilisateur dont le bannissement doit être levé
     * @return le DTO de l'utilisateur débanni
     * @throws RuntimeException si l'utilisateur n'est pas trouvé
     */
    public UserDTO unbanUser(Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setBanni(false);
                    user.setRaisonBanni(null);
                    user.setDureeBanni(null);
                    return UserMapper.toDTO(userRepository.save(user));
                })
                .orElseThrow(() -> new RuntimeException(USER_NOT_FOUND_ID + id));
    }

    /**
     * Assigne un véhicule à un utilisateur.
     * @param userId l'identifiant de l'utilisateur
     * @param vehicleId l'identifiant du véhicule à assigner
     * @return le DTO de l'utilisateur avec le véhicule assigné
     * @throws RuntimeException si l'utilisateur n'est pas trouvé
     */
    public UserDTO assignVehicle(Long userId, Long vehicleId) {
        return userRepository.findById(userId)
                .map(user -> {
                    user.setVehiculePerso(true);
                    user.setVehiculeId(vehicleId);
                    return UserMapper.toDTO(userRepository.save(user));
                })
                .orElseThrow(() -> new RuntimeException(USER_NOT_FOUND_ID + userId));
    }

    /**
     * Vérifie si un utilisateur existe déjà avec l'adresse email fournie.
     * @param email l'adresse email à vérifier
     * @return true si un utilisateur existe avec cette email, false sinon
     */
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    /**
     * Vérifie si un utilisateur existe déjà avec le nom d'utilisateur fourni.
     * @param username le nom d'utilisateur à vérifier
     * @return true si un utilisateur existe avec ce nom d'utilisateur, false sinon
     */
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}