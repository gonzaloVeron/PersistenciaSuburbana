package ar.edu.unq.epers.bichomon.backend.service.entrenador;

import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEntrenadorDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateNivelDAO;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.nivel.NivelManager;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Dojo;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Ubicacion;
import ar.edu.unq.epers.bichomon.backend.service.nivel.NivelServiceImpl;
import ar.edu.unq.epers.bichomon.backend.service.runner.SessionFactoryProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;

public class EntrenadorServiceTest {

    @Mock
    NivelManager nivelManager;

    private HibernateEntrenadorDAO entrenadorDAO;
    private EntrenadorService entrenadorService;
    private NivelServiceImpl nivelService;

    private Dojo dojo;
    private Entrenador entrenador;

    @Before
    public void setUp() {
        nivelService = new NivelServiceImpl(new HibernateNivelDAO());
        entrenadorDAO = new HibernateEntrenadorDAO();
        entrenadorService = new EntrenadorService(entrenadorDAO);
        dojo = new Dojo();
        dojo.setNombre("Un dojo");
        entrenador = new Entrenador("Spore", dojo);
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
    public void guardarYRecuperar() {
        this.entrenadorService.guardar(entrenador);
        assertEquals(entrenador.nombre(), entrenadorService.recuperar("Spore").nombre());
    }

    @Test
    public void actualizar() {
        this.entrenadorService.guardar(entrenador);
        entrenador.agregarExperiencia(100);
        entrenadorService.actualizar(entrenador);
        assertEquals(100, entrenadorService.recuperar("Spore").getExperiencia());
    }

}