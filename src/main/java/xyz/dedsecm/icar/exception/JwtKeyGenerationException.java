package xyz.dedsecm.icar.exception;

public class JwtKeyGenerationException extends RuntimeException {

    public JwtKeyGenerationException(Throwable cause) {
        super("Erreur lors de la génération des clés JWT", cause);
    }
}