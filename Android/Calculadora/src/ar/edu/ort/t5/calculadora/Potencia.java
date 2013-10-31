package ar.edu.ort.t5.calculadora;

public final class Potencia extends Operacion {

	@Override
	public double calcular(double numero1, double numero2) {
		return Math.pow(numero1, numero2);
	}
}