package es.jesusdeserdio.padelone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
/**
 * Clase MainActivity
 * 
 * Esta es la clase principal de la aplicación
 * 
 * @author Jesús de Serdio
 *
 */
public class MainActivity extends Activity {
	/**
	 * Método onCreate
	 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PadelOneDBAdapter dbAdapter=new PadelOneDBAdapter(this);
		if(PadelOneDBAdapter.existeBD()){
			dbAdapter.abrir();
			dbAdapter.borrarTodosRegistro();
			dbAdapter.cerrar();
		}
			
		
        	cargarRanking();
        
        
    }


    
    /**
     * Método que nos permite lanzar la actividad Acercade
     * @param view
     */
    public void lanzarAcercaDe(View view){
    	Intent i = new Intent(this, AcercaDe.class);
    	startActivity(i);
    	}
    /**
     * Método que nos permite lanzar la actividad Login
     * @param view
     */
    public void lanzarLogin(View view){
    	Intent i = new Intent(this, LoginActivity.class);
    	startActivity(i);
    	}
    /**
     * Método que nos permite lanzar la actividad Noticias
     * @param view
     */
    public void lanzarNoticias(View view){
    	Intent i = new Intent(this, NoticiasActivity.class);
    	startActivity(i);
    	}
    /**
     * Método que nos permite lanzar la actividad Ranking
     * @param view
     */
    public void lanzarRanking(View view){
    	Intent i = new Intent(this, RankingActivity.class);
    	startActivity(i);
    	}
    /**
     * Método que nos permite cargar el ranking en nuestra base de datos SQLite
     */
    public void cargarRanking(){
    	final ConectarWeb cw=new ConectarWeb(this);
		Thread tr = new Thread(){
			@Override
			public void run(){
				final String Resultado = cw.leer();
				runOnUiThread(
						new Runnable() {
				
							@Override
							public void run() {
									cw.cargaListado(cw.obtDatosJSON(Resultado));
							}
						});
			}			
		};
		tr.start();
    }
    
}
