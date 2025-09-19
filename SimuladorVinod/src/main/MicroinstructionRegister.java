package main;

import java.util.ArrayList;

import modelo.registradores.Registrador;

public class MicroinstructionRegister extends Registrador{
	private static MicroinstructionRegister mir = null;
	
	private MicroinstructionRegister() {
		super(32);
		for(int i = 0; i<32; i++) {
			this.registrador[i] = 0;
		}
	}
	
	public static MicroinstructionRegister getMir() {
		if(mir == null) {
			mir = new MicroinstructionRegister();
		}
		return mir;
	}
	
	public ArrayList<Integer> getPieceAs32bit(int start, int end){
		ArrayList<Integer> list = new ArrayList<>(32);
		for(int i = start; i<=end; i++) {
			list.add(this.registrador[i]);
		}
		while(list.size()<32) {
			list.add(0, 0);
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
