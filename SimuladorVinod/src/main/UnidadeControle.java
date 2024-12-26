package main;

import java.util.ArrayList;

import modelo.AMux;
import visao.WindowData;

public class UnidadeControle extends Thread{
	
	private int sleepInMillis = 1000;
	private boolean stop = false;
	@Override
	public void run() {
		MicroprogramCounter mpc = new MicroprogramCounter();
		MemoriaDeControle mc = new MemoriaDeControle(mpc);
		MicroinstructionRegister mir = new MicroinstructionRegister();
		IncrementMicroprogramCounter impc = new IncrementMicroprogramCounter(mpc);
		ControladorDeFluxo cdf = new ControladorDeFluxo();
		MMux mmux = new MMux();
		Integer[] zeroValue = new Integer[32];
		for(int i = 0; i<32; i++) {
			zeroValue[i] = 0;
		}
		mpc.set(zeroValue); 
		int i = 0;
		while(!stop) {
			try {
				//adicionar code da simulação aqui
				/*
				System.out.println(i);
				WindowData.txtData = "loop "+i;*/
				
				//Subciclo 1
				if(i!=0) { //incrementa o MPC. Ficou feio, mas a coversão não suportya valores negativos
					impc.getIncremento();
				}
				mmux.setControle(cdf.getDesvia());
				mmux.setEntradas(mc.getValueAtMPCAddress(), (Integer[]) mir.getPieceAs32bit(23, 31).toArray());
				mir.set(mmux.getSaida());
				// fim subciclo 1
				
				
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
