package es.jesusdeserdio.padelone;
/**
 * Clase Registro
 * 
 * @author Jesús de Serdio Perea
 *
 */
public class Registro {
	
	private String nombre;
	private int cod_jugador, p_ganados, p_perdidos, p_jugados, posicion, puntos;
	
	/**
	 * getter
	 * @return int
	 */
	public int getPosicion() {
		return posicion;
	}
	/**
	 * Setter
	 * @param posicion de tipo int
	 */
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}
	/**
	 * getter
	 * @return int
	 */
	public int getPuntos() {
		return puntos;
	}
	/**
	 * Setter
	 * @param puntos de tipo int
	 */
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	/**
	 * getter
	 * @return int
	 */
	 public int getCod_jugador() {
	 	return cod_jugador;
	 }
	 /**
		 * Setter
		 * @param cod_jugador de tipo int
		 */
	 public void setCod_jugador(int cod_jugador) {
	 	this.cod_jugador = cod_jugador;
	 }
	 /**
	  * getter
	  * @return String
	  */
	 public String getNombre() {
	   	return nombre;
	}
	 /**
	  * Setter
	  * @param nombre de tipo String
	  */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * getter
	 * @return int
	 */
	 public int getP_ganados() {
		return p_ganados;
	 }
	 /**
	 * Setter
	 * @param p_ganados de tipo int
	 */
	public void setP_ganados(int p_ganados) {
		this.p_ganados = p_ganados;
	}
	/**
	 * getter
	 * @return int
	 */
	public int getP_perdidos() {
		return p_perdidos;
	}
	/**
	 * Setter
	 * @param p_perdidos de tipo int
	 */
	public void setP_perdidos(int p_perdidos) {
		this.p_perdidos = p_perdidos;
	}
	/**
	 * getter
	 * @return int
	 */
	public int getP_jugados() {
		return p_jugados;
	}
	/**
	 * Setter
	 * @param p_jugados de tipo int
	 */
	public void setP_jugados(int p_jugados) {
		this.p_jugados = p_jugados;
	}
	
}
