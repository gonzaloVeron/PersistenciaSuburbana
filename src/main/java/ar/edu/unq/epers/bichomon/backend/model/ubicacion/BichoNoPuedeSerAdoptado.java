package ar.edu.unq.epers.bichomon.backend.model.ubicacion;

/**
 ** Situación excepcional en que un bicho no pudo ser encontrado en una Ubicacion
 **/

public class BichoNoPuedeSerAdoptado extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BichoNoPuedeSerAdoptado() {
        super("No puede adoptarse un bicho que fue abandonado por el mismo entrenador.");
    }

}

