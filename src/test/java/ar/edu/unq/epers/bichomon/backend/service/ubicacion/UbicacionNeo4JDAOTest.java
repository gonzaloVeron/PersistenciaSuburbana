package ar.edu.unq.epers.bichomon.backend.service.ubicacion;

import ar.edu.unq.epers.bichomon.backend.dao.impl.UbicacionNeo4JDAO;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.CostoCamino;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Guarderia;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Ubicacion;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.UbicacionMuyLejanaException;
import jdk.nashorn.internal.runtime.ECMAException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class UbicacionNeo4JDAOTest {

    private UbicacionNeo4JDAO dao;

    @Before
    public void setUp() {
        this.dao = new UbicacionNeo4JDAO();
    }

    @Test
    public void crearUbicacion() {
        Ubicacion guarderia1 = new Guarderia();
        guarderia1.setNombre("Guarderia1");
        this.dao.crearUbicacion(guarderia1);

        Ubicacion guarderia2 = new Guarderia();
        guarderia2.setNombre("Guarderia2");
        this.dao.crearUbicacion(guarderia2);

    }

    @Test
    public void conectarUbicaciones() {
        Ubicacion guarderia1 = new Guarderia();
        guarderia1.setNombre("Guarderia1");
        this.dao.crearUbicacion(guarderia1);

        Ubicacion guarderia2 = new Guarderia();
        guarderia2.setNombre("Guarderia2");
        this.dao.crearUbicacion(guarderia2);

        this.dao.conectar(guarderia1.getNombre(), guarderia2.getNombre(), CostoCamino.tierra);

    }

    @Test
    public void conectados() {
        Ubicacion guarderia1 = new Guarderia();
        guarderia1.setNombre("Guarderia1");
        this.dao.crearUbicacion(guarderia1);

        Ubicacion guarderia2 = new Guarderia();
        guarderia2.setNombre("Guarderia2");
        this.dao.crearUbicacion(guarderia2);

        Ubicacion guarderia3 = new Guarderia();
        guarderia3.setNombre("Guarderia3");
        this.dao.crearUbicacion(guarderia3);

        Ubicacion guarderia4 = new Guarderia();
        guarderia4.setNombre("Guarderia4");
        this.dao.crearUbicacion(guarderia4);

        Ubicacion guarderia5 = new Guarderia();
        guarderia5.setNombre("Guarderia5");
        this.dao.crearUbicacion(guarderia5);

        this.dao.conectar(guarderia1.getNombre(), guarderia2.getNombre(), CostoCamino.tierra);
        this.dao.conectar(guarderia1.getNombre(), guarderia3.getNombre(), CostoCamino.tierra);
        this.dao.conectar(guarderia1.getNombre(), guarderia4.getNombre(), CostoCamino.tierra);
        this.dao.conectar(guarderia4.getNombre(), guarderia5.getNombre(), CostoCamino.tierra);

        List<String> ubicaciones = this.dao.conectados(guarderia1.getNombre(), CostoCamino.tierra);
        assertEquals(3, ubicaciones.size());
        assertTrue(ubicaciones.contains(guarderia2.getNombre()));
        assertTrue(ubicaciones.contains(guarderia4.getNombre()));
        assertTrue(ubicaciones.contains(guarderia3.getNombre()));
        assertFalse(ubicaciones.contains(guarderia5.getNombre()));
    }

    @Test
    public void costoEntreUbicaciones() throws Exception {
        Ubicacion guarderia1 = new Guarderia();
        guarderia1.setNombre("Guarderia1");
        this.dao.crearUbicacion(guarderia1);

        Ubicacion guarderia2 = new Guarderia();
        guarderia2.setNombre("Guarderia2");
        this.dao.crearUbicacion(guarderia2);

        this.dao.conectar(guarderia1.getNombre(), guarderia2.getNombre(), CostoCamino.aire);
        this.dao.conectar(guarderia1.getNombre(), guarderia2.getNombre(), CostoCamino.tierra);

        assertEquals(CostoCamino.valueOf("tierra").getValue(), this.dao.getCostoEntreUbicaciones(guarderia1.getNombre(), guarderia2.getNombre()));
    }




}
