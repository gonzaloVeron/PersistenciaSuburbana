package ar.edu.unq.epers.bichomon.backend.dao;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;

import java.util.List;

public interface EspecieDAO {
	
	void guardar(Especie especie);

	void actualizar(Especie especie);

	void deleteAll();

	Especie recuperar(String nombreEspecie);

	List<Especie> recuperarTodos();

	List<Especie> populares();

	List<Especie> impopulares();

	public Especie especieLider();

}
