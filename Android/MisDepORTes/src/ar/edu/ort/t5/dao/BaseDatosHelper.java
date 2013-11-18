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

	// Database Version
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "depORTes.db";

	/***********COMMONS*******/
	private static final String ID_TYPE = " integer primary key autoincrement";
	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";
	
	/***********ACTIVIDAD*******/
	
	private static final String SQL_CREATE_ENTRIES_ACTIVIDAD =
			"CREATE TABLE " + ActividadRegistro.TABLE_NAME + " (" +
					ActividadRegistro.COLUMN_NAME_ID +  ID_TYPE + COMMA_SEP +
					ActividadRegistro.COLUMN_NAME_CODIGO + TEXT_TYPE + COMMA_SEP +
					ActividadRegistro.COLUMN_NAME_DESCRIPCION + TEXT_TYPE + " )";
	
	private static final String SQL_DELETE_ENTRIES_ACTIVIDAD =
			"DROP TABLE IF EXISTS " + ActividadRegistro.TABLE_NAME;

	/***********SESSION*******/
	//todo: fijarse de cambiar los tipos de datos de los campos
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
	
    
    /*******ACTIVIDAD - OPERACIONES*****/
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
	
//	public List<ActividadContract> selectAll(){
//	    List<ActividadContract> list = new ArrayList<ActividadContract>();
//	    // Select All Query
//	    String selectQuery = "SELECT  * FROM " + ActividadRegistro.TABLE_NAME;
//	 
//	    SQLiteDatabase db = this.getWritableDatabase();
//	    Cursor cursor = db.rawQuery(selectQuery, null);
//	 
//	    // looping through all rows and adding to list
//	    if (cursor.moveToFirst()) {
//	        do {
//	        	ActividadContract actividad = new ActividadContract();	            
//	            actividad.setCodigo(cursor.getString(1));
//	            actividad.setDescripcion(cursor.getString(2));
//	            // Adding contact to list
//	            list.add(actividad);
//	        } while (cursor.moveToNext());
//	    }
//	 
//	    db.close(); 
//	    // return contact list
//	    return list;
//	}
//	
	public Cursor selectAllActividades(){
	    // Select All Query
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
//	public Cursor select(){
//		SQLiteDatabase db = this.getReadableDatabase();
//
//		// Define a projection that specifies which columns from the database
//		// you will actually use after this query.
//		String[] projection = {
//			ActividadRegistro.COLUMN_NAME_CODIGO,
//			ActividadRegistro.COLUMN_NAME_DESCRIPCION,		    
//		    };
//
//		String selection = "";
//		String[] selectionArgs = new String[0];
//		// How you want the results sorted in the resulting Cursor
//		String sortOrder =
//				ActividadRegistro.COLUMN_NAME_CODIGO + " DESC";
//
//		Cursor c = db.query(
//			ActividadRegistro.TABLE_NAME,  // The table to query
//		    projection,                               // The columns to return
//		    selection,                                // The columns for the WHERE clause
//		    selectionArgs,                            // The values for the WHERE clause
//		    null,                                     // don't group the rows
//		    null,                                     // don't filter by row groups
//		    sortOrder                                 // The sort order
//		    );
//		
//		return c;
//	}
	
//	public void update(){
//		SQLiteDatabase db = this.getReadableDatabase();
//
//		// New value for one column
//		ContentValues values = new ContentValues();
//		values.put(ActividadRegistro.COLUMN_NAME_CODIGO, "codigo");
//
//		// Which row to update, based on the ID
//		String selection = ActividadRegistro.COLUMN_NAME_CODIGO + " LIKE ?";
//		String[] selectionArgs = { String.valueOf(rowId) };
//
//		int count = db.update(
//		    Actividad.ActividadRegistro.TABLE_NAME,
//		    values,
//		    selection,
//		    selectionArgs);
//	}
	
    /*******SESSION - OPERACIONES*****/
	public void insertAllSessiones(){
		ContentValues values = new ContentValues();
		values.put(SessionRegistro.COLUMN_NAME_ACTIVIDAD,"correr");
		values.put(SessionRegistro.COLUMN_NAME_COMENTARIOS,"flojito");
		values.put(SessionRegistro.COLUMN_NAME_DISTANCIA, "12km");
		values.put(SessionRegistro.COLUMN_NAME_FECHA,  "2013-11-01");
		values.put(SessionRegistro.COLUMN_NAME_TIEMPO, "01:05:54");
		values.put(SessionRegistro.COLUMN_NAME_VELOCIDAD, "8km/h");
    	insertSession(values);
		ContentValues values1 = new ContentValues();
		values1.put(SessionRegistro.COLUMN_NAME_ACTIVIDAD,"caminata");
		values1.put(SessionRegistro.COLUMN_NAME_COMENTARIOS,"bien");
		values1.put(SessionRegistro.COLUMN_NAME_DISTANCIA, "12km");
		values1.put(SessionRegistro.COLUMN_NAME_FECHA,  "2013-11-02");
		values1.put(SessionRegistro.COLUMN_NAME_TIEMPO, "01:05:54");
		values1.put(SessionRegistro.COLUMN_NAME_VELOCIDAD, "8km/h");
		insertSession(values1);
		ContentValues values2 = new ContentValues();
		values2.put(SessionRegistro.COLUMN_NAME_ACTIVIDAD,"ciclismo");
		values2.put(SessionRegistro.COLUMN_NAME_COMENTARIOS,"pare a comer una bondiola");
		values2.put(SessionRegistro.COLUMN_NAME_DISTANCIA, "12km");
		values2.put(SessionRegistro.COLUMN_NAME_FECHA,  "2013-11-03");
		values2.put(SessionRegistro.COLUMN_NAME_TIEMPO, "01:05:54");
		values2.put(SessionRegistro.COLUMN_NAME_VELOCIDAD, "8km/h");
		insertSession(values2);
		ContentValues values3 = new ContentValues();
		values3.put(SessionRegistro.COLUMN_NAME_ACTIVIDAD,"caminata");
		values3.put(SessionRegistro.COLUMN_NAME_COMENTARIOS,"genial");
		values3.put(SessionRegistro.COLUMN_NAME_DISTANCIA, "12km");
		values3.put(SessionRegistro.COLUMN_NAME_FECHA,  "2013-11-04");
		values3.put(SessionRegistro.COLUMN_NAME_TIEMPO, "01:05:54");
		values3.put(SessionRegistro.COLUMN_NAME_VELOCIDAD, "8km/h");
		insertSession(values3);
    	
    }  

	public void insertSession( ContentValues values ){
		// Gets the data repository in write mode
		SQLiteDatabase db = this.getWritableDatabase();
	
		// Insert the new row, returning the primary key value of the new row
		long newRowId;
		newRowId = db.insert(
				SessionRegistro.TABLE_NAME,
				SessionRegistro.COLUMN_NAME_NULLABLE,
		         values);	
		 db.close(); 
	}
	
	public Cursor selectAllSessiones(){
	    // Select All Query
	    String selectQuery = "SELECT  * FROM " + SessionRegistro.TABLE_NAME;
	    
	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);
	    cursor.moveToFirst();
	    return cursor;
	}
	
	public void updateSession(String id, ContentValues values){
	SQLiteDatabase db = this.getReadableDatabase();

	// New value for one column

	// Which row to update, based on the ID
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
