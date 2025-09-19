package main;

import modelo.registradores.Registrador;

public class MicroprogramCounter extends Registrador{
	private static MicroprogramCounter mpc = null;
	private MicroprogramCounter() {
		super(32);
	}
	public static MicroprogramCounter getMpc() {
		if(mpc == null) {
			MicroprogramCounter.mpc = new MicroprogramCounter();
		}
		return mpc;
	}
	public int[] getValue() {
		return this.registrador;
	}
}
