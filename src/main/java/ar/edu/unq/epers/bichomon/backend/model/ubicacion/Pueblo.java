package ar.edu.unq.epers.bichomon.backend.model.ubicacion;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Pueblo extends Ubicacion {

    @Column
    @OneToMany(mappedBy="pueblo", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<EspeciePosible> especiesQueHabitan = new HashSet<>();

    private int siguienteProbabilidadInicial = 0;

    private int dividendoFactorPoblacion = 100;

    @Override
    public int getFactorPoblacion() {
        if(super.getPoblacion() > 0) {
            return (dividendoFactorPoblacion / super.getPoblacion());
        }
        else {
            return 0;
        }
    }

    public void setDividendoFactorPoblacion(int dividendoFactorPoblacion) {
        this.dividendoFactorPoblacion = dividendoFactorPoblacion;
    }



    //No se puede agregar una especie con probabilidad 0
    public void agregarEspecie(Especie especie, int probabilidad) {
        this.especiesQueHabitan.add(new EspeciePosible(especie, siguienteProbabilidadInicial, siguienteProbabilidadInicial + probabilidad - 1));
        siguienteProbabilidadInicial += probabilidad;
    }

    // La especie debe existir en especiesQueHabitan
    public void eliminarEspecie(Especie especie) {
        EspeciePosible especieAEliminar = null;
        for(EspeciePosible esp : especiesQueHabitan) {
            if(esp.getEspecie() == especie) {
                especieAEliminar = esp;
            }
        }
        especiesQueHabitan.remove(especieAEliminar);
    }

    public Set<EspeciePosible> getEspeciesQueHabitan() {
        return especiesQueHabitan;
    }

    @Override
    public Boolean esPueblo() {
        return true;
    }

    @Override
    public Bicho bichoEncontrado(Entrenador entrenador) {
        if(this.especiesQueHabitan.size() > 0) {
            int resultado = (int) (Math.random() * siguienteProbabilidadInicial);
            Especie especieResultado = null;
            for (EspeciePosible especie : especiesQueHabitan) {
                if (resultado > especie.getProbInicial() && resultado < especie.getProbFinal()) {
                    especieResultado = especie.getEspecie();
                }
            }
            return especieResultado.crearBicho(entrenador);
        }
        else {
            throw new BichoNoEncontradoException("especies que la habiten", this.getNombre());
        }

    }

}
