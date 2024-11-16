package modelo.registradores;

import java.util.ArrayList;

public class Memoria {
	public ArrayList<Integer[]> mem;
	public Memoria(int enderecos, int largura) {
		mem = new ArrayList<Integer[]>(enderecos);
	}
	public Integer[] get(int pos) {
		return mem.get(pos);
	}
}
