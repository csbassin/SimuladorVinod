package main;

import modelo.registradores.Memoria;

public class MemoriaDeControle extends Memoria{
	public MemoriaDeControle() {
		super(78, 32);
		this.mem.add(new Integer[]{0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});//0
		this.mem.add(new Integer[]{0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0});//1
		this.mem.add(new Integer[]{0,0,1,1,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0});//2
		this.mem.add(new Integer[]{0,0,1,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,1,1,0,0,1,1,0,0,0,1,0,0,1,1});//3
		this.mem.add(new Integer[]{0,0,1,1,0,1,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,0,0,0,1,0,1,1});//4
		//this.mem.add(new Integer[]{0,0,1,0,0,1,0,1,0,0,0,0,0,1,0,0,0,0,1,1,0,0,1,1,0,0,0,0,1,0,0,1});//5
	}
	
}
