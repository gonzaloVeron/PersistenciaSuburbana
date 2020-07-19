package ar.edu.unq.epers.bichomon.backend.model.ubicacion;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.campeon.Campeon;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Dojo extends Ubicacion {

    @OneToOne(cascade= CascadeType.ALL, fetch = FetchType.EAGER)
    private Campeon campeon;

    @Override
    public Campeon getCampeon() {
        return this.campeon;
    }

    public Campeon actualizarYRetornarCampeon(Bicho bicho, LocalDate fechaInicio) {
        if(campeon == null) {
            campeon = new Campeon(bicho, fechaInicio, this);
            return campeon;
        }
        else if(campeon.getBicho().getID() != bicho.getID()) {
            Campeon campeonAnterior = campeon;
            campeonAnterior.setFechaFin(fechaInicio);
            campeon = new Campeon(bicho, fechaInicio, this);

            return campeonAnterior;
            //Retorno campeon anterior para que el service (que es quien utiliza esta funcion) actualice el campeon anterior en la BBDD.
        }
        else {
            return campeon;
        }
    }

    @Override
    public Boolean esDojo() {
        return true;
    }

    @Override
    public Bicho bichoEncontrado(Entrenador entrenador) {
        if(this.campeon != null) {
            Especie especie = campeon.getBicho().getEspecie().getEspecieRaiz();
            return especie.crearBicho(entrenador);
        }
        else {
            throw new BichoNoEncontradoException("campeon", this.getNombre());
        }
    }

}
