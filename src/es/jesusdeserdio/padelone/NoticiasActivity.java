package es.jesusdeserdio.padelone;

import java.util.ArrayList;

import org.json.JSONArray;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Clase NoticiaActivity
 * 
 * Con esta clase obtenemos las noticias y la mostramos en una
 * lista
 * 
 * @author Jesús de Serdio
 *
 */
public class NoticiasActivity extends Activity {
	
	// Listado donde guardamos las noticias leídas y que sirve de adaptador al ListActivity
	private ArrayList<Noticia> noticias=new ArrayList<Noticia>();
	private ListView listaElemento;
	private AdaptadorNoticias adaptador;
	 String res;
	
	/**
	 * Método oncreate de la clase NoticiasActivity
	 */
	@Override 
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_noticias);
		
		leer();
		
		
	}
	
	
	
	/**
	 * Método que nos permite leer el contenido de un servicio web
	 * en formato JSON de donde obtenemos las noticias
	 */
	public void leer(){
		 final ConectarWeb cwn=new ConectarWeb(this);
			Thread ntN = new Thread() {
				@Override
				public void run() {
					
					try {
						final String res,aux="Noticias cargadas";
						//Guardo la respuesta en un String
						res = cwn.leerNoticias();
						
						//obtenemos el valor del estado
						obtNoticiasJSON(res);
						
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								adaptador.notifyDataSetChanged();
							Toast.makeText(NoticiasActivity.this, aux,
										Toast.LENGTH_LONG).show();
								
							}
							
						});
					} catch (Exception e) {
						Toast.makeText(NoticiasActivity.this,"Error: "+e,
								Toast.LENGTH_LONG).show();
					}
				}
			};
			ntN.start();
			

			listaElemento =(ListView) findViewById(R.id.listaElementosNoticia);
			//Llenamos el adaptador
			adaptador = new AdaptadorNoticias(this);
			adaptador.setRegistros(noticias);

			//rellenamos el listado
			listaElemento.setAdapter(adaptador);
			adaptador.notifyDataSetChanged();
			
	 }
	/**
	 * Método que nos permite convertir un String que está
	 *  en formato JSON a un ArrayList de Noticias
	 * @param res de tipo String
	 * @return ArrayList<Noticias>
	 */
	
	public  void obtNoticiasJSON(String res){
		 Noticia registro;
		try {
			JSONArray json= new JSONArray(res);
			for (int i=0; i<json.length();i++){
			    registro = new Noticia();
				registro.setTitulo(json.getJSONObject(i).getString("titulo"));
				registro.setFecha(json.getJSONObject(i).getString("fecha"));
				registro.setContenido(json.getJSONObject(i).getString("contenido"));
				registro.setImagen(json.getJSONObject(i).getString("imagen"));
				noticias.add(registro);
			}
		} catch (Exception e) {
			Toast.makeText(this, "Error: "+ e,
					Toast.LENGTH_SHORT).show();
		}
	
	}
	

}
