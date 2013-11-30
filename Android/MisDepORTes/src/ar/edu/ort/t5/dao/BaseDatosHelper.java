package ar.edu.ort.t5.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import ar.edu.ort.t5.dao.ActividadContract.ActividadRegistro;
import ar.edu.ort.t5.dao.SessionContract.SessionRegistro;

public class BaseDatosHelper  extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "depORTes.db";

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
			"CREATE TABLE " + SessionRegistro.TABLE_NAME + " (" +
					SessionRegistro.COLUMN_NAME_ID +  ID_TYPE + COMMA_SEP +
					SessionRegistro.COLUMN_NAME_ACTIVIDAD + TEXT_TYPE + COMMA_SEP +
					SessionRegistro.COLUMN_NAME_DISTANCIA + TEXT_TYPE + COMMA_SEP +
					SessionRegistro.COLUMN_NAME_FECHA + TEXT_TYPE + COMMA_SEP +
					SessionRegistro.COLUMN_NAME_TIEMPO + TEXT_TYPE + COMMA_SEP +
					SessionRegistro.COLUMN_NAME_VELOCIDAD + TEXT_TYPE + COMMA_SEP +					
					SessionRegistro.COLUMN_NAME_COMENTARIOS + TEXT_TYPE + " )";

	private static final String SQL_DELETE_ENTRIES_SESSION =
			"DROP TABLE IF EXISTS " + SessionRegistro.TABLE_NAME;
	
	
	public BaseDatosHelper(Context context) {
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
    	insert("1","caminata");
    	insert("2","correr");
    	insert("3","ciclismo");
    }  
    
	private void insert( String codigo, String desc){
		// Gets the data repository in write mode
		SQLiteDatabase db = this.getWritableDatabase();
	
		// Create a new map of values, where column names are the keys
		ContentValues values = new ContentValues();
		values.put(ActividadRegistro.COLUMN_NAME_CODIGO, codigo);
		values.put(ActividadRegistro.COLUMN_NAME_DESCRIPCION, desc);
		
	
		// Insert the new row, returning the primary key value of the new row
		long newRowId;
		newRowId = db.insert(
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
	
    public void insertAllSessiones(){
		ContentValues values = new ContentValues();
		values.put(SessionRegistro.COLUMN_NAME_ACTIVIDAD,"correr");
		values.put(SessionRegistro.COLUMN_NAME_COMENTARIOS,"flojito");
		values.put(SessionRegistro.COLUMN_NAME_DISTANCIA, "12km");
		values.put(SessionRegistro.COLUMN_NAME_FECHA,  "01-11-2013");
		values.put(SessionRegistro.COLUMN_NAME_TIEMPO, "01:05:54");
		values.put(SessionRegistro.COLUMN_NAME_VELOCIDAD, "8km/h");
    	insertSession(values);
    	
		ContentValues values1 = new ContentValues();
		values1.put(SessionRegistro.COLUMN_NAME_ACTIVIDAD,"caminata");
		values1.put(SessionRegistro.COLUMN_NAME_COMENTARIOS,"bien");
		values1.put(SessionRegistro.COLUMN_NAME_DISTANCIA, "12km");
		values1.put(SessionRegistro.COLUMN_NAME_FECHA,  "30-11-2013");
		values1.put(SessionRegistro.COLUMN_NAME_TIEMPO, "01:05:54");
		values1.put(SessionRegistro.COLUMN_NAME_VELOCIDAD, "8km/h");
		insertSession(values1);
		
		ContentValues values2 = new ContentValues();
		values2.put(SessionRegistro.COLUMN_NAME_ACTIVIDAD,"ciclismo");
		values2.put(SessionRegistro.COLUMN_NAME_COMENTARIOS,"pare a comer una bondiola");
		values2.put(SessionRegistro.COLUMN_NAME_DISTANCIA, "12km");
		values2.put(SessionRegistro.COLUMN_NAME_FECHA,  "15-10-2013");
		values2.put(SessionRegistro.COLUMN_NAME_TIEMPO, "01:05:54");
		values2.put(SessionRegistro.COLUMN_NAME_VELOCIDAD, "8km/h");
		insertSession(values2);
		
		ContentValues values3 = new ContentValues();
		values3.put(SessionRegistro.COLUMN_NAME_ACTIVIDAD,"caminata");
		values3.put(SessionRegistro.COLUMN_NAME_COMENTARIOS,"genial");
		values3.put(SessionRegistro.COLUMN_NAME_DISTANCIA, "12km");
		values3.put(SessionRegistro.COLUMN_NAME_FECHA,  "11-04-2013");
		values3.put(SessionRegistro.COLUMN_NAME_TIEMPO, "01:05:54");
		values3.put(SessionRegistro.COLUMN_NAME_VELOCIDAD, "8km/h");
		insertSession(values3);
    }  

	public void insertSession( ContentValues values ){
		SQLiteDatabase db = this.getWritableDatabase();
	
		long newRowId;
		newRowId = db.insert(
				SessionRegistro.TABLE_NAME,
				SessionRegistro.COLUMN_NAME_NULLABLE,
		         values);	
		 db.close(); 
	}
	
	public Cursor selectAllSessiones(){
	    String selectQuery = "SELECT  * FROM " + SessionRegistro.TABLE_NAME;
	    
	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);
	    cursor.moveToFirst();
	    return cursor;
	}
	
	public void updateSession(String id, ContentValues values){
	SQLiteDatabase db = this.getReadableDatabase();

	String selection = SessionRegistro.COLUMN_NAME_ID + " LIKE ?";
	String[] selectionArgs = { id };

	int count = db.update(
	    SessionContract.SessionRegistro.TABLE_NAME,
	    values,
	    selection,
	    selectionArgs);
	}
	
	public void deleteSession( String id ){
		SQLiteDatabase db = this.getReadableDatabase();

		String whereClause = SessionRegistro.COLUMN_NAME_ID + "= \"" + id + "\"";
		db.delete(SessionRegistro.TABLE_NAME, whereClause, null);
	}	
}