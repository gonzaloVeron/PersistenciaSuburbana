package ar.edu.unq.epers.bichomon.backend.service.ubicacion;

import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Ubicacion;

/**
 * Situación excepcional en que una especie buscada no es
 * encontrada.
 */
public class UbicacionIncorrectaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UbicacionIncorrectaException(String nombreUbicacion, String tipo) {
        super("La ubicacion [" + nombreUbicacion + "] no es del tipo [" + tipo + "].");
    }

}
