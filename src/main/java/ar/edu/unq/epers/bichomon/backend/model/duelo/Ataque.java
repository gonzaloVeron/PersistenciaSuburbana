package ar.edu.unq.epers.bichomon.backend.model.duelo;

public class Ataque {

    private double daño;
    private int atacante;
    private int atacado;

    public Ataque(int atacado, double daño, int atacante){
        this.atacante = atacante;
        this.atacado = atacado;
        this.daño = daño;
    }

    public int atacado(){
        return this.atacado;
    }

    public int atacante(){
        return this.atacante;
    }

    public double daño() {
        return this.daño;
    }

}
