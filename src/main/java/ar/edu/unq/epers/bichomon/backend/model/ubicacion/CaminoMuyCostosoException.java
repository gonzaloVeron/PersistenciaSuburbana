package ar.edu.unq.epers.bichomon.backend.model.ubicacion;

public class CaminoMuyCostosoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CaminoMuyCostosoException(String entrenador, String destino) {
        super("El entrenador [" + entrenador + "] no tiene suficientes monedas para llegar a [" + destino + "].");
    }

}