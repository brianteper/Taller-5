package ar.edu.ort.t5.calculadora;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.EditText;

public class CalculadoraGrafica extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora_grafica);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.calculadora_grafica, menu);
        return true;
    }
    
    public void calcFunctionClear(){
    	EditText text = (EditText) findViewById(R.id.editTotal);
    	
    	CalculadoraLineal.getInstance().limpiar();
    	
    	text.setText("@string/calc_total_default");
    }
    
    public void calcOperacionEqu(){
    	EditText text = (EditText) findViewById(R.id.editTotal);
    	
    	text.setText(String.valueOf(CalculadoraLineal.getInstance().getTotal()));
    }
    
}
