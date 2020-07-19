package ar.edu.unq.epers.bichomon.backend.dao.impl;

import ar.edu.unq.epers.bichomon.backend.dao.EspecieDAO;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import org.hibernate.Session;
import org.hibernate.query.Query;


import java.util.EmptyStackException;
import java.util.List;

public class HibernateEspecieDAO implements EspecieDAO {

    public void guardar(Especie especie){
        Session session = Runner.getCurrentSession();
        session.save(especie);
    }

    public void actualizar(Especie especie){
        Session session = Runner.getCurrentSession();
        session.update(especie);
    }

    public void deleteAll(){
        Session session = Runner.getCurrentSession();

        String hql = "delete from Especie";

        Query<Especie> query = session.createQuery(hql, Especie.class);
        int result = query.executeUpdate();
    }

    public Especie recuperar(String nombreEspecie){
        Session session = Runner.getCurrentSession();
        return session.get(Especie.class, nombreEspecie);
    }

    public List<Especie> recuperarTodos(){
        Session session = Runner.getCurrentSession();

        String hql = "from Especie";

        Query<Especie> query = session.createQuery(hql, Especie.class);
        return query.getResultList();
    }

    public List<Especie> populares(){
        Session session = Runner.getCurrentSession();
        
        String hql = "from Especie e order by e.popularidad desc";

        Query<Especie> query = session.createQuery(hql,Especie.class);
        return query.setMaxResults(10).getResultList();
    }

    public List<Especie> impopulares(){
        Session session = Runner.getCurrentSession();

        String hql = "from Especie e order by e.popularidad asc";

        Query<Especie> query = session.createQuery(hql,Especie.class);
        return query.setMaxResults(10).getResultList();
    }

    public Especie especieLider() {
        Session session = Runner.getCurrentSession();

        String hql = "select e from Campeon c join c.bicho as b join b.especie as e group by e order by count(b) desc";

        Query<Especie> query = session.createQuery(hql, Especie.class);
        query.setMaxResults(1);
        return query.getSingleResult();

    }

    public Especie siguienteEvolucion(Especie especie){
        Session session = Runner.getCurrentSession();

        String hql = "from Especie e where e.nombre =:siguiente and e.especieRaiz =:raiz";

        Query<Especie> query = session.createQuery(hql, Especie.class);
        query.setParameter("siguiente", especie.getSiguienteEvolucion());
        query.setParameter("raiz", especie.getEspecieRaiz());
        return query.getResultList().stream().findFirst().orElse(null);
    }

}
