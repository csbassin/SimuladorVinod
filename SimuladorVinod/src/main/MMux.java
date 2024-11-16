package main;

import modelo.registradores.Multiplexador;

public class MMux extends Multiplexador{
	public MMux() {
		super(2,1);
	}
	public void setControle(int[]controle) {
		this.controle = controle;
	}
	public Integer[] getSaida() {
		if(controle[0] == 1) {
			return entradas.get(1);
		}
		return entradas.get(0);
	}
}
