package ar.edu.ort.t5.ejemplo;

public class TestString {

	public static void main(String[] args) {
		String s1 = "abcd";
		String s2 = s1.toUpperCase();
		String s3 = s2.toLowerCase();
		
		//if(s1==s3)//Compara instancias de Objetos
		//if(s1 == "abc"+"d")		
		if (s1.equals(s3)){
			System.out.println("Son iguales");
		}else{
			System.out.println("Son diferentes");
		}
	}
	
}
