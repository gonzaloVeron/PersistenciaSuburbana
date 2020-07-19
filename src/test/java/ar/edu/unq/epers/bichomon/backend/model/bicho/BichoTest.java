package ar.edu.unq.epers.bichomon.backend.model.bicho;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import ar.edu.unq.epers.bichomon.backend.model.duelo.Ataque;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.nivel.NivelManager;
import ar.edu.unq.epers.bichomon.backend.service.nivel.NivelServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

public class BichoTest {

    private Bicho bicho;

    private Bicho otroBicho;

    @Mock
    private Especie especieAgua;

    @Mock
    private Especie especieAguaEvolucion;

    @Mock
    private Entrenador entrenador;

    @Mock
    private Ataque ataque;

    @Mock
    private NivelServiceImpl nivelService;

    @Mock
    private NivelManager nivelManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(especieAgua.getNombre()).thenReturn("Squirtle");
        when(especieAgua.getSiguienteEvolucion()).thenReturn(especieAguaEvolucion);
        when(nivelService.getNivelManager()).thenReturn(nivelManager);
        when(nivelManager.capacidadMaximaDeBichos(10)).thenReturn(10);
        when(especieAguaEvolucion.getNombre()).thenReturn("Wartortle");
        bicho = new Bicho(especieAgua, entrenador);
        otroBicho = new Bicho(especieAgua, entrenador);


    }

    @Test
    public void testGetEspecie(){
        assertEquals(especieAgua, bicho.getEspecie());
    }

    @Test
    public void testGetEnergia(){
        bicho.setEnergia(100);

        assertEquals(100,bicho.getEnergia());
    }

    @Test
    public void testGetFechaDeCaptura(){
        bicho.setFechaDeCaptura(LocalDate.now());

        assertEquals(LocalDate.now(),bicho.getFechaDeCaptura());
    }

    @Test
    public void testGetVictorias(){
        bicho.incrementarVictorias();

        assertEquals(1,bicho.getVictorias());
    }

    @Test
    public void testGetEntrenador(){
        bicho.serAdoptado(entrenador);

        assertEquals(entrenador, bicho.getEntrenador());
    }

    @Test
    public void testEvolucionar(){
        when(especieAgua.buscarSiguienteEvolucion()).thenReturn(especieAguaEvolucion);
        when(especieAgua.puedeEvolucionar(bicho, nivelManager)).thenReturn(true);
        when(especieAgua.tieneSiguienteEvolucion()).thenReturn(true);

        bicho.evolucionar(nivelManager, 5);

        assertEquals(especieAgua.getSiguienteEvolucion().getNombre(), bicho.getEspecie().getNombre());
    }

    @Test
    public void testPuedeEvolucionar(){
        when(especieAgua.puedeEvolucionar(bicho, nivelManager)).thenReturn(true);
        when(especieAgua.tieneSiguienteEvolucion()).thenReturn(true);

        assertTrue(bicho.puedeEvolucionar(nivelManager));
    }

    @Test
    public void testNoPuedeEvolucionar(){
        when(especieAgua.puedeEvolucionar(bicho, nivelManager)).thenReturn(false);

        assertFalse(bicho.puedeEvolucionar(nivelManager));
    }

    @Test
    public void testAtacar(){

        when(ataque.atacado()).thenReturn(0);
        when(ataque.atacante()).thenReturn(0);

        assertEquals(ataque.atacado(),bicho.atacar(otroBicho).atacado());
        assertEquals(ataque.atacante(),bicho.atacar(otroBicho).atacante());
    }

    @Test
    public void testIncrementarEnergia(){
        int energiaInicial = bicho.getEnergia();
        bicho.incrementarEnergia();

        assertTrue(bicho.getEnergia() > energiaInicial);

    }
}