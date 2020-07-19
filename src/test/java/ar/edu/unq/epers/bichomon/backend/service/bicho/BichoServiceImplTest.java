package ar.edu.unq.epers.bichomon.backend.service.bicho;

import ar.edu.unq.epers.bichomon.backend.dao.impl.*;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.campeon.Campeon;
import ar.edu.unq.epers.bichomon.backend.model.condicion.BasadoEnVictoria;
import ar.edu.unq.epers.bichomon.backend.model.condicion.Condicion;
import ar.edu.unq.epers.bichomon.backend.model.duelo.ResultadoCombate;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.especie.TipoBicho;
import ar.edu.unq.epers.bichomon.backend.model.nivel.Nivel;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.*;
import ar.edu.unq.epers.bichomon.backend.service.condicion.CondicionService;
import ar.edu.unq.epers.bichomon.backend.service.condicion.CondicionServiceImpl;
import ar.edu.unq.epers.bichomon.backend.service.entrenador.EntrenadorService;
import ar.edu.unq.epers.bichomon.backend.service.especie.EspecieServiceImpl;
import ar.edu.unq.epers.bichomon.backend.service.experiencia.ExperienciaServiceImpl;
import ar.edu.unq.epers.bichomon.backend.service.feed.FeedService;
import ar.edu.unq.epers.bichomon.backend.service.mapa.MapaService;
import ar.edu.unq.epers.bichomon.backend.service.nivel.NivelServiceImpl;
import ar.edu.unq.epers.bichomon.backend.service.runner.SessionFactoryProvider;
import ar.edu.unq.epers.bichomon.backend.service.ubicacion.UbicacionServiceImp;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import static org.junit.Assert.*;

public class BichoServiceImplTest {

    private HibernateExperienciaDAO experienciaDAO;

    private HibernateCampeonDAO campeonDAO;

    private HibernateBichoDAO bichoDAO;

    private HibernateEntrenadorDAO entrenadorDAO;

    private HibernateEspecieDAO especieDAO;

    private HibernateCondicionDAO condDAO;

    private HibernateUbicacionDAO ubiDAO;

    private UbicacionNeo4JDAO ubicacionNeo4JDAO;

    @Mock
    private FeedService feedService;

    private CondicionService condService;

    private BichoServiceImpl bichoService;

    private UbicacionServiceImp ubicacionService;

    private EntrenadorService entrenadorService;

    private EspecieServiceImpl especieService;

    private ExperienciaServiceImpl experienciaService;

    private MapaService mapaService;

    private NivelServiceImpl nivelService;

    private HibernateNivelDAO nivelDAO;

    private Nivel nivel1;

    private Nivel nivel2;

    private Nivel nivel3;

    private Nivel nivel4;

    private Nivel nivel5;

    private Nivel nivel6;

    private Nivel nivel7;

    private Nivel nivel8;

    private Nivel nivel9;

    private Nivel nivel10;

    private Especie especie;

    private Especie especie2;

    private Especie especie3;

    private Pueblo pueblo;

    private Entrenador entrenador;

    private Entrenador entrenador2;

    private Condicion condVic;

    private Guarderia guarderia;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        nivelDAO = new HibernateNivelDAO();
        experienciaDAO = new HibernateExperienciaDAO();
        experienciaService = new ExperienciaServiceImpl(experienciaDAO);
        campeonDAO = new HibernateCampeonDAO();
        ubiDAO = new HibernateUbicacionDAO();
        condDAO = new HibernateCondicionDAO();
        bichoDAO = new HibernateBichoDAO();
        entrenadorDAO = new HibernateEntrenadorDAO();
        ubicacionNeo4JDAO = new UbicacionNeo4JDAO();

        nivelService = new NivelServiceImpl(nivelDAO);
        entrenadorService = new EntrenadorService(entrenadorDAO);
        especieDAO = new HibernateEspecieDAO();
        especieService = new EspecieServiceImpl(especieDAO);
        ubicacionService = new UbicacionServiceImp(ubiDAO);
        bichoService = new BichoServiceImpl(bichoDAO, entrenadorDAO, especieDAO, nivelService, ubiDAO, experienciaDAO, feedService);
        condService = new CondicionServiceImpl(condDAO);
        especieService = new EspecieServiceImpl(especieDAO);
        mapaService = new MapaService(ubiDAO, campeonDAO, entrenadorDAO, ubicacionNeo4JDAO, feedService);

