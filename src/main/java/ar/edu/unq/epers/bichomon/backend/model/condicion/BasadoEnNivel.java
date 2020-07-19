package ar.edu.unq.epers.bichomon.backend.model.condicion;

import ar.edu.unq.epers.bichomon.backend.model.bicho.*;
import ar.edu.unq.epers.bichomon.backend.model.nivel.NivelManager;

import javax.persistence.Entity;

@Entity
public class BasadoEnNivel extends Condicion {

    private int condicionNivel;

    public BasadoEnNivel(){}

    public BasadoEnNivel (int condicionNivel){
        this.setCondicionNivel(condicionNivel);
    }

    @Override
    public Boolean cumpleConLaCondicion(Bicho bicho, NivelManager nivelManager) {
        return bicho.getEntrenador().getNivel(nivelManager) > condicionNivel;
    }

    public int getCondicionNivel(){
        return this.condicionNivel;
    }

    private void setCondicionNivel(int nivel){
        this.condicionNivel = nivel;
    }

}
