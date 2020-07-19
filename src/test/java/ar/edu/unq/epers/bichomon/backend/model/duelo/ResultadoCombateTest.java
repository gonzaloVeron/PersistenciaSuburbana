package ar.edu.unq.epers.bichomon.backend.model.duelo;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ResultadoCombateTest {

    @Mock
    private Ataque ataque1;
    @Mock
    private Ataque ataque2;
    @Mock
    private Ataque ataque3;

    private ArrayList<Ataque> ataques;

    @Mock
    private Bicho bichoGanador;

    private ResultadoCombate resultado;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        ataques = new ArrayList<Ataque>();

        ataques.add(ataque1);
        ataques.add(ataque2);

        /* Ataque 1 */
        when(ataque1.daño()).thenReturn(5d);
        when(ataque1.atacante()).thenReturn(1);
        when(ataque1.atacado()).thenReturn(2);
        /* Ataque 2 */
        when(ataque2.daño()).thenReturn(5d);
        when(ataque2.atacante()).thenReturn(2);
        when(ataque2.atacado()).thenReturn(1);
        /* Ataque 3 */
        when(ataque3.daño()).thenReturn(5d);
        when(ataque3.atacante()).thenReturn(1);
        when(ataque3.atacado()).thenReturn(2);

        when(bichoGanador.getID()).thenReturn(1);

        resultado = new ResultadoCombate(ataques, bichoGanador);
    }

    @Test
    public void addAtaqueTest(){
        resultado.addAtaque(ataque3);

        assertEquals(3, resultado.cantidadDeAtaques());
    }

    @Test
    public void ataqueRecibidoTest(){
        assertEquals(5d, resultado.ataqueRecibido(resultado.getGanador().getID()), 0);
    }

    @Test
    public void testGetGanador(){
        assertEquals(bichoGanador, resultado.getGanador());
    }
}