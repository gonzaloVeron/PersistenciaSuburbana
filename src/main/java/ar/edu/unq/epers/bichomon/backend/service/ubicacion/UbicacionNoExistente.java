package ar.edu.unq.epers.bichomon.backend.service.ubicacion;

/**
 * Situación excepcional en que una especie buscada no es
 * encontrada.
 */
public class UbicacionNoExistente extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UbicacionNoExistente(String ubicacion) {
        super("No se encuentra la ubicacion [" + ubicacion + "]");
    }

}