        nivel1 = new Nivel(1,1,99);
        nivel2 = new Nivel(2,100,399);
        nivel3 = new Nivel(3,400,999);
        nivel4 = new Nivel(4,1000,1999);
        nivel5 = new Nivel(5,2000,2999);
        nivel6 = new Nivel(6,3000,3999);
        nivel7 = new Nivel(7,4000,4999);
        nivel8 = new Nivel(8,5000,5999);
        nivel9 = new Nivel(9,6000,6999);
        nivel10 = new Nivel(10,7000,7999);
        nivelService.crearNivel(nivel1);
        nivelService.crearNivel(nivel2);
        nivelService.crearNivel(nivel3);
        nivelService.crearNivel(nivel4);
        nivelService.crearNivel(nivel5);
        nivelService.crearNivel(nivel6);
        nivelService.crearNivel(nivel7);
        nivelService.crearNivel(nivel8);
        nivelService.crearNivel(nivel9);
        nivelService.crearNivel(nivel10);

        pueblo = new Pueblo();
        pueblo.setNombre("Sporeland");

        guarderia = new Guarderia();
        guarderia.setNombre("Una guarderia");

        mapaService.crearUbicacion(pueblo);
        mapaService.crearUbicacion(guarderia);

        condVic = new BasadoEnVictoria(5);
        especie = new Especie("Onix", null,TipoBicho.CHOCOLATE, condVic,257,300,9999999, null);
        especie3 = new Especie("Charmeleon", null, TipoBicho.FUEGO, condVic,88,100,300, especie2);
        especie2 = new Especie("Charmander", especie3, TipoBicho.FUEGO, condVic, 55, 75, 110, null);

        especieService.crearEspecie(especie);
        especieService.crearEspecie(especie2);

        entrenador = new Entrenador("Spore", guarderia);
        entrenador.agregarExperiencia(10);
        entrenador2 = new Entrenador("Mauro", guarderia);
        entrenador2.agregarExperiencia(10);

        entrenador.setMonedas(10);
        entrenador2.setMonedas(10);

