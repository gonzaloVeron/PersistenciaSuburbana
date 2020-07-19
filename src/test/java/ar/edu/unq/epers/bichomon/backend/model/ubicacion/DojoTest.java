package ar.edu.unq.epers.bichomon.backend.model.ubicacion;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.nivel.NivelManager;
import ar.edu.unq.epers.bichomon.backend.service.nivel.NivelServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class DojoTest {

    private Dojo dojo;

    @Mock
    private Entrenador entrenador;

    @Mock
    private Bicho bicho1;

    @Mock
    private Bicho bicho2;

    @Mock
    private Especie especieBicho;

    @Mock
    private Especie especieRaiz;

    @Mock
    private NivelServiceImpl nivelService;

    @Mock
    private NivelManager nivelManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(nivelService.getNivelManager()).thenReturn(nivelManager);
        when(nivelManager.capacidadMaximaDeBichos(10)).thenReturn(10);
        dojo = new Dojo();
        when(bicho1.getEspecie()).thenReturn(especieBicho);
        when(especieBicho.getEspecieRaiz()).thenReturn(especieRaiz);
        when(entrenador.getExperiencia()).thenReturn(1000);
        when(entrenador.factorNivel(nivelManager)).thenReturn(1000f);
        when(entrenador.factorTiempo()).thenReturn(1000f);
        when(especieRaiz.crearBicho(entrenador)).thenReturn(bicho2);
        when(bicho2.getEspecie()).thenReturn(especieRaiz);
        when(especieBicho.getEspecieRaiz()).thenReturn(especieRaiz);
    }

    @Test(expected = BichoNoEncontradoException.class)
    public void testBuscarEnDojoSinCampeon() {
        assertNull(dojo.getCampeon());
        Bicho bichoEncontrado = dojo.buscar(entrenador, nivelManager, 100);
    }

    @Test
    public void testGetYSetCampeonNuevo() {
        dojo.actualizarYRetornarCampeon(bicho2, LocalDate.now());
        assertEquals(dojo.getCampeon().getId(), bicho2.getID());
    }

    @Test
    public void testEsDojo() {
        assertTrue(dojo.esDojo());
    }

    @Test
    public void testBusqueda() {
        dojo.actualizarYRetornarCampeon(bicho1, LocalDate.now());
        Especie especie = dojo.bichoEncontrado(entrenador).getEspecie();
        assertEquals(especie, especieRaiz);
    }

}