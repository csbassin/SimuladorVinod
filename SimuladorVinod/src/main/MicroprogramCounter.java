package main;

import modelo.registradores.Registrador;

public class MicroprogramCounter extends Registrador{
	private static MicroprogramCounter mpc = null;
	// quem diabos criou isso com 32 bits?
	private MicroprogramCounter() {
		super(32);
	}
	public static MicroprogramCounter getMpc() {
		if(mpc == null) {
			MicroprogramCounter.mpc = new MicroprogramCounter();
		}
		return mpc;
	}
	public void reset() {
		this.set(new Integer[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
	}
	public int[] getValue() {
		return this.registrador;
	}
}
