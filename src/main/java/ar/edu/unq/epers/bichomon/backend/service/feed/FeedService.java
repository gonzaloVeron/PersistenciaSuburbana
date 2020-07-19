package ar.edu.unq.epers.bichomon.backend.service.feed;

import ar.edu.unq.epers.bichomon.backend.dao.impl.UbicacionNeo4JDAO;
import ar.edu.unq.epers.bichomon.backend.dao.mongodb.EventoDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.evento.*;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Ubicacion;
import ar.edu.unq.epers.bichomon.backend.service.entrenador.EntrenadorService;

import java.util.ArrayList;
import java.util.List;

public class FeedService {

    private EventoDAO eventoDAO;
    private EntrenadorService entrenadorService;
    private UbicacionNeo4JDAO ubicacionNeo4JDAO;

    public FeedService(EventoDAO eventoDAO, EntrenadorService entrenadorService, UbicacionNeo4JDAO ubicacionNeo4JDAO) {
        this.eventoDAO = eventoDAO;
        this.entrenadorService = entrenadorService;
        this.ubicacionNeo4JDAO = ubicacionNeo4JDAO;
    }

    public void guardarArribo(Entrenador entrenador, Ubicacion ubicacionDestino) {
        Evento eventoAGuardar = new Arribo(entrenador.nombre(), ubicacionDestino.getNombre(), entrenador.ubicacion().getNombre());
        this.eventoDAO.save(eventoAGuardar);
    }

    public void guardarCaptura(Entrenador entrenador, Bicho bichoAtrapado){
        Evento evento = new Captura(entrenador.nombre(), bichoAtrapado.getEspecie().getNombre(), entrenador.ubicacion().getNombre());
        this.eventoDAO.save(evento);
    }

    public void guardarCoronacion(Entrenador entrenadorCoronado, Entrenador entrenadorDescoronado){
        Evento evento = new Coronacion(entrenadorCoronado.nombre(), entrenadorDescoronado.nombre(), entrenadorCoronado.ubicacion().getNombre());
        this.eventoDAO.save(evento);
    }

    public void guardarAbandono(Entrenador entrenador, Bicho bichoAbandonado){
        Evento evento = new Abandono(entrenador.nombre(), bichoAbandonado.getEspecie().getNombre(), entrenador.ubicacion().getNombre());
        this.eventoDAO.save(evento);
    }

    public List<Evento> feedEntrenador(String entrenador) {
        return this.eventoDAO.getEventosDeEntrenador(entrenador);
    }


    public List<Evento> feedUbicacion(String entrenador) {
        Entrenador entrenadorRec = this.entrenadorService.recuperar(entrenador);
        List<String> ubicaciones = new ArrayList<String>();
        ubicaciones.addAll(this.ubicacionNeo4JDAO.conectados(entrenadorRec.ubicacion().getNombre()));
        ubicaciones.add(entrenadorRec.ubicacion().getNombre());
        return this.eventoDAO.getEventosDeUbicaciones(ubicaciones);
    }

}
