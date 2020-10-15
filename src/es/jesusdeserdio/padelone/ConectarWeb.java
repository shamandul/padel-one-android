package es.jesusdeserdio.padelone;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import android.content.Context;
import android.widget.Toast;

/**
 * Clase ConectaWeb
 * Con esta clase podemos conectarnos al servicio web para recuperar
 * los datos de la base de datos de MySQL y los recibimos en formato JSON
 * 
 * @author Jesús de Serdio Perea 
 *
 */

public class ConectarWeb {
	private Context contexto;
	private static string URL="http://192.168.1.224/DWES/WebPadelOne/servicios/" // Cambiar en producción
	public ConectarWeb(Context contexto){
		this.contexto=contexto;
	}
	/**
	 * Método que nos permite cargar un ArrayList de Registros
	 *  a la base de datos SQLITE para tratarlos en modo local en la aplicación
	 * @param datos
	 */
	public void cargaListado(ArrayList<Registro> datos){
		PadelOneDBAdapter dbAdapter=new PadelOneDBAdapter(contexto);
		for(int i=0;i<datos.size();i++){
			dbAdapter.abrir();
			dbAdapter.insertarRegistro(datos.get(i));
			dbAdapter.cerrar();
		}
		
	}
	/**
	 * Método que nos permite leer el servicio web donde podemos 
	 * coger los datos de la base de datos en MySQL y nos devuelve
	 *  un String con todos los datos
	 * @return String
	 */
	public String leer(){
		HttpClient cliente =new DefaultHttpClient();
		HttpContext contexto = new BasicHttpContext();
		HttpGet httpget = new HttpGet(URL+"cogerDatos.php");
		String resultado=null;
		try {
			HttpResponse respuesta = cliente.execute(httpget,contexto);
			HttpEntity entity = respuesta.getEntity();
			resultado = EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			Toast.makeText(this.contexto, "Error: "+ e,
					Toast.LENGTH_SHORT).show();
		}
		return resultado;
	}
	
	/**
	 * Método que nos permite convertir un String que está
	 *  en formato JSON a un ArrayList de Registros
	 * @param res de tipo String
	 * @return ArrayList<Registro>
	 */
	public ArrayList<Registro> obtDatosJSON(String res){
		ArrayList<Registro> listado= new ArrayList<Registro>();
		 Registro registro;
		try {
			JSONArray json= new JSONArray(res);
			for (int i=0; i<json.length();i++){
			    registro = new Registro();
				registro.setNombre(json.getJSONObject(i).getString("nombre"));
				registro.setPosicion(i+1);
				registro.setP_jugados(Integer.valueOf(json.getJSONObject(i).getString("p_jugados")));
				registro.setP_ganados(Integer.valueOf(json.getJSONObject(i).getString("p_ganados")));
				registro.setP_perdidos(Integer.valueOf(json.getJSONObject(i).getString("p_perdidos")));
				int puntos=Integer.valueOf(json.getJSONObject(i).getString("p_ganados"))*3;
				registro.setPuntos(puntos);
				listado.add(registro);
			}
		} catch (Exception e) {
			Toast.makeText(this.contexto, "Error: "+ e,
					Toast.LENGTH_SHORT).show();
		}
		return listado;
	}
	/**
	 * Método que nos permite convertir un String que está
	 *  en formato JSON a un ArrayList de Registros
	 * @param res de tipo String
	 * @return ArrayList<Noticias>
	 */
	
	
	
	public String leerNoticias(){
		HttpClient cliente =new DefaultHttpClient();
		HttpContext contexto = new BasicHttpContext();
		HttpGet httpget = new HttpGet(URL+"cogerNoticias.php");
		String resultado=null;
		try {
			HttpResponse respuesta = cliente.execute(httpget,contexto);
			HttpEntity entity = respuesta.getEntity();
			resultado = EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			Toast.makeText(this.contexto, "Error: "+ e,
					Toast.LENGTH_SHORT).show();
		}
		return resultado;
	}
	
	
	/**
	 *  Método que nos permite enviar por POST el usuario y contraseña y
	 *  nos devuelve un String con el resultado de la consulta.
	 *  Si los datos son correctos nos devuelve un String en formato JSON 
	 *  [{"estado":"1"}] en otro caso nos devuelve [{"estado":"0"}] 
	 *  
	 * @param usuario de tipo String
	 * @param pass de Tipo String
	 * @return String 
	 */
	public String enviarPost(String usuario, String pass) {

		HttpClient httpCliente = new DefaultHttpClient();
		HttpContext localContext = new BasicHttpContext();
		//Aquí hay que especificar la direccion del servidor
		HttpPost httpPost = new HttpPost(URL+"logueoAndroid.php");
		HttpResponse respuesta = null;
		String resultado=null;
		try {
			List<NameValuePair> params = new ArrayList<NameValuePair>(3);
			params.add(new BasicNameValuePair("usuario", usuario));
			params.add(new BasicNameValuePair("password", pass));
			httpPost.setEntity(new UrlEncodedFormEntity(params));
			respuesta = httpCliente.execute(httpPost, localContext);
			HttpEntity entity = respuesta.getEntity();
			resultado = EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			// capturamos el error
			Toast.makeText(this.contexto, "Error: "+e,
					Toast.LENGTH_LONG).show();
		}
		return resultado;

	}
	/**
	 * Método que nos permite enviar a los jugadores a un servicio web por POST
	 * 
	 * @param jugador1 de tipo String
	 * @param jugador2 de tipo String
	 * @param jugador3 de tipo String
	 * @param jugador4 de tipo String
	 */
	public void enviarJugadores(String jugador1, String jugador2, String jugador3, String jugador4) {

		HttpClient httpCliente = new DefaultHttpClient();
		HttpContext localContext = new BasicHttpContext();
		//Aquí hay que especificar la direccion del servidor
		HttpPost httpPost = new HttpPost(URL+"crearPartida.php");
		
		try {
			List<NameValuePair> params = new ArrayList<NameValuePair>(3);
			params.add(new BasicNameValuePair("jugador1", jugador1));
			params.add(new BasicNameValuePair("jugador2", jugador2));
			params.add(new BasicNameValuePair("jugador3", jugador3));
			params.add(new BasicNameValuePair("jugador4", jugador4));
			
			httpPost.setEntity(new UrlEncodedFormEntity(params));
			
			httpCliente.execute(httpPost, localContext);;
		} catch (Exception e) {
			// capturamos el error
			Toast.makeText(this.contexto, "Error: "+e,
					Toast.LENGTH_LONG).show();
		}
		

	}
	/**
	 * Método que nos permite leer los torneos que se encuentran en el servidor
	 * @return
	 */
	public String leerTorneos(){
		HttpClient cliente =new DefaultHttpClient();
		HttpContext contexto = new BasicHttpContext();
		HttpGet httpget = new HttpGet(URL+"cogerTorneos.php");
		String resultado=null;         
		try {
			HttpResponse respuesta = cliente.execute(httpget,contexto);
			HttpEntity entity = respuesta.getEntity();
			resultado = EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			Toast.makeText(this.contexto, "Error: "+ e,
					Toast.LENGTH_SHORT).show();
		}
		return resultado;
	}
	
	
}
