package ar.edu.unq.epers.bichomon.backend.model.duelo;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;

import javax.persistence.Entity;
import java.util.ArrayList;


public class ResultadoCombate {
    private ArrayList<Ataque> ataques;
    private Bicho ganador;

    public ResultadoCombate(ArrayList<Ataque> ataques, Bicho ganador){
        this.ganador = ganador;
        this.ataques = ataques;
    }

    public void addAtaque(Ataque ataque){
        this.ataques.add(ataque);
    }

    public Bicho getGanador(){
        return ganador;
    }

    public float ataqueRecibido(int bicho){
        float daño = 0;
        for(Ataque ataque:this.ataques){
            if(bicho == ataque.atacado()){
                daño += ataque.daño();
            }
        }
        return daño;
    }

    public ArrayList<Ataque> getAtaques(){
        return ataques;
    }

    public int cantidadDeAtaques(){
        return ataques.size();
    }
}
