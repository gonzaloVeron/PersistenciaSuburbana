package ar.edu.unq.epers.bichomon.frontend.mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.unq.epers.bichomon.backend.dao.EspecieDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.especie.TipoBicho;
import ar.edu.unq.epers.bichomon.backend.service.especie.EspecieService;

/**
 * Esta es una implementacion mock de {@link EspecieDAO}
 * 
 */
public class EspecieDAOMock {
	/*
	private static Map<String, Especie> DATA = new HashMap<>();
	
	static {
		Especie red = new Especie(1, "Rojomon", TipoBicho.FUEGO);
		red.setAltura(180);
		red.setPeso(75);
		red.setEnergiaIncial(100);
		red.setUrlFoto("/image/rojomon.jpg");
		DATA.put(red.getNombre(), red);
		
		Especie amarillo = new Especie(2, "Amarillomon", TipoBicho.ELECTRICIDAD);
		amarillo.setAltura(170);
		amarillo.setPeso(69);
		amarillo.setEnergiaIncial(300);
		amarillo.setUrlFoto("/image/amarillomon.png");
		DATA.put(amarillo.getNombre(), amarillo);
		
		Especie green = new Especie(3, "Verdemon", TipoBicho.PLANTA);
		green.setAltura(150);
		green.setPeso(55);
		green.setEnergiaIncial(5000);
		green.setUrlFoto("/image/verdemon.jpg");
		DATA.put(green.getNombre(), green);
		
		Especie turronmon = new Especie(4, "Tierramon", TipoBicho.TIERRA);
		turronmon.setAltura(1050);
		turronmon.setPeso(99);
		turronmon.setEnergiaIncial(5000);
		turronmon.setUrlFoto("/image/tierramon.jpg");
		DATA.put(turronmon.getNombre(), turronmon);
		
		Especie fantasmon = new Especie(5, "Fantasmon", TipoBicho.AIRE);
		fantasmon.setAltura(1050);
		fantasmon.setPeso(99);
		fantasmon.setEnergiaIncial(5000);
		fantasmon.setUrlFoto("/image/fantasmon.jpg");
		DATA.put(fantasmon.getNombre(), fantasmon);
		
		Especie vampiron = new Especie(6, "Vanpiron", TipoBicho.AIRE);
		vampiron.setAltura(1050);
		vampiron.setPeso(99);
		vampiron.setEnergiaIncial(5000);
		vampiron.setUrlFoto("/image/vampiromon.jpg");
		DATA.put(vampiron.getNombre(), vampiron);
		
		Especie fortmon = new Especie(7, "Fortmon", TipoBicho.CHOCOLATE);
		fortmon.setAltura(1050);
		fortmon.setPeso(99);
		fortmon.setEnergiaIncial(5000);
		fortmon.setUrlFoto("/image/fortmon.png");
		DATA.put(fortmon.getNombre(), fortmon);
		
		Especie dientemon = new Especie(8, "Dientemon", TipoBicho.AGUA);
		dientemon.setAltura(1050);
		dientemon.setPeso(99);
		dientemon.setEnergiaIncial(5000);
		dientemon.setUrlFoto("/image/dientmon.jpg");
		DATA.put(dientemon.getNombre(), dientemon);
	}
	
	@Override
	public void guardar(Especie especie) {
		DATA.put(especie.getNombre(), especie);
	}

	@Override
	public Especie recuperar(String nombreEspecie) {
		return DATA.get(nombreEspecie);
	}


	@Override
	public List<Especie> recuperarTodos() {
		return new ArrayList<>(DATA.values());
	}

	@Override
	public void actualizar(Especie especie) {
	}
	*/
}
