package ar.edu.unq.epers.bichomon.backend.model.ubicacion;

public class UbicacionMuyLejanaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UbicacionMuyLejanaException (String origen, String destino) {
        super("No se puede llegar hasta [" + destino + "] desde [" + origen + "] por un solo camino.");
    }

}