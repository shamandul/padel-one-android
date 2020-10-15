package es.jesusdeserdio.padelone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
/**
 * Clase PadelOneDBAdapter
 * 
 * En esta clase solo están implementado los métodos que necesitamos para
 * esta aplicación, dichos métodos son abrir(), cerrar(), obtenerNombres()
 * 
 * @author Jesús de Serdio
 *
 */
public class PadelOneDBAdapter {
	
	//Defino los campos de la BD
	private static final String CAMPO_ID = "_id";
	public final String CAMPO_NOMBRE = "nombre";
	public final String CAMPO_POSICION = "posicion";
	public final String CAMPO_PGANADOS = "pganados";
	public final String CAMPO_PPERDIDOS = "pperdidos";
	public final String CAMPO_PJUGADOS = "pjugados";
	public final String CAMPO_PUNTOS = "puntos";
	private static final String TABLA_BD = "ranking";
	private Context contexto;
	private SQLiteDatabase basedatos;
	private PadelOneSqliteHelper bdHelper;
	
	/**
	 * Constructor
	 * @param contexto
	 */
	public PadelOneDBAdapter(Context contexto){
		this.contexto=contexto;
	}
	/**
	 * Método que abre la base de datos
	 * @return
	 * @throws SQLException
	 */
	public PadelOneDBAdapter abrir() throws SQLException {
		
		// Abrimos la base de datos en modo escritura
		bdHelper = new PadelOneSqliteHelper(contexto);
		basedatos = bdHelper.getReadableDatabase();
		return this;
	}
	/**
	 * Método que cierra la base de datos
	 */
	public void cerrar(){
		bdHelper.close();
	}
	
	/**
	 * Método que nos permite obtener los registro de la tabla y nos devuelve
	 * un cursor
	 * @return
	 */
	public Cursor obtenerRanking(){
		
		return basedatos.query(TABLA_BD, new String[]{getCampoId(), CAMPO_NOMBRE,
				CAMPO_POSICION, CAMPO_PGANADOS, CAMPO_PPERDIDOS, CAMPO_PJUGADOS, CAMPO_PUNTOS}, null, null, null, null, null);
		
	}
	/**
	 * Método que nos permite obtener los registro de la tabla y nos devuelve
	 * un cursor cun los jugadores
	 * @return
	 */
	public Cursor obtenerJugador(){
		
		return basedatos.query(TABLA_BD, new String[]{getCampoId(), CAMPO_NOMBRE}, null, null, null, null, null);
		
	}
	
/**
 * Método que sos permite buscar en nuestra base de datos a partir del nombre
 * @param nombre
 * @return Cursor de tipo Cursor
 * @throws SQLException
 */
	
	public Cursor getActividad(String nombre) throws SQLException{
		
		Cursor mCursor = basedatos.query(true, TABLA_BD, new String[]{getCampoId(), CAMPO_NOMBRE,
				CAMPO_POSICION, CAMPO_PGANADOS, CAMPO_PPERDIDOS, CAMPO_PJUGADOS, CAMPO_PUNTOS}, CAMPO_NOMBRE + "= '" + nombre +"'", null, null, null, null, null);
		if(mCursor != null){
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	/**
	 * Metodo que nos permite buscar en nuestra base de datos a partir de la id y
	 * @param id
	 * @return Cursor de tipo Cursor
	 * @throws SQLException
	 */
		
		public Cursor getId(int id) throws SQLException{
			
			Cursor mCursor = basedatos.query(true, TABLA_BD, new String[]{getCampoId(), CAMPO_NOMBRE,
					CAMPO_POSICION, CAMPO_PGANADOS, CAMPO_PPERDIDOS, CAMPO_PJUGADOS, CAMPO_PUNTOS}, getCampoId() + "= '" + id +"'", null, null, null, null, null);
			if(mCursor != null){
				mCursor.moveToFirst();
			}
			return mCursor;
		}
	
	/**
	 * Método que nos permite ingresar registros en nuestra Base de datos
	 * @param registro de tipo Registro
	 */
	public void insertarRegistro(Registro registro){
		
		ContentValues nuevoRegistro= new ContentValues();
		
		nuevoRegistro.put(CAMPO_NOMBRE, registro.getNombre());
		nuevoRegistro.put(CAMPO_POSICION, registro.getPosicion());
		nuevoRegistro.put(CAMPO_PGANADOS, registro.getP_ganados());
		nuevoRegistro.put(CAMPO_PPERDIDOS, registro.getP_perdidos());
		nuevoRegistro.put(CAMPO_PJUGADOS, registro.getP_jugados());
		nuevoRegistro.put(CAMPO_PUNTOS, registro.getPuntos());
		
		basedatos.insert(TABLA_BD, null, nuevoRegistro);
		
	}
	/**
	 * Metodo que nos permite borrar un registro
	 * @param id
	 */
	public void borrarRegistro(int id){
		
		basedatos.delete(TABLA_BD,"_id="+id, null);
		
	}
	/**
	 * Metodo que nos permite borrar todos registro
	 * @param id
	 */
	public void borrarTodosRegistro(){
		
		basedatos.delete(TABLA_BD,null, null);
		
	}
	/**
	 * Método que nos permite obtener el campo id
	 * @return String
	 */
	public static String getCampoId() {
		return CAMPO_ID;
	}
	public static boolean existeBD(){
		SQLiteDatabase sqliteDB = null;
		String myPath ="/data/data/es.jesusdeserdio.padelone/databases/dbpadelone.db";
		try{
			sqliteDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
		 
		}catch(SQLiteException e){
		 
		//si llegamos aqui es porque la base de datos no existe todavía.
		 
		}
		if(sqliteDB != null){
		 
			sqliteDB.close();
		 
		}
		return sqliteDB != null ? true : false;
	}
	
	
	
}
