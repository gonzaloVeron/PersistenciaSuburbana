package ar.edu.unq.epers.bichomon.backend.model.ubicacion;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.nivel.NivelManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class PuebloTest {

    private Pueblo pueblo;

    @Mock
    public Especie especie1;

    @Mock
    public Especie especie2;

    @Mock
    public Entrenador entrenador;

    @Mock
    Bicho bicho1;

    @Mock
    private NivelManager nivelManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        pueblo = new Pueblo();
        when(especie1.crearBicho(entrenador)).thenReturn(bicho1);
        when(bicho1.getEspecie()).thenReturn(especie1);
        when(nivelManager.capacidadMaximaDeBichos(10)).thenReturn(10);
        when(entrenador.getExperiencia()).thenReturn(1000000);
        when(entrenador.factorNivel(nivelManager)).thenReturn(1000000f);
        when(entrenador.factorTiempo()).thenReturn(10000000f);
    }

    @Test
    public void testGetYSetPoblacion() {
        assertEquals(0, pueblo.getPoblacion());
        pueblo.sumarPoblacion();
        assertEquals(1, pueblo.getPoblacion());
        pueblo.restarPoblacion();
        assertEquals(0, pueblo.getPoblacion());
    }

    @Test
    public void testEsPueblo() {
        assertTrue(pueblo.esPueblo());
    }

    @Test
    public void testAgregarEspecie() {
        pueblo.agregarEspecie(especie1, 100);
        assertEquals(1, pueblo.getEspeciesQueHabitan().size());
    }

    @Test
    public void testEliminarEspecie() {
        pueblo.agregarEspecie(especie1, 50);
        pueblo.agregarEspecie(especie2, 50);
        pueblo.eliminarEspecie(especie1);
        assertEquals(1, pueblo.getEspeciesQueHabitan().size());
    }

    @Test
    public void testFactorPoblacion() {
        //Por defecto el dividendoFactorPoblacion es 100
        pueblo.sumarPoblacion();
        pueblo.sumarPoblacion();
        assertEquals(50, pueblo.getFactorPoblacion());
    }

    @Test
    public void testBichoEncontrado() {
        pueblo.agregarEspecie(especie1, 100);
        assertEquals(1, pueblo.getEspeciesQueHabitan().size());
        Bicho bichoEncontrado = pueblo.bichoEncontrado(entrenador);
        assertEquals(especie1, bichoEncontrado.getEspecie());
    }


    @Test(expected = BichoNoEncontradoException.class)
    public void testBuscarEnDojoSinCampeon() {
        assertEquals(0, pueblo.getEspeciesQueHabitan().size());
        pueblo.sumarPoblacion();
        Bicho bichoEncontrado = pueblo.buscar(entrenador, nivelManager, 100);
    }

}