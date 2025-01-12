package main;

import java.util.ArrayList;

import modelo.AMux;
import modelo.MemoriaPrincipal;
import modelo.ULA;
import modelo.registradores.MemoryBufferRegisterRead;
import modelo.registradores.MemoryBufferRegisterWrite;
import util.Conversoes;
import util.GetRegistrador;
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
		MemoriaPrincipal memoriaPrincipal = new MemoriaPrincipal();
		MMux mmux = new MMux();
		ULA ula = new ULA();
		MemoryBufferRegisterRead mbrRead = new MemoryBufferRegisterRead(memoriaPrincipal);
		MemoryBufferRegisterWrite mbrWrite = new MemoryBufferRegisterWrite(memoriaPrincipal);
		GetRegistrador gr = new GetRegistrador();
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
				
				// Subciclo 2 ------------------------------
				
				//Pegando valores dos registradores
            	int[] latchA;
            	int[] latchB = gr.get(Conversoes.bitArrayToDecimal(mir.getPieceAs32bit(16, 19))).getRegistrador();
            	if(mir.getPieceAs32bit(0, 0).get(31) == 1) {
            		mbrRead.read();
            		latchA = mbrRead.getRegistrador();
            	}
				// fim subciclo 2 ------------------------------

				// Subciclo 3 ------------------------------

				//Determinando os valores de entrada do ULA
           	 	int valorA = mir.isAMuxEnabled() ? mbr.getValue() : latchA.getValue();
            	int valorB = latchB.getValue();

				//Executando a operação da ULA
            	ula.setA();
            	int resultadoULA = ula.operar(valorA, valorB, mir.getULAOperation());

				// O resultado da ULA passa pelo deslocador
            	int resultadoFinal = deslocador.deslocar(resultadoULA, mir.getShiftType());
            	
				//Se a microinstrução pedir, gravamos o valor do latch B no registrador de endereço (MAR)
				if (mir.isMARWriteEnabled()) {
                	mar.setValue(latchB.getValue());
            	}
				
				// fim subciclo 3 ------------------------------

				// Subciclo 4 ------------------------------

				//Se a microinstrução habilitar o MBR, gravamos o resultado la
           	 	if (mir.isMBREnabled()) {
                	mbr.setValue(resultadoFinal);
            	}

				//Gravar o resultado no registrador especificado(Se a microinstrução permitir, claro)
            	if (mir.isEnCEnabled()) {
                	registradores.set(mir.getBarC(), resultadoFinal);
            	}

				// fim subciclo 4 ------------------------------

				
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
