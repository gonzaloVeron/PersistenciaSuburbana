package ar.edu.unq.epers.bichomon.backend.service.ubicacion;

import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateUbicacionDAO;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Dojo;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Guarderia;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Pueblo;
import ar.edu.unq.epers.bichomon.backend.service.runner.SessionFactoryProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UbicacionServiceImpTest {

    private UbicacionServiceImp service;
    private HibernateUbicacionDAO dao;

    private Dojo dojo;
    private Pueblo pueblo;
    private Guarderia guarderia;

    @Before
    public void setUp() {
        dao = new HibernateUbicacionDAO();
        service = new UbicacionServiceImp(dao);
        dojo = new Dojo();
        dojo.setNombre("Un dojo");
        pueblo = new Pueblo();
        pueblo.setNombre("Un pueblo");
        guarderia = new Guarderia();
        guarderia.setNombre("Una guarderia");
    }

    @After
    public void cleanUp(){
        //Destroy cierra la session factory y fuerza a que, la proxima vez, una nueva tenga
        //que ser creada.
        //
        //Al tener hibernate configurado con esto <property name="hibernate.hbm2ddl.auto">create-drop</property>
        //al crearse una nueva session factory todo el schema será destruido y creado desde cero.
        SessionFactoryProvider.destroy();
    }


    @Test
    public void crearYRecuperarUbicacion() {
        service.crearUbicacion(dojo);
        service.crearUbicacion(guarderia);
        assertEquals(dojo.getNombre(), service.getUbicacion("Un dojo").getNombre());
    }

    @Test
    public void actualizarUbicacion() {
        service.crearUbicacion(dojo);
        dojo.sumarPoblacion();
        service.actualizarUbicacion(dojo);
        assertEquals(dojo.getPoblacion(), service.getUbicacion("Un dojo").getPoblacion());

    }

    @Test
    public void getAllUbicaciones() {
        service.crearUbicacion(pueblo);
        service.crearUbicacion(guarderia);
        service.crearUbicacion(dojo);
        assertEquals(3, service.getAllUbicaciones().size());
    }
}