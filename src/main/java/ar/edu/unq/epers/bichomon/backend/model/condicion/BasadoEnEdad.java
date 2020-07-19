package ar.edu.unq.epers.bichomon.backend.model.condicion;
import ar.edu.unq.epers.bichomon.backend.model.bicho.*;
import ar.edu.unq.epers.bichomon.backend.model.nivel.NivelManager;

import javax.persistence.Entity;
import javax.persistence.ExcludeSuperclassListeners;
import java.time.LocalDate;

@Entity
public class BasadoEnEdad extends Condicion{

    private LocalDate condicionFecha;

    public BasadoEnEdad(){}

    public BasadoEnEdad(LocalDate condicionFecha){
        this.setCondicionEdad(condicionFecha);
    }

    @Override
    public Boolean cumpleConLaCondicion(Bicho bicho, NivelManager nivelManager){
        return bicho.getFechaDeCaptura().isAfter(condicionFecha);
    }

    private void setCondicionEdad(LocalDate condicionFecha){
        this.condicionFecha = condicionFecha;
    }

    public LocalDate getCondicionFecha(){
        return this.condicionFecha;
    }

}
