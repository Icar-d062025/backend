package xyz.dedsecm.icar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import xyz.dedsecm.icar.dto.UserDTO;
import xyz.dedsecm.icar.model.User;
import xyz.dedsecm.icar.repository.UserRepository;
import xyz.dedsecm.icar.service.TokenService;

/**
 * Contrôleur REST pour la gestion de l'authentification.
 * Ce contrôleur gère les opérations liées à l'authentification des utilisateurs
 * comme la connexion et la génération de tokens JWT.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    /**
     * Repository pour accéder aux données des utilisateurs.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Encodeur de mot de passe pour vérifier les mots de passe.
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Service de gestion des tokens JWT.
     */
    @Autowired
    private TokenService tokenService;

    /**
     * Authentifie un utilisateur et génère un token JWT.
     *
     * @param loginDto objet contenant les identifiants de connexion (nom d'utilisateur et mot de passe)
     * @return un token JWT si l'authentification réussit, sinon un message d'erreur
     */
    @PostMapping("/login")
    public String login(@RequestBody UserDTO loginDto) {
        User user = userRepository.findAll().stream()
                .filter(u -> u.getUsername().equals(loginDto.getUsername()))
                .findFirst().orElse(null);

        if (user != null && passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            return tokenService.generateToken(user.getUsername(), user.getRole().name());
        }

        return "Invalid credentials";
    }
}