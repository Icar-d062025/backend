package xyz.dedsecm.icar.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test unitaire pour JwtKeyGenerationException.
 * <p>
 * Vérifie le bon fonctionnement de l'exception personnalisée liée à la génération des clés JWT.
 * </p>
 */
class JwtKeyGenerationExceptionTest {

    /**
     * Teste la création de l'exception avec une cause d'origine.
     * Vérifie que le message et la cause sont correctement transmis.
     */
    @Test
    void testConstructeurAvecCause() {
        Exception cause = new Exception("Erreur initiale");
        JwtKeyGenerationException exception = new JwtKeyGenerationException(cause);
        assertEquals("Erreur lors de la génération des clés JWT", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}

