package ar.edu.unq.epers.bichomon.backend.dao.impl;

import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;

public class HibernateEntrenadorDAO implements EntrenadorDAO {

    @Override
    public void guardar(Entrenador entrenador){
        Session session = Runner.getCurrentSession();
        session.save(entrenador);
    }

    @Override
    public Entrenador recuperar(String nombre){
        Session session = Runner.getCurrentSession();
        return session.get(Entrenador.class, nombre);
    }

    @Override
    public void actualizar(Entrenador entrenador) {
        Session session = Runner.getCurrentSession();
        session.update(entrenador);
    }

    public List<Entrenador> recuperarTodos() {
        Session session = Runner.getCurrentSession();

        String hql = "from Entrenador e";

        Query<Entrenador> query = session.createQuery(hql, Entrenador.class);

        return query.getResultList();
    }

    public void agregarBicho(String nombreEntrenador, Bicho bicho) {
        Session session = Runner.getCurrentSession();

        Entrenador entrenador = this.recuperar(nombreEntrenador);
        entrenador.capturarBicho(bicho);
        session.update(entrenador);
    }

    public void aumentarExperiencia(String nombreEntrenador, int valor){
        Session session = Runner.getCurrentSession();
        Entrenador entrenador = this.recuperar(nombreEntrenador);
        entrenador.agregarExperiencia(valor);
        session.update(entrenador);
    }

    public List<Entrenador> lideres() {
        Session session = Runner.getCurrentSession();

        String hql = "select e from Entrenador as e join e.bichos as b group by e order by sum(b.energia) desc";

        Query<Entrenador> query = session.createQuery(hql, Entrenador.class).setMaxResults(10);

        return query.getResultList();
    }

}
