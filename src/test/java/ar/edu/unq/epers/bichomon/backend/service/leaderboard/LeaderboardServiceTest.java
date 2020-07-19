package ar.edu.unq.epers.bichomon.backend.service.leaderboard;

import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateBichoDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateCampeonDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateUbicacionDAO;
import ar.edu.unq.epers.bichomon.backend.dao.CampeonDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.*;
import ar.edu.unq.epers.bichomon.backend.dao.mongodb.EventoDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.nivel.NivelManager;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Dojo;
import ar.edu.unq.epers.bichomon.backend.service.bicho.BichoService;
import ar.edu.unq.epers.bichomon.backend.service.bicho.BichoServiceImpl;
import ar.edu.unq.epers.bichomon.backend.service.campeon.CampeonService;
import ar.edu.unq.epers.bichomon.backend.service.entrenador.EntrenadorService;
import ar.edu.unq.epers.bichomon.backend.service.feed.FeedService;
import ar.edu.unq.epers.bichomon.backend.service.nivel.NivelServiceImpl;
import ar.edu.unq.epers.bichomon.backend.service.runner.SessionFactoryProvider;
import ar.edu.unq.epers.bichomon.backend.service.ubicacion.UbicacionServiceImp;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class LeaderboardServiceTest {

    private LeaderboardService leaderboardService;
    private HibernateCampeonDAO campeonDAO;
    private CampeonService campeonService;
    private BichoServiceImpl bichoService;
    private UbicacionServiceImp ubicacionService;
    private EntrenadorService entrenadorService;
    private NivelServiceImpl nivelService;

    private Dojo dojo1;
    private Dojo dojo2;

    private Especie especie1;
    private Especie especie2;

    @Mock
    private NivelManager nivelManager;

    private Entrenador entrenador1;
    private Entrenador entrenador2;
    private Entrenador entrenador3;
    private Entrenador entrenador4;

    private Bicho bicho1;
    private Bicho bicho2;
    private Bicho bicho3;
    private Bicho bicho4;
    private Bicho bicho5;
    private Bicho bicho6;
    private Bicho bicho7;

    private LocalDate fechaInicio1 = LocalDate.of(2018,10,05);
    private LocalDate fechaInicio2 = LocalDate.of(2018,10,10);
    private LocalDate fechaInicio3 = LocalDate.of(2018,10,12);
    private LocalDate fechaInicio4 = LocalDate.of(2018,10,15);
    private LocalDate fechaInicio5 = LocalDate.of(2018,10,28);
    private LocalDate fechaInicio6 = LocalDate.of(2018,10,30);

    @Before
    public void setUp() {
        campeonDAO = new HibernateCampeonDAO();
        campeonService = new CampeonService(campeonDAO);
        leaderboardService = new LeaderboardService(campeonDAO, new HibernateEntrenadorDAO(), new HibernateEspecieDAO());
        bichoService = new BichoServiceImpl(new HibernateBichoDAO(), new HibernateEntrenadorDAO(), new HibernateEspecieDAO(), nivelService, new HibernateUbicacionDAO(), new HibernateExperienciaDAO(), new FeedService(new EventoDAO(), entrenadorService, new UbicacionNeo4JDAO()));
        ubicacionService = new UbicacionServiceImp(new HibernateUbicacionDAO());
        nivelService = new NivelServiceImpl(new HibernateNivelDAO());
        entrenadorService = new EntrenadorService(new HibernateEntrenadorDAO());


        dojo1 = new Dojo();
        dojo2 = new Dojo();
        dojo1.setNombre("Dojo 1");
        dojo2.setNombre("Dojo 2");

        especie1 = new Especie("Pikachu");
        especie2 = new Especie("Charmander");

        entrenador1 = new Entrenador("entrenador1", dojo1);
        entrenador2 = new Entrenador("entrenador2", dojo2);
        entrenador3 = new Entrenador("entrenador3", dojo2);
        entrenador4 = new Entrenador("entrenador4", dojo2);

        bicho1 = new Bicho(especie1, entrenador1);
        bicho1.setEnergia(10);
        bicho2 = new Bicho(especie2, entrenador1);
        bicho2.setEnergia(10);
        bicho3 = new Bicho(especie1, entrenador2);
        bicho3.setEnergia(50);
        bicho4 = new Bicho(especie2, entrenador2);
        bicho4.setEnergia(50);
        bicho5 = new Bicho(especie2, entrenador3);
        bicho5.setEnergia(5);
        bicho6 = new Bicho(especie1, entrenador3);
        bicho6.setEnergia(5);


        ubicacionService.crearUbicacion(dojo1);
        ubicacionService.crearUbicacion(dojo2);
        entrenadorService.guardar(entrenador1);
        entrenadorService.guardar(entrenador2);
        entrenadorService.guardar(entrenador3);
        entrenadorService.guardar(entrenador4);
        bichoService.crearBicho(bicho1);
        bichoService.crearBicho(bicho2);
        bichoService.crearBicho(bicho3);
        bichoService.crearBicho(bicho4);
        bichoService.crearBicho(bicho5);
        bichoService.crearBicho(bicho6);
        entrenadorService.agregarBicho(entrenador1, bicho1);
        entrenadorService.agregarBicho(entrenador1, bicho2);
        entrenadorService.agregarBicho(entrenador2, bicho3);
        entrenadorService.agregarBicho(entrenador2, bicho4);
        entrenadorService.agregarBicho(entrenador3, bicho5);
        entrenadorService.agregarBicho(entrenador3, bicho6);

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
    public void campeones() {
        campeonService.actualizarCampeon(dojo1.actualizarYRetornarCampeon(bicho1, fechaInicio1));
        ubicacionService.actualizarUbicacion(dojo1);
        campeonService.actualizarCampeon(dojo1.actualizarYRetornarCampeon(bicho2, fechaInicio2));
        ubicacionService.actualizarUbicacion(dojo1);
        campeonService.actualizarCampeon(dojo2.actualizarYRetornarCampeon(bicho3, fechaInicio3));
        ubicacionService.actualizarUbicacion(dojo2);
        campeonService.actualizarCampeon(dojo2.actualizarYRetornarCampeon(bicho4, fechaInicio4));
        ubicacionService.actualizarUbicacion(dojo2);
        campeonService.actualizarCampeon(dojo2.actualizarYRetornarCampeon(bicho5, fechaInicio5));
        ubicacionService.actualizarUbicacion(dojo2);
        campeonService.actualizarCampeon(dojo2.actualizarYRetornarCampeon(bicho6, fechaInicio6));
        ubicacionService.actualizarUbicacion(dojo2);
        List<Entrenador> e = leaderboardService.campeones();
        assertEquals(2, e.size());
        assertEquals("entrenador1", e.get(0).nombre());
        assertEquals("entrenador3", e.get(1).nombre());
    }

    @Test
    public void lideres() {

        List<Entrenador> l = leaderboardService.lideres();
        assertEquals(3,l.size());
        assertEquals("entrenador2",l.get(0).nombre());
        assertEquals(100, l.get(0).getPoderTotal());
        assertEquals("entrenador1", l.get(1).nombre());
        assertEquals(20, l.get(1).getPoderTotal());
        assertEquals("entrenador3", leaderboardService.lideres().get(2).nombre());
        assertEquals(10, l.get(2).getPoderTotal());
    }
}