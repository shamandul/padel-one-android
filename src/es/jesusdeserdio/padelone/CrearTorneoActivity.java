package es.jesusdeserdio.padelone;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;
/**
 * Clase CrearTorneoActivity
 * 
 * @author Jesús de Serdio
 *
 */
public class CrearTorneoActivity  extends Activity{
	
	private PadelOneDBAdapter padelOneHelper;
	private Cursor cursor;
	private Jugador jugador;
	private ArrayList<Jugador> lista= new ArrayList<Jugador>();
	private String valorSp1="",valorSp2="",valorSp3="",valorSp4="",mensaje="";
	int pos=0;
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_crear_torneo);
	       
	        // rellenamos los Spinner
	        Spinner sp1=(Spinner) findViewById(R.id.spinner1);
	        Spinner sp2=(Spinner) findViewById(R.id.spinner2);
	        Spinner sp3=(Spinner) findViewById(R.id.spinner3);
	        Spinner sp4=(Spinner) findViewById(R.id.spinner4);
	        // leemos de la base de datos SQLIte
	        leerBD();
	        // creamos un adaptador
	        SimpleCursorAdapter sca=new SimpleCursorAdapter(this,android.R.layout.simple_spinner_item,
	        		cursor,new String[] {"nombre"},
	       	    new int[] {android.R.id.text1}); 
	        sca.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        // cargamos nuestro adaptador en los spinner
	        sp1.setAdapter(sca);
	        sp2.setAdapter(sca);
	        sp3.setAdapter(sca);
	        sp4.setAdapter(sca);
	    }
	 /**
	  * Método que nos permite crear el torneo
	  * @param view
	  */
	 public void crear(View view){
		 Spinner sp1=(Spinner) findViewById(R.id.spinner1);
		 Spinner sp2=(Spinner) findViewById(R.id.spinner2);
		 Spinner sp3=(Spinner) findViewById(R.id.spinner3);
		 Spinner sp4=(Spinner) findViewById(R.id.spinner4);
		 //obtengo el valor de cada Spinner
		 pos=sp1.getSelectedItemPosition();
		 valorSp1=lista.get(pos).getNombre().toString();
		 
		 pos=sp2.getSelectedItemPosition();
		 valorSp2=lista.get(pos).getNombre().toString();
		 
		 pos=sp3.getSelectedItemPosition();
		 valorSp3=lista.get(pos).getNombre().toString();
		 
		 pos=sp4.getSelectedItemPosition();
		 valorSp4=lista.get(pos).getNombre().toString();
		 
		 //miro si todos los valores son distintos
		 mensaje="No puedes repetir los jugadores en la misma partida";
		 if(valorSp1!=valorSp2){
			 if((valorSp3!=valorSp2)&&(valorSp3!=valorSp1)){
				 if((valorSp4!=valorSp3)&&(valorSp4!=valorSp2)&&(valorSp4!=valorSp1)){
					 mensaje="Partida creada";
					 enviarDatos(valorSp1, valorSp2, valorSp3, valorSp4);
				 }
			 }
		 }
		 Toast.makeText(this,mensaje,
					Toast.LENGTH_LONG).show();
		 
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
			cursor=padelOneHelper.obtenerJugador();
			lista.clear();
	    	startManagingCursor(cursor);// con esto decimos que cierre el Cursor cuando cierre la app
	    	if(cursor.moveToFirst()){ //vemos si hay algun elemento el la tabla
	    		do{
	    			jugador = new Jugador();
	    			
	    			jugador.setNombre(cursor.getString(cursor.getColumnIndex(padelOneHelper.CAMPO_NOMBRE)));
	    			lista.add(jugador);
	    		}while(cursor.moveToNext());
	    
	    	}
		}
		/**
		 * Método que nos permite enviar a los jugadores por POST par
		 * crear una partida
		 * 
		 * @param jugador1 de tipo String
		 * @param jugador2 de tipo String
		 * @param jugador3 de tipo String
		 * @param jugador4 de tipo String
		 */
		public void enviarDatos(String jugador1, String jugador2, String jugador3, String jugador4){
			final ConectarWeb cw=new ConectarWeb(this);
			final String jug1=jugador1, jug2=jugador2, jug3=jugador3, jug4=jugador4;
			Thread nt = new Thread() {
				@Override
				public void run() {
				
					try {
						
						cw.enviarJugadores(jug1, jug2, jug3, jug4);
						
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								
								Toast.makeText(CrearTorneoActivity.this, "Envio correcto",
										Toast.LENGTH_LONG).show();
								
							}
						});
					} catch (Exception e) {
						Toast.makeText(CrearTorneoActivity.this,"Error: "+e,
								Toast.LENGTH_LONG).show();
					}
				}
			};
			nt.start();
		}
		

}
