package ar.edu.ort.t5.view;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import ar.edu.ort.t5.tp2.CalculadoraLineal;

public class VistaGrafica extends JFrame implements Observer {
	private JTextArea memo;
	private String nombre;
	
	public VistaGrafica(String nombre)
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setSize(300,500);//Setea tamaño
		//setLocation(100,0);//Setea posicion
		setBounds(100,0,300,500);//Setea posicion(x;y) + tamaño
		getContentPane().setLayout(new BorderLayout());
		memo = new JTextArea();
		getContentPane().add(memo, BorderLayout.CENTER);
		
		//memo.append("Primer Texto\n");
		//memo.append("Segundo Texto\n");
		this.nombre = nombre;
	}
	@Override
	public void update(Observable who, Object what) {
		CalculadoraLineal calc = (CalculadoraLineal) who;
		memo.append(what + ": " + nombre + ": " + calc.getTotal()+"\n");
	}
	

	
	public static void main(String[] args)
	{
		VistaGrafica frame = new VistaGrafica("Test");
		frame.setVisible(true);
	}

}
