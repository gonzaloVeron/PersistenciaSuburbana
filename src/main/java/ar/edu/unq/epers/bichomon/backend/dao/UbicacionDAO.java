package ar.edu.unq.epers.bichomon.backend.dao;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.campeon.Campeon;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Dojo;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Ubicacion;

import java.util.List;

public interface UbicacionDAO {

    void guardar(Ubicacion ubicacion);

    void actualizar(Ubicacion ubicacion);

    void eliminar(Ubicacion ubicacion);

    Ubicacion recuperar(String nombre);

    List<Ubicacion> recuperarTodos();

    List<Ubicacion> recuperarTodos(List<String> nombresDeUbicaciones);

    int getCantidadEntrenadores(String nombreUbicacion);

    void actualizarCampeon(Ubicacion dojo, Bicho bicho);
}
