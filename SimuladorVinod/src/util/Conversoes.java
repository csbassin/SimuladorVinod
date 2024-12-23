package util;

import java.util.ArrayList;

public class Conversoes {
	
	public static int bitArrayToDecimal(ArrayList<Integer> data) {
		while(data.size()<32) {
			data.addFirst(0);
		}
		int sum = 0;
		for(int i = 31; i>-1; i--) {
			sum += data.get(i)*Math.pow(2, i);
		}
		return sum;
	}
	
	public static ArrayList<Integer> decimalToArrayList(int decimal){
		String sequence = "";
		
		while(decimal != 0) {
			sequence += decimal % 2;
			decimal = decimal/2;
		}
		
		while(sequence.length()<32) {
			sequence = '0'+sequence;
		}
		
		ArrayList<Integer> converted = new ArrayList<>(32);
		for(int i = 0; i<32; i++) {
			converted.add(Integer.parseInt(String.valueOf(sequence.charAt(i))));
		}
		return converted;
	}
	
	/*public static Integer[] completeArray(Integer[] array) {
		Integer
	}*/
}