        experienciaService.crearExperiencia("Combate", 10);
        experienciaService.crearExperiencia("Captura", 10);
        experienciaService.crearExperiencia("Evolucion", 5);
    }

    @After
    public void cleanUp(){
        //Destroy cierra la session factory y fuerza a que, la proxima vez, una nueva tenga
        //que ser creada.
        //
        //Al tener hibernate configurado con esto <property name="hibernate.hbm2ddl.auto">create-drop</property>
        //al crearse una nueva session factory todo el schema será destruido y creado desde cero.

        SessionFactoryProvider.destroy();
        ubicacionNeo4JDAO.destroy();
    }


    @Test
    public void testGetNivel(){
        //Con 5000 de experiencia deberia estar en el nivel 8.
        entrenador.agregarExperiencia(5000);

        assertEquals(8, entrenador.getNivel(nivelService.getNivelManager()));
    }


    @Test
    public void crearBicho() {
        Bicho bicho1 = especieService.crearBicho("Onix", entrenador);
        bichoService.crearBicho(bicho1);

    }

    @Test
    public void puedeEvolucionar(){
        Bicho bicho3 = especieService.crearBicho(especie2.getNombre(), entrenador);
        bicho3.incrementarVictorias();
        bicho3.incrementarVictorias();
        bicho3.incrementarVictorias();
        bicho3.incrementarVictorias();
        bicho3.incrementarVictorias();
        bicho3.incrementarVictorias();
        bichoService.crearBicho(bicho3);
        condService.crearCondicion(condVic);
        Bicho bicho4 = especieService.crearBicho(especie3.getNombre(), entrenador);
        bicho4.incrementarVictorias();
        bicho4.incrementarVictorias();
        bicho4.incrementarVictorias();
        bicho4.incrementarVictorias();
        bicho4.incrementarVictorias();
        bicho4.incrementarVictorias();
        bicho4.incrementarVictorias();
        bicho4.incrementarVictorias();
        bichoService.crearBicho(bicho4);

        assertFalse(bichoService.puedeEvolucionar(entrenador.nombre(), bicho4.getID()));
        assertTrue(bichoService.puedeEvolucionar(entrenador.nombre(), bicho3.getID()));
    }

    @Test
    public void evolucionar(){
        Bicho bicho3 = especieService.crearBicho(especie2.getNombre(), entrenador);
        bicho3.incrementarVictorias();
        bicho3.incrementarVictorias();
        bicho3.incrementarVictorias();
        bicho3.incrementarVictorias();
        bicho3.incrementarVictorias();
        bicho3.incrementarVictorias();
        bichoService.crearBicho(bicho3);
        condService.crearCondicion(condVic);
        Bicho bicho4 = especieService.crearBicho(especie3.getNombre(), entrenador);
        bicho4.incrementarVictorias();
        bicho4.incrementarVictorias();
        bicho4.incrementarVictorias();
        bicho4.incrementarVictorias();
        bicho4.incrementarVictorias();
        bicho4.incrementarVictorias();
        bicho4.incrementarVictorias();
        bicho4.incrementarVictorias();
        bichoService.crearBicho(bicho4);

        assertEquals(especie3.getNombre(), bichoService.evolucionar(entrenador.nombre(), bicho3.getID()).getEspecie().getNombre());
    }

    @Test
    public void abandonarBicho() {

        entrenadorService.guardar(entrenador);
        Bicho bicho1 = especieService.crearBicho("Onix", entrenador);
        bichoService.crearBicho(bicho1);
        Bicho bicho2 = especieService.crearBicho("Onix", entrenador);
        bichoService.crearBicho(bicho2);
        ubicacionService.actualizarUbicacion(guarderia);
        entrenadorService.agregarBicho(entrenador, bicho1);
        entrenadorService.agregarBicho(entrenador, bicho2);

        bichoService.abandonar(entrenador.nombre(), bicho1.getID());

        assertEquals(1, entrenadorService.recuperar("Spore").cantidadBichos());
        assertEquals(1, ubicacionService.getUbicacion("Una guarderia").getCantidadBichosAbandonados());
        assertEquals(1, especieService.getEspecie("Onix").getPopularidad());
        verify(feedService, times(1)).guardarAbandono(entrenador, bicho1);
    }

    @Test(expected = BichoNoPuedeSerAdoptado.class)
    public void noPuedeAdoptarBichoQueYaAbandonoAntes() {

        entrenadorService.guardar(entrenador);
        Bicho bicho1 = especieService.crearBicho("Onix", entrenador);
        bichoService.crearBicho(bicho1);
        Bicho bicho2 = especieService.crearBicho("Onix", entrenador);
        bichoService.crearBicho(bicho2);
        Bicho bicho3 = especieService.crearBicho("Charmander", entrenador2);
        bichoService.crearBicho(bicho3);
        Bicho bicho4 = especieService.crearBicho("Charmander", entrenador2);
        bichoService.crearBicho(bicho4);
        ubicacionService.actualizarUbicacion(guarderia);
        entrenadorService.agregarBicho(entrenador, bicho1);
        entrenadorService.agregarBicho(entrenador, bicho2);
        entrenadorService.agregarBicho(entrenador2, bicho3);
        entrenadorService.agregarBicho(entrenador2, bicho4);
        bichoService.abandonar(entrenador2.nombre(), bicho4.getID());
        ubicacionService.actualizarUbicacion(guarderia);

        bichoService.buscar(entrenador.nombre());

        bichoService.abandonar(entrenador.nombre(), bicho4.getID());

        bichoService.buscar(entrenador2.nombre()); /** En este punto se espera la exception */

        verify(feedService, times(1)).guardarAbandono(entrenador2, bicho4);
        verify(feedService, times(1)).guardarCaptura(entrenador, bicho4);
        verify(feedService, times(1)).guardarAbandono(entrenador, bicho4);
    }

    @Test
    public void testAdoptarBicho(){

        entrenadorService.guardar(entrenador2);
        entrenadorService.guardar(entrenador);
        Bicho bicho1 = especieService.crearBicho("Onix", entrenador);
        bichoService.crearBicho(bicho1);
        Bicho bicho2 = especieService.crearBicho("Onix", entrenador);
        bichoService.crearBicho(bicho2);
        Bicho bicho3 = especieService.crearBicho("Charmander", entrenador2);
        bichoService.crearBicho(bicho3);
        Bicho bicho4 = especieService.crearBicho("Charmander", entrenador2);
        bichoService.crearBicho(bicho4);
        ubicacionService.actualizarUbicacion(guarderia);
        entrenadorService.agregarBicho(entrenador, bicho1);
        entrenadorService.agregarBicho(entrenador, bicho2);
        entrenadorService.agregarBicho(entrenador2, bicho3);
        entrenadorService.agregarBicho(entrenador2, bicho4);
        bichoService.abandonar(entrenador2.nombre(), bicho4.getID());
        ubicacionService.actualizarUbicacion(guarderia);

        bichoService.buscar(entrenador.nombre());

        assertEquals(3, entrenadorService.recuperar(entrenador.nombre()).cantidadBichos());
        assertEquals(1, entrenadorService.recuperar(entrenador2.nombre()).cantidadBichos());
        verify(feedService, times(1)).guardarCaptura(entrenador, bicho4);
    }

    @Test(expected = BichoNoEncontradoException.class)
    public void noPuedeAdoptarBichoPorqueNoHayBichosEnGuarderia(){

        entrenadorService.guardar(entrenador2);
        entrenadorService.guardar(entrenador);
        Bicho bicho1 = especieService.crearBicho("Onix", entrenador);
        bichoService.crearBicho(bicho1);
        Bicho bicho2 = especieService.crearBicho("Onix", entrenador);
        bichoService.crearBicho(bicho2);
        Bicho bicho3 = especieService.crearBicho("Charmander", entrenador2);
        bichoService.crearBicho(bicho3);
        Bicho bicho4 = especieService.crearBicho("Charmander", entrenador2);
        bichoService.crearBicho(bicho4);
        ubicacionService.actualizarUbicacion(guarderia);
        entrenadorService.agregarBicho(entrenador, bicho1);
        entrenadorService.agregarBicho(entrenador, bicho2);
        entrenadorService.agregarBicho(entrenador2, bicho3);
        entrenadorService.agregarBicho(entrenador2, bicho4);


        bichoService.buscar(entrenador.nombre());
    }

    @Test
    public void duelo(){

        Bicho bichoRetador = especieService.crearBicho("Onix", entrenador);
        Bicho bichoCampeon  = especieService.crearBicho("Charmeleon", entrenador2);

        bichoService.crearBicho(bichoCampeon);
        bichoService.crearBicho(bichoRetador);

        Dojo dojo = new Dojo();
        dojo.setNombre("Cobra Kai");

        Campeon campeon = dojo.actualizarYRetornarCampeon(bichoCampeon,LocalDate.now());

        mapaService.crearUbicacion(dojo);

        mapaService.conectar(guarderia.getNombre(), dojo.getNombre(), CostoCamino.tierra);
        mapaService.conectar(dojo.getNombre(), guarderia.getNombre(), CostoCamino.tierra);

        mapaService.mover(entrenador.nombre(), dojo.getNombre());
        mapaService.mover(entrenador2.nombre(), dojo.getNombre());

        assertEquals(10, entrenador2.getExperiencia());
        assertEquals(10, entrenador.getExperiencia());

        ResultadoCombate resultado = bichoService.duelo("Spore", bichoRetador.getID());

        assertEquals(bichoRetador.getID(), resultado.getGanador().getID());

        entrenador = entrenadorService.recuperar(entrenador.nombre());
        entrenador2 = entrenadorService.recuperar(entrenador2.nombre());
        assertEquals(20, entrenador.getExperiencia());
        assertEquals(20, entrenador2.getExperiencia());

        verify(feedService, times(1)).guardarCoronacion(entrenador, entrenador2);
    }

    @Test(expected = EntrenadorNoPuedeAbandonarException.class)
    public void entrenadorNoPuedeAbandonarException() {

        entrenadorService.guardar(entrenador);
        Bicho bicho1 = especieService.crearBicho("Onix", entrenador);
        bichoService.crearBicho(bicho1);
        ubicacionService.actualizarUbicacion(guarderia);
        entrenadorService.agregarBicho(entrenador, bicho1);

        bichoService.abandonar(entrenador.nombre(), bicho1.getID());
    }

}