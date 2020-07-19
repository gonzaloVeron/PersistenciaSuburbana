package ar.edu.unq.epers.bichomon.backend.model.ubicacion;

/**
 ** Situación excepcional en que un bicho no pudo ser encontrado en una Ubicacion
 **/

public class BichoNoEncontradoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BichoNoEncontradoException(String buscado, String nombreUbicacion) {
        super("La ubicacion [" + nombreUbicacion + "] no tiene [" + buscado + "].");
    }

}