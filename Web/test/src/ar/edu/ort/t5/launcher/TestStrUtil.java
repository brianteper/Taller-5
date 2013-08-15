package ar.edu.ort.t5.launcher;

import ar.edu.ort.t5.util.StrUtil;

public class TestStrUtil {

	public static void main(String[] args) {
		String s = "esto es un ejemplo";
		boolean b = StrUtil.isLowerCase(s);
		System.out.println("Es min["+ s + "]?"+b);
		
		String s1 = "Esto es un ejemplo";
		boolean b1 = StrUtil.isLowerCase(s1);
		System.out.println("Es min["+ s1 + "]?"+b1);
		
		System.out.println("["+ s1 + "] invertido es ["+ StrUtil.reverse(s1)+"]");
	}

}
