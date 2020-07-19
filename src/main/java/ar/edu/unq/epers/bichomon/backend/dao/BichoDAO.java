package ar.edu.unq.epers.bichomon.backend.dao;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;

public interface BichoDAO {

    void guardar(Bicho bicho);

    void actualizar(Bicho bicho);

    Bicho recuperar(int idBicho);

    void abandonarBicho(Bicho bicho);

    void adoptarBicho(Bicho bicho, Entrenador entrenador);
}
