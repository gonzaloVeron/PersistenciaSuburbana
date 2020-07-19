package ar.edu.unq.epers.bichomon.backend.dao.mongodb;

import ar.edu.unq.epers.bichomon.backend.model.evento.Evento;

import java.util.List;

public class EventoDAO extends GenericMongoDAO<Evento> {

	public EventoDAO() {
		super(Evento.class);
	}

	public List<Evento> getEventosDeEntrenador(String entrenadorNombre) {
		return this.findInOrder("{ entrenador: #}", "{ fecha: -1 }", entrenadorNombre);
	}

	public List<Evento> getEventosDeUbicaciones(List<String> ubicaciones) {
		return this.findInOrder("{ ubicacion: { $in: # } }", "{ fecha: -1 }", ubicaciones);
	}

}
