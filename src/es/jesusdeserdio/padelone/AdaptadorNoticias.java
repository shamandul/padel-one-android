package es.jesusdeserdio.padelone;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
/**
 * Clase AdaptadorNoticias
 * 
 * @author Jesús de Serdio Perea
 *
 */
public class AdaptadorNoticias  extends BaseAdapter{
	//Creamos el contexto y un array con los registros
	Context contexto;
	ArrayList<Noticia> registros = new ArrayList<Noticia>();
	/**
	 * Constructor de la clase Adaptador
	 * @param context
	 */
	public AdaptadorNoticias(Context context){
		contexto = context;
		
	}
	
	/**
	 * Método que nos sirve para añadir  nuestros registros
	 */
	public void setRegistros(ArrayList<Noticia> registros) {
		this.registros = registros;
	}


	@Override
	/**
	 * Método que nos sirve para contar el tamaño de nuestro adaptador que es el
	 * tamaño de nuestro array nos devuelve un entero
	 */
	
	public int getCount() {
		return registros.size();
	}

	@Override
	/**
	 * Método nos permite coger un elemento de nuestra lista pasándole como
	 * parámetro la posición del elemento y nos devuelve un objeto que es nuestro elemento
	 */
	public Object getItem(int posicion) {
		return registros.get(posicion);
	}

	@Override
	/**
	 * Método que tenemos que implementar pero que no voy a utilizar
	 */
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	/**
	 * Método más importante de toda nuestra clase adaptador es el método que nos permite 
	 * obtener nuestra vista apartir de los parámetros de la posición, una vista si es que existe
	 * y el grupo al que pertenece la vista
	 */
	public View getView(int posicion, View view, ViewGroup viewGrup) {
		
		// creamos nuestra vista y la ponemos a null
		View vista= view;
		VistaTagNoticia vistaTagNoticia;
		/* 
		 * comprobamos si la vista es null en caso de que sea así nos estará diciendo que
		 * es la primera vez que creamos la vista con lo cual tendremos que inflarla
		 * en caso contrario quiere decir que la vista ya ha sido inflada con lo cual solo
		 * tendremos que pasarle el valor de view a nuestra vista
		 */
		if(vista==null){
			
			String infService= Context.LAYOUT_INFLATER_SERVICE;
			LayoutInflater li= (LayoutInflater) contexto.getSystemService(infService);
			vista= li.inflate(R.layout.fila, null);
			// definimos el objeto que vamos a utilizar en nuestra vista
			vistaTagNoticia =new VistaTagNoticia();
			// obtenemos el puntero de la etiqueta recien inflada
			vistaTagNoticia.txt_titulo = (TextView) vista.findViewById(R.id.txt_fila_titulo);
			vistaTagNoticia.txt_contenido = (TextView) vista.findViewById(R.id.txt_fila_contenido);
			// guardamos el objeto
			vista.setTag(vistaTagNoticia);	
					
			
		}else{
			//recuperamos el objeto
			vistaTagNoticia = (VistaTagNoticia) vista.getTag();
		}
		
		vistaTagNoticia.txt_titulo.setText(registros.get(posicion).getTitulo());
		vistaTagNoticia.txt_contenido.setText(registros.get(posicion).getContenido());
		
		
		// Devolvemos nuestra vista
		return vista;
	}

}
