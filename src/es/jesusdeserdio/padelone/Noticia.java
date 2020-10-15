package es.jesusdeserdio.padelone;


/**
 * Clase Noticia
 * 
 * Clase donde definimos los atributo de la noticias e implementamos sus métodos 
 * tanto getter como setter
 * 
 * @author Jesús de Serdio
 *
 */
public class Noticia {
	private String titulo;
	private String fecha;
	private String contenido;
	private String imagen;
	/**
	 * getter
	 * @return String
	 */
	public String getTitulo() {
		return titulo;
	}
	/**
	 * Setter
	 * @param titulo de tipo String
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	/**
	 * getter
	 * @return String
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * Setter
	 * @param fecha de tipo String
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	/**
	 * getter
	 * @return String
	 */
	public String getContenido() {
		return contenido;
	}
	/**
	 * Setter
	 * @param contenido de tipo String
	 */
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	/**
	 * getter
	 * @return String
	 */
	public String getImagen() {
		return imagen;
	}
	/**
	 * Setter
	 * @param imagen de tipo String
	 */
	public void setImagen(String imagen) {
		
			this.imagen = imagen;
		
	}
}
