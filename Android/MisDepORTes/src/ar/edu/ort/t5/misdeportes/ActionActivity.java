package ar.edu.ort.t5.misdeportes;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import ar.edu.ort.t5.dao.BaseDatosHelper;
import ar.edu.ort.t5.dao.SessionContract.SessionRegistro;

public class ActionActivity extends Activity {
	
	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context =this;
		setContentView(R.layout.action_activity);
		
		llenarListaActividades();
		
		TextView txtId= (TextView) findViewById(R.id.textIdToEdit);
		txtId.setText( getIntent().getStringExtra("ID"));

//		TextView txt= (TextView) findViewById(R.id.EditTextActividad);
//		txt.setText( getIntent().getStringExtra("ACTIVIDAD"));
				
		TextView txtComentarios= (TextView) findViewById(R.id.editTextComentarios);
		txtComentarios.setText( getIntent().getStringExtra("COMENTARIOS"));
		TextView txtDistancia= (TextView) findViewById(R.id.EditTextDistancia);
		txtDistancia.setText( getIntent().getStringExtra("DISTANCIA"));
		TextView txtFecha= (TextView) findViewById(R.id.editTextFecha);
		txtFecha.setText( getIntent().getStringExtra("FECHA"));
		
//		Button buttonFecha= (Button) findViewById(R.id.buttonFecha);
//		buttonFecha.setText( getIntent().getStringExtra("FECHA"));
		
		TextView txtTiempo= (TextView) findViewById(R.id.EditTextTiempo);
		txtTiempo.setText( getIntent().getStringExtra("TIEMPO"));
		TextView txtVelo= (TextView) findViewById(R.id.EditTexVelocidad);
		txtVelo.setText( getIntent().getStringExtra("VELOCIDAD"));
		
		if ( getIntent().getStringExtra("BOTON_NOMBRE") != null ){
			Button botonActualizar = (Button) findViewById(R.id.buttonActualizar);
			botonActualizar.setVisibility(View.INVISIBLE);
			Button boton = (Button) findViewById(R.id.buttonCrear);
			boton.setText(getIntent().getStringExtra("BOTON_NOMBRE"));
			
		}else{
			setActividadInSpinner(getIntent().getStringExtra("ACTIVIDAD"));
			Button botonCrear = (Button) findViewById(R.id.buttonCrear);
			botonCrear.setVisibility(View.INVISIBLE);
		}
	}
	
	public void llenarListaActividades(){
		BaseDatosHelper dbhelper = new BaseDatosHelper(this);
				
		Spinner spinner = (Spinner) findViewById(R.id.spinnerActividad);
        List<String> lables = dbhelper.selectAllActividadesList();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lables);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
	}
	
	private void setActividadInSpinner(String actividad){
		Spinner spinner = (Spinner) findViewById(R.id.spinnerActividad);
		BaseDatosHelper dbhelper = new BaseDatosHelper(this);
		spinner.setSelection(dbhelper.getActividadId(actividad)-1);
	}
	
	public void actualizarSession( View v ){
		TextView id = (TextView) findViewById(R.id.textIdToEdit);
//		TextView txtActividad = (TextView) findViewById(R.id.EditTextActividad);
		Spinner spinner = (Spinner) findViewById(R.id.spinnerActividad);
		
		String fecha = "12-12-2012";
		TextView txtDistancia = (TextView) findViewById(R.id.EditTextDistancia);
		TextView txtTiempo= (TextView) findViewById(R.id.EditTextTiempo);
		TextView txtVelo= (TextView) findViewById(R.id.EditTexVelocidad);
		TextView txtComentarios = (TextView) findViewById(R.id.editTextComentarios);
		
		BaseDatosHelper dbhelper = new BaseDatosHelper(this);
		
		ContentValues values = new ContentValues();
		values.put(SessionRegistro.COLUMN_NAME_ACTIVIDAD,  spinner.getSelectedItem().toString());
		values.put(SessionRegistro.COLUMN_NAME_COMENTARIOS, txtComentarios.getText().toString());
		values.put(SessionRegistro.COLUMN_NAME_DISTANCIA, txtDistancia.getText().toString());
		values.put(SessionRegistro.COLUMN_NAME_FECHA, fecha);
		values.put(SessionRegistro.COLUMN_NAME_TIEMPO, txtTiempo.getText().toString());
		values.put(SessionRegistro.COLUMN_NAME_VELOCIDAD, txtVelo.getText().toString());
		
		dbhelper.updateSession(id.getText().toString(), values );
		
		//alerta de session actualizada!		
		AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle("Accion");
        dialog.setMessage("Session Actualizada Correctamente!!");
        dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which){
            	Intent i = new Intent(context, MainActivity.class);
         	   i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);

            	startActivity(i);
            };
        });
        dialog.show();
	}
	
	public void crearSession( View v){
		Spinner spinner = (Spinner) findViewById(R.id.spinnerActividad);
		
		String fecha = "12-12-2012";
		TextView txtDistancia = (TextView) findViewById(R.id.EditTextDistancia);
		TextView txtTiempo= (TextView) findViewById(R.id.EditTextTiempo);
		TextView txtVelo= (TextView) findViewById(R.id.EditTexVelocidad);
		TextView txtComentarios = (TextView) findViewById(R.id.editTextComentarios);
		
		BaseDatosHelper dbhelper = new BaseDatosHelper(this);
		
		ContentValues values = new ContentValues();
		values.put(SessionRegistro.COLUMN_NAME_ACTIVIDAD,  spinner.getSelectedItem().toString());
		values.put(SessionRegistro.COLUMN_NAME_COMENTARIOS, txtComentarios.getText().toString());
		values.put(SessionRegistro.COLUMN_NAME_DISTANCIA, txtDistancia.getText().toString());
		values.put(SessionRegistro.COLUMN_NAME_FECHA, fecha);
		values.put(SessionRegistro.COLUMN_NAME_TIEMPO, txtTiempo.getText().toString());
		values.put(SessionRegistro.COLUMN_NAME_VELOCIDAD, txtVelo.getText().toString());
		
		dbhelper.insertSession( values );
		
		//alerta de session creada!		
		AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle("Accion");
        dialog.setMessage("Session Creada Correctamente!!");
        dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which){
            	Intent i = new Intent(context, MainActivity.class);
         	   i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);

            	startActivity(i);
            };
        });
        dialog.show();
		
	}
}
