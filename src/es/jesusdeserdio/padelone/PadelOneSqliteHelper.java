package es.jesusdeserdio.padelone;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Clase PadelOneSqliteHelper
 * 
 * @author Jesús de Serdio
 *
 */
public class PadelOneSqliteHelper extends SQLiteOpenHelper {
	
	private static final String BD_NOMBRE = "dbpadelone.db";
	private static final int BD_VERSION = 1;
	private static final String BD_CREAR = "" +"CREATE TABLE ranking(_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL," +
			"posicion INTEGER NOT NULL, pganados INTEGER NOT NULL, pperdidos INTEGER NOT NULL, pjugados INTEGER NOT NULL," +
			"puntos INTEGER)";
	
	
	/**
	 * constructor de la clase PadelOneSqliteHelper
	 * @param contexto
	 */
	public PadelOneSqliteHelper(Context contexto) {
		super(contexto, BD_NOMBRE, null, BD_VERSION);
	}
	
	/**
	 * Método onCreate que es llamado si la base de datos no existe
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// Se ejecuta la sentencia SQL de creación de la tabla
		db.execSQL(BD_CREAR);
	}
	
	/**
	 * Método onUpgrade 
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		// se elimina la versión anterior de la tabla
		db.execSQL("DROP TABLE IF EXISTS ranking");                                  
		// Se crea la nueva versión de la tabla
		onCreate(db);
	}

}
