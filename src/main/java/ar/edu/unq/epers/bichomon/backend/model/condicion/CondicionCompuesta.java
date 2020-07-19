package ar.edu.unq.epers.bichomon.backend.model.condicion;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.nivel.NivelManager;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
public class CondicionCompuesta extends Condicion {

    public CondicionCompuesta(){}

    @OneToMany(cascade= javax.persistence.CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Condicion> condiciones = new HashSet<Condicion>();

    public void agregarCondicion(Condicion c){
        condiciones.add(c);
    }

    public void removerCondicion(Condicion c){
        condiciones.remove(c);
    }

    public int cantidadDeCondiciones(){
        return condiciones.size();
    }

    public Boolean cumpleConLaCondicion(Bicho bicho, NivelManager nivelManager){
        Boolean cumple = true;
        for(Condicion cond : condiciones){
            cumple = cumple && cond.cumpleConLaCondicion(bicho, nivelManager);
        }
        return cumple;
    }

}
