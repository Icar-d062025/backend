package xyz.dedsecm.vroomvroomcar.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private final JwtEncoder encoder;

    @Value("${jwt.expiration:86400}") // 24 heures par défaut
    private long expirationInSeconds;

    public TokenService(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    public String generateToken(String username, String role) {
        Instant now = Instant.now();

        // Format correct pour les rôles dans le JWT
        String formattedRole = role.startsWith("ROLE_") ? role : "ROLE_" + role;

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(expirationInSeconds, ChronoUnit.SECONDS))
                .subject(username)
                .claim("authorities", Collections.singletonList(formattedRole))  // Changé de "scope" à "authorities" et utilisation d'une liste
                .build();

        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}