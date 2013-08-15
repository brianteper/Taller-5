package ar.edu.ort.t5.launcher;

import ar.edu.ort.t5.util.StrUtil;

public class TestStrUtil {

	public static void main(String[] args) {
		String s = "esto es un ejemplo";
		
		String r = StrUtil.reverse(s);
		
		System.out.println("[" + s + "] invertido es [" + r  + "]");
	}

}
