package ar.edu.ort.t5.misdeportes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import ar.edu.ort.t5.basededatos.BaseDeDatos;
import ar.edu.ort.t5.basededatos.Sesion.SesionRegistro;

public class MainActivity extends Activity {

	Context context;
	private BaseDeDatos db = null;
	
	@Override
	protected void onResume(){
		super.onResume();
		llenarListView(db.selectAllSesiones());		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context =this;
		String[] a = this.databaseList();
		
		for (int i=0; i<a.length; i++){
			this.deleteDatabase(a[i]);			
		}
		
		db = new BaseDeDatos(this);
		
		db.insertAllActividades();
		db.insertAllSesiones();

		onResume();
	}	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, 0, Menu.NONE, "Añadir Sesion");
		getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case 0:
	        	Intent i = new Intent(context, ActionActivity.class);
	        	i.putExtra("BOTON_NOMBRE", "Crear");
	            startActivity(i);
           	   
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	private void llenarListView( Cursor cursor){
		ListView listView= (ListView) findViewById(R.id.listView);
 
	    String[] arrayColumns = new String[]{
    		SesionRegistro.COLUMN_NAME_ID,
    		SesionRegistro.COLUMN_NAME_ACTIVIDAD,
    		SesionRegistro.COLUMN_NAME_COMENTARIOS,
    		SesionRegistro.COLUMN_NAME_DISTANCIA,
    		SesionRegistro.COLUMN_NAME_FECHA,
    		SesionRegistro.COLUMN_NAME_TIEMPO,
    		SesionRegistro.COLUMN_NAME_VELOCIDAD
	    };
	     
	    int[] arrayViewIDs = new int[]{
	    		R.id.textIdSession,
	    		R.id.textViewActividad,
	    		R.id.TextViewComentarios,
	    		R.id.TextViewDistancia,
	    		R.id.textViewFecha,
	    		R.id.TextViewTiempo,
	    		R.id.TextViewVelocidad
		};
	     
	     
	    SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.listview_each_item, cursor, arrayColumns, arrayViewIDs,0);
	    listView.setAdapter(adapter);
   
	    listView.setOnItemClickListener(new OnItemClickListener(){
           public void onItemClick(AdapterView<?> arg0, View v,int position, long arg3){
               final TextView id = (TextView)v.findViewById(R.id.textIdSession);
        	   final TextView textViewAcvitidad = (TextView)v.findViewById(R.id.textViewActividad);
        	   final TextView textViewFecha = (TextView)v.findViewById(R.id.textViewFecha);
        	   final TextView textViewComentarios = (TextView)v.findViewById(R.id.TextViewComentarios);
        	   final TextView textViewDistancia = (TextView)v.findViewById(R.id.TextViewDistancia);
        	   final TextView textViewTiempo = (TextView)v.findViewById(R.id.TextViewTiempo);
        	   final TextView textViewVelocidad = (TextView)v.findViewById(R.id.TextViewVelocidad);
               
               AlertDialog dialog = new AlertDialog.Builder(context).create();
               dialog.setTitle("Opciones");
               dialog.setIcon(android.R.drawable.ic_dialog_info);
               dialog.setMessage("Elija una de las siguientes opciones:");
               dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Actualizar", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int which){
                	   Intent i = new Intent(context, ActionActivity.class);
                	   i.putExtra("ID", id.getText().toString());
                	   i.putExtra("ACTIVIDAD", textViewAcvitidad.getText().toString());
                	   i.putExtra("FECHA", textViewFecha.getText().toString());
                	   i.putExtra("COMENTARIOS", textViewComentarios.getText().toString());
                	   i.putExtra("DISTANCIA", textViewDistancia.getText().toString());
                	   i.putExtra("TIEMPO", textViewTiempo.getText().toString());
                	   i.putExtra("VELOCIDAD", textViewVelocidad.getText().toString());
                		
                	   i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                       startActivity(i);
                   }  
               });
               
               dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Eliminar", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int which){
                	   db.deleteSesion(id.getText().toString());
                	   motrarMensajeSesionEliminada();
                   }  
               });
               
               dialog.show();
           }
       });
	}
	
	private void motrarMensajeSesionEliminada(){
		AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle("Alerta");
        dialog.setMessage("Se ha eliminado la sesión seleccionada.");
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