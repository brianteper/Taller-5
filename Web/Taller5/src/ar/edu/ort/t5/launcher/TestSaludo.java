package ar.edu.ort.t5.launcher;

import ar.edu.ort.t5.ejemplo.*;

public class TestSaludo {

	public static void main(String[] args) {
		Saludo ahora = new SaludoDia();
		ahora.Saludar();
		
		Saludo ahora2 = new SaludoNoche();
		ahora2.Saludar();
		
		Saludo ahora3 = new SaludoTarde();
		ahora3.Saludar();
	}

}
