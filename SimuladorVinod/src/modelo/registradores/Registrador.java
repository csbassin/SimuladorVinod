package modelo.registradores;

public class Registrador {
	protected int[] registrador;
	
	public Registrador(int size) {
		this.registrador = new int[size];
	}
	
	public void set(Integer[] memLine) {
		for(int i = 0; i<registrador.length; i++) {
			this.registrador[i] = memLine[i];
		}
	}
	
	public int[] getRegistrador() {
		return registrador;
	}
}
