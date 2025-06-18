package xyz.dedsecm.icar.exception;

/**
 * Exception personnalisée pour signaler une erreur lors de la génération des clés JWT.
 * <p>
 * Cette exception est levée lorsqu'une erreur survient pendant la génération des clés nécessaires à la gestion des tokens JWT.
 * Elle permet de centraliser la gestion de ce type d'erreur dans l'application.
 * </p>
 */
public class JwtKeyGenerationException extends RuntimeException {

    /**
     * Construit une nouvelle exception avec la cause d'origine.
     * @param cause l'exception à l'origine de l'erreur de génération des clés JWT
     */
    public JwtKeyGenerationException(Throwable cause) {
        super("Erreur lors de la génération des clés JWT", cause);
    }
}