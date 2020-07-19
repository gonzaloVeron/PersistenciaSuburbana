package ar.edu.unq.epers.bichomon.backend.model.duelo;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.campeon.Campeon;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Dojo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class DueloTest {

    private Duelo duelo;

    @Mock
    private Dojo dojo;

    @Mock
    private Campeon campeon;

    @Mock
    private Entrenador entrenadorDelCampeon;

    @Mock
    private Entrenador entrenadorDelRetador;

    @Mock
    private Especie especieAgua;

    @Mock
    private Especie especieFuego;

    @Mock
    private Bicho bichoRetador;

    @Mock
    private Bicho bichoCampeon;

    @Mock
    private Ataque ataque1;

    @Mock
    private Ataque ataque2;

    @Mock
    private Ataque ataque3;

    @Mock
    private Ataque ataque4;

    @Mock
    private Ataque ataque5;

    @Mock
    private Ataque ataque6;

    @Mock
    private Ataque ataque7;

    @Mock
    private Ataque ataque8;

    @Mock
    private Ataque ataque9;

    @Mock
    private Ataque ataque10;

    @Mock
    private ResultadoCombate resultado;

    private ArrayList<Ataque> ataques;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        ataques = new ArrayList<Ataque>();

        when(dojo.getCampeon()).thenReturn(campeon);
        when(campeon.getBicho()).thenReturn(bichoCampeon);

        ataques.add(ataque1);
        ataques.add(ataque2);
        ataques.add(ataque3);
        ataques.add(ataque4);
        ataques.add(ataque5);
        ataques.add(ataque6);
        ataques.add(ataque7);
        ataques.add(ataque8);
        ataques.add(ataque9);
        ataques.add(ataque10);

        /* Ataque 1 - BichoRetador */
        when(ataque1.atacante()).thenReturn(1);
        when(ataque1.atacado()).thenReturn(2);
        when(ataque1.daño()).thenReturn(650d);
        /* Ataque 1 - BichoCampeon */
        when(ataque2.atacante()).thenReturn(2);
        when(ataque2.atacado()).thenReturn(1);
        when(ataque2.daño()).thenReturn(700d);
        /* Ataque 2 - BichoRetador */
        when(ataque3.atacante()).thenReturn(1);
        when(ataque3.atacado()).thenReturn(2);
        when(ataque3.daño()).thenReturn(650d);
        /* Ataque 2 - BichoCampeon */
        when(ataque4.atacante()).thenReturn(2);
        when(ataque4.atacado()).thenReturn(1);
        when(ataque4.daño()).thenReturn(500d);
        /* Ataque 3 - BichoRetador */
        when(ataque5.atacante()).thenReturn(1);
        when(ataque5.atacado()).thenReturn(2);
        when(ataque5.daño()).thenReturn(1170d);
        /* Ataque 3 - BichoCampeon */
        when(ataque6.atacante()).thenReturn(2);
        when(ataque6.atacado()).thenReturn(1);
        when(ataque6.daño()).thenReturn(600d);
        /* Ataque 4 - BichoRetador */
        when(ataque7.atacante()).thenReturn(1);
        when(ataque7.atacado()).thenReturn(2);
        when(ataque7.daño()).thenReturn(650d);
        /* Ataque 4 - BichoCampeon */
        when(ataque8.atacante()).thenReturn(2);
        when(ataque8.atacado()).thenReturn(1);
        when(ataque8.daño()).thenReturn(500d);
        /* Ataque 5 - BichoRetador */
        when(ataque9.atacante()).thenReturn(1);
        when(ataque9.atacado()).thenReturn(2);
        when(ataque9.daño()).thenReturn(910d);
        /* Ataque 5 - BichoCampeon */
        when(ataque10.atacante()).thenReturn(2);
        when(ataque10.atacado()).thenReturn(1);
        when(ataque10.daño()).thenReturn(700d);

        /* BichoRetador */
        when(bichoRetador.getID()).thenReturn(1);
        when(bichoRetador.getEnergia()).thenReturn(1300);
        /* BichoCampeon */
        when(bichoCampeon.getID()).thenReturn(2);
        when(bichoCampeon.getEnergia()).thenReturn(1000);

        when(resultado.getGanador()).thenReturn(bichoRetador);
        when(resultado.cantidadDeAtaques()).thenReturn(10);

        duelo = new Duelo(dojo, bichoRetador);

        when(bichoCampeon.getEntrenador()).thenReturn(entrenadorDelCampeon);
        when(bichoRetador.getEntrenador()).thenReturn(entrenadorDelRetador);
    }

    @Test
    public void pelear(){
        /**
         * En este test no se pueden usar mocks porque en la funcion "pelear()" que devuelve un "resultadoCombate"
         * requiere que los mocks generen un objeto "Ataque" y no puede ser posible utilizando mocks
         */
        Bicho bichoRetadorMock = new Bicho();
        Bicho bichoCampeonMock = new Bicho();

        bichoRetadorMock.setEnergia(1300);
        bichoCampeonMock.setEnergia(1);

        bichoCampeonMock.setEntrenador(entrenadorDelCampeon);
        bichoRetadorMock.setEntrenador(entrenadorDelRetador);

        Dojo dojo2 = new Dojo();


        Campeon campeon = dojo2.actualizarYRetornarCampeon(bichoCampeonMock, LocalDate.now());

        Duelo dueloN = new Duelo(dojo2, bichoRetadorMock);

        assertTrue(dueloN.pelear(10).getGanador().getEnergia() > 1300);
    }

    @Test
    public void debeTerminarLaPelea() {
        duelo.setAtaques(ataques);

        assertTrue(duelo.debeTerminarLaPelea());
    }

    @Test
    public void pasoLos10Turnos() {
        int n = 5;
        assertFalse(duelo.pasoLos10Turnos(n));
    }

    @Test
    public void terminarPelea() {
        duelo.setAtaques(ataques);

        assertEquals(resultado.getGanador(), duelo.terminarPelea(10).getGanador());
        verify(bichoRetador,times(1)).incrementarEnergia();
        verify(bichoCampeon,times(1)).incrementarEnergia();
        verify(bichoRetador,times(1)).incrementarVictorias();
    }

    @Test
    public void ganador() {
        duelo.setAtaques(ataques);

        assertEquals(bichoRetador, duelo.ganador());
    }

    @Test
    public void dañoCausadoA() {
        duelo.setAtaques(ataques);

        assertEquals(4030,duelo.dañoCausadoA(bichoCampeon),0);
    }
}