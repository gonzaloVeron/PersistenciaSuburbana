package ar.edu.unq.epers.bichomon.backend.dao;

import ar.edu.unq.epers.bichomon.backend.model.experiencia.Experiencia;

public interface ExperienciaDAO {

    int obtenerExperiencia(String nombre);

    void actualizarExperiencia(String nombre, int valor);

    void crearExperiencia(String nombre, int valor);

}
