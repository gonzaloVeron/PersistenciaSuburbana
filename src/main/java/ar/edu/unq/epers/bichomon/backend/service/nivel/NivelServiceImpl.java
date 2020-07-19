package ar.edu.unq.epers.bichomon.backend.service.nivel;

import ar.edu.unq.epers.bichomon.backend.dao.NivelDAO;
import ar.edu.unq.epers.bichomon.backend.model.nivel.Nivel;
import ar.edu.unq.epers.bichomon.backend.model.nivel.NivelManager;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

import java.util.List;

public class NivelServiceImpl implements NivelService {

    private NivelDAO dao;

    public NivelServiceImpl(NivelDAO dao){
        this.dao = dao;
    }

    @Override
    public Nivel getNivel(int idNivel) {
        return Runner.runInSession(() -> {
            Nivel nivel = this.dao.recuperar(idNivel);
            return nivel;
        });
    }

    @Override
    public void crearNivel(Nivel nivel) {
        Runner.runInSession(() -> {
            this.dao.guardar(nivel);
            return null;
        });
    }

    @Override
    public void actualizar(Nivel nivel) {
        Runner.runInSession(() -> {
            this.dao.actualizar(nivel);
            return null;
        });
    }

    @Override
    public List<Nivel> recuperarTodos(){
        return Runner.runInSession(() -> {
           List<Nivel> niveles = this.dao.recuperarTodos();
            return niveles;
        });
    }

    @Override
    public NivelManager getNivelManager(){
        return Runner.runInSession(() -> {
            return new NivelManager(this.recuperarTodos(),10);
        });
    }
}
