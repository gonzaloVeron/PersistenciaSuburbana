package ar.edu.unq.epers.bichomon.backend.model.nivel;

import javax.persistence.*;

@Entity
public class Nivel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Nivel", nullable = false, unique = true, length=190)
    private int nivel;

    private int cantMaxBichos;
    private int expInicial;
    private int expFinal;

    public Nivel(){}

    public Nivel(int cantMaxBichos, int expInicial, int expFinal){
        this.cantMaxBichos = cantMaxBichos;
        this.expInicial = expInicial;
        this.expFinal = expFinal;

    }

    public int getNivel(){return this.nivel;}

    public int getCapacidadMaximaDeBichos(){return this.cantMaxBichos;}

    public int getExpInicial(){return this.expInicial;}

    public int getExpFinal(){return this.expFinal;}

    public void setCapacidadMaximaDeBichos(int cantMaxBichos){ this.cantMaxBichos = cantMaxBichos;}

    public void setExpInicial(int expInicial){ this.expInicial = expInicial;}

    public void setExpFinal(int expFinal){ this.expFinal = expFinal;}

}
