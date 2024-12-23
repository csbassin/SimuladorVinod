package main;

import java.util.ArrayList;

import modelo.AMux;
import visao.WindowData;

public class UnidadeControle extends Thread{
	
	private int sleepInMillis = 1000;
	private boolean stop = false;
	@Override
	public void run() {
		MemoriaDeControle mc = new MemoriaDeControle();
		MicroprogramCounter mpc = new MicroprogramCounter();
		MicroinstructionRegister mir = new MicroinstructionRegister();
		IncrementMicroprogramCounter impc = new IncrementMicroprogramCounter(mpc);
		AMux amux = new AMux();
		Integer[] zeroValue = new Integer[32];
		for(int i = 0; i<32; i++) {
			zeroValue[i] = 0;
		}
		mpc.set(zeroValue); 
		int i = 0;
		while(!stop) {
			//adicionar code da simulação aqui
			/*
			System.out.println(i);
			WindowData.txtData = "loop "+i;*/
			
			if(i!=0) {
				impc.getIncremento();
			}
			
			try {
				sleep(sleepInMillis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i++;	
		}
		
	}
	
	public int getSleepInMillis() {
		return sleepInMillis;
	}
	public void setSleepInMillis(int sleepInMillis) {
		this.sleepInMillis = sleepInMillis;
	}
	public boolean isStop() {
		return stop;
	}
	public void setStop(boolean stop) {
		this.stop = stop;
	}
	
	

}
