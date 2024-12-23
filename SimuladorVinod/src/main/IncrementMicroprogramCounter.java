package main;

import java.util.ArrayList;
import java.util.Arrays;

import util.Conversoes;

public class IncrementMicroprogramCounter {
	public MicroprogramCounter mpc;
	
	public IncrementMicroprogramCounter(MicroprogramCounter mpc) {
		this.mpc = mpc;
	}
	
	public void getIncremento() {
		ArrayList<Integer> valor = (ArrayList) Arrays.asList(mpc.getRegistrador());
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
