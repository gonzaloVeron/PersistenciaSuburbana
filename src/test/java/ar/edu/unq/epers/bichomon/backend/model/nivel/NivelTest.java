package ar.edu.unq.epers.bichomon.backend.model.nivel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NivelTest {

    private Nivel nivel;

    @Before
    public void setUp() {
        nivel = new Nivel(1, 10, 100);
    }

    @Test
    public void getNivelTest(){
        assertEquals(0, nivel.getNivel());
    }

    @Test
    public void getCapacidadMaximaDeBichosTest(){
        assertEquals(1, nivel.getCapacidadMaximaDeBichos());
    }

    @Test
    public void getExpInicialTest(){
        assertEquals(10, nivel.getExpInicial());
    }

    @Test
    public void getExpFinalTest(){
        assertEquals(100, nivel.getExpFinal());
    }

    @Test
    public void setCapacidadMaximaDeBichosTest(){
        nivel.setCapacidadMaximaDeBichos(20);

        assertEquals(20, nivel.getCapacidadMaximaDeBichos());
    }

    @Test
    public void setExpInicialTest(){
        nivel.setExpInicial(5);

        assertEquals(5, nivel.getExpInicial());
    }

    @Test
    public void setExpFinalTest(){
        nivel.setExpFinal(105);

        assertEquals(105, nivel.getExpFinal());
    }
}