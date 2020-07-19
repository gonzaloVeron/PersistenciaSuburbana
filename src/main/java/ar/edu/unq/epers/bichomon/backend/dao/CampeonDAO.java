package ar.edu.unq.epers.bichomon.backend.dao;

import ar.edu.unq.epers.bichomon.backend.model.campeon.Campeon;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;

import java.util.List;

public interface CampeonDAO {

    Campeon getCampeon(String nombreDojo);

    Campeon getCampeonHistorico(String nombreUbicacion);

    void actualizarOGuardarCampeon(Campeon campeon);

    public List<Campeon> recuperarTodos();

    public List<Entrenador> campeones();

    }
