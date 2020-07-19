package ar.edu.unq.epers.bichomon.backend.model.entrenador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.duelo.Duelo;
import ar.edu.unq.epers.bichomon.backend.model.duelo.ResultadoCombate;
import ar.edu.unq.epers.bichomon.backend.model.nivel.NivelManager;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.CaminoMuyCostosoException;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Ubicacion;
import ar.edu.unq.epers.bichomon.backend.service.bicho.EntrenadorNoPuedeAbandonarException;
import ar.edu.unq.epers.bichomon.backend.service.ubicacion.UbicacionIncorrectaException;

import javax.persistence.*;
import static java.time.temporal.ChronoUnit.DAYS;

@Entity
public class Entrenador {

    @Id
    @Column(name = "Nombre", nullable = false, unique = true, length=190)
    private String Nombre;

    @OneToOne(cascade = CascadeType.ALL)
    private Ubicacion ubicacion;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entrenador that = (Entrenador) o;
        return Objects.equals(Nombre, that.Nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Nombre);
    }

    private int exp = 0; //Siempr empieza en 0 de experiencia

    private int monedas;

    private LocalDate ulimaCaptura;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Bicho> bichos = new ArrayList<>();

    public Entrenador(){

    }

    public Entrenador(String nombre, Ubicacion ubicacion){
        this.Nombre = nombre;
        this.ubicacion = ubicacion;
        ubicacion.sumarPoblacion();
    }

    public String nombre(){
        return this.Nombre;
    }

    public Ubicacion ubicacion(){
        return this.ubicacion;
    }

    public void mover(Ubicacion destino, int costo) {
        if(this.monedas >= costo) {
            Ubicacion anterior = this.ubicacion;
            this.ubicacion = destino;
            anterior.restarPoblacion();
            this.ubicacion.sumarPoblacion();
            this.restarMonedas(costo);
        }
        else {
            throw new CaminoMuyCostosoException(this.nombre(), destino.getNombre());
        }
    }

    public boolean puedeCapturarBicho(NivelManager nivelManager) {
        return this.capacidadMaxima(nivelManager) > bichos.size();
    }

    public boolean puedeAbandonarBicho() { return this.bichos.size() > 1; }

    public int capacidadMaxima(NivelManager nivelManager) {
        return nivelManager.capacidadMaximaDeBichos(this.exp);
    }

    public float factorNivel(NivelManager nivelManager){
        return nivelManager.factorNivel() / this.getNivel(nivelManager);
    }

    public float factorTiempo(){
        if(this.diasDesdeUltimaCaptura() == 0){
            return 1;
        }else{
            return this.diasDesdeUltimaCaptura();
        }
    }

    private long diasDesdeUltimaCaptura(){
        return DAYS.between(this.ulimaCaptura, LocalDate.now());
    }

    public int getNivel(NivelManager nivelManager){return nivelManager.getNivel(this.exp);}

    public void agregarExperiencia(int exp){
        this.exp += exp;
    }

    public void capturarBicho(Bicho bicho){
        bichos.add(bicho);
        ulimaCaptura = LocalDate.now();
    }

    public ResultadoCombate duelo(Bicho bicho, int experiencia){
        if(ubicacion.esDojo()){
            Duelo duelo = new Duelo(ubicacion, bicho);

            return duelo.pelear(experiencia);

        }else{
            throw new UbicacionIncorrectaException(ubicacion.getNombre(), "Dojo");
        }
    }

    public int cantidadBichos(){
        return bichos.size();
    }

    public void agregarBicho(Bicho bicho) {
        bicho.serAdoptado(this);
        this.bichos.add(bicho);
    }

    public int getExperiencia(){
        return exp;
    }

    public void setUlimaCaptura(LocalDate fecha){
        this.ulimaCaptura = fecha;
    }

    public int getPoderTotal() {
        int poderTotal = 0;
        for(Bicho bicho : bichos) {
            poderTotal += bicho.getEnergia();
        }
        return poderTotal;
    }

    public void abandonarBicho(Bicho bicho) {
        if(this.ubicacion().esGuarderia()) {
            if(this.puedeAbandonarBicho()) {
                bicho.serAbandonado(this);
                this.bichos.remove(bicho);
                this.ubicacion().abandonarBicho(bicho);
            }
            else {
                throw new EntrenadorNoPuedeAbandonarException(this.Nombre);
            }
        }
        else {
            throw new UbicacionIncorrectaException(this.ubicacion().getNombre(), "guarderia");
        }
    }

    public void agregarMonedas(int monedas) {
        this.monedas += monedas;
    }

    public int getMonedas() {
        return this.monedas;
    }

    public void restarMonedas(int monedas) {
        this.monedas -= monedas;
    }

    public void setMonedas(int monedas) {
        this.monedas = monedas;
    }

}