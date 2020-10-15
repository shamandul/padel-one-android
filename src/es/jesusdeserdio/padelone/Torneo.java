package es.jesusdeserdio.padelone;


public class Torneo {
	private int cod_torneo;
	private String nombre;
	
	/**
	  * getter
	  * @return in
	  */
	public int getCod_torneo() {
		return cod_torneo;
	}
	/**
	 * Setter
	 * @param cod_torneo de tipo int
	 */
	public void setCod_torneo(int cod_torneo) {
		this.cod_torneo = cod_torneo;
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


}
