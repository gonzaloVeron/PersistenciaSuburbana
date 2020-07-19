package ar.edu.unq.epers.bichomon.backend.service.mapa;

import ar.edu.unq.epers.bichomon.backend.dao.CampeonDAO;
import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDAO;
import ar.edu.unq.epers.bichomon.backend.dao.UbicacionDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.UbicacionNeo4JDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.campeon.Campeon;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.CaminoMuyCostosoException;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.CostoCamino;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Ubicacion;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.UbicacionMuyLejanaException;
import ar.edu.unq.epers.bichomon.backend.service.feed.FeedService;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import ar.edu.unq.epers.bichomon.backend.service.ubicacion.UbicacionIncorrectaException;
import ar.edu.unq.epers.bichomon.backend.service.ubicacion.UbicacionNoExistente;

import java.util.List;

public class MapaService {


    private UbicacionDAO ubicacionDAO;
    private CampeonDAO campeonDAO;
    private EntrenadorDAO entrenadorDAO;
    private UbicacionNeo4JDAO ubicacionNeo4JDAO;
    private FeedService feedService;

    public MapaService(UbicacionDAO ubicacionDAO, CampeonDAO campeonDAO, EntrenadorDAO entrenadorDAO, UbicacionNeo4JDAO ubicacionNeo4JDAO, FeedService feedService) {
        this.ubicacionDAO = ubicacionDAO;
        this.campeonDAO = campeonDAO;
        this.entrenadorDAO = entrenadorDAO;
        this.ubicacionNeo4JDAO = ubicacionNeo4JDAO;
        this.feedService = feedService;
    }

    public void mover(String entrenador, String destino) {
        Runner.runInSession( () -> {

            Entrenador entrenadorR = entrenadorDAO.recuperar(entrenador);
            Ubicacion ubicacionNueva = ubicacionDAO.recuperar(destino);

                int costo = this.ubicacionNeo4JDAO.getCostoEntreUbicaciones(entrenadorR.ubicacion().getNombre(), destino);

                this.feedService.guardarArribo(entrenadorR, ubicacionNueva);

                entrenadorR.mover(ubicacionNueva, costo);

                return null;
        });
    }

    public void moverMasCorto(String entrenador, String destino){
        Runner.runInSession(() -> {

            Entrenador ent = entrenadorDAO.recuperar(entrenador);
            Ubicacion ubicacion = ubicacionDAO.recuperar(destino);

            int costo = ubicacionNeo4JDAO.getCostoCaminoMasCorto(ent.ubicacion().getNombre(), ubicacion.getNombre());

            this.feedService.guardarArribo(ent, ubicacion);

            ent.mover(ubicacion, costo);

            return null;
        });

    }

    public int cantidadEntrenadores(String ubicacion) {
        return Runner.runInSession( () -> {
            return ubicacionDAO.getCantidadEntrenadores(ubicacion);
        });
    }

    public Campeon campeon(String dojo) {
        return Runner.runInSession( () -> {
            Ubicacion ubicacion = ubicacionDAO.recuperar(dojo);
            if (ubicacion.esDojo()) {
                return ubicacion.getCampeon();
            } else {
                throw new UbicacionIncorrectaException(dojo, "dojo");
            }
        });
    }

    public Bicho campeonHistorico(String dojo) {
        return Runner.runInSession( () -> {
            if (ubicacionDAO.recuperar(dojo).esDojo()) {
                return campeonDAO.getCampeonHistorico(dojo).getBicho();
            } else {
                throw new UbicacionIncorrectaException(dojo, "dojo");
            }
        });
    }

    public void crearUbicacion(Ubicacion ubicacion) {
        Runner.runInSession( () -> {
            this.ubicacionDAO.guardar(ubicacion);
            this.ubicacionNeo4JDAO.crearUbicacion(ubicacion);

            return null;
        });
    }

    public void conectar(String ubicacion1, String ubicacion2, CostoCamino costoCamino) {
        Runner.runInSession( () -> {
            if(null == this.ubicacionDAO.recuperar(ubicacion1)) {
                throw new UbicacionNoExistente(ubicacion1);
            }
            else if(null == this.ubicacionDAO.recuperar(ubicacion2)) {
                throw new UbicacionNoExistente(ubicacion2);
            }
            else {
                this.ubicacionNeo4JDAO.conectar(ubicacion1, ubicacion2, costoCamino);
            }
            return null;
        });
    }

    public List<Ubicacion> conectados(String nombreUbicacion, CostoCamino costoCamino) {
        return Runner.runInSession(() -> {

            List<String> nombresDeUbicaciones = this.ubicacionNeo4JDAO.conectados(nombreUbicacion, costoCamino);

            return this.ubicacionDAO.recuperarTodos(nombresDeUbicaciones);
        });
    }

}
