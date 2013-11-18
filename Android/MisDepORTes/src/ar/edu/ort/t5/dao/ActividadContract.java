package ar.edu.ort.t5.dao;

import android.provider.BaseColumns;


//a) Tabla Actividades (Codigo, Descripcion)
//1 - Caminata
//2 - Correr
//3 - Ciclismo
//N - etc
public final class ActividadContract {
	 
	public ActividadContract(){
		
	}
	
	/* Inner class that defines the table contents */
	public static abstract class ActividadRegistro implements BaseColumns {
		public static final String TABLE_NAME = "Actividad";
		public static final String COLUMN_NAME_ID = "_id";
		public static final String COLUMN_NAME_CODIGO = "codigo";
		public static final String COLUMN_NAME_DESCRIPCION = "descripcion";
		public static final String COLUMN_NAME_NULLABLE = null;
	}
		
	
	private String codigo = null;
	private String descripcion = null;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
