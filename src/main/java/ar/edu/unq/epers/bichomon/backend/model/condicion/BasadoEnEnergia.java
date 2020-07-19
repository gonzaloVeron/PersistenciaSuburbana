package ar.edu.unq.epers.bichomon.backend.model.condicion;
import ar.edu.unq.epers.bichomon.backend.model.bicho.*;
import ar.edu.unq.epers.bichomon.backend.model.nivel.NivelManager;

import javax.persistence.Entity;

@Entity
public class BasadoEnEnergia extends Condicion {

    private int condicionEnergia;

    public BasadoEnEnergia(){}

    public BasadoEnEnergia(int energia) {
        this.setCondicionEnergia(energia);
    }

    public int getEnergia() {
        return this.condicionEnergia;
    }

    private void setCondicionEnergia(int energia) {
        this.condicionEnergia = energia;
    }

    @Override
    public Boolean cumpleConLaCondicion(Bicho bicho, NivelManager nivelManager) {
        return bicho.getEnergia() > this.condicionEnergia;
    }

}