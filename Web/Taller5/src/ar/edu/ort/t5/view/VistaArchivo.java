package ar.edu.ort.t5.view;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Observable;
import java.util.Observer;

import ar.edu.ort.t5.tp2.CalculadoraLineal;

public final class VistaArchivo implements Observer {
	private PrintStream fos;

	public VistaArchivo(String nombre) {
		try {
			this.fos = new PrintStream("Z:\\T5VistaArchivo\\" + nombre + ".txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Observable who, Object what) {
		CalculadoraLineal calc = (CalculadoraLineal) who;
		fos.println(what + ": " + calc.getTotal());
	}

}
