package ar.edu.ort.t5.calculadora;

import java.util.Observable;

public final class CalculadoraLineal extends Observable {
	private static CalculadoraLineal singleton;
	private double total;
	private Operacion operacion;
	
	private CalculadoraLineal(){
	
	}
	
	public static final CalculadoraLineal getInstance() {
		if (singleton == null)
			singleton = new CalculadoraLineal();
		return singleton;
	}
	
	public void limpiar() {
		total = 0;
		operacion = null;
		setChanged();
		notifyObservers();
	}
	
	public double getTotal() {
		return total;
	}
	
	public void agregarNumero(double valor) {
		if (operacion == null)
			setTotal(valor);
		else{
			setTotal(operacion.calcular(total, valor));
		}
		
		setChanged();
		notifyObservers();
	}

	private void setTotal(double valor) {
		total = valor;
		
		setChanged();
		notifyObservers();
	}
	
	public void agregarOperacion(String signo) {
		operacion = FabricaOperaciones.getInstance().crearOperacion(signo);
	}
	
}