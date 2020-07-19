package ar.edu.unq.epers.bichomon.backend.model.especie;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.condicion.CondicionCompuesta;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import org.junit.Test;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class EspecieTest {

    private Especie especieRaiz;

    private Especie especieEvolucion1;

    private Especie especieEvolucion2;

    @Mock
    private CondicionCompuesta condicionEvolucion1;

    @Mock
    private CondicionCompuesta condicionEvolucion2;

    @Mock
    private CondicionCompuesta condicionEvolucion3;

    @Mock
    private Entrenador entrenador;

     @Before
     public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        especieEvolucion2 = new Especie ("Charizard",null, TipoBicho.FUEGO, condicionEvolucion3,85,32,550, especieRaiz);
        especieEvolucion1 = new Especie("Charmeleon", especieEvolucion2,TipoBicho.FUEGO, condicionEvolucion2, 40, 16, 225,  especieRaiz);
        especieRaiz = new Especie("Charmander", especieEvolucion1, TipoBicho.FUEGO, condicionEvolucion1,35,8,100,null);

        Bicho bicho1 = especieRaiz.crearBicho(entrenador);
        Bicho bicho2 = especieEvolucion1.crearBicho(entrenador);
    }

    @Test
    public void testGetNombre(){
        assertEquals("Charmander", especieRaiz.getNombre());
        assertEquals("Charmeleon", especieEvolucion1.getNombre());
        assertEquals("Charizard", especieEvolucion2.getNombre());
    }

    @Test
    public void testGetAltura(){
        assertEquals(35, especieRaiz.getAltura(),0);
        assertEquals(40, especieEvolucion1.getAltura(),0);
        assertEquals(85, especieEvolucion2.getAltura(),0);
    }

    @Test
    public void testGetPeso(){
        assertEquals(8,especieRaiz.getPeso(),0);
        assertEquals(16,especieEvolucion1.getPeso(),0);
        assertEquals(32,especieEvolucion2.getPeso(),0);
    }


    @Test
    public void testGetCondicion(){
        assertEquals(condicionEvolucion1, especieRaiz.getCondicionDeEvolucion());
        assertEquals(condicionEvolucion2, especieEvolucion1.getCondicionDeEvolucion());
        assertEquals(condicionEvolucion3, especieEvolucion2.getCondicionDeEvolucion());
    }

    @Test
    public void testCantidadBichosCreados(){
        assertEquals(1,especieRaiz.getCantidadBichos());
        assertEquals(1, especieEvolucion1.getCantidadBichos());
    }

    @Test
    public void testEsSiguienteEvolucion(){
        assertTrue(especieEvolucion1.esSiguienteEvolucion(especieRaiz));
    }

    @Test
    public void testBuscarSiguienteEvolucion(){
        assertEquals(especieEvolucion2.getNombre(), especieEvolucion1.buscarSiguienteEvolucion().getNombre());
    }
}