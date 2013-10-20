package ar.edu.ort.t5.tp2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class FabricaOperaciones {
	private static FabricaOperaciones singleton;
	private Properties props;
	
	private FabricaOperaciones() {
		props = new Properties();
		try {
			InputStream is = getClass().getResourceAsStream(
					"operaciones.properties"); 
			props.load(is);
		} catch (IOException e) {}
		props.put("+", Suma.class.getCanonicalName());
		props.put("-", Resta.class.getCanonicalName());
		props.put("/", Division.class.getCanonicalName());
		props.put("*", Multiplicacion.class.getCanonicalName());
	}
	
	public static final FabricaOperaciones getInstance() {
		if (singleton == null)
			singleton = new FabricaOperaciones();
		return singleton;
	}
	
	@SuppressWarnings("unchecked")
	public Operacion crearOperacion(String signo) {
		String nombreClase = props.getProperty(signo);
		
		try {
			Class<Operacion> classOperacion = (Class<Operacion>) 
				Class.forName(nombreClase);
			return (Operacion) classOperacion.newInstance();
		} catch (Exception e) {
			return null;
		}
	}
}