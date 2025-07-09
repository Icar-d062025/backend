package xyz.dedsecm.icar.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

/**
 * Service pour la gestion et la génération des tokens JWT dans l'application.
 * <p>
 * Permet de générer un JWT signé contenant le nom d'utilisateur et le rôle de l'utilisateur,
 * avec une durée de validité configurable (par défaut 24h).
 * </p>
 */
@Service
public class TokenService {

    private final JwtEncoder encoder;

    /**
     * Durée d'expiration du token en secondes (défaut : 24h).
     */
    @Value("${jwt.expiration:86400}")
    private long expirationInSeconds;

    /**
     * Constructeur avec injection du JwtEncoder.
     * @param encoder l'encodeur JWT utilisé pour signer les tokens
     */
    public TokenService(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    /**
     * Génère un token JWT signé pour un utilisateur donné.
     * <p>
     * Le token contient :
     * <ul>
     *   <li>le nom d'utilisateur (subject)</li>
     *   <li>le rôle de l'utilisateur (dans la claim "authorities")</li>
     *   <li>la date d'émission et la date d'expiration</li>
     * </ul>
     * Le rôle est automatiquement préfixé par "ROLE_" si besoin.
     * </p>
     * @param username le nom d'utilisateur
     * @param role le rôle de l'utilisateur
     * @return le token JWT signé sous forme de chaîne
     */
    public String generateToken(String username, String role, Long userId) {
        Instant now = Instant.now();

        // Format correct pour les rôles dans le JWT
        String formattedRole = role.startsWith("ROLE_") ? role : "ROLE_" + role;

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(expirationInSeconds, ChronoUnit.SECONDS))
                .subject(username)
                .claim("authorities", Collections.singletonList(formattedRole))  // Changé de "scope" à "authorities" et utilisation d'une liste
                .claim("userId", userId)
                .build();

        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}