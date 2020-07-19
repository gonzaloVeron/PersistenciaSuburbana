package ar.edu.unq.epers.bichomon.backend.dao;

import ar.edu.unq.epers.bichomon.backend.model.condicion.Condicion;

public interface CondicionDAO {

    void guardar(Condicion bicho);

    void actualizar(Condicion bicho);

    Condicion recuperar(int idBicho);

}
