package ar.edu.ort.t5.tp2;

import java.util.Observable;

public final class CalculadoraLineal extends Observable {
	private double total;
	private Operacion operacion;
	
	public void limpiar() {
		total = 0;
		operacion = null;
		setChanged();
		notifyObservers("Limpiar");
	}
	
	public double getTotal() {
		return total;
	}
	
	public void agregarNumero(double valor) {
		String what = "Primera Vez";
		if (operacion == null)
			total = valor;
		else{
			total = operacion.calcular(total, valor);
			what = operacion.getClass().getName();
			}
		setChanged();
		notifyObservers(what);
	}
	
	public void agregarOperacion(String signo) {
		operacion = FabricaOperaciones.getInstance().crearOperacion(signo);
	}
	
}