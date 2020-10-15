package es.jesusdeserdio.padelone;

import java.util.ArrayList;

import org.json.JSONArray;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
/**
 * Clase VerTorneoActivity
 * 
 * @author Jesús de Serdio
 *
 */
public class VerTorneoActivity extends Activity {
	
	private ListView listaElemento;
	private  ArrayList<Torneo> torneos= new ArrayList<Torneo>();
	private AdaptadorTorneo adaptador;
	ArrayList<CodTor> ct=new ArrayList<CodTor>();
	
	 String res;
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_ver_torneo);
	         
	         leer();
	    }
	 
	
	 /**
	  * Método que nos permite leer los torneos del servidor 
	  */
	 public void leer(){
		  final ConectarWeb cwt=new ConectarWeb(this);
		  
		  Thread nt = new Thread() {
				@Override
				public void run() {
					
					try {
						final String res, aux="Torneo cargado";
						//Guardo la respuesta en un String
						res = cwt.leerTorneos();
						obtTorneosJSON(res);
						//pasarArrayCorTor();
						
						
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								
								adaptador.notifyDataSetChanged();
								Toast.makeText(VerTorneoActivity.this, aux,
										Toast.LENGTH_LONG).show();
								
							}
						});
					} catch (Exception e) {
						Toast.makeText(VerTorneoActivity.this,"Error: "+e,
								Toast.LENGTH_LONG).show();
					}
					
				}
			};
			nt.start();
			
			listaElemento =(ListView) findViewById(R.id.lista_torneo);
			//Llenamos el adaptador
			adaptador = new AdaptadorTorneo(this);
			//pasarArrayCorTor();
			adaptador.setRegistros(torneos);

			//rellenamos el listado
			listaElemento.setAdapter(adaptador);
			adaptador.notifyDataSetChanged();
	 }
	 /**
		 * Método que nos permite convertir un String que está
		 *  en formato JSON a un ArrayList de Registros
		 * @param res de tipo String
		 * @return ArrayList<Torneo>
		 */
	public void  obtTorneosJSON(String res){
			
			 Torneo registro;
			try {
				JSONArray json= new JSONArray(res);
				
				for (int i=0; i<json.length();i++){
				    registro = new Torneo();
				    registro.setCod_torneo(Integer.valueOf(json.getJSONObject(i).getString("cod_torneo")));
				    registro.setNombre(json.getJSONObject(i).getString("nombre"));
					torneos.add(registro);
				}
			} catch (Exception e) {
				Toast.makeText(this, "Error: "+ e,
						Toast.LENGTH_SHORT).show();
			}
			
	}
	
	
		
}
