package ar.edu.ort.t5.launcher;

import ar.edu.ort.t5.tp2.CalculadoraLineal;
import ar.edu.ort.t5.view.VistaArchivo;
import ar.edu.ort.t5.view.VistaConsola;
import ar.edu.ort.t5.view.VistaGrafica;

public class TestCalculadora {
	public static void main(String[] args) {
		CalculadoraLineal calc = new CalculadoraLineal();
		VistaConsola vista1 = new VistaConsola("SubTotal");
		calc.addObserver(vista1);
		//VistaConsola vista2 = new VistaConsola("vista2");
		//calc.addObserver(vista2);
		VistaArchivo vista3 = new VistaArchivo("vista3");
		calc.addObserver(vista3);
		VistaGrafica vista4 = new VistaGrafica("VistaGrafica");
		vista4.setVisible(true);
		calc.addObserver(vista4);
		
//		calc.agregarOperacion("-");
//		calc.agregarNumero(20);
		
		calc.agregarNumero(12);
		calc.agregarOperacion("+");
		calc.agregarNumero(34);
		calc.agregarOperacion("+");
		calc.agregarOperacion("/");
		calc.agregarOperacion("*");
		calc.agregarOperacion("-");
		calc.agregarNumero(5);
		
//		calc.agregarOperacion("*");
//		calc.agregarNumero(5);
//		
//		calc.agregarOperacion("-");
//		calc.agregarNumero(12);
//		
//		calc.agregarOperacion("*");
//		calc.agregarOperacion("/");
//		calc.agregarNumero(2);
		System.out.println("Total: " + calc.getTotal());
	}
}
