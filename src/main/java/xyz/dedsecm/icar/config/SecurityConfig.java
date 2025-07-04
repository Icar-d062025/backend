package xyz.dedsecm.icar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import xyz.dedsecm.icar.exception.JwtKeyGenerationException;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

/**
 * Configuration de sécurité pour l'application.
 * Cette classe définit les paramètres de sécurité Spring, notamment l'authentification JWT,
 * la gestion des autorisations et le chiffrement des mots de passe.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    /**
     * Configure la chaîne de filtres de sécurité HTTP.
     *
     * @param http l'objet HttpSecurity à configurer
     * @return la chaîne de filtres de sécurité configurée
     * @throws Exception si une erreur survient pendant la configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/users", "/api/users/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/users").permitAll()
                        .requestMatchers("/api/auth/login").permitAll()
                        .requestMatchers("/api/auth/register").permitAll()
                        .requestMatchers("/api/covoiturages").permitAll()
                        .requestMatchers("/api/vs").permitAll()
                        .requestMatchers("/api/vehicules").permitAll()
                        .requestMatchers("/h2-console/**").permitAll() // Si vous utilisez la console H2
                        .requestMatchers("/api/admin/**").hasAuthority("ROLE_ADMIN") // Remarque : utilisation de hasAuthority au lieu de hasRole
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())))
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)); // Pour la console H2

        return http.build();
    }

    /**
     * Configure le convertisseur d'authentification JWT pour extraire les autorités.
     *
     * @return le convertisseur d'authentification JWT configuré
     */
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthorityPrefix(""); // Pas de préfixe supplémentaire, car déjà inclus
        grantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }

    /**
     * Fournit un encodeur de mot de passe pour le chiffrement.
     *
     * @return l'encodeur de mot de passe BCrypt
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Génère une paire de clés RSA pour la signature JWT.
     *
     * @return la paire de clés RSA générée
     * @throws JwtKeyGenerationException si la génération de clé échoue
     */
    @Bean
    public KeyPair keyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            throw new JwtKeyGenerationException(e);
        }
    }

    /**
     * Configure la source JWK qui contient la paire de clés pour signer et vérifier les JWT.
     *
     * @return la source JWK configurée avec la paire de clés RSA
     */
    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        KeyPair keyPair = keyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        JWK jwk = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();

        JWKSet jwkSet = new JWKSet(jwk);
        return new ImmutableJWKSet<>(jwkSet);
    }

    /**
     * Configure le décodeur JWT qui utilise la clé publique RSA.
     *
     * @return le décodeur JWT configuré
     */
    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey((RSAPublicKey) keyPair().getPublic()).build();
    }

    /**
     * Configure l'encodeur JWT qui utilise la source JWK.
     *
     * @param jwkSource la source JWK à utiliser pour l'encodage
     * @return l'encodeur JWT configuré
     */
    @Bean
    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
        return new NimbusJwtEncoder(jwkSource);
    }
}