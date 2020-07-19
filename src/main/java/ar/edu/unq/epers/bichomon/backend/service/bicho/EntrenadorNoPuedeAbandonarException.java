package ar.edu.unq.epers.bichomon.backend.service.bicho;

/**
 * Situación excepcional en que un entrenador no puede abandonar un bicho
 */

public class EntrenadorNoPuedeAbandonarException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EntrenadorNoPuedeAbandonarException(String nombreEntrenador) {
        super("El entrenador [" + nombreEntrenador + "] no puede abandonar el bicho." );
    }

}
