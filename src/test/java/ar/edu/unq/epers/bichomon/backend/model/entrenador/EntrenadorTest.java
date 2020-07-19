package ar.edu.unq.epers.bichomon.backend.model.entrenador;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.nivel.NivelManager;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Pueblo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class EntrenadorTest {

    private Entrenador entrenador;

    @Mock
    private Bicho bicho1;

    @Mock
    private Bicho bicho2;

    @Mock
    private NivelManager nivelManager;

    @Mock
    private Pueblo puebloPaleta;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        entrenador = new Entrenador("Spore",puebloPaleta);

        entrenador.agregarExperiencia(250);

        when(nivelManager.factorNivel()).thenReturn(100);
        when(nivelManager.getNivel(250)).thenReturn(2);
        when(nivelManager.capacidadMaximaDeBichos(250)).thenReturn(2);
    }

    @Test
    public void testNombre(){
        assertEquals("Spore", entrenador.nombre());
    }

    @Test
    public void testUbicacion(){
        assertEquals(puebloPaleta, entrenador.ubicacion());
    }

    @Test
    public void testCapacidadMaxima(){
        System.out.println(" " + nivelManager.getNivel(250));

        assertEquals(2, entrenador.capacidadMaxima(nivelManager),0);
    }

    @Test
    public void testPuedeCapturarBicho(){
        entrenador.capturarBicho(bicho1);

        assertTrue(entrenador.puedeCapturarBicho(nivelManager));
    }

    @Test
    public void testFactorNivel(){
        assertEquals(50, entrenador.factorNivel(nivelManager),0);
    }

    @Test
    public void testGetNivel(){
        assertEquals(2,entrenador.getNivel(nivelManager));
    }

    @Test
    public void testAgregarExperiencia(){
        entrenador.agregarExperiencia(500);

        assertEquals(750, entrenador.getExperiencia());
    }

    @Test
    public void testCapturarBicho(){
        entrenador.capturarBicho(bicho2);

        assertEquals(1, entrenador.cantidadBichos());
    }

    @Test
    public void factorTiempo(){
        entrenador.setUlimaCaptura(LocalDate.now());

        assertEquals(1, entrenador.factorTiempo(), 0);
    }

}
