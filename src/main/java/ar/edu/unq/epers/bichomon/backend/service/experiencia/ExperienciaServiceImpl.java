package ar.edu.unq.epers.bichomon.backend.service.experiencia;

import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateExperienciaDAO;
import ar.edu.unq.epers.bichomon.backend.model.experiencia.Experiencia;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

public class ExperienciaServiceImpl implements ExperienciaService {

    private HibernateExperienciaDAO experienciaDAO;

    public ExperienciaServiceImpl(HibernateExperienciaDAO experienciaDAO){
        this.experienciaDAO = experienciaDAO;
    }

    @Override
    public int obtenerExperiencia(String nombre) {
        return Runner.runInSession(() -> {
           return experienciaDAO.obtenerExperiencia(nombre);
        });
    }

    @Override
    public void actualizarExperiencia(String nombre, int valor) {
        Runner.runInSession(() -> {
            experienciaDAO.actualizarExperiencia(nombre, valor);
            return null;
        });
    }

    @Override
    public void crearExperiencia(String nombre, int valor) {
        Runner.runInSession(() -> {
            experienciaDAO.crearExperiencia(nombre, valor);
            return null;
        });
    }
}
