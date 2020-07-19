package ar.edu.unq.epers.bichomon.backend.model.evento;

import com.fasterxml.jackson.annotation.JsonTypeName;

public class Abandono extends Evento {

    private String nombreEspecieBichoAbandonado;

    protected Abandono() {}

    public Abandono(String nombreEntrenador, String nombreEspecieBichoAbandonado, String nombreUbicacionOrigen) {
        super(nombreEntrenador, "Abandono", nombreUbicacionOrigen);
        this.nombreEspecieBichoAbandonado = nombreEspecieBichoAbandonado;
    }

    public String getNombreEspecieBichoAbandonado() {
        return nombreEspecieBichoAbandonado;
    }

}
