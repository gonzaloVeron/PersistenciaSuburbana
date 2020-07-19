package ar.edu.unq.epers.bichomon.backend.model.condicion;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.nivel.NivelManager;
import ar.edu.unq.epers.bichomon.backend.service.nivel.NivelServiceImpl;
import org.junit.Test;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BasadoEnEdadTest {

    private BasadoEnEdad condicion;

    @Mock
    private Bicho bichoMock;

    @Mock
    private NivelServiceImpl nivelService;

    @Mock
    private NivelManager nivelManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(nivelService.getNivelManager()).thenReturn(nivelManager);
        when(nivelManager.capacidadMaximaDeBichos(10)).thenReturn(10);
        condicion = new BasadoEnEdad(LocalDate.now());
    }

    @Test
    public void cumpleConLaCondicionTrue() {
        when(bichoMock.getFechaDeCaptura()).thenReturn(LocalDate.of(2030,11,19));

        assertTrue(condicion.cumpleConLaCondicion(bichoMock, nivelManager));
    }

    @Test
    public void cumpleConLaCondicionFalse(){
        when(bichoMock.getFechaDeCaptura()).thenReturn(LocalDate.of(1996,04,25));

        assertFalse(condicion.cumpleConLaCondicion(bichoMock, nivelManager));
    }

    @Test
    public void getCondicionFecha() {
        assertEquals(LocalDate.now(), condicion.getCondicionFecha());
    }
}