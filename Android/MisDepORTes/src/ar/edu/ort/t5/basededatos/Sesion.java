package ar.edu.ort.t5.basededatos;

import android.provider.BaseColumns;


//b) Tabla Sesiones (Fecha, Actividad, Distancia, Tiempo, Velocidad, Comentarios)

public class Sesion {
	
	public Sesion(){
		
	}
	
	public static abstract class SesionRegistro implements BaseColumns {
		public static final String TABLE_NAME = "Session";
		public static final String COLUMN_NAME_ID = "_id";
		public static final String COLUMN_NAME_FECHA = "fecha";
		public static final String COLUMN_NAME_ACTIVIDAD = "actividad";
		public static final String COLUMN_NAME_DISTANCIA = "distancia";
		public static final String COLUMN_NAME_TIEMPO = "tiempo";
		public static final String COLUMN_NAME_VELOCIDAD = "velocidad";
		public static final String COLUMN_NAME_COMENTARIOS = "comentarios";
		public static final String COLUMN_NAME_NULLABLE = null;
	}
}
