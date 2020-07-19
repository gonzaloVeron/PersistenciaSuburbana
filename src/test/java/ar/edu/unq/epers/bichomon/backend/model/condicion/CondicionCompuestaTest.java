package ar.edu.unq.epers.bichomon.backend.model.condicion;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.*;
import ar.edu.unq.epers.bichomon.backend.model.nivel.NivelManager;
import ar.edu.unq.epers.bichomon.backend.service.nivel.NivelServiceImpl;
import org.junit.Test;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CondicionCompuestaTest {

    private CondicionCompuesta condicion;

    @Mock
    private Bicho bicho;

    @Mock
    private BasadoEnEdad condEdad;

    @Mock
    private BasadoEnNivel condNivel;

    @Mock
    private BasadoEnEnergia condEnergia;

    @Mock
    private BasadoEnVictoria condVictoria;

    @Mock
    private NivelServiceImpl nivelService;

    @Mock
    private NivelManager nivelManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(nivelService.getNivelManager()).thenReturn(nivelManager);
        when(nivelManager.capacidadMaximaDeBichos(10)).thenReturn(10);
        condicion = new CondicionCompuesta();

        condicion.agregarCondicion(condEdad);
        condicion.agregarCondicion(condNivel);
        condicion.agregarCondicion(condEnergia);
        condicion.agregarCondicion(condVictoria);

        when(condEdad.cumpleConLaCondicion(bicho, nivelManager)).thenReturn(true);
        when(condNivel.cumpleConLaCondicion(bicho, nivelManager)).thenReturn(true);
        when(condEnergia.cumpleConLaCondicion(bicho, nivelManager)).thenReturn(true);
    }

    @Test
    public void testAgregarCondicion(){
        assertEquals(4, condicion.cantidadDeCondiciones());
    }

    @Test
    public void testRemoverCondicion(){
        condicion.removerCondicion(condEdad);

        assertEquals(3, condicion.cantidadDeCondiciones());
    }

    @Test
    public void testCumpleTodasLasCondiciones(){
        when(condVictoria.cumpleConLaCondicion(bicho, nivelManager)).thenReturn(true);

        assertTrue(condicion.cumpleConLaCondicion(bicho, nivelManager));
    }

    @Test
    public void testNoCumpleTodasLasCondiciones(){
        when(condVictoria.cumpleConLaCondicion(bicho, nivelManager)).thenReturn(false);

        assertFalse(condicion.cumpleConLaCondicion(bicho, nivelManager));
    }

}