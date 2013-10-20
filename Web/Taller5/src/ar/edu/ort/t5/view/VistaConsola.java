package ar.edu.ort.t5.view;

import java.util.Observable;
import java.util.Observer;

import ar.edu.ort.t5.tp2.CalculadoraLineal;

public final class VistaConsola implements Observer {
	private String nombre;

	public VistaConsola(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public void update(Observable who, Object what) {
		CalculadoraLineal calc = (CalculadoraLineal) who;
		System.out.println(what + ": " + nombre + ": " + calc.getTotal());
	}

}
