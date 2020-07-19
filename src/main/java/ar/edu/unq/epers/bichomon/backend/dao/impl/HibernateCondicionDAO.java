package ar.edu.unq.epers.bichomon.backend.dao.impl;

import ar.edu.unq.epers.bichomon.backend.dao.CondicionDAO;
import ar.edu.unq.epers.bichomon.backend.model.condicion.Condicion;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import org.hibernate.Session;

public class HibernateCondicionDAO implements CondicionDAO {

    @Override
    public void guardar(Condicion cond) {
        Session session = Runner.getCurrentSession();
        session.save(cond);
    }

    @Override
    public void actualizar(Condicion cond) {
        Session session = Runner.getCurrentSession();
        session.update(cond);
    }

    @Override
    public Condicion recuperar(int idCond) {
        Session session = Runner.getCurrentSession();
        return session.get(Condicion.class, idCond);
    }
}
