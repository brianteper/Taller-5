package com.example.parcial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BasedeDatos extends SQLiteOpenHelper {
	private static int dbVersion = 1;
	private static String dbName = "parcial.db";
	
	String sqlCreate ="CREATE TABLE Parcial(codigo INTEGER, nombre TEXT)";
	String sqlDelete = "DROP TABLE IF EXISTS Parcial";

	public BasedeDatos(Context context) {
		super(context, dbName, null, dbVersion);		
		context.openOrCreateDatabase(dbName, dbVersion, null);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		 db.execSQL(sqlCreate);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(sqlDelete);
	    onCreate(db);
	}

	public Cursor execQuery(String query, Object object) {
		SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(query, null);
	    cursor.moveToFirst();
	    
	    return cursor;	
	}
	
	public void insertarAlumno(String codigo, String nombre){
		ContentValues values = new ContentValues();
		values.put("codigo",codigo);
		values.put("nombre",nombre);
    	
		SQLiteDatabase db = this.getWritableDatabase();
	
		db.insert("Parcial", null, values);
		
		db.close();   
	}
}
