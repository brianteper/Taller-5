package ar.edu.ort.t5.tp2;

public class Tester {

	public static void main(String[] args) {
		CalculadoraLineal cl = new CalculadoraLineal();
		cl.agregarNumero(10);
		cl.agregarOperacion("-");
		cl.agregarNumero(15);
		cl.agregarOperacion("+");
		cl.agregarNumero(5.50);
		cl.agregarOperacion("*");
		cl.agregarNumero(2);
		
		System.out.println("El total es: " + cl.getTotal());
	}

}
