package ar.edu.unq.epers.bichomon.backend.service.nivel;

import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEspecieDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateNivelDAO;
import ar.edu.unq.epers.bichomon.backend.model.nivel.Nivel;
import ar.edu.unq.epers.bichomon.backend.service.runner.SessionFactoryProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class NivelServiceImplTest {

    private NivelServiceImpl service;

    private HibernateNivelDAO nivelDAO;

    private Nivel nivel1;

    private Nivel nivel2;

    @Before
    public void setUp(){
        nivelDAO = new HibernateNivelDAO();
        service = new NivelServiceImpl(nivelDAO);

        nivel1 = new Nivel(1,1,99);
        nivel2 = new Nivel(2,100,400);

        service.crearNivel(nivel1);
        service.crearNivel(nivel2);
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
    public void crearNivelYGetNivel() {
        assertEquals(nivel1.getExpInicial(), service.getNivel(1).getExpInicial());
        assertEquals(nivel2.getExpFinal(), service.getNivel(2).getExpFinal());

    }

    @Test
    public void actualizar() {
        Nivel nivelAModificar = service.getNivel(2);
        nivelAModificar.setCapacidadMaximaDeBichos(9999);
        service.actualizar(nivelAModificar);

        assertEquals(nivelAModificar.getCapacidadMaximaDeBichos(), service.getNivel(2).getCapacidadMaximaDeBichos());
    }

    @Test
    public void recuperarTodos(){
        List<Nivel> niveles = new ArrayList<Nivel>();
        niveles.add(nivel1);
        niveles.add(nivel2);

        assertEquals(niveles.size(),service.recuperarTodos().size());
    }
}