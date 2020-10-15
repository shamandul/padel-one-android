package es.jesusdeserdio.padelone;


import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Clase LoginActivity
 * 
 * @author Jesús de Serdio
 *
 */
public class LoginActivity extends Activity {
	
	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_log );
		}
	/**
	 * Método que nos permite realizar la acción de loguearnos
	 * cuando hacemos click en el botón de Conectar
	 * Pasamos como parámetros la vista 
	 * @param view
	 */
	public void loguearse(View view){
		final ConectarWeb cw=new ConectarWeb(this);
		Thread nt = new Thread() {
			@Override
			public void run() {
				EditText usuario = (EditText) findViewById(R.id.txt_usuario);
				EditText pass = (EditText) findViewById(R.id.editText1);

				try {
					final String res,aux;
					//envio los datos y guardo la respuesta en un String
					res = cw.enviarPost(usuario.getText().toString(), pass.getText().toString());
					JSONArray jsonArray=null;
					// Pasamos el resultado que hemos obtenido a JSONArray
					jsonArray=pasarJsonArray(res);
					//obtenemos el valor del estado
					final int obtEstado=Integer.valueOf(jsonArray.getJSONObject(0).getString("estado"));
					//si el estado tiene el valor uno lanzamos la actividad CrearTorneo
					if(obtEstado==1){
						aux="Bienvenido "+usuario.getText().toString();
						Intent i = new Intent(LoginActivity.this, OpcionesTorneoActivity.class);
				    	startActivity(i);
					}else{
						aux="Usuario o contraseña no validá";
					}
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							
							Toast.makeText(LoginActivity.this, aux,
									Toast.LENGTH_LONG).show();
							
						}
					});
				} catch (Exception e) {
					Toast.makeText(LoginActivity.this,"Error: "+e,
							Toast.LENGTH_LONG).show();
				}
			}
		};
		nt.start();
		
	}
	/**
	 * Metodo que nos permite pasar un String a un JSONArray
	 * @param result
	 * @return
	 */
	public JSONArray pasarJsonArray(String result){
	  	//parse json data
	  	try{
	          JSONArray jArray = new JSONArray(result);
	          
	          return jArray;
	  	}
	  	catch(JSONException e){
	  		Toast.makeText(LoginActivity.this,"Error: "+e,
					Toast.LENGTH_LONG).show();
	  	        return null;
	  	}
			
	  }
	

}
