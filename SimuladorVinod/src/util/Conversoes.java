package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Conversoes {
	private static HashMap<String, String> binToHex = new HashMap<>();
	static {
		binToHex.put("0000", "0");
		binToHex.put("0001", "1");
		binToHex.put("0010", "2");
		binToHex.put("0011", "3");
		binToHex.put("0100", "4");
		binToHex.put("0101", "5");
		binToHex.put("0110", "6");
		binToHex.put("0111", "7");
		binToHex.put("1000", "8");
		binToHex.put("1001", "9");
		binToHex.put("1010", "A");
		binToHex.put("1011", "B");
		binToHex.put("1100", "C");
		binToHex.put("1101", "D");
		binToHex.put("1110", "E");
		binToHex.put("1111", "F");
	}
	
	public static int bitArrayToDecimal(ArrayList<Integer> data) {
		while(data.size()<32) {
			data.add(0, 0);
		}
		int sum = 0;
		for(int i = 31; i>-1; i--) {
			sum += (data.get(i)*Math.pow(2, 31 - i));
		}
		return sum;
	}

	//Exemplo : 0...011, o tem 1 na última e penúltima posição
	//Testando : data.get(31) * 2^(31), ou seja, está elevando o último elemento da lista por 31, mas deveria ser o contrário
	
	public static ArrayList<Integer> decimalToArrayList(int decimal){
		String binary = Integer.toBinaryString(decimal);
		binary = String.format("%32s", binary).replaceAll(" ", "0");
		ArrayList<Integer> converted = new ArrayList<>(32);
		for(int i = 0; i<32; i++) {
			converted.add(Integer.parseInt(String.valueOf(binary.charAt(i))));
		}
		return converted;

	}
	
	public static String binaryToHex(String binary) { // só funciona se for múltiplo de 4. não vou corrigir
		String retorno = "";
		for(int i = 0; i<binary.length(); i+=4) { //para cada bloco de 4
			retorno += binToHex.get(binary.substring(i, i+4)); // dá um append no valor para cada bloco de 4
		}
		return retorno;
	}
	
	public static String conversaoCompleta(int operando, int size) {
		String binario = Integer.toBinaryString(Math.abs(operando));
		while(binario.length()<size) {
			binario = "0"+binario;
		}
		// acerta o sinal, fazendo a conversão para c2
		if(operando<0) {
			String inverso = "";
			// inverte
			for(int i = binario.length()-1; i>=0; i--) {
				if(binario.charAt(i) == '0') {
					inverso += '1';
				}else {
					inverso += '0';
				}
			}
			// soma 1
			char carry = '1';
			int i = inverso.length()-1;
			binario = ""; // limpa binario para aproveitar a String
			while(i >= 0) {
				if(carry == '1') {
					if(inverso.charAt(i) == '0') {
						binario += '1';
						carry = '0';
					}else {
						binario += '0';
					}
				}else {
					binario += inverso.charAt(i);
				}
				
				i--;
			}
		}
		return binario;
	}
	
	public static int binaryIntToDecimal(int[] binaryAddress) {
		return Integer.parseInt(Arrays.toString(binaryAddress).replaceAll("[\\[,\\s\\]]", ""),2);
	}
	public static int binaryIntToDecimal(Integer[] binaryAddress) {
		return Integer.parseInt(Arrays.toString(binaryAddress).replaceAll("[\\[,\\s\\]]", ""),2);
	}
	
	public static String bitArrayToC2(int[] data) {
		int sum = 0;
		int len = data.length-1;
		sum -= data[0]*(Math.pow(2, len));
		for(int i = 1; i<len+1; i++) {
			sum += data[i]*(Math.pow(2, len-i));
		}
		return String.valueOf(sum);
	}
	public static String bitArrayToC2(Integer[] data) {
		int sum = 0;
		int len = data.length-1;
		sum -= data[0]*(Math.pow(2, len));
		for(int i = 1; i<len+1; i++) {
			sum += data[i]*(Math.pow(2, len-i));
		}
		return String.valueOf(sum);
	}
	public static Integer[] stringToIntegerArray(String array) {
		Integer[] retorno = new Integer[array.length()];
		for(int i = 0; i<array.length();i++) {
			retorno[i] = Integer.parseInt(String.valueOf(array.charAt(i)));
		}
		return retorno;
	}
	
	public static String integerArrayToString(Integer[] array) {
		String retorno = "";
		for(int i = 0; i<array.length; i++) {
			retorno += array[i];
		}
		return retorno;
	}
	public static String intArrayToString(int[] array) {
		String retorno = "";
		for(int i = 0; i<array.length; i++) {
			retorno += array[i];
		}
		return retorno;
	}
	public static Integer[] intArrayToIntegerArray(int[] array) {
		Integer[] retorno = new Integer[array.length];
		for(int i = 0; i<retorno.length; i++) {
			retorno[i] = array[i];
		}
		return retorno;
	}
	public static int[] integerArrayToIntArray(Integer[] array) {
		int[] retorno = new int[array.length];
		for(int i = 0; i<retorno.length; i++) {
			retorno[i] = array[i];
		}
		return retorno;
	}
}
