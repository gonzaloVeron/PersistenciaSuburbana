package ar.edu.unq.epers.bichomon.backend.service.condicion;

import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateCondicionDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.condicion.*;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.nivel.NivelManager;
import ar.edu.unq.epers.bichomon.backend.service.nivel.NivelServiceImpl;
import ar.edu.unq.epers.bichomon.backend.service.runner.SessionFactoryProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class CondicionServiceImplTest {

    private HibernateCondicionDAO dao;

    private CondicionServiceImpl service;

    private CondicionCompuesta condComp;

    private BasadoEnEdad condEdad;

    private BasadoEnEnergia condEner;

    private BasadoEnNivel condNiv;

    private BasadoEnVictoria condVic;

    @Mock
    private Bicho bicho;

    @Mock
    private Entrenador entrenador;

    @Mock
    private NivelServiceImpl nivelService;

    @Mock
    private NivelManager nivelManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(nivelService.getNivelManager()).thenReturn(nivelManager);
        when(nivelManager.capacidadMaximaDeBichos(10)).thenReturn(10);
        dao = new HibernateCondicionDAO();
        service = new CondicionServiceImpl(dao);

        condEdad = new BasadoEnEdad(LocalDate.of(1996,4,25));
        condEner = new BasadoEnEnergia(500);
        condNiv = new BasadoEnNivel(3);
        condVic = new BasadoEnVictoria(5);
        condComp = new CondicionCompuesta();

        condComp.agregarCondicion(condEdad);
        condComp.agregarCondicion(condEner);
        condComp.agregarCondicion(condNiv);
        condComp.agregarCondicion(condVic);

        when(bicho.getFechaDeCaptura()).thenReturn(LocalDate.now());
        when(bicho.getEnergia()).thenReturn(700);
        when(bicho.getEntrenador()).thenReturn(entrenador);
        when(entrenador.getNivel(nivelManager)).thenReturn(7);
        when(bicho.getVictorias()).thenReturn(7);
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
    public void crearCondicionYGetCondicion() {
        service.crearCondicion(condEdad);
        service.crearCondicion(condEner);
        service.crearCondicion(condNiv);
        service.crearCondicion(condVic);
        service.crearCondicion(condComp);

        Condicion cond = service.getCondicion(1);

        assertTrue(service.getCondicion(1).cumpleConLaCondicion(bicho, nivelManager));
        assertTrue(service.getCondicion(2).cumpleConLaCondicion(bicho, nivelManager));
        assertTrue(service.getCondicion(3).cumpleConLaCondicion(bicho, nivelManager));
        assertTrue(service.getCondicion(4).cumpleConLaCondicion(bicho, nivelManager));
        assertTrue(service.getCondicion(5).cumpleConLaCondicion(bicho, nivelManager));
    }

}