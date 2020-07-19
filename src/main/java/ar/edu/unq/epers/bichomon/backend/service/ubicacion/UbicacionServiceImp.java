package ar.edu.unq.epers.bichomon.backend.service.ubicacion;

import ar.edu.unq.epers.bichomon.backend.dao.UbicacionDAO;
import ar.edu.unq.epers.bichomon.backend.model.campeon.Campeon;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Ubicacion;
import ar.edu.unq.epers.bichomon.backend.service.especie.EspecieNoExistente;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

import java.util.List;

public class UbicacionServiceImp {

    private UbicacionDAO ubicacionDAO;

    public UbicacionServiceImp(UbicacionDAO ubicacionDAO) {
        this.ubicacionDAO = ubicacionDAO;
    }

    public void crearUbicacion(Ubicacion ubicacion){
        Runner.runInSession( () -> {
            this.ubicacionDAO.guardar(ubicacion);
            return null;
        });
    }

    public Ubicacion getUbicacion(String nombreUbicacion) {
        return Runner.runInSession( () -> {
            Ubicacion ubicacion = ubicacionDAO.recuperar(nombreUbicacion);
            if(ubicacion == null){
                throw new EspecieNoExistente(nombreUbicacion);
            }
            return ubicacion;
        });
    }

    public void actualizarUbicacion(Ubicacion ubicacion) {
        Runner.runInSession( () -> {
            this.ubicacionDAO.actualizar(ubicacion);
            return null;
        });
    }

    public List<Ubicacion> getAllUbicaciones(){
        return Runner.runInSession( () -> {
            return ubicacionDAO.recuperarTodos();
        });
    }

}
