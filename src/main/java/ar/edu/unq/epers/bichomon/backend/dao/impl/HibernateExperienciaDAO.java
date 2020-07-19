package ar.edu.unq.epers.bichomon.backend.dao.impl;

import ar.edu.unq.epers.bichomon.backend.dao.ExperienciaDAO;
import ar.edu.unq.epers.bichomon.backend.model.experiencia.Experiencia;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class HibernateExperienciaDAO implements ExperienciaDAO {

    @Override
    public int obtenerExperiencia(String nombre) {
        Session session = Runner.getCurrentSession();

        String hql = "from Experiencia e where e.nombre =:nombre";

        Query<Experiencia> query = session.createQuery(hql, Experiencia.class);
        query.setParameter("nombre", nombre);
        return query.getSingleResult().getValor();
    }

    @Override
    public void actualizarExperiencia(String nombre, int valor) {
        Session session = Runner.getCurrentSession();
        Experiencia exp = session.get(Experiencia.class, nombre);
        exp.setValor(valor);
        session.update(exp);
    }

    @Override
    public void crearExperiencia(String nombre, int valor) {
        Session session = Runner.getCurrentSession();
        Experiencia exp = new Experiencia(nombre);
        exp.setValor(valor);
        session.save(exp);
    }
}
