package ar.edu.unq.epers.bichomon.backend.model.ubicacion;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.nivel.NivelManager;
import ar.edu.unq.epers.bichomon.backend.service.nivel.NivelServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


public class GuarderiaTest {

    private Guarderia guarderia = new Guarderia();

    @Mock
    private Bicho bicho1;

    @Mock
    private Bicho bicho2;

    @Mock
    private Bicho bicho3;

    @Mock
    private Entrenador entrenador1;

    @Mock
    private Entrenador entrenador2;

    @Mock
    private NivelServiceImpl nivelService;

    @Mock
    private NivelManager nivelManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(nivelService.getNivelManager()).thenReturn(nivelManager);
        when(nivelManager.capacidadMaximaDeBichos(10)).thenReturn(10);
        when(entrenador1.factorNivel(nivelManager)).thenReturn(1f);
        when(entrenador1.getExperiencia()).thenReturn(1000);
        when(entrenador1.factorNivel(nivelManager)).thenReturn(1000f);
        when(entrenador1.factorTiempo()).thenReturn(1000f);
        when(bicho1.getEntrenador()).thenReturn(entrenador1);
        when(bicho2.getEntrenador()).thenReturn(entrenador2);
        when(bicho3.getEntrenador()).thenReturn(entrenador2);
        when(bicho1.noFueAbandonadoAntesPor(entrenador2)).thenReturn(true);
        when(bicho2.noFueAbandonadoAntesPor(entrenador2)).thenReturn(true);
        when(bicho3.noFueAbandonadoAntesPor(entrenador2)).thenReturn(true);
    }

    @Test
    public void testEsGuarderia() {
        assertTrue(guarderia.esGuarderia());
    }

    @Test
    public void testAbandonarYRecuperarBicho() {
        guarderia.abandonarBicho(bicho1);
        assertTrue(guarderia.getBichosAbandonados().contains(bicho1));
    }

    @Test
    public void testBuscarBicho() {
        guarderia.abandonarBicho(bicho1);
        guarderia.abandonarBicho(bicho2);
        guarderia.abandonarBicho(bicho3);
        assertEquals(3, guarderia.getBichosAbandonados().size());
        Bicho bichoEncontrado = guarderia.bichoEncontrado(entrenador2);
        assertEquals(2, guarderia.getBichosAbandonados().size());
    }

    @Test(expected = BichoNoEncontradoException.class)
    public void testBuscarEnDojoSinCampeon() {
        assertEquals(0, guarderia.getCantidadBichosAbandonados());
        Bicho bichoEncontrado = guarderia.buscar(entrenador1, nivelManager, 100);
    }


}