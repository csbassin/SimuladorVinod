package modelo.registradores;

import java.util.ArrayList;

public class Multiplexador {
	public ArrayList<Integer[]>entradas;
	public int[]controle;
	public Multiplexador(int entradasSize, int controleSize) {
		entradas = new ArrayList<Integer[]>(entradasSize);
		controle = new int[controleSize];
	}
	
	
}
