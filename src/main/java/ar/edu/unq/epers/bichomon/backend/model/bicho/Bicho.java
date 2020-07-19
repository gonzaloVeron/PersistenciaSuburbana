package ar.edu.unq.epers.bichomon.backend.model.bicho;

import ar.edu.unq.epers.bichomon.backend.model.condicion.*;
import ar.edu.unq.epers.bichomon.backend.model.duelo.Ataque;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.nivel.NivelManager;
import ar.edu.unq.epers.bichomon.backend.service.bicho.BichoNoPuedeEvolucionarException;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Un {@link Bicho} existente en el sistema, el mismo tiene un nombre
 * y pertenece a una {@link Especie} en particular.
 * 
 * @author Charly Backend
 */
@Entity
public class Bicho {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true, length=190)
	private int id;

	@ManyToOne(cascade= CascadeType.ALL, fetch = FetchType.EAGER)
	private Especie especie;
	private int energia;

	@ManyToOne(cascade= CascadeType.ALL, fetch = FetchType.EAGER)
	private Entrenador entrenador;

	private LocalDate fechaDeCaptura;
	private int victorias;
	private boolean estaAbandonado;

	@OneToMany(cascade= CascadeType.ALL, fetch = FetchType.EAGER)
	@Column()
	private Set<Entrenador> entrenadoresQueMeAbandonaron = new HashSet<Entrenador>();


	public Bicho(){}

	public Bicho(Especie especie, Entrenador entrenador) {
		this.entrenador = entrenador;
		this.especie = especie;
		this.energia = especie.getEnergiaInicial();
	}

	/**
	 * @return la especie a la que este bicho pertenece.
	 */
	public Especie getEspecie() {
		return this.especie;
	}
	
	/**
	 * @return la cantidad de puntos de energia de este bicho en
	 * particular. Dicha cantidad crecerá (o decrecerá) conforme
	 * a este bicho participe en combates contra otros bichomones.
	 */
	public int getEnergia() {
		return this.energia;
	}

	public void setEnergia(int energia) {
		this.energia = energia;
	}

	public LocalDate getFechaDeCaptura(){
		return fechaDeCaptura;
	}

	public void setFechaDeCaptura(LocalDate fecha){
		this.fechaDeCaptura = fecha;
	}

	public int getVictorias(){
		return victorias;
	}

	public void incrementarVictorias(){
	    victorias++;
    }

	public Entrenador getEntrenador(){
		return entrenador;
	}

	public void serAdoptado(Entrenador entrenador){
		this.especie.incrementarPopularidad();
	    this.entrenador = entrenador;
    }

    public void evolucionar(NivelManager manager, int experiencia){
		if(puedeEvolucionar(manager)) {
			this.especie.decrementarPopularidad();
			this.especie = especie.getSiguienteEvolucion();
			this.especie.incrementarPopularidad();
			this.getEntrenador().agregarExperiencia(experiencia);
			this.setearNuevaEnergia();
		}else {
			throw new BichoNoPuedeEvolucionarException(this.especie.getNombre());
		}
    }

    public boolean puedeEvolucionar(NivelManager nivelManager){
		return this.especie.puedeEvolucionar(this, nivelManager) && this.especie.tieneSiguienteEvolucion();
	}

	public Ataque atacar(Bicho bicho) {
		return new Ataque(bicho.getID(),this.getEnergia() * ((Math.random() * 1) + 0.5f), this.getID());
	}

	public void incrementarEnergia(){
		this.energia += (Math.random() * 5) + 1;
	}

	public void setEntrenador(Entrenador entrenador){ this.entrenador = entrenador; }

	public int getID() {
		return id;
	}

	public void serAbandonado(Entrenador entrenador){
		this.entrenadoresQueMeAbandonaron.add(entrenador);
		this.estaAbandonado = true;
		this.especie.decrementarPopularidad();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Bicho bicho = (Bicho) o;
		return id == bicho.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public boolean noFueAbandonadoAntesPor(Entrenador entrenador){
		return !entrenadoresQueMeAbandonaron.contains(entrenador);
	}

	private void setearNuevaEnergia(){
		this.energia = this.especie.getEnergiaInicial();
	}
}
