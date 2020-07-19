package ar.edu.unq.epers.bichomon.backend.service.bicho;

/**
 * Situación excepcional en que una especie buscada no es
 * encontrada.
 */
public class BichoNoPuedeEvolucionarException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BichoNoPuedeEvolucionarException(String bicho) {
        super("No se encuentra evolucion para : [" + bicho + "]");
    }

}
