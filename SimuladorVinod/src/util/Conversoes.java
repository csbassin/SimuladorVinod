package util;

import java.util.ArrayList;
import java.util.Arrays;

public class Conversoes {
	
	public static int bitArrayToDecimal(ArrayList<Integer> data) {
		while(data.size()<32) {
			data.addFirst(0);
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
	

	public static int binaryIntToDecimal(int[] binaryAddress) {
		return Integer.parseInt(Arrays.toString(binaryAddress).replaceAll("[\\[,\\s\\]]", ""),2);
	}
	
	public static String bitArrayToC2(int[] data) {
		int sum = 0;
		int len = data.length-1;
		sum -= data[0]*(Math.pow(2, len));
		for(int i = 1; i<len; i++) {
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
}
