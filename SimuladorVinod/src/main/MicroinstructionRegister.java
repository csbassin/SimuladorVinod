package main;

import java.util.ArrayList;

import modelo.registradores.Registrador;

public class MicroinstructionRegister extends Registrador{
	public MicroinstructionRegister() {
		super(32);
		for(int i = 0; i<32; i++) {
			this.registrador[i] = 0;
		}
	}
	
	public ArrayList<Integer> getPieceAs32bit(int start, int end){
		ArrayList<Integer> list = new ArrayList<>(32);
		for(int i = start; i<=end; i++) {
			list.add(this.registrador[i]);
		}
		while(list.size()<32) {
			list.addFirst(0);
		}
		return list;
	}
	public ArrayList<Integer> getSlice(int start, int end){
		ArrayList<Integer> list = new ArrayList<>(end-start+1);
		for(int i = start; i<=end; i++) {
			list.add(this.registrador[i]);
		}
		return list;
	}
}
