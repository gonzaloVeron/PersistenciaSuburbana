package ar.edu.unq.epers.bichomon.backend.model.condicion;
import ar.edu.unq.epers.bichomon.backend.model.bicho.*;
import ar.edu.unq.epers.bichomon.backend.model.nivel.NivelManager;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Condicion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true, length=190)
    private int id;

    abstract public Boolean cumpleConLaCondicion(Bicho bicho, NivelManager nivelManager);

    public int getID(){
        return id;
    }
}
