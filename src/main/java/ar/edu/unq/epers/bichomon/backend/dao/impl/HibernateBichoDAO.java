package ar.edu.unq.epers.bichomon.backend.dao.impl;

import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import org.hibernate.Session;

import ar.edu.unq.epers.bichomon.backend.dao.BichoDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

import javax.persistence.OneToOne;


public class HibernateBichoDAO implements BichoDAO {

    @Override
    public void guardar(Bicho bicho){
        Session session = Runner.getCurrentSession();
        session.save(bicho);
    }

    @Override
    public void actualizar(Bicho bicho) {
        Session session = Runner.getCurrentSession();
        session.update(bicho);
    }

    @Override
    public Bicho recuperar(int id){
        Session session = Runner.getCurrentSession();
        return session.get(Bicho.class,id);
    }

    @Override
    public void abandonarBicho(Bicho bicho) {
        Session session = Runner.getCurrentSession();
        //bicho.serAbandonado();
        session.update(bicho);
    }

    @Override
    public void adoptarBicho(Bicho bicho, Entrenador entrenador) {
        Session session = Runner.getCurrentSession();
        bicho.serAdoptado(entrenador);
        session.update(bicho);
    }

}
