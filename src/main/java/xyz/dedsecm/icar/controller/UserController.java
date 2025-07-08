package xyz.dedsecm.icar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import xyz.dedsecm.icar.dto.UserDTO;
import xyz.dedsecm.icar.service.UserService;

import java.time.LocalTime;
import java.util.List;

/**
 * Contrôleur REST pour la gestion des utilisateurs.
 * Ce contrôleur traite les requêtes HTTP relatives aux opérations CRUD sur les utilisateurs,
 * ainsi que d'autres fonctionnalités spécifiques comme le bannissement et l'attribution de véhicules.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    /**
     * Service de gestion des utilisateurs.
     */
    @Autowired
    private UserService userService;

    /**
     * Récupère la liste de tous les utilisateurs.
     *
     * @return une liste de DTOs représentant les utilisateurs
     */
    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.getAllUsers();
    }

    /**
     * Récupère un utilisateur par son identifiant.
     *
     * @param id l'identifiant de l'utilisateur à récupérer
     * @return une réponse HTTP contenant le DTO de l'utilisateur
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    /**
     * Récupère un utilisateur par son adresse email.
     *
     * @param email l'adresse email de l'utilisateur à récupérer
     * @return une réponse HTTP contenant le DTO de l'utilisateur
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    /**
     * Récupère un utilisateur par son nom d'utilisateur.
     *
     * @param username le nom d'utilisateur de l'utilisateur à récupérer
     * @return une réponse HTTP contenant le DTO de l'utilisateur
     */
    @GetMapping("/username/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    /**
     * Crée un nouvel utilisateur.
     *
     * @param userDTO le DTO contenant les informations de l'utilisateur à créer
     * @return le DTO de l'utilisateur créé avec son identifiant
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    /**
     * Met à jour les informations d'un utilisateur existant.
     *
     * @param id l'identifiant de l'utilisateur à mettre à jour
     * @param userDTO le DTO contenant les nouvelles informations de l'utilisateur
     * @return une réponse HTTP contenant le DTO de l'utilisateur mis à jour
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or @userSecurity.isOwner(authentication, #id)")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateUser(id, userDTO));
    }

    /**
     * Supprime un utilisateur.
     *
     * @param id l'identifiant de l'utilisateur à supprimer
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or @userSecurity.isOwner(authentication, #id)")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    /**
     * Bannit un utilisateur pour une durée déterminée.
     *
     * @param id l'identifiant de l'utilisateur à bannir
     * @param raison la raison du bannissement
     * @param duree la durée du bannissement
     * @return une réponse HTTP contenant le DTO de l'utilisateur banni
     */
    @PostMapping("/{id}/ban")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<UserDTO> banUser(
            @PathVariable Long id,
            @RequestParam String raison,
            @RequestParam LocalTime duree) {
        return ResponseEntity.ok(userService.banUser(id, raison, duree));
    }

    /**
     * Annule le bannissement d'un utilisateur.
     *
     * @param id l'identifiant de l'utilisateur dont le bannissement doit être levé
     * @return une réponse HTTP contenant le DTO de l'utilisateur débanni
     */
    @PostMapping("/{id}/unban")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<UserDTO> unbanUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.unbanUser(id));
    }

    /**
     * Attribue un véhicule à un utilisateur.
     *
     * @param userId l'identifiant de l'utilisateur
     * @param vehicleId l'identifiant du véhicule à attribuer
     * @return une réponse HTTP contenant le DTO de l'utilisateur avec le véhicule attribué
     */
    @PostMapping("/{userId}/assign-vehicle/{vehicleId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or @userSecurity.isOwner(authentication, #userId)")
    public ResponseEntity<UserDTO> assignVehicle(
            @PathVariable Long userId,
            @PathVariable Long vehicleId) {
        return ResponseEntity.ok(userService.assignVehicle(userId, vehicleId));
    }

    /**
     * Vérifie si une adresse email existe déjà dans le système.
     *
     * @param email l'adresse email à vérifier
     * @return une réponse HTTP contenant un booléen indiquant si l'email existe
     */
    @GetMapping("/check/email")
    public ResponseEntity<Boolean> checkEmailExists(@RequestParam String email) {
        return ResponseEntity.ok(userService.existsByEmail(email));
    }

    /**
     * Vérifie si un nom d'utilisateur existe déjà dans le système.
     *
     * @param username le nom d'utilisateur à vérifier
     * @return une réponse HTTP contenant un booléen indiquant si le nom d'utilisateur existe
     */
    @GetMapping("/check/username")
    public ResponseEntity<Boolean> checkUsernameExists(@RequestParam String username) {
        return ResponseEntity.ok(userService.existsByUsername(username));
    }
}