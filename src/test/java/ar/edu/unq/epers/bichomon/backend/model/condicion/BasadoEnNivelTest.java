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

public class BasadoEnNivelTest {

    private BasadoEnNivel condicion;

    @Mock
    private Entrenador entrenador;

    @Mock
    private Bicho bicho;

    @Mock
    private NivelServiceImpl nivelService;

    @Mock
    private NivelManager nivelManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(nivelService.getNivelManager()).thenReturn(nivelManager);
        when(nivelManager.capacidadMaximaDeBichos(10)).thenReturn(10);
        condicion = new BasadoEnNivel(5);

        when(bicho.getEntrenador()).thenReturn(entrenador);
        when(entrenador.getNivel(nivelManager)).thenReturn(10);
    }

    @Test
    public void testGetCondicionNivel(){
        assertEquals(5, condicion.getCondicionNivel());
    }

    @Test
    public void testCumpleConLaCondicionTrue(){
        assertTrue(condicion.cumpleConLaCondicion(bicho, nivelManager));
    }

    @Test
    public void testCumpleConLaCondicionFalse(){
        when(entrenador.getNivel(nivelManager)).thenReturn(4);

        assertFalse(condicion.cumpleConLaCondicion(bicho, nivelManager));
    }

}