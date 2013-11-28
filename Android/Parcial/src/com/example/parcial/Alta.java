package com.example.parcial;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Alta extends Activity {
	private Button botonIngreso;
	private TextView textoCodigo;
	private TextView textoNombre;
	private TextView textoResultado;
	private BasedeDatos db;
	private Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alta);
		
		context = this;
		
		textoCodigo = (TextView) findViewById(R.id.codigo);
		textoNombre = (TextView) findViewById(R.id.nombre);
		textoResultado = (TextView) findViewById(R.id.resultado);
		
		botonIngreso = (Button) findViewById(R.id.ingreso);
		
		db = new BasedeDatos(context);
		
		botonIngreso.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				db.insertarAlumno(textoCodigo.getText().toString(), textoNombre.getText().toString());
				
				verConsulta();
			}
		});
	}
	
	public void verConsulta() {
		Cursor c = db.execQuery("SELECT codigo, nombre FROM Parcial", null);
		textoResultado.setText("");
		
		do{
			textoResultado.setText(textoResultado.getText().toString() + "\n" + c.getString(0) + " - " + c.getString(1));
		}while(c.moveToNext());
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alta, menu);
		return true;
	}
}
