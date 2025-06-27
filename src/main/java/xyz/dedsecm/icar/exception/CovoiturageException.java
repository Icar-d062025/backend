package xyz.dedsecm.icar.exception;

/**
 * Exception personnalisée pour les erreurs liées au covoiturage.
 */
public class CovoiturageException extends RuntimeException {

    /**
     * Construit une nouvelle exception avec un message.
     * @param message le détail de l'erreur
     */
    public CovoiturageException(String message) {
        super(message);
    }

    /**
     * Construit une nouvelle exception avec un message et la cause.
     * @param message le détail de l'erreur
     * @param cause   la cause de l'exception
     */
    public CovoiturageException(String message, Throwable cause) {
        super(message, cause);
    }
}
