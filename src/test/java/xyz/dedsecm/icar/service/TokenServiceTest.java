package xyz.dedsecm.icar.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.Jwt;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Classe de test unitaire pour TokenService.
 * <p>
 * Vérifie la génération de JWT et la gestion des claims.
 * </p>
 */
class TokenServiceTest {

    private JwtEncoder encoder;
    private TokenService tokenService;

    @BeforeEach
    void setUp() {
        encoder = Mockito.mock(JwtEncoder.class);
        tokenService = new TokenService(encoder);
    }

    /**
     * Teste la génération d'un token JWT avec un rôle simple.
     */
    @Test
    void testGenerateTokenAvecRole() {
        Jwt jwt = Mockito.mock(Jwt.class);
        when(encoder.encode(any(JwtEncoderParameters.class))).thenReturn(jwt);
        when(jwt.getTokenValue()).thenReturn("fake-jwt-token");

        String token = tokenService.generateToken("user", "USER");
        assertEquals("fake-jwt-token", token);

        ArgumentCaptor<JwtEncoderParameters> captor = ArgumentCaptor.forClass(JwtEncoderParameters.class);
        verify(encoder).encode(captor.capture());
        JwtClaimsSet claims = captor.getValue().getClaims();
        assertEquals("user", claims.getSubject());
        assertTrue(((List<?>) claims.getClaim("authorities")).contains("ROLE_USER"));
        assertNotNull(claims.getIssuedAt());
        assertNotNull(claims.getExpiresAt());
    }

    /**
     * Teste la génération d'un token JWT avec un rôle déjà préfixé.
     */
    @Test
    void testGenerateTokenAvecRolePrefixe() {
        Jwt jwt = Mockito.mock(Jwt.class);
        when(encoder.encode(any(JwtEncoderParameters.class))).thenReturn(jwt);
        when(jwt.getTokenValue()).thenReturn("jwt-prefixed");

        String token = tokenService.generateToken("admin", "ROLE_ADMIN");
        assertEquals("jwt-prefixed", token);

        ArgumentCaptor<JwtEncoderParameters> captor = ArgumentCaptor.forClass(JwtEncoderParameters.class);
        verify(encoder).encode(captor.capture());
        JwtClaimsSet claims = captor.getValue().getClaims();
        assertEquals("admin", claims.getSubject());
        assertTrue(((List<?>) claims.getClaim("authorities")).contains("ROLE_ADMIN"));
    }
}

