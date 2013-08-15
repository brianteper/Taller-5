package ar.edu.ort.t5.util;

public class StrUtil {

	public static String reverse(String s){
		String ret = "";
		for (int i = s.length() - 1; i >= 0; i--){
			ret = ret.concat(String.valueOf(s.charAt(i)));
		}
		
		return ret;
	}
}
