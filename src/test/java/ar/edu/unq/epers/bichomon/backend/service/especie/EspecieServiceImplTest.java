package ar.edu.unq.epers.bichomon.backend.service.especie;

import ar.edu.unq.epers.bichomon.backend.dao.impl.*;
import ar.edu.unq.epers.bichomon.backend.dao.mongodb.EventoDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.condicion.Condicion;
import ar.edu.unq.epers.bichomon.backend.model.condicion.CondicionCompuesta;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.especie.TipoBicho;
import ar.edu.unq.epers.bichomon.backend.model.nivel.NivelManager;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Dojo;
import ar.edu.unq.epers.bichomon.backend.service.bicho.BichoServiceImpl;
import ar.edu.unq.epers.bichomon.backend.service.entrenador.EntrenadorService;
import ar.edu.unq.epers.bichomon.backend.service.feed.FeedService;
import ar.edu.unq.epers.bichomon.backend.service.nivel.NivelServiceImpl;
import ar.edu.unq.epers.bichomon.backend.service.runner.SessionFactoryProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class EspecieServiceImplTest {

    private EspecieServiceImpl service;
    private HibernateEspecieDAO hibernateEspecieDAO;
    private Condicion condicion;
    private BichoServiceImpl bichoService;

    private Especie pikachu;

    private Especie raichu;

    private Especie charmander;

    private Especie charmeleon;

    private Especie charizard;

    private Especie squirtle;

    private Especie wartortle;

    private Especie blastoise;

    private Especie onix;

    private Especie articuno;

    private Especie zapdos;

    private Especie moltres;

    private Especie lugia;

    private Especie mewtow;

    private ArrayList<Especie> especies;

    private Entrenador entrenador;

    private Dojo dojo;

    @Mock
    private NivelServiceImpl nivelService;

    @Mock
    private NivelManager nivelManager;

    private FeedService feedService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        feedService = new FeedService(new EventoDAO(), new EntrenadorService(new HibernateEntrenadorDAO()), new UbicacionNeo4JDAO() );
        when(nivelService.getNivelManager()).thenReturn(nivelManager);
        when(nivelManager.capacidadMaximaDeBichos(10)).thenReturn(10);
        hibernateEspecieDAO = new HibernateEspecieDAO();
        bichoService = new BichoServiceImpl(new HibernateBichoDAO(), new HibernateEntrenadorDAO(), hibernateEspecieDAO, nivelService, new HibernateUbicacionDAO(), new HibernateExperienciaDAO(), feedService);
        service = new EspecieServiceImpl(hibernateEspecieDAO);
        condicion = new CondicionCompuesta();
        dojo = new Dojo();
        dojo.setNombre("Sporeland");
        entrenador = new Entrenador("Spore", dojo);

        pikachu = new Especie("Pikachu", raichu, TipoBicho.ELECTRICIDAD, condicion, 55, 99, 100, null);
        raichu = new Especie("Raichu", null, TipoBicho.ELECTRICIDAD, condicion, 80, 110, 300, pikachu);

        charmander = new Especie("Charmander", charmeleon, TipoBicho.FUEGO, condicion, 55, 75, 110, null);
        charmeleon = new Especie("Charmeleon", charizard, TipoBicho.FUEGO, condicion, 88, 100, 300, charmander);
        charizard = new Especie("Charizard", null, TipoBicho.FUEGO, condicion, 120, 220, 750,charmander);

        squirtle = new Especie("Squirtle", wartortle, TipoBicho.AGUA, condicion, 55, 56, 115,null);
        wartortle = new Especie("Wartortle", blastoise, TipoBicho.AGUA, condicion, 70, 81, 279, squirtle);
        blastoise = new Especie("Blastoise", null, TipoBicho.AGUA, condicion, 101, 110, 554, squirtle);

        onix = new Especie("Onix", null, TipoBicho.CHOCOLATE,condicion,257,300,446, null);
        articuno = new Especie("Articuno", null, TipoBicho.CHOCOLATE,condicion,110,85,1551, null);
        zapdos = new Especie("Zapdos", null, TipoBicho.ELECTRICIDAD,condicion,115,71,1551, null);
        moltres = new Especie("Moltres", null, TipoBicho.FUEGO,condicion,105,88,1551, null);
        lugia = new Especie("Lugia", null, TipoBicho.AGUA,condicion,120,91,2641, null);
        mewtow = new Especie("MewTwo", null, TipoBicho.CHOCOLATE,condicion,100,70,9999, null);

        especies = new ArrayList<Especie>();

        service.crearEspecie(pikachu);
        service.crearEspecie(raichu);
        service.crearEspecie(charmander);
        service.crearEspecie(charmeleon);
        service.crearEspecie(charizard);
        service.crearEspecie(squirtle);
        service.crearEspecie(wartortle);
        service.crearEspecie(blastoise);
        service.crearEspecie(onix);
        service.crearEspecie(articuno);
        service.crearEspecie(zapdos);
        service.crearEspecie(moltres);
        service.crearEspecie(lugia);
        service.crearEspecie(mewtow);

        /*---------------------------------------------------------------------------*/
        Bicho bicho1 = service.crearBicho("Pikachu", entrenador);
        bichoService.crearBicho(bicho1);
        Bicho bicho2 = service.crearBicho("Pikachu", entrenador);
        bichoService.crearBicho(bicho2);
        Bicho bicho3 = service.crearBicho("Pikachu", entrenador);
        bichoService.crearBicho(bicho3);
        Bicho bicho4 = service.crearBicho("Pikachu", entrenador);
        bichoService.crearBicho(bicho4);
        Bicho bicho5 = service.crearBicho("Pikachu", entrenador);
        bichoService.crearBicho(bicho5);
        Bicho bicho6 = service.crearBicho("Pikachu", entrenador);
        bichoService.crearBicho(bicho6);
        Bicho bicho7 = service.crearBicho("Pikachu", entrenador);
        bichoService.crearBicho(bicho7);
        Bicho bicho8 = service.crearBicho("Pikachu", entrenador);
        bichoService.crearBicho(bicho8);
        Bicho bicho9 = service.crearBicho("Pikachu", entrenador);
        bichoService.crearBicho(bicho9);
        Bicho bicho10 = service.crearBicho("Pikachu", entrenador);
        bichoService.crearBicho(bicho10);
        Bicho bicho11 = service.crearBicho("Pikachu", entrenador);
        bichoService.crearBicho(bicho11);
        Bicho bicho12 = service.crearBicho("Pikachu", entrenador);
        bichoService.crearBicho(bicho12);
        Bicho bicho13 = service.crearBicho("Pikachu", entrenador);
        bichoService.crearBicho(bicho13);
        Bicho bicho14 = service.crearBicho("Pikachu", entrenador);
        bichoService.crearBicho(bicho14);
        Bicho bicho15 = service.crearBicho("Pikachu", entrenador);
        bichoService.crearBicho(bicho15);

        Bicho bicho16 = service.crearBicho("Raichu", entrenador);
        bichoService.crearBicho(bicho16);

        /*---------------------------------------------------------------------------*/
        Bicho bicho17 = service.crearBicho("Charmander", entrenador);
        bichoService.crearBicho(bicho17);
        Bicho bicho18 = service.crearBicho("Charmander", entrenador);
        bichoService.crearBicho(bicho18);
        Bicho bicho19 = service.crearBicho("Charmander", entrenador);
        bichoService.crearBicho(bicho19);
        Bicho bicho20 = service.crearBicho("Charmander", entrenador);
        bichoService.crearBicho(bicho20);
        Bicho bicho21 = service.crearBicho("Charmander", entrenador);
        bichoService.crearBicho(bicho21);
        Bicho bicho22 = service.crearBicho("Charmander", entrenador);
        bichoService.crearBicho(bicho22);
        Bicho bicho23 = service.crearBicho("Charmander", entrenador);
        bichoService.crearBicho(bicho23);
        Bicho bicho24 = service.crearBicho("Charmander", entrenador);
        bichoService.crearBicho(bicho24);

        Bicho bicho25 = service.crearBicho("Charmeleon", entrenador);
        bichoService.crearBicho(bicho25);
        Bicho bicho26 = service.crearBicho("Charmeleon", entrenador);
        bichoService.crearBicho(bicho26);
        Bicho bicho27 = service.crearBicho("Charmeleon", entrenador);
        bichoService.crearBicho(bicho27);
        Bicho bicho28 = service.crearBicho("Charmeleon", entrenador);
        bichoService.crearBicho(bicho28);

        Bicho bicho29 = service.crearBicho("Charizard", entrenador);
        bichoService.crearBicho(bicho29);
        Bicho bicho30 = service.crearBicho("Charizard", entrenador);
        bichoService.crearBicho(bicho30);
        Bicho bicho31 = service.crearBicho("Charizard", entrenador);
        bichoService.crearBicho(bicho31);
        Bicho bicho32 = service.crearBicho("Charizard", entrenador);
        bichoService.crearBicho(bicho32);
        Bicho bicho33 = service.crearBicho("Charizard", entrenador);
        bichoService.crearBicho(bicho33);
        Bicho bicho34 = service.crearBicho("Charizard", entrenador);
        bichoService.crearBicho(bicho34);
        Bicho bicho35 = service.crearBicho("Charizard", entrenador);
        bichoService.crearBicho(bicho35);
        Bicho bicho36 = service.crearBicho("Charizard", entrenador);
        bichoService.crearBicho(bicho36);
        Bicho bicho37 = service.crearBicho("Charizard", entrenador);
        bichoService.crearBicho(bicho37);
        Bicho bicho38 = service.crearBicho("Charizard", entrenador);
        bichoService.crearBicho(bicho38);

        /*---------------------------------------------------------------------------*/
        Bicho bicho39 = service.crearBicho("Squirtle", entrenador);
        bichoService.crearBicho(bicho39);

        Bicho bicho40 = service.crearBicho("Wartortle", entrenador);
        bichoService.crearBicho(bicho40);
        Bicho bicho41 = service.crearBicho("Wartortle", entrenador);
        bichoService.crearBicho(bicho41);
        Bicho bicho42 = service.crearBicho("Wartortle", entrenador);
        bichoService.crearBicho(bicho42);
        Bicho bicho43 = service.crearBicho("Wartortle", entrenador);
        bichoService.crearBicho(bicho43);
        Bicho bicho44 = service.crearBicho("Wartortle", entrenador);
        bichoService.crearBicho(bicho44);
        Bicho bicho45 = service.crearBicho("Wartortle", entrenador);
        bichoService.crearBicho(bicho45);

        Bicho bicho46 = service.crearBicho("Blastoise", entrenador);
        bichoService.crearBicho(bicho46);
        Bicho bicho47 = service.crearBicho("Blastoise", entrenador);
        bichoService.crearBicho(bicho47);
        Bicho bicho48 = service.crearBicho("Blastoise", entrenador);
        bichoService.crearBicho(bicho48);
        Bicho bicho49 = service.crearBicho("Blastoise", entrenador);
        bichoService.crearBicho(bicho49);
        Bicho bicho50 = service.crearBicho("Blastoise", entrenador);
        bichoService.crearBicho(bicho50);
        Bicho bicho51 = service.crearBicho("Blastoise", entrenador);
        bichoService.crearBicho(bicho51);
        Bicho bicho52 = service.crearBicho("Blastoise", entrenador);
        bichoService.crearBicho(bicho52);
        Bicho bicho53 = service.crearBicho("Blastoise", entrenador);
        bichoService.crearBicho(bicho53);
        Bicho bicho54 = service.crearBicho("Blastoise", entrenador);
        bichoService.crearBicho(bicho54);
        Bicho bicho55 = service.crearBicho("Blastoise", entrenador);
        bichoService.crearBicho(bicho55);
        Bicho bicho56 = service.crearBicho("Blastoise", entrenador);
        bichoService.crearBicho(bicho56);
        /*---------------------------------------------------------------------------*/
        Bicho bicho57 = service.crearBicho("Articuno", entrenador);
        bichoService.crearBicho(bicho57);
        Bicho bicho58 = service.crearBicho("Zapdos", entrenador);
        bichoService.crearBicho(bicho58);
        Bicho bicho59 = service.crearBicho("Zapdos", entrenador);
        bichoService.crearBicho(bicho59);
        Bicho bicho60 = service.crearBicho("Moltres", entrenador);
        bichoService.crearBicho(bicho60);
        Bicho bicho61 = service.crearBicho("Moltres", entrenador);
        bichoService.crearBicho(bicho61);
        Bicho bicho62 = service.crearBicho("Moltres", entrenador);
        bichoService.crearBicho(bicho62);
        Bicho bicho63 = service.crearBicho("Lugia", entrenador);
        bichoService.crearBicho(bicho63);
        /*---------------------------------------------------------------------------*/
        Bicho bicho64 = service.crearBicho("Onix", entrenador);
        bichoService.crearBicho(bicho64);
        Bicho bicho65 = service.crearBicho("Onix", entrenador);
        bichoService.crearBicho(bicho65);
        Bicho bicho66 = service.crearBicho("Onix", entrenador);
        bichoService.crearBicho(bicho66);
        Bicho bicho67 = service.crearBicho("Onix", entrenador);
        bichoService.crearBicho(bicho67);
        Bicho bicho68 = service.crearBicho("Onix", entrenador);
        bichoService.crearBicho(bicho68);
        Bicho bicho69 = service.crearBicho("Onix", entrenador);
        bichoService.crearBicho(bicho69);
        Bicho bicho70 = service.crearBicho("Onix", entrenador);
        bichoService.crearBicho(bicho70);
        Bicho bicho71 = service.crearBicho("Onix", entrenador);
        bichoService.crearBicho(bicho71);
        Bicho bicho72 = service.crearBicho("Onix", entrenador);
        bichoService.crearBicho(bicho72);
        Bicho bicho73 = service.crearBicho("Onix", entrenador);
        bichoService.crearBicho(bicho73);
        Bicho bicho74 = service.crearBicho("Onix", entrenador);
        bichoService.crearBicho(bicho74);
        Bicho bicho75 = service.crearBicho("Onix", entrenador);
        bichoService.crearBicho(bicho75);
        Bicho bicho76 = service.crearBicho("Onix", entrenador);
        bichoService.crearBicho(bicho76);
        Bicho bicho77 = service.crearBicho("Onix", entrenador);
        bichoService.crearBicho(bicho77);
        Bicho bicho78 = service.crearBicho("Onix", entrenador);
        bichoService.crearBicho(bicho78);
        Bicho bicho79 = service.crearBicho("Onix", entrenador);
        bichoService.crearBicho(bicho79);
        Bicho bicho80 = service.crearBicho("Onix", entrenador);
        bichoService.crearBicho(bicho80);
        Bicho bicho81 = service.crearBicho("Onix", entrenador);
        bichoService.crearBicho(bicho81);
        Bicho bicho82 = service.crearBicho("Onix", entrenador);
        bichoService.crearBicho(bicho82);
        Bicho bicho83 = service.crearBicho("Onix", entrenador);
        bichoService.crearBicho(bicho83);
        Bicho bicho84 = service.crearBicho("Onix", entrenador);
        bichoService.crearBicho(bicho84);
        Bicho bicho85 = service.crearBicho("Onix", entrenador);
        bichoService.crearBicho(bicho85);
        Bicho bicho86 = service.crearBicho("Onix", entrenador);
        bichoService.crearBicho(bicho86);
        Bicho bicho87 = service.crearBicho("Onix", entrenador);
        bichoService.crearBicho(bicho87);
        Bicho bicho88 = service.crearBicho("Onix", entrenador);
        bichoService.crearBicho(bicho88);
        Bicho bicho89 = service.crearBicho("Onix", entrenador);
        bichoService.crearBicho(bicho89);
        Bicho bicho90 = service.crearBicho("Onix", entrenador);
        bichoService.crearBicho(bicho90);
        Bicho bicho91 = service.crearBicho("Onix", entrenador);
        bichoService.crearBicho(bicho91);
        Bicho bicho92 = service.crearBicho("Onix", entrenador);
        bichoService.crearBicho(bicho92);
        Bicho bicho93 = service.crearBicho("Onix", entrenador);
        bichoService.crearBicho(bicho93);
        /*---------------------------------------------------------------------------*/
        Bicho bicho94 = service.crearBicho("MewTwo", entrenador);
        bichoService.crearBicho(bicho94);

        service.decrementarPopularidad("Pikachu");
        service.decrementarPopularidad("Pikachu");
        service.decrementarPopularidad("Charmander");
        service.decrementarPopularidad("Charmander");
        service.decrementarPopularidad("Charmander");
        service.decrementarPopularidad("Squirtle");
        service.decrementarPopularidad("Lugia");
        service.decrementarPopularidad("Onix");
        service.decrementarPopularidad("Onix");
        service.decrementarPopularidad("Onix");
        service.decrementarPopularidad("Onix");
        service.decrementarPopularidad("Onix");
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
    public void testCrearEspecieYGetEspecie() {
        assertEquals(pikachu.getNombre(), service.getEspecie("Pikachu").getNombre());
    }

    @Test(expected = EspecieNoExistente.class)
    public void testEspecieNoExistenteException(){
        assertEquals("PapaFrita", service.getEspecie("PapaFrita").getNombre());
    }

    @Test
    public void getAllEspecies() {
        especies.add(pikachu);
        especies.add(raichu);
        especies.add(charmander);
        especies.add(charmeleon);
        especies.add(charizard);
        especies.add(squirtle);
        especies.add(wartortle);
        especies.add(blastoise);
        especies.add(onix);
        especies.add(articuno);
        especies.add(zapdos);
        especies.add(moltres);
        especies.add(lugia);
        especies.add(mewtow);

        assertEquals(especies.size(), service.getAllEspecies().size());
    }

    @Test
    public void crearBicho() {
        assertEquals(30, service.getEspecie("Onix").getCantidadBichos(),0);
    }

    @Test
    public void actualizarAltura(){
        Especie especie = service.getEspecie("Pikachu");
        especie.setAltura(60);
        service.actualizar(especie);

        assertEquals(60,service.getEspecie("Pikachu").getAltura(),0);
    }

    @Test
    public void populares(){
        assertEquals(10, service.populares().size());
        assertEquals(25, service.populares().get(0).getPopularidad());
        assertEquals(13, service.populares().get(1).getPopularidad());
        assertEquals(11, service.populares().get(2).getPopularidad());
        assertEquals(10, service.populares().get(3).getPopularidad());
        assertEquals(6, service.populares().get(4).getPopularidad());
        assertEquals(5, service.populares().get(5).getPopularidad());
        assertEquals(4, service.populares().get(6).getPopularidad());
        assertEquals(3, service.populares().get(7).getPopularidad());
        assertEquals(2, service.populares().get(8).getPopularidad());
        assertEquals(1, service.populares().get(9).getPopularidad());
    }

    @Test
    public void impopulares(){
        assertEquals(10, service.populares().size());
        assertEquals(0, service.impopulares().get(0).getPopularidad());
        assertEquals(0, service.impopulares().get(1).getPopularidad());
        assertEquals(1, service.impopulares().get(2).getPopularidad());
        assertEquals(1, service.impopulares().get(3).getPopularidad());
        assertEquals(1, service.impopulares().get(4).getPopularidad());
        assertEquals(2, service.impopulares().get(5).getPopularidad());
        assertEquals(3, service.impopulares().get(6).getPopularidad());
        assertEquals(4, service.impopulares().get(7).getPopularidad());
        assertEquals(5, service.impopulares().get(8).getPopularidad());
        assertEquals(6, service.impopulares().get(9).getPopularidad());
    }
}