package ar.edu.unq.epers.bichomon.backend.model.especie;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EvolutionHandler {
    private static EvolutionHandler ourInstance = new EvolutionHandler();

    private ArrayList<Especie> especies = new ArrayList<Especie>();

    private EvolutionHandler() {
    }

    public static EvolutionHandler getInstance() {

        if(ourInstance == null){
            ourInstance = new EvolutionHandler();
        }

        return ourInstance;
    }

    public void agregarEspecie(Especie especie){
        especies.add(especie);
    }

    public void removerEspecie(Especie especie){
        especies.remove(especie);
    }

    public int cantEspecies(){
        return especies.size();
    }

    public List<String> getEspecies(){
        return especies.stream().map(especie -> especie.getNombre()).collect(Collectors.toList());
    }

    public Especie buscarSiguienteEvolucion(Especie especie){
        for(Especie esp : especies){
            if(esp.esSiguienteEvolucion(especie)){
                return esp;
            }
        }
        return null;
    }
}
