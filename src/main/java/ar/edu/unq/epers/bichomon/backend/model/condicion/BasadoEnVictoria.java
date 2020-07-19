package ar.edu.unq.epers.bichomon.backend.model.condicion;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.nivel.NivelManager;

import javax.persistence.Entity;

@Entity
public class BasadoEnVictoria extends Condicion{

    private int condicionVictorias;

    public BasadoEnVictoria(){}

    public BasadoEnVictoria(int condicionVictorias){
        this.setCondicionVictorias(condicionVictorias);
    }

    @Override
    public Boolean cumpleConLaCondicion(Bicho bicho, NivelManager nivelManager){
        return bicho.getVictorias() > condicionVictorias;
    }

    private void setCondicionVictorias(int condicionVictorias){
        this.condicionVictorias = condicionVictorias;
    }

    public int getCondicionVictorias(){
         return condicionVictorias;
    }
}
