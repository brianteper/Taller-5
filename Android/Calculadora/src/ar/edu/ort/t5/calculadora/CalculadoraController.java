package ar.edu.ort.t5.calculadora;

import java.util.Observer;

public class CalculadoraController {
	private CalculadoraLineal calc;
	
	public CalculadoraController(Observer o){
		calc = CalculadoraLineal.getInstance();
		
		calc.addObserver(o);
	}
	
	public void agregarOperacion(String oper){
		calc.agregarOperacion(oper);
	}
	
	public void agregarNumero(Double valor){
		calc.agregarNumero(valor);
	}
	
	public void limpiar(){
		calc.limpiar();
	}
	
	public Double getTotal(){
		return calc.getTotal();
	}
}
