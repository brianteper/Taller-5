package ar.edu.ort.t5.util;

public final class StrUtil {
	
	// =============================================================
	// Retorna un valor Booleano indicando si el caracter ch es una
	// letra minúscula.
	// =============================================================
	public static boolean isLowerCase(char ch) {
		return (ch >= 'a' && ch <= 'z');
	}

	// =============================================================
	// Retorna un valor Booleano indicando si el caracter ch es una
	// letra mayúscula.
	// =============================================================
	public static boolean isUpperCase(char ch) {
		return (ch >= 'A' && ch <= 'Z');
	}

	// =============================================================
	// Retorna un valor Booleano indicando si el caracter es letra
	// =============================================================
	public static boolean isLetter(char ch) {
		return (isLowerCase(ch) || isUpperCase(ch));
	}

	// =============================================================
	// Retorna el caracter ch convertido a minúsculas
	// =============================================================
	public static char toLowerCase(char ch) {
		if (isLowerCase(ch)) {
            ch += 32;
		}

        return ch;
	}

	// =============================================================
	// Retorna el caracter ch convertido a mayúsculas
	// =============================================================
	public static char toUpperCase(char ch) {
		if (isUpperCase(ch)) {
            ch -= 32;
		}

        return ch;
	}

	// =============================================================
	// Retorna el String s convertido a minúsculas
	// =============================================================
	// Realizar la conversión caracter a caracter.
	public static String toLowerCase(String s) {
		String ret = "";

        for (int i = 0; i < s.length(); i++) {
            if (isUpperCase(s.charAt(i))) {
                ret += String.valueOf(toLowerCase((char)s.charAt(i)));
            } else {
                ret += s.charAt(i);
            }
        }

        return ret;
	}

	// =============================================================
	// Retorna el String s convertido a mayúsculas
	// =============================================================
	// Realizar la conversión caracter a caracter.
	public static String toUpperCase(String s) {
		String ret = "";

        for (int i = 0; i < s.length(); i++) {
            if (isLowerCase((s.charAt(i)))) {
                ret += String.valueOf(toUpperCase((char)s.charAt(i)));
            } else {
                ret += s.charAt(i);
            }
        }

        return ret;
	}

	// =============================================================
	// Retorna el String s con el orden de los caracteres invertidos
	// =============================================================
	public static String reverse(String s) {
		String ret = "";
		
		for (int i = 0; i < s.length(); i++) {
			ret = s.charAt(i) + ret ;
		}
		
		return ret;
	}

	// =============================================================
	// Retorna un valor Booleano indicando si el String s es capicúa
	// =============================================================
	public static boolean isPalindrome(String s) {
		boolean palindrome = false;
		
		if (s.length() > 0) {
			int inc = 0;
			int des = s.length() - 1;		

			while ((inc < des) && (!palindrome)) {
				if (s.charAt(inc) == s.charAt(des)) {
					inc++;
					des--;
				} else {
					palindrome = true;
				}
			}
		} else {
			palindrome = true;
		}
		
        return palindrome;
	}

	// =============================================================
	// Retorna el String s capitalizado
	// =============================================================
	// Convierte a mayúscula la primera letra de cada palabra y
	// a minúscula el resto.
	//
	// Tip: convertir a mayúscula la primera letra que le sigue a
	// una no letra; convertir a minúscula toda letra que le
	// sigue a otra letra
	public static String capitalize(String s) {
		String ret = "";
    	int i = 1;
    	int j;
    	
    	if (s.length() > 0) {
			if (isLowerCase((char)s.charAt(0))) {
				ret += toUpperCase(s.charAt(0));
			} else {
				ret += s.charAt(0);
			}
			
			while(i < s.length()) {
				j = i - 1;
				
				if (isLetter(s.charAt(i))) {
					if (!isLetter(s.charAt(j))) {
						ret += toUpperCase(s.charAt(i));
					} else {
						ret += toLowerCase(s.charAt(i));
					}
				} else {
					ret += s.charAt(i);
				}
				
				i++;
			}
	    }
		
		return ret;
	}

	// =============================================================
	// Retorna un valor Booleano indicando si el String s es número
	// entero válido.
	// El número puede (pero no requiere) tener un signo al inicio.
	// =============================================================
	public static boolean isInteger(String s) {
		boolean isInt = false;
    	int i = 0;
    	
    	if (s.length() > 0) {         	
			if (s.charAt(0) == '-' || s.charAt(0) == '+') {
				if (s.length() > 1) {
					i++;
				}
			}
			
			for ( ; i < s.length(); i++) {
				isInt = isNumber(s.charAt(i));
				
				if (isInt == false){
					return isInt;
				}
			}
		}
		
        return isInt;
	}
	
	public static boolean isNumber(char c)
    {
        return (c >= '0' && c <= '9');
    }

	// =============================================================
	// Retorna un valor Booleano indicando si el String s es número
	// fraccional válido.
	// El número puede (pero no requiere) tener un signo al inicio.
	// =============================================================
	public static boolean isNumber(String s) {
		int i = 0;
        boolean ret = false;
        int cant = 0;
		
		if(s.length() > 0) {
			if (s.charAt(0) == '-' || s.charAt(0) == '+') {
				if (s.length() > 1) {
					i++;
				}
			}

			while (i < s.length()) {
				if (s.charAt(i) == '.') {
					cant ++;
				}
				
				if ((isNumber(s.charAt(i)) || s.charAt(i) == '.')  && cant <= 1) {
					ret = true;
				} else {
					return false;
				}

				i++;
			}
		}
        
		return ret;
	}

	// =============================================================
	// Retornar el cálculo de los valores recibidos en args.
	// =============================================================
	// Aceptar que el array args siempre viene bien formado.
	// O bien recibe un número o alguna de las siguientes operaciones:
	// + Suma
	// - Resta
	// / División
	// * Multiplicación
	//
	// Utilizar el método StrUtils.esNumero para saber si el
	// String es un número.
	//
	// Tip: para convertir el String a double, utilizar el método
	// Double.parseDouble.
	public static double calculate(String[] args) {
		double resultado = 0.0;
        int i = 0;
        
        while (i < args.length) {
        	switch (args[i]) {
			case "+":
				i++;
                resultado += new Double(args[i]);
				break;
			case "-":
				i++;
                resultado -= new Double(args[i]);
                break;
			case "/":
                i++;
                
                if (!isNumber(args[i])) {
                    String signo = args[i];
                    i++;

                    resultado /= new Double(signo + args[i]);
                } else {
                	resultado /= new Double(args[i]);
                }
                
                break;
			case "*":
				i++;
                if (!isNumber(args[i])) {
                    String signo = args[i];
                    i++;
                    resultado *= new Double(signo + args[i]);
                } else {
                	resultado *= new Double(args[i]);
                }
                
                break;

			default:
				resultado += new Double(args[i + 1]);
				break;
			}

            i++;
        }

        return resultado;
	}
}