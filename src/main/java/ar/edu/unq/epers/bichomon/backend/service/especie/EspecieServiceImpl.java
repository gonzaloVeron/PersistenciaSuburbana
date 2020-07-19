package ar.edu.unq.epers.bichomon.backend.service.especie;

import java.util.List;

import ar.edu.unq.epers.bichomon.backend.dao.*;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;


public class EspecieServiceImpl implements EspecieService {

	private EspecieDAO especieDAO;


	public EspecieServiceImpl(EspecieDAO dao){
		this.especieDAO = dao;
	}
	

	@Override
	public void crearEspecie(Especie especie){
		Runner.runInSession( () -> {
			this.especieDAO.guardar(especie);
			return null;
		});
	}
	

	@Override
	public Especie getEspecie(String nombreEspecie){
		return Runner.runInSession( () -> {
			Especie especie = especieDAO.recuperar(nombreEspecie);
			if(especie == null){
				throw new EspecieNoExistente(nombreEspecie);
			}
			return especie;
		});
	}


	@Override
	public List<Especie> getAllEspecies(){
		return Runner.runInSession( () -> {
			return especieDAO.recuperarTodos();
		});
	}


	@Override //Crea un bicho e incrementa la popularidad de su especie
	public Bicho crearBicho(String nombreEspecie, Entrenador entrenador){
		return Runner.runInSession( () -> {
			Especie especie = especieDAO.recuperar(nombreEspecie);
			Bicho bicho = especie.crearBicho(entrenador);
			especieDAO.actualizar(especie);
			return bicho;
		});
	}

	public void actualizar(Especie especie){
		Runner.runInSession(() -> {
			especieDAO.actualizar(especie);
			return null;
		});
	}

	@Override
	public List<Especie> populares(){
		return Runner.runInSession(() -> {
			return especieDAO.populares();
		});
	}

	@Override
	public List<Especie> impopulares(){
		return Runner.runInSession(() -> {
			return especieDAO.impopulares();
		});
	}

	@Override
	public void incrementarPopularidad(String nombreEspecie){
		Runner.runInSession(() -> {
			Especie especie = especieDAO.recuperar(nombreEspecie);
			especie.incrementarPopularidad();
			especieDAO.actualizar(especie);
			return null;
		});
	}

	@Override
	public void decrementarPopularidad(String nombreEspecie){
		Runner.runInSession(() -> {
			Especie especie = especieDAO.recuperar(nombreEspecie);
			especie.decrementarPopularidad();
			especieDAO.actualizar(especie);
			return null;
		});
	}

}
