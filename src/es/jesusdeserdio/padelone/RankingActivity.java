package es.jesusdeserdio.padelone;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
/**
 * Clase RankingActivity
 * 
 * Con esta clase podemos obtener los datos y rellenar la lista que se mostrará en el Ranking
 * 
 * @author Jesús de Serdio Perea
 *
 */

public class RankingActivity extends Activity {
	
	private ListView listaElemento;
	private PadelOneDBAdapter padelOneHelper;
	private Adaptador adaptador;
	private ArrayList<Registro> listaRegistros= new ArrayList<Registro>();
	private Cursor cursor;
	private Registro registro;
	
	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ranking);
		
	
	listaElemento =(ListView) findViewById(R.id.listaElementos);
	// rellenamos los datos
	
	leerBD();
	
	//Llenamos el adaptador
	adaptador = new Adaptador(this);
	adaptador.setRegistros(listaRegistros);

	//rellenamos el listado
	listaElemento.setAdapter(adaptador);
	adaptador.notifyDataSetChanged();
	}
	/**
	 * Método que nos permite leer de la base de datos SQLite
	 */
	private void leerBD(){
		padelOneHelper = new PadelOneDBAdapter(this);
		padelOneHelper.abrir();
		rellenarDatos();
		padelOneHelper.cerrar();
	}
	/**
	 * Método que nos permite rellenar los datos del Ranking 
	 */
	private void rellenarDatos(){
		cursor=padelOneHelper.obtenerRanking();
		listaRegistros.clear();
    	startManagingCursor(cursor);// con esto decimos que cierre el Cursor cuando cierre la app
    	if(cursor.moveToFirst()){ //vemos si hay algun elemento el la tabla
    		do{
    			registro = new Registro();
    			registro.setCod_jugador(cursor.getInt(cursor.getColumnIndex(PadelOneDBAdapter.getCampoId())));
    			registro.setNombre(cursor.getString(cursor.getColumnIndex(padelOneHelper.CAMPO_NOMBRE)));
    			registro.setP_jugados(cursor.getInt(cursor.getColumnIndex(padelOneHelper.CAMPO_PJUGADOS)));
    			registro.setP_ganados(cursor.getInt(cursor.getColumnIndex(padelOneHelper.CAMPO_PGANADOS)));
    			registro.setP_perdidos(cursor.getInt(cursor.getColumnIndex(padelOneHelper.CAMPO_PPERDIDOS)));
    			registro.setPosicion(cursor.getInt(cursor.getColumnIndex(padelOneHelper.CAMPO_POSICION)));
    			registro.setPuntos(cursor.getInt(cursor.getColumnIndex(padelOneHelper.CAMPO_PUNTOS)));
    			listaRegistros.add(registro);
    		}while(cursor.moveToNext());
    
    	}
    	
	}
}