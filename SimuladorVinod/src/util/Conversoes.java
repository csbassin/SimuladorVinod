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

//		String sequence = "";
//
//		while(decimal != 0) {
//			sequence += decimal % 2;
//			decimal = decimal/2;
//		}
//
//		while(sequence.length()<32) {
//			sequence = '0'+sequence;
//		}
//
//		ArrayList<Integer> converted = new ArrayList<>(32);
//		for(int i = 0; i<32; i++) {
//			converted.add(Integer.parseInt(String.valueOf(sequence.charAt(i))));
//		}
//		return converted;
	}
	
	/*public static Integer[] completeArray(Integer[] array) {
		Integer
	}*/

	public static int binaryIntToDecimal(int[] binaryAddress) {
		return Integer.parseInt(Arrays.toString(binaryAddress).replaceAll("[\\[,\\s\\]]", ""),2);
	}
}
