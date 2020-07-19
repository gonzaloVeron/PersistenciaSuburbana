package ar.edu.unq.epers.bichomon.backend.model.evento;

import com.fasterxml.jackson.annotation.JsonTypeName;

public class Captura extends Evento{

    private String nombreEspecieBicho;

    protected Captura() {}

    public Captura(String nombreEntrenador, String nombreEspecieBicho, String nombreUbicacionOrigen) {
        super(nombreEntrenador, "Captura", nombreUbicacionOrigen);
        this.nombreEspecieBicho = nombreEspecieBicho;
    }

    public String getNombreEspecieBicho() {
        return this.nombreEspecieBicho;
    }


}
