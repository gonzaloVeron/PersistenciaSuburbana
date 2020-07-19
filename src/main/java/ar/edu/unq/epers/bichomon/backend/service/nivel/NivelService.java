package ar.edu.unq.epers.bichomon.backend.service.nivel;

import ar.edu.unq.epers.bichomon.backend.model.nivel.Nivel;
import ar.edu.unq.epers.bichomon.backend.model.nivel.NivelManager;

import java.util.List;

public interface NivelService {

    Nivel getNivel(int idNivel);

    void crearNivel(Nivel nivel);

    void actualizar(Nivel nivel);

    List<Nivel> recuperarTodos();

    NivelManager getNivelManager();

}
