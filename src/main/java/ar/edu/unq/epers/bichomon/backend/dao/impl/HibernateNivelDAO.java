package ar.edu.unq.epers.bichomon.backend.dao.impl;

import ar.edu.unq.epers.bichomon.backend.dao.NivelDAO;
import ar.edu.unq.epers.bichomon.backend.model.nivel.Nivel;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateNivelDAO implements NivelDAO {

    @Override
    public void guardar(Nivel nivel) {
        Session session = Runner.getCurrentSession();
        session.save(nivel);
    }

    @Override
    public void actualizar(Nivel nivel) {
        Session session = Runner.getCurrentSession();
        session.update(nivel);
    }

    @Override
    public Nivel recuperar(int idNivel) {
        Session session = Runner.getCurrentSession();
        return session.get(Nivel.class, idNivel);
    }

    @Override
    public List<Nivel> recuperarTodos(){
        Session session = Runner.getCurrentSession();

        String hql = "from Nivel";

        Query<Nivel> query = session.createQuery(hql, Nivel.class);
        return query.getResultList();
    }
}
