package es.jesusdeserdio.padelone;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
/**
 * Clase AdaptadorTorneo
 * 
 * @author Jesús de Serdio Perea
 *
 */
public class AdaptadorTorneo extends BaseAdapter{
	//Creamos el contexto y un array con los registros
	Context contexto;
	ArrayList<Torneo> registros = new ArrayList<Torneo>();
	/**
	 * Constructor de la clase Adaptador
	 * @param context
	 */
	public AdaptadorTorneo(Context context){
		contexto = context;
		
	}
	
	/**
	 * Método que nos sirve para añadir  nuestros registros
	 */
	public void setRegistros(ArrayList<Torneo> registros) {
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
		VistaTagTorneo vistaTagTorneo;
		/* 
		 * comprobamos si la vista es null en caso de que sea así nos estará diciendo que
		 * es la primera vez que creamos la vista con lo cual tendremos que inflarla
		 * en caso contrario quiere decir que la vista ya ha sido inflada con lo cual solo
		 * tendremos que pasarle el valor de view a nuestra vista
		 */
		if(vista==null){
			
			String infService= Context.LAYOUT_INFLATER_SERVICE;
			LayoutInflater li= (LayoutInflater) contexto.getSystemService(infService);
			vista= li.inflate(R.layout.elemento_torneo, null);
			// definimos el objeto que vamos a utilizar en nuestra vista
			vistaTagTorneo =new VistaTagTorneo();
			// obtenemos el puntero de la etiqueta recien inflada
			vistaTagTorneo.txt_cod_torneo= (TextView) vista.findViewById(R.id.txt_cod_torneo);
			vistaTagTorneo.txt_jugador1= (TextView) vista.findViewById(R.id.txt_jugador1);
			//vistaTagTorneo.txt_jugador2= (TextView) vista.findViewById(R.id.txt_jugador2);
			//vistaTagTorneo.txt_jugador3= (TextView) vista.findViewById(R.id.txt_jugador3);
			//vistaTagTorneo.txt_jugador4= (TextView) vista.findViewById(R.id.txt_jugador4);
			// guardamos el objeto
			vista.setTag(vistaTagTorneo);	
					
			
		}else{
			//recuperamos el objeto
			vistaTagTorneo = (VistaTagTorneo) vista.getTag();
		}
		
		vistaTagTorneo.txt_cod_torneo.setText(String.valueOf(registros.get(posicion).getCod_torneo()));
		vistaTagTorneo.txt_jugador1.setText(String.valueOf(registros.get(posicion).getNombre()));
		//vistaTagTorneo.txt_jugador2.setText(registros.get(posicion).getJugador2());
		//vistaTagTorneo.txt_jugador3.setText(registros.get(posicion).getJugador3());
		//vistaTagTorneo.txt_jugador4.setText(registros.get(posicion).getJugador4());
		
		// Devolvemos nuestra vista
		return vista;
	}

}
