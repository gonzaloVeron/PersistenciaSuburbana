package ar.edu.unq.epers.bichomon.backend.model.duelo;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Dojo;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Ubicacion;

import java.util.ArrayList;

public class Duelo {

    private Bicho campeon;
    private Bicho bicho;
    private ArrayList<Ataque> ataques;

    public Duelo(){

    }
    //Esta ubicacion tiene que ser un dojo
    public Duelo(Ubicacion dojo, Bicho bicho){
        campeon = dojo.getCampeon().getBicho();
        this.bicho = bicho;
        ataques = new ArrayList<Ataque>();
    }

    public ResultadoCombate pelear(int experiencia){
        /** Deben ser 10 turnos si o si */
        int turnos = 1;
        while (!this.debeTerminarLaPelea() && !this.pasoLos10Turnos(turnos)){
            ataques.add(bicho.atacar(campeon));
            if(!this.debeTerminarLaPelea()) {
                return this.terminarPelea(experiencia);
            }else{
                ataques.add(campeon.atacar(bicho));
            }
        }
        /** Si el duelo llega hasta aca el campeon gano y no debe ser descoronado */
        return this.terminarPelea(experiencia);
    }

    public boolean debeTerminarLaPelea(){
        return this.dañoCausadoA(campeon) > campeon.getEnergia() || this.dañoCausadoA(bicho) > bicho.getEnergia();
    }

    public boolean pasoLos10Turnos(int n){
        return n > 10;
    }

    public ResultadoCombate terminarPelea(int experiencia){
        campeon.incrementarEnergia();
        bicho.incrementarEnergia();
        //IncrementarExperienciaAEntrenadores
        campeon.getEntrenador().agregarExperiencia(experiencia);
        bicho.getEntrenador().agregarExperiencia(experiencia);
        //CoronarCampeon
        campeon = this.ganador();
        campeon.incrementarVictorias();
        return new ResultadoCombate(ataques, campeon);
    }

    public Bicho ganador(){
        if(this.dañoCausadoA(campeon) > campeon.getEnergia()){
            return bicho;
        }else{
            return campeon;
        }
    }

    public int dañoCausadoA(Bicho bicho) {
        int dañoCausado = 0;
        for (Ataque a : ataques) {
            if (a.atacado() == bicho.getID()) {
                dañoCausado += a.daño();
            }
        }
        return dañoCausado;
    }

    /** Para testear */
    public void setAtaques(ArrayList<Ataque> ataques){
        this.ataques = ataques;
    }

    public Bicho getCampeon(){
        return campeon;
    }

    public Bicho getRetador(){
        return bicho;
    }
}
