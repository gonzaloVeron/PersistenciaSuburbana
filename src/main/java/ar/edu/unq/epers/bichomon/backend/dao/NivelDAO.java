package ar.edu.unq.epers.bichomon.backend.dao;

import ar.edu.unq.epers.bichomon.backend.model.nivel.Nivel;

import java.util.List;

public interface NivelDAO {

    void guardar(Nivel bicho);

    void actualizar(Nivel bicho);

    Nivel recuperar(int idBicho);

    List<Nivel> recuperarTodos();
}

