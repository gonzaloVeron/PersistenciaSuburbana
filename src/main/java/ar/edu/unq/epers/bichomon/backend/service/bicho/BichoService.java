package ar.edu.unq.epers.bichomon.backend.service.bicho;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.duelo.ResultadoCombate;

public interface BichoService {

    void crearBicho(Bicho bicho);

    Bicho buscar(String entrenador);

    void abandonar(String entrenador, int bicho);

    ResultadoCombate duelo(String entrenador, int bicho);

    boolean puedeEvolucionar(String nombreEntrenador, int nroBicho);

    Bicho evolucionar(String entrenador, int idBicho);

    Bicho getBicho(int idBicho);
    
}
