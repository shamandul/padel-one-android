package es.jesusdeserdio.padelone;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
/**
 * Clase Adaptador
 * 
 * @author Jesús de Serdio Perea
 *
 */
public class Adaptador extends BaseAdapter{
	//Creamos el contexto y un array con los registros
	Context contexto;
	ArrayList<Registro> registros = new ArrayList<Registro>();
	/**
	 * Constructor de la clase Adaptador
	 * @param context
	 */
	public Adaptador(Context context){
		contexto = context;
		
	}
	
	/**
	 * Método que nos sirve para añadir  nuestros registros
	 */
	public void setRegistros(ArrayList<Registro> registros) {
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
		VistaTag vistaTag;
		/* 
		 * comprobamos si la vista es null en caso de que sea así nos estará diciendo que
		 * es la primera vez que creamos la vista con lo cual tendremos que inflarla
		 * en caso contrario quiere decir que la vista ya ha sido inflada con lo cual solo
		 * tendremos que pasarle el valor de view a nuestra vista
		 */
		if(vista==null){
			
			String infService= Context.LAYOUT_INFLATER_SERVICE;
			LayoutInflater li= (LayoutInflater) contexto.getSystemService(infService);
			vista= li.inflate(R.layout.elemento_ranking, null);
			// definimos el objeto que vamos a utilizar en nuestra vista
			vistaTag =new VistaTag();
			// obtenemos el puntero de la etiqueta recien inflada
			vistaTag.txt_posicion= (TextView) vista.findViewById(R.id.txt_posicion);
			vistaTag.txt_nombre_usuario= (TextView) vista.findViewById(R.id.txt_nombre_usuario);
			vistaTag.txt_Pganados= (TextView) vista.findViewById(R.id.txt_Pganados);
			vistaTag.txt_Pperdidos= (TextView) vista.findViewById(R.id.txt_pPerdidos);
			vistaTag.txt_Pjugados= (TextView) vista.findViewById(R.id.txt_Pjugados);
			vistaTag.txt_puntos= (TextView) vista.findViewById(R.id.txt_puntos);
			// guardamos el objeto
			vista.setTag(vistaTag);	
					
			
		}else{
			//recuperamos el objeto
			vistaTag = (VistaTag) vista.getTag();
		}
		
		vistaTag.txt_posicion.setText(String.valueOf(registros.get(posicion).getPosicion()));
		vistaTag.txt_nombre_usuario.setText(registros.get(posicion).getNombre());
		vistaTag.txt_Pganados.setText(String.valueOf(registros.get(posicion).getP_ganados()));
		vistaTag.txt_Pperdidos.setText(String.valueOf(registros.get(posicion).getP_perdidos()));
		vistaTag.txt_Pjugados.setText(String.valueOf(registros.get(posicion).getP_jugados()));
		vistaTag.txt_puntos.setText(String.valueOf(registros.get(posicion).getPuntos()));
		
		// Devolvemos nuestra vista
		return vista;
	}

}
