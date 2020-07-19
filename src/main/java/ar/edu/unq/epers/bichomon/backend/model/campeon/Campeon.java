package ar.edu.unq.epers.bichomon.backend.model.campeon;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Dojo;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Campeon{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    @JoinColumn(name = "id_bicho")
    private Bicho bicho;

    @OneToOne
    @JoinColumn(name = "nombre_dojo")
    private Dojo dojo;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    public Campeon() {

    }

    public Campeon(Bicho unBicho, LocalDate fechaInicial, Dojo unDojo) {
        fechaInicio = fechaInicial;
        this.bicho = unBicho;
        this.dojo = unDojo;
    }

    public Bicho getBicho() {
        return bicho;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFinal) {
        fechaFin = fechaFinal;
    }

    public Dojo getDojo() {
        return dojo;
    }

    public int getId() {
        return id;
    }

}