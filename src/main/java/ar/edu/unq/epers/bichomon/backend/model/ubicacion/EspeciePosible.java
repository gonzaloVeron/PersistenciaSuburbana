package ar.edu.unq.epers.bichomon.backend.model.ubicacion;

import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;

import javax.persistence.*;

@Entity
public class EspeciePosible {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne
    private Especie especie;

    @ManyToOne
    private Pueblo pueblo;

    private int probInicial;

    private int probFinal;

    public EspeciePosible() {

    }

    public EspeciePosible(Especie especie, int probInicial, int probFinal) {
        this.especie = especie;
        this.probInicial = probInicial;
        this.probFinal = probFinal;
    }

    public Especie getEspecie() {
        return especie;
    }

    public int getProbInicial() {
        return probInicial;
    }

    public int getProbFinal() {
        return probFinal;
    }


}
