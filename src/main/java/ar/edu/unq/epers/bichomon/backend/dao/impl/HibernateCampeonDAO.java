package ar.edu.unq.epers.bichomon.backend.dao.impl;

import ar.edu.unq.epers.bichomon.backend.dao.CampeonDAO;
import ar.edu.unq.epers.bichomon.backend.model.campeon.Campeon;
import ar.edu.unq.epers.bichomon.backend.model.campeon.NoHayCampeonHistoricoException;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.*;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

public class HibernateCampeonDAO implements CampeonDAO {


    public Campeon getCampeon(String nombreDojo) {
        Session session = Runner.getCurrentSession();

        return session.get(Dojo.class, nombreDojo).getCampeon();
    }

    public Campeon getCampeonHistorico(String nombreUbicacion) {
        Session session = Runner.getCurrentSession();

        try {
            String hql = "from Campeon c where c.fechaFin is not null and c.dojo.Nombre =:nombreUbicacion order by date(c.fechaFin) - date(c.fechaInicio) desc";

            Query<Campeon> query = session.createQuery(hql, Campeon.class);
            query.setParameter("nombreUbicacion", nombreUbicacion);

            Campeon campeon = query.setMaxResults(1).getSingleResult();

            return campeon;

        }
        catch (NoResultException nre) {
            throw new NoHayCampeonHistoricoException(nombreUbicacion);
        }

    }

    public void actualizarOGuardarCampeon(Campeon campeon) {
        Session session = Runner.getCurrentSession();
        session.saveOrUpdate(campeon);
    }


    public List<Campeon> recuperarTodos() {
        Session session = Runner.getCurrentSession();

        String hql = "from Campeon campeon";

        Query<Campeon> query = session.createQuery(hql, Campeon.class);

        List<Campeon> lista = query.getResultList();

        return lista;
    }

    public List<Entrenador> campeones() {
        Session session = Runner.getCurrentSession();

        String hql = "select e from Campeon c join c.bicho.entrenador as e where c.fechaFin is null order by date(c.fechaInicio) asc";

        Query<Entrenador> query = session.createQuery(hql, Entrenador.class);

        return query.getResultList();
    }


}
