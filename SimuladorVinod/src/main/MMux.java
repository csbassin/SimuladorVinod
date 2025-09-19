package main;

import modelo.registradores.Multiplexador;

public class MMux extends Multiplexador{
	private static MMux mmux = null;
	private MMux() {
		super(2,1);
		entradas.add(null);
		entradas.add(null);
	}
	public static MMux getMmux() {
		if(mmux == null) {
			mmux = new MMux();
		}
		return mmux;
	}
	public void setControle(int[] controle) {
		this.controle = controle;
	}
	public Integer[] getSaida() {
		if(controle[0] == 1) {
			return entradas.get(1);
		}
		return entradas.get(0);
	}
	
	public void setEntradas(Integer[] mpc, Integer[] addr) {
		this.entradas.set(0, mpc);
		this.entradas.set(1, addr);
    }
}
