package ar.edu.unq.epers.bichomon.backend.service.data;

import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.especie.TipoBicho;
import ar.edu.unq.epers.bichomon.backend.dao.*;
import java.util.ArrayList;
import java.util.List;


public class DataServiceImp implements DataService {

    private JDBCEspecieDAO dao;

    public DataServiceImp(JDBCEspecieDAO dao) {
        this.dao = dao;
    }

    @Override
    public void deleteAll() {
        this.dao.deleteAll();
    }

    @Override
    public void crearSetDatosIniciales() {
        List<Especie> list = new ArrayList<>();

        Especie red = new Especie();
        Especie amarillo = new Especie();

        //red.setId(1);
        red.setNombre("Rojomon");
        red.setTipo(TipoBicho.FUEGO);
        red.setAltura(180);
        red.setPeso(75);
        red.setCantidadBichos(10);
        red.setEnergiaIncial(100);
        red.setUrlFoto("/image/rojomon.jpg");
        list.add(red);

        //amarillo.setId(2);
        amarillo.setNombre("Amarillomon");
        amarillo.setTipo(TipoBicho.ELECTRICIDAD);
        amarillo.setAltura(170);
        amarillo.setPeso(69);
        amarillo.setCantidadBichos(5);
        amarillo.setEnergiaIncial(300);
        amarillo.setUrlFoto("/image/amarillomon.png");
        list.add(amarillo);

        for(Especie especie: list) {
            this.dao.guardar(especie);
        }
    }
}

