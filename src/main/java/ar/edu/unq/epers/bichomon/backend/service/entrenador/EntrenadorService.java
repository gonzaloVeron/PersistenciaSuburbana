package ar.edu.unq.epers.bichomon.backend.service.entrenador;

import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEntrenadorDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.nivel.NivelManager;

import ar.edu.unq.epers.bichomon.backend.service.nivel.NivelServiceImpl;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

public class EntrenadorService {

    private HibernateEntrenadorDAO entrenadorDAO;

    public EntrenadorService(HibernateEntrenadorDAO entrenadorDAO) {
        this.entrenadorDAO = entrenadorDAO;
    }

    public void guardar(Entrenador entrenador) {
        Runner.runInSession( () -> {
            entrenadorDAO.guardar(entrenador);
            return null;
        });
    }

    public Entrenador recuperar(String entrenador) {
        return Runner.runInSession(() -> {
            Entrenador entr = entrenadorDAO.recuperar(entrenador);
            return entr;
        });
    }

    public void actualizar(Entrenador entrenador) {
        Runner.runInSession(() -> {
           entrenadorDAO.actualizar(entrenador);
           return null;
        });
    }

    public void agregarBicho(Entrenador entrenador, Bicho bicho) {
        Runner.runInSession(() -> {
            entrenadorDAO.agregarBicho(entrenador.nombre(), bicho);
            return null;
        });
    }

}
