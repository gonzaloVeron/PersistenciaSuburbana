package ar.edu.unq.epers.bichomon.backend.model.ubicacion;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.campeon.Campeon;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.nivel.NivelManager;
import ar.edu.unq.epers.bichomon.backend.service.nivel.NivelService;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Ubicacion {

    @Id
    @Column(name = "Nombre", nullable = false, unique = true, length = 190)
    private String Nombre;

    private int poblacion = 0;

    public String getNombre() {
        return Nombre;
    }

    public void sumarPoblacion() {
        poblacion += 1;
    }

    public void restarPoblacion() {
        poblacion -= 1;
    }

    public int getPoblacion() {
        return poblacion;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public Bicho buscar(Entrenador entrenador, NivelManager nivelManager, int experiencia) {
        if (busquedaExitosa(entrenador, nivelManager)) {
            Bicho bichoEncontrado = bichoEncontrado(entrenador);
            entrenador.agregarBicho(bichoEncontrado);
            entrenador.agregarExperiencia(experiencia);
            return bichoEncontrado;
        } else {
            return null;
        }
    }

    private Boolean busquedaExitosa(Entrenador entrenador, NivelManager nivelManager) {
        return (entrenador.factorTiempo() * entrenador.factorNivel(nivelManager) * getFactorPoblacion() * (float) (Math.random() * 1) > 0.5);
    }

    public int getFactorPoblacion() {
        return 1;
    }

    abstract Bicho bichoEncontrado(Entrenador entrenador);

    public Boolean esDojo() {
        return false;
    }

    public Boolean esGuarderia() {
        return false;
    }

    public Boolean esPueblo() {
        return false;
    }

    public void abandonarBicho(Bicho bicho) {
    }

    public int getCantidadBichosAbandonados() {
        return 0;
    }

    public Campeon getCampeon() {
        return null;
    }

    public Campeon actualizarYRetornarCampeon(Bicho bicho, LocalDate fecha){
        return null;
    }
}
