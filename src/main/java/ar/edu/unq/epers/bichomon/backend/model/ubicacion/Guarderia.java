package ar.edu.unq.epers.bichomon.backend.model.ubicacion;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Guarderia extends Ubicacion {


    @OneToMany(cascade= CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Bicho> bichosAbandonados = new HashSet<>();

    public Set<Bicho> getBichosAbandonados() {
        return this.bichosAbandonados;
    }

    @Override
    public void abandonarBicho(Bicho unBicho) {
        bichosAbandonados.add(unBicho);
    }

    //bicho.getEntrenador().nombre() != entrenador.nombre() &&
    private ArrayList<Bicho> getBichosPosiblesPara(Entrenador entrenador) {
        ArrayList<Bicho> bichosPosibles = new ArrayList<>();
        for(Bicho bicho : bichosAbandonados) {
            if(bicho.noFueAbandonadoAntesPor(entrenador)) {
                bichosPosibles.add(bicho);
            }
        }
        return bichosPosibles;
    }

    @Override
    public Boolean esGuarderia() {
        return true;
    }

    @Override
    public Bicho bichoEncontrado(Entrenador entrenador) {
        if(this.getCantidadBichosAbandonados() > 0) {
            ArrayList<Bicho> bichosPosibles = getBichosPosiblesPara(entrenador);
            return this.bichoPosibleAleatorio(bichosPosibles.size(), bichosPosibles);
        }
        else {
            throw new BichoNoEncontradoException("bichos abandonados", this.getNombre());
        }
    }

    private Bicho bichoPosibleAleatorio(int numero, ArrayList<Bicho> bichosPosibles){
        if(numero > 0){
            Bicho bicho = bichosPosibles.get((int) Math.random() * (bichosAbandonados.size() - 1));
            bichosAbandonados.remove(bicho);
            return bicho;
        }else{
            throw new BichoNoPuedeSerAdoptado();
        }
    }

    @Override
    public int getCantidadBichosAbandonados() {
        return this.bichosAbandonados.size();
    }

}
