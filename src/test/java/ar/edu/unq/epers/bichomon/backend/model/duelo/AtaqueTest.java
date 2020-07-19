package ar.edu.unq.epers.bichomon.backend.model.duelo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AtaqueTest {

    private Ataque ataque;

    @Before
    public void setUp(){
        ataque =  new Ataque(1, 100.2, 2);
    }

    @Test
    public void dañoTest() { assertEquals(ataque.daño(), 100.2, 0); }

    @Test
    public void atacadoTest(){
        assertEquals(ataque.atacado(), 1);
    }

    @Test
    public void atacanteTest(){
        assertEquals(ataque.atacante(), 2);
    }

}