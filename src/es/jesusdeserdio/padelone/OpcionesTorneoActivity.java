package es.jesusdeserdio.padelone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
/**
 * Clase OpcionesTorneoActivity
 * 
 * @author Jesús de Serdio
 *
 */
public class OpcionesTorneoActivity extends Activity {
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_opciones_torneo);
	    }
	 /**
	  * Método que nos permite lanzar la Actividad activity_crear_torneo
	  * @param view
	  */
	 public void lanzarCrearTorneo(View view){
		 Intent i = new Intent(this, CrearTorneoActivity.class);
	    	startActivity(i);
	 }

	 /**
	  * Método que nos permite lanzar la Actividad activity_ver_torneo
	  * @param view
	  */
	 public void lanzarVerTorneo(View view){
		 Intent i = new Intent(this, VerTorneoActivity.class);
	    	startActivity(i);
	 }

}
