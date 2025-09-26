package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import util.Conversoes;

public class IncrementMicroprogramCounter {
	private MicroprogramCounter mpc = MicroprogramCounter.getMpc();
	
	private static IncrementMicroprogramCounter impc = null;
	private IncrementMicroprogramCounter() {
		
	}
	public static IncrementMicroprogramCounter getImpc() {
		if(impc == null) {
			impc = new IncrementMicroprogramCounter();
		}
		return impc;
	}
	public void getIncremento() {
		ArrayList<Integer> valor = new ArrayList<>(Arrays.stream(mpc.getRegistrador()).boxed().collect(Collectors.toList()));
		int value = Conversoes.bitArrayToDecimal(valor);
		value++;
		valor = Conversoes.decimalToArrayList(value);
		Integer[] novo = new Integer[32];
		for(int i = 0; i<32; i++) {
			novo[i] = valor.get(i);
		}
		mpc.set(novo);
	}
	
}
