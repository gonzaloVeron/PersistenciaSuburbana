package ar.edu.unq.epers.bichomon.backend.service.campeon;

import ar.edu.unq.epers.bichomon.backend.dao.CampeonDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateCampeonDAO;
import ar.edu.unq.epers.bichomon.backend.model.campeon.Campeon;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

public class CampeonService {


    private HibernateCampeonDAO campeonDAO;

    public CampeonService(HibernateCampeonDAO campeonDAO){
        this.campeonDAO = campeonDAO;
    }

    public void actualizarCampeon(Campeon campeon) {
        Runner.runInSession( () -> {
            this.campeonDAO.actualizarOGuardarCampeon(campeon);
            return null;
        });
    }



}
