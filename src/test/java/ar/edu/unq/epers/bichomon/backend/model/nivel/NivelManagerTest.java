package ar.edu.unq.epers.bichomon.backend.model.nivel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anySet;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NivelManagerTest {

    private NivelManager nivel;

    private ArrayList<Nivel> niveles;

    @Mock
    private Nivel nivel1;

    @Mock
    private Nivel nivel2;

    @Mock
    private Nivel nivel3;

    @Mock
    private Nivel nivel4;

    @Mock
    private Nivel nivel5;

    @Mock
    private Nivel nivel6;

    @Mock
    private Nivel nivel7;

    @Mock
    private Nivel nivel8;

    @Mock
    private Nivel nivel9;

    @Mock
    private Nivel nivel10;

    @Mock
    private Nivel nivel11;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        niveles = new ArrayList<Nivel>();

        niveles.add(nivel1);
        niveles.add(nivel2);
        niveles.add(nivel3);
        niveles.add(nivel4);
        niveles.add(nivel5);
        niveles.add(nivel6);
        niveles.add(nivel7);
        niveles.add(nivel8);
        niveles.add(nivel9);
        niveles.add(nivel10);

        nivel = new NivelManager(niveles, 100);

        /* Nivel 1 */
        when(nivel1.getExpInicial()).thenReturn(0);
        when(nivel1.getExpFinal()).thenReturn(99);
        when(nivel1.getNivel()).thenReturn(1);
        when(nivel1.getCapacidadMaximaDeBichos()).thenReturn(1);
        /* Nivel 2 */
        when(nivel2.getExpInicial()).thenReturn(100);
        when(nivel2.getExpFinal()).thenReturn(400);
        when(nivel2.getNivel()).thenReturn(2);
        when(nivel2.getCapacidadMaximaDeBichos()).thenReturn(2);
        /* Nivel 3 */
        when(nivel3.getExpInicial()).thenReturn(400);
        when(nivel3.getExpFinal()).thenReturn(1000);
        when(nivel3.getNivel()).thenReturn(3);
        when(nivel3.getCapacidadMaximaDeBichos()).thenReturn(3);
        /* Nivel 4 */
        when(nivel4.getExpInicial()).thenReturn(1000);
        when(nivel4.getExpFinal()).thenReturn(2000);
        when(nivel4.getNivel()).thenReturn(4);
        when(nivel4.getCapacidadMaximaDeBichos()).thenReturn(4);
        /* Nivel 5 */
        when(nivel5.getExpInicial()).thenReturn(2000);
        when(nivel5.getExpFinal()).thenReturn(3000);
        when(nivel5.getNivel()).thenReturn(5);
        when(nivel5.getCapacidadMaximaDeBichos()).thenReturn(5);
        /* Nivel 6 */
        when(nivel6.getExpInicial()).thenReturn(3000);
        when(nivel6.getExpFinal()).thenReturn(4000);
        when(nivel6.getNivel()).thenReturn(6);
        when(nivel6.getCapacidadMaximaDeBichos()).thenReturn(6);
        /* Nivel 7 */
        when(nivel7.getExpInicial()).thenReturn(4000);
        when(nivel7.getExpFinal()).thenReturn(5000);
        when(nivel7.getNivel()).thenReturn(7);
        when(nivel7.getCapacidadMaximaDeBichos()).thenReturn(7);
        /* Nivel 8 */
        when(nivel8.getExpInicial()).thenReturn(5000);
        when(nivel8.getExpFinal()).thenReturn(6000);
        when(nivel8.getNivel()).thenReturn(8);
        when(nivel8.getCapacidadMaximaDeBichos()).thenReturn(8);
        /* Nivel 9 */
        when(nivel9.getExpInicial()).thenReturn(6000);
        when(nivel9.getExpFinal()).thenReturn(7000);
        when(nivel9.getNivel()).thenReturn(9);
        when(nivel9.getCapacidadMaximaDeBichos()).thenReturn(9);
        /* Nivel 10 */
        when(nivel10.getExpInicial()).thenReturn(7000);
        when(nivel10.getExpFinal()).thenReturn(8000);
        when(nivel10.getNivel()).thenReturn(10);
        when(nivel10.getCapacidadMaximaDeBichos()).thenReturn(10);
        /* Nivel 11*/
        when(nivel11.getExpInicial()).thenReturn(99999);
        when(nivel11.getExpFinal()).thenReturn(99999);
        when(nivel11.getNivel()).thenReturn(11);
        when(nivel11.getCapacidadMaximaDeBichos()).thenReturn(99999);
    }

    @Test
    public void factorNivel() {
        assertEquals(100, nivel.factorNivel());
    }

    @Test
    public void getNivel() {
        assertEquals(7, nivel.getNivel(4469));
    }

    @Test
    public void capacidadMaximaDeBichos() {
        assertEquals(9, nivel.capacidadMaximaDeBichos(6681));
    }

    @Test
    public void agregarNivel() {
        niveles.add(nivel11);
        nivel.agregarNivel(nivel11);

        assertEquals(niveles.size(), nivel.cantidadDeNiveles());
    }

    @Test
    public void modificarNivel() {
        nivel.agregarNivel(nivel11);
        nivel.modificarNivel(11,8000,9000,11);

        verify(nivel11,times(1)).setExpInicial(8000);
        verify(nivel11,times(1)).setExpFinal(9000);
        verify(nivel11,times(1)).setCapacidadMaximaDeBichos(11);
    }

    @Test
    public void cantidadDeNiveles(){
        assertEquals(10, nivel.cantidadDeNiveles());
    }
}