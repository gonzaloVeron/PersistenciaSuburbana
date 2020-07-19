package ar.edu.unq.epers.bichomon.backend.service.condicion;

import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateCondicionDAO;
import ar.edu.unq.epers.bichomon.backend.model.condicion.Condicion;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

public class CondicionServiceImpl implements CondicionService {

    private HibernateCondicionDAO dao;

    public CondicionServiceImpl(HibernateCondicionDAO dao){
        this.dao = dao;
    }

    @Override
    public void crearCondicion(Condicion cond) {
        Runner.runInSession(() -> {
            dao.guardar(cond);
            return null;
        });
    }

    @Override
    public Condicion getCondicion(int idCond) {
        return Runner.runInSession(() -> {
            Condicion cond = dao.recuperar(idCond);
            return cond;
        });
    }

}
