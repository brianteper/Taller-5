package ar.edu.ort.t5.basededatos;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import ar.edu.ort.t5.basededatos.Actividad.ActividadRegistro;
import ar.edu.ort.t5.basededatos.Sesion.SesionRegistro;

public class BaseDeDatos  extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "deportes.db";

	private static final String ID_TYPE = " integer primary key autoincrement";
	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";
		
	private static final String SQL_CREATE_ENTRIES_ACTIVIDAD =
			"CREATE TABLE " + ActividadRegistro.TABLE_NAME + " (" +
					ActividadRegistro.COLUMN_NAME_ID +  ID_TYPE + COMMA_SEP +
					ActividadRegistro.COLUMN_NAME_CODIGO + TEXT_TYPE + COMMA_SEP +
					ActividadRegistro.COLUMN_NAME_DESCRIPCION + TEXT_TYPE + " )";
	
	private static final String SQL_DELETE_ENTRIES_ACTIVIDAD =
			"DROP TABLE IF EXISTS " + ActividadRegistro.TABLE_NAME;

	private static final String SQL_CREATE_ENTRIES_SESSION =
			"CREATE TABLE " + SesionRegistro.TABLE_NAME + " (" +
					SesionRegistro.COLUMN_NAME_ID +  ID_TYPE + COMMA_SEP +
					SesionRegistro.COLUMN_NAME_ACTIVIDAD + TEXT_TYPE + COMMA_SEP +
					SesionRegistro.COLUMN_NAME_DISTANCIA + TEXT_TYPE + COMMA_SEP +
					SesionRegistro.COLUMN_NAME_FECHA + TEXT_TYPE + COMMA_SEP +
					SesionRegistro.COLUMN_NAME_TIEMPO + TEXT_TYPE + COMMA_SEP +
					SesionRegistro.COLUMN_NAME_VELOCIDAD + TEXT_TYPE + COMMA_SEP +					
					SesionRegistro.COLUMN_NAME_COMENTARIOS + TEXT_TYPE + " )";

	private static final String SQL_DELETE_ENTRIES_SESSION =
			"DROP TABLE IF EXISTS " + SesionRegistro.TABLE_NAME;
	
	
	public BaseDeDatos(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		context.openOrCreateDatabase(DATABASE_NAME, DATABASE_VERSION, null);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		 db.execSQL(SQL_CREATE_ENTRIES_ACTIVIDAD);
		 db.execSQL(SQL_CREATE_ENTRIES_SESSION);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(SQL_DELETE_ENTRIES_ACTIVIDAD);
		db.execSQL(SQL_DELETE_ENTRIES_SESSION);
	    onCreate(db);
	}
	
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    
    public void insertAllActividades(){
    	insert("1","Caminata");
    	insert("2","Correr");
    	insert("3","Ciclismo");
    }  
    
	private void insert(String codigo, String desc){
		SQLiteDatabase db = this.getWritableDatabase();
	
		ContentValues values = new ContentValues();
		values.put(ActividadRegistro.COLUMN_NAME_CODIGO, codigo);
		values.put(ActividadRegistro.COLUMN_NAME_DESCRIPCION, desc);
	
		db.insert(
			ActividadRegistro.TABLE_NAME,
			ActividadRegistro.COLUMN_NAME_NULLABLE,
			values);
		
		 db.close(); 
	}
	
	public Cursor selectAllActividades(){
	    String selectQuery = "SELECT  * FROM " + ActividadRegistro.TABLE_NAME;
	    
	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);
	    cursor.moveToFirst();
	    
	    return cursor;
	}

	public List<String> selectAllActividadesList(){
		List<String> actividades = new ArrayList<String>();
		Cursor cursor = selectAllActividades();
		 
		if (cursor.moveToFirst()) {
	        do {
	        	actividades.add(cursor.getString(2));
	        } while (cursor.moveToNext());
        }
		 
		return actividades;
	}
	
	public int getActividadId(String actividad){
		String selectQuery = "SELECT " + ActividadRegistro.COLUMN_NAME_CODIGO + " FROM " + ActividadRegistro.TABLE_NAME
					+ " WHERE " + ActividadRegistro.COLUMN_NAME_DESCRIPCION + "=\"" + actividad + "\"";
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		cursor.moveToFirst();
		
		return cursor.getInt(0);
	}
	
    public void insertAllSesiones(){
		ContentValues values = new ContentValues();
		
		values.put(SesionRegistro.COLUMN_NAME_ACTIVIDAD,"Ciclismo");
		values.put(SesionRegistro.COLUMN_NAME_COMENTARIOS,"A Palermo ida y vuelta");
		values.put(SesionRegistro.COLUMN_NAME_DISTANCIA, "32km");
		values.put(SesionRegistro.COLUMN_NAME_FECHA,  "13-08-2013");
		values.put(SesionRegistro.COLUMN_NAME_TIEMPO, "01:29:54");
		values.put(SesionRegistro.COLUMN_NAME_VELOCIDAD, "25km/h");
    	insertSesion(values);
    	
		values = new ContentValues();
		values.put(SesionRegistro.COLUMN_NAME_ACTIVIDAD,"Correr");
		values.put(SesionRegistro.COLUMN_NAME_COMENTARIOS,"Nike 10K");
		values.put(SesionRegistro.COLUMN_NAME_DISTANCIA, "10km");
		values.put(SesionRegistro.COLUMN_NAME_FECHA,  "30-11-2013");
		values.put(SesionRegistro.COLUMN_NAME_TIEMPO, "00:47:32");
		values.put(SesionRegistro.COLUMN_NAME_VELOCIDAD, "12km/h");
		insertSesion(values);
		
		values = new ContentValues();
		values.put(SesionRegistro.COLUMN_NAME_ACTIVIDAD,"Caminata");
		values.put(SesionRegistro.COLUMN_NAME_COMENTARIOS,"Parque Centenario");
		values.put(SesionRegistro.COLUMN_NAME_DISTANCIA, "8km");
		values.put(SesionRegistro.COLUMN_NAME_FECHA,  "9-10-2013");
		values.put(SesionRegistro.COLUMN_NAME_TIEMPO, "01:32:23");
		values.put(SesionRegistro.COLUMN_NAME_VELOCIDAD, "5km/h");
		insertSesion(values);
    }  

	public void insertSesion(ContentValues values){
		SQLiteDatabase db = this.getWritableDatabase();
	
		db.insert(
				SesionRegistro.TABLE_NAME,
				SesionRegistro.COLUMN_NAME_NULLABLE,
			values);
		
		 db.close(); 
	}
	
	public Cursor selectAllSesiones(){
	    String selectQuery = "SELECT  * FROM " + SesionRegistro.TABLE_NAME;
	    
	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);
	    cursor.moveToFirst();
	    return cursor;
	}
	
	public void updateSesion(String id, ContentValues values){
	SQLiteDatabase db = this.getReadableDatabase();

	String selection = SesionRegistro.COLUMN_NAME_ID + " LIKE ?";
	String[] selectionArgs = { id };

	db.update(
	    Sesion.SesionRegistro.TABLE_NAME,
	    values,
	    selection,
	    selectionArgs);
	}
	
	public void deleteSesion( String id ){
		SQLiteDatabase db = this.getReadableDatabase();

		String whereClause = SesionRegistro.COLUMN_NAME_ID + "= \"" + id + "\"";
		db.delete(SesionRegistro.TABLE_NAME, whereClause, null);
	}	
}