package ar.edu.unq.epers.bichomon.backend.model.especie;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.condicion.*;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.nivel.NivelManager;

import javax.persistence.*;
/**
 * Representa una {@link Especie} de bicho.
 * 
 * @author Charly Backend
 */
@Entity
public class Especie {

 	@Id
	@Column(name = "Nombre", nullable = false, unique = true, length=190)
	private String nombre;

 	private int altura;

 	private int peso;

	private int popularidad;

	private TipoBicho tipo;

	@OneToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private Especie especieRaiz;

	@OneToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private Especie siguienteEvolucion;

	private int nroEvolucion;

	private int energiaInicial; //Es necesaria ?

	private String urlFoto;

	private int cantidadBichos;

	@OneToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private Condicion condicionDeEvolucion;
	
	public Especie(){
	}

	/**
	 * Constructor para crear especies sin usar nroEvolucion
	 * -----------------------------------------------------
	 * @param nombre = El nombre de la especie a crear.
	 * @param tipo = Tipo de la especie (ver enumerador "TipoBicho").
	 * @param condicionDeEvolucion = La condicion necesaria para que la especie pueda evolucionar.
	 * @param altura = La altura de la especie.
	 * @param peso = El peso de la especie.
	 * @param energiaInicial = la energia inicial de la especie.
	 * @param especieRaiz = La especie raiz a la que pertenece la especie, de ser una especie base se tiene que setear a si mismo.
	 */
	public Especie(String nombre, Especie siguienteEvolucion, TipoBicho tipo, Condicion condicionDeEvolucion, int altura, int peso, int energiaInicial, Especie especieRaiz){
		this.setNombre(nombre);
		this.siguienteEvolucion = siguienteEvolucion;
		this.setTipo(tipo);
		this.setCondicionDeEvolucion(condicionDeEvolucion);
		this.setAltura(altura);
		this.setPeso(peso);
		this.setEnergiaIncial(energiaInicial);
		this.setEspecieRaiz(especieRaiz);
		EvolutionHandler.getInstance().agregarEspecie(this);
	}

	public Especie(String nombre) {
		this.setNombre(nombre);
	}

	public Condicion getCondicionDeEvolucion(){
		return condicionDeEvolucion;
	}

	public void setCondicionDeEvolucion(Condicion condicionDeEvolucion) {
		this.condicionDeEvolucion = condicionDeEvolucion;
	}

	public Especie getSiguienteEvolucion(){
		return siguienteEvolucion;
	}

	public void setSiguienteEvolucion(Especie siguienteEvolucion){
		this.siguienteEvolucion = siguienteEvolucion;
	}

	/**
	 * @return el nombre de la especie (por ejemplo: Perromon)
	 */
	public String getNombre() {
		return this.nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * @return la altura de todos los bichos de esta especie
	 */
	public int getAltura() {
		return this.altura;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}
	
	/**
	 * @return el peso de todos los bichos de esta especie
	 */
	public int getPeso() {
		return this.peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	/**
	 * @return una url que apunta al un recurso imagen el cual será
	 * utilizado para mostrar un thumbnail del bichomon por el frontend.
	 */
	public String getUrlFoto() {
		return this.urlFoto;
	}
	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}
	
	/**
	 * @return la cantidad de energia de poder iniciales para los bichos
	 * de esta especie.
	 */
	public int getEnergiaInicial() {
		return this.energiaInicial;
	}
	public void setEnergiaIncial(int energiaInicial) {
		this.energiaInicial = energiaInicial;
	}

	/**
	 * @return el tipo de todos los bichos de esta especie
	 */
	public TipoBicho getTipo() {
		return this.tipo;
	}
	public void setTipo(TipoBicho tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * @return la cantidad de bichos que se han creado para esta
	 * especie.
	 */
	public int getCantidadBichos() {
		return this.cantidadBichos;
	}
	public void setCantidadBichos(int i) {
		this.cantidadBichos = i;
	}

	public Bicho crearBicho(Entrenador entrenador){
		this.cantidadBichos++;
		Bicho bicho = new Bicho(this, entrenador);
		this.incrementarPopularidad();
		return bicho;
	}

	public Especie getEspecieRaiz(){
			return especieRaiz;
	}

	public void setEspecieRaiz(Especie especie){
		if(especie != null) {
			this.especieRaiz = especie;
		}else{
			this.especieRaiz = this;
		}
	}

	public int getNroEvolucion(){
		return nroEvolucion;
	}

	public void setNroEvolucion(int nroEvolucion){
		this.nroEvolucion = nroEvolucion;
	}

	public boolean puedeEvolucionar(Bicho bicho, NivelManager nivelManager){
		return this.condicionDeEvolucion.cumpleConLaCondicion(bicho, nivelManager);
	}

	public Especie buscarSiguienteEvolucion(){
		return EvolutionHandler.getInstance().buscarSiguienteEvolucion(this);
	}

	public boolean esSiguienteEvolucion(Especie especie) {
		return this.nombre == especie.getSiguienteEvolucion().getNombre();
	}

	public void incrementarPopularidad(){
		this.popularidad++;
	}

	public void decrementarPopularidad(){
		this.popularidad = this.popularidad - 1;
	}

	public int getPopularidad(){
		return this.popularidad;
	}

	public boolean tieneSiguienteEvolucion(){
		return siguienteEvolucion != null;
	}
}
