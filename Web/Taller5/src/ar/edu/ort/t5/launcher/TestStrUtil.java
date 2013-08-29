package ar.edu.ort.t5.launcher;

import ar.edu.ort.t5.util.*;

public class TestStrUtil {

	public static void main(String[] args) {
		char ch = 'm';
		
		System.out.println(ch + " es minúscula?: " + StrUtil.isLowerCase(ch));
		boolean u = StrUtil.isUpperCase(ch);
		boolean le = StrUtil.isLetter(ch);
		
		char up = StrUtil.toUpperCase(ch);
		char low = StrUtil.toLowerCase(up);
		
		String s = "esto es un ejemplo";
		String sUp = StrUtil.toUpperCase(s);
		System.out.println(sUp);
		System.out.println(StrUtil.toLowerCase(sUp));
		System.out.println(StrUtil.reverse(s));
		
		String pal = "neuquen";
		boolean t = StrUtil.isPalindrome(pal);
		boolean f = StrUtil.isPalindrome(s);
		
		System.out.println(StrUtil.capitalize(s));
		
		String num = "10";
		
		boolean isInt =  StrUtil.isInteger(num);
		
		String[] arr = {
				"10",
				"+",
				"20",
				"-",
				"25",
				"+",
				"-",
				"/",
				"5",
				"*",
				"2"
		};
		
		System.out.println(StrUtil.calculate(arr));
	}

}
