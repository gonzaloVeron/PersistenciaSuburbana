package ar.edu.unq.epers.bichomon.backend.model.condicion;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.nivel.NivelManager;
import ar.edu.unq.epers.bichomon.backend.service.nivel.NivelServiceImpl;
import org.junit.Test;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BasadoEnEnergiaTest {

    private BasadoEnEnergia condicion;

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
        condicion = new BasadoEnEnergia(150);

        }

    @Test
    public void testGetEnergia(){
        assertEquals(150, condicion.getEnergia());
    }

    @Test
    public void testCumpleConLaCondicionTrue(){
        when(bicho.getEnergia()).thenReturn(160);

        assertTrue(condicion.cumpleConLaCondicion(bicho, nivelManager));
    }

    @Test
    public void testCumpleConLaCondicionFalse(){
        when(bicho.getEnergia()).thenReturn(40);

        assertFalse(condicion.cumpleConLaCondicion(bicho, nivelManager));
    }
}