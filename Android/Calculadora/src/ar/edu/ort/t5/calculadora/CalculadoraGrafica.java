package ar.edu.ort.t5.calculadora;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Observable;
import java.util.Observer;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class CalculadoraGrafica extends Activity implements Observer {
	private String valor = "";
	private boolean start = false;
	private CalculadoraController controller;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora_grafica);
        
        controller = new CalculadoraController(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.calculadora_grafica, menu);
        return true;
    }
    
    public void calcFunctionClear(View v){
    	controller.limpiar();
    	
    	valor = "";
    	start = false;
    }
    
    public void calcOperacionEqu(View v){
    	controller.agregarNumero(Double.valueOf(valor));
    
    	controller.getTotal();
    
    	start = true;
    	valor = "";
    }
    
    private void addValor(String val) {
    	if (start){
    		valor = "";
    	}
    	
    	valor += val;
	}
    
    public void calcNumero0(View v){
    	addValor("0");
    }
    
    public void calcNumero1(View v){
    	addValor("1");
    }
    
    public void calcNumero2(View v){
    	addValor("2");
    }
    
    public void calcNumero3(View v){
    	addValor("3");
    }
    
    public void calcNumero4(View v){
    	addValor("4");
    }
    
    public void calcNumero5(View v){
    	addValor("5");
    }
    
    public void calcNumero6(View v){
    	addValor("6");
    }
    
    public void calcNumero7(View v){
    	addValor("7");
    }
    
    public void calcNumero8(View v){
    	addValor("8");
    }
    
    public void calcNumero9(View v){
    	addValor("9");
    }
    
    public void calcNumeroDot(View v){
    	addValor(".");
    }
    
    private void addOperacion(String oper) {
    	if (!start && valor != ""){   	
    		controller.agregarNumero(Double.valueOf(valor));
        	controller.agregarOperacion(oper);
        	start = true;
    	}else{
    		controller.agregarOperacion(oper);
    		start = false;
    	}
	}
    
    public void calcOperacionSum(View v){
    	addOperacion("+");
    }
    
    public void calcOperacionMin(View v){
    	addOperacion("-");
    }
    
    public void calcOperacionMul(View v){
    	addOperacion("*");
    }
    
    public void calcOperacionDiv(View v){
    	addOperacion("/");
    }


	@Override
	public void update(Observable arg0, Object arg1) {
		NumberFormat nf = new DecimalFormat("##.###");
		double total = controller.getTotal();
		
		EditText text = (EditText) findViewById(R.id.editTotal);
    	text.setText(String.valueOf(nf.format(total)));
	}
}
