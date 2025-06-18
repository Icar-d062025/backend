package xyz.dedsecm.icar.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SecurityConfigTest {

    private final SecurityConfig securityConfig = new SecurityConfig();

    /**
     * Vérifie que la méthode jwkSource() retourne bien une instance d'ImmutableJWKSet non nulle.
     */
    @Test
    void jwkSource_shouldReturnImmutableJWKSet() {
        JWKSource<SecurityContext> jwkSource = securityConfig.jwkSource();
        assertNotNull(jwkSource);
        assertTrue(jwkSource instanceof ImmutableJWKSet);
    }

    /**
     * Vérifie que la méthode jwtDecoder() retourne bien une instance de NimbusJwtDecoder non nulle.
     */
    @Test
    void jwtDecoder_shouldReturnNimbusJwtDecoder() {
        JwtDecoder decoder = securityConfig.jwtDecoder();
        assertNotNull(decoder);
        assertTrue(decoder instanceof NimbusJwtDecoder);
    }

    /**
     * Vérifie que la méthode jwtEncoder() retourne bien une instance de NimbusJwtEncoder non nulle.
     */
    @Test
    void jwtEncoder_shouldReturnNimbusJwtEncoder() {
        JWKSource<SecurityContext> jwkSource = securityConfig.jwkSource();
        JwtEncoder encoder = securityConfig.jwtEncoder(jwkSource);
        assertNotNull(encoder);
        assertTrue(encoder instanceof NimbusJwtEncoder);
    }
}
