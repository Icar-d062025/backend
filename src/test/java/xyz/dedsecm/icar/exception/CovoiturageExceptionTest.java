package xyz.dedsecm.icar.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour l'exception personnalisée CovoiturageException.
 */
class CovoiturageExceptionTest {

    @Test
    void testConstructorWithMessage() {
        String message = "Erreur spécifique";
        CovoiturageException ex = new CovoiturageException(message);
        assertEquals(message, ex.getMessage());
        assertNull(ex.getCause(), "La cause doit être nulle");
    }

    @Test
    void testConstructorWithMessageAndCause() {
        String message = "Erreur avec cause";
        Throwable cause = new RuntimeException("Cause originale");
        CovoiturageException ex = new CovoiturageException(message, cause);
        assertEquals(message, ex.getMessage());
        assertSame(cause, ex.getCause());
    }
}
