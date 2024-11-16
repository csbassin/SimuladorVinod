package main;

import modelo.registradores.Registrador;

public class MicroprogramCounter extends Registrador{
	public MicroprogramCounter() {
		super(32);
	}
	
	public int[] getValue() {
		return this.registrador;
	}
}
