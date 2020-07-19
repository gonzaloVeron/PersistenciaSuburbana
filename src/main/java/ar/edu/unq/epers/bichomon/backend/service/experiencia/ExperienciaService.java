package ar.edu.unq.epers.bichomon.backend.service.experiencia;

public interface ExperienciaService {

    int obtenerExperiencia(String nombre);

    void actualizarExperiencia(String nombre, int valor);

    void crearExperiencia(String nombre, int valor);

}
