package ar.edu.ort.t5.util;

public final class StrUtil {
	
	public static boolean isLowerCase(char c){
		return c >= 'a' && c <= 'z';
		//return c<=97 && c<97+26;//Ascy
	}
	
	public static boolean isUpperCase(char c){
		return c >= 'A' && c <= 'Z';
		//return c<=97 && c<97+26;//Ascy
	}
	
	public static boolean isLeter(char c){
		return isLowerCase(c) || isUpperCase(c);
		//return c<=97 && c<97+26;//Ascy
	}
	
	public static boolean isLowerCase(String s){
		boolean isMin = s.length()>0;
		
		for(int i=0;i<s.length()&& isMin;i++){
			isMin = !StrUtil.isLeter(s.charAt(i))||
					StrUtil.isLowerCase(s.charAt(i));
			
		}
		
		return isMin;
	}

	public static String reverse(String s){
		String ret = "";
		
		//Recorro para atras y concateno en orden
		/*for (int i = s.length()-1;i>=0; i--){
			//ret = ret.concat(String.valueOf(s.charAt(i)));
			ret = ret + s.charAt(i);
		}*/
		
		//Recorro para adelante y concateno alreves
		for (int i = 0;i<s.length(); i++){
			//ret = ret.concat(String.valueOf(s.charAt(i)));
			ret = s.charAt(i) + ret ;
		}
		
		return ret;
	}
}
