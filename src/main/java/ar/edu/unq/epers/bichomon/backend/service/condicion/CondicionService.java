package ar.edu.unq.epers.bichomon.backend.service.condicion;

import ar.edu.unq.epers.bichomon.backend.model.condicion.Condicion;

public interface CondicionService {

    void crearCondicion(Condicion cond);

    Condicion getCondicion(int idCond);

}
