package main;

import java.util.ArrayList;
import java.util.Arrays;

import modelo.AMux;
import modelo.Desclocador;
import modelo.MemoriaPrincipal;
import modelo.ULA;
import modelo.registradores.MemoryAdressRegister;
import modelo.registradores.MemoryBufferRegisterRead;
import modelo.registradores.MemoryBufferRegisterWrite;
import modelo.registradores.Registrador;
import util.Conversoes;
import util.GetRegistrador;
import util.Other;
import visao.WindowData;

public class UnidadeControle extends Thread{
	
	private GetRegistrador gr;
	private int sleepInMillis = 1000;
	private boolean stop = false;
	private boolean pause = false;
	public MemoriaPrincipal memoriaPrincipal = new MemoriaPrincipal();
	private int[] pcValueForPause = null;
	private boolean pausaAutomaticaJaPassou = false;
	@Override
	public void run() {
		MicroprogramCounter mpc = new MicroprogramCounter();
		MemoriaDeControle mc = new MemoriaDeControle(mpc);
		MicroinstructionRegister mir = new MicroinstructionRegister();
		IncrementMicroprogramCounter impc = new IncrementMicroprogramCounter(mpc);
		ControladorDeFluxo cdf = new ControladorDeFluxo();
		MMux mmux = new MMux();
		AMux amux = new AMux();
		ULA ula = new ULA();
		Desclocador desclocador = new Desclocador();
		MemoryBufferRegisterRead mbrRead = new MemoryBufferRegisterRead(memoriaPrincipal);
		MemoryBufferRegisterWrite mbrWrite = new MemoryBufferRegisterWrite(memoriaPrincipal);
		MemoryAdressRegister mar = new MemoryAdressRegister(memoriaPrincipal);
		gr = new GetRegistrador();
		Integer[] zeroValue = new Integer[32];
		for (int i = 0; i < 32; i++) {
			zeroValue[i] = 0;
		}
		mpc.set(zeroValue);
		int i = 0;
		int contRead = 0, contWrite = 0;
		long executionTime = 0;
		while(!stop) {
			long cycleStartTime = System.currentTimeMillis();
			try {
				updateRegisterExibitionValue();

				//Subciclo 1
				WindowData.currentSub = "1";
				if (i != 0) { //incrementa o MPC. Ficou feio, mas a coversão não suportya valores negativos
					impc.getIncremento();
				}
				mmux.setControle(cdf.getDesvia());
				mmux.setEntradas(Arrays.stream(mpc.getValue()).boxed().toArray(Integer[]::new), mir.getPieceAs32bit(24, 31).toArray(new Integer[0]));
				mpc.set(mmux.getSaida());

				mir.set(mc.getValueAtMPCAddress());
				WindowData.microAtual = Other.microInstructionsAsStrings[Conversoes.binaryIntToDecimal(mpc.getValue())];
				sleep(sleepInMillis); // Feito para desacelerar a execução e permitir a visualização após cada subciclo 
				
				// fim subciclo 1

				// Subciclo 2 ------------------------------
				WindowData.currentSub = "2";

				//Pegando valores dos registradores
				int[] latchA = gr.get(Conversoes.bitArrayToDecimal(mir.getPieceAs32bit(20, 23))).getRegistrador();
				int[] latchB = gr.get(Conversoes.bitArrayToDecimal(mir.getPieceAs32bit(16, 19))).getRegistrador();

				amux.setControle(mir.getSlice(0,0).stream().mapToInt(Integer::intValue).toArray());
				amux.setEntradas(Arrays.stream(latchA).boxed().toArray(Integer[]::new), Arrays.stream(mbrRead.getRegistrador()).boxed().toArray(Integer[]::new));
				sleep(sleepInMillis);
				

				// fim subciclo 2 ------------------------------

				// Subciclo 3 ------------------------------
				WindowData.currentSub = "3";

				ula.setF(mir.getSlice(3,4).stream().mapToInt(Integer::intValue).toArray());

				ula.setA(Arrays.stream(amux.getSaida()).mapToInt(Integer::intValue).toArray());
				ula.setB(latchB);

				ula.perform();
				cdf.setCond(mir.getSlice(1,2).stream().mapToInt(Integer::intValue).toArray());
				cdf.setN(ula.getD()[0]);
				cdf.setZ(ula.getD()[1]);

				desclocador.setEntrada(ula.getR());
				desclocador.setDesl_mir(mir.getSlice(5,6).stream().mapToInt(Integer::intValue).toArray());

				desclocador.perform();
				sleep(sleepInMillis);
				
				// Subciclo 4 ------------------------------
				WindowData.currentSub = "4";


				if (mir.getSlice(8,8).get(0) == 1) { //mudar valor do MAR
					mar.set(Arrays.stream(latchB).boxed().toArray(Integer[]::new));
					mar.definirEndereco();
				}

				if (mir.getSlice(11,11).get(0) == 1) { //ENC ligado muda algum registrador
					gr.get(Conversoes.bitArrayToDecimal(mir.getPieceAs32bit(12, 15))).set(Arrays.stream(desclocador.getSaida()).boxed().toArray(Integer[]::new));
				}


				if (mir.getSlice(7,7).get(0) == 1){ //mbr ligado entao seta o que sera escrito
					mbrWrite.set(Arrays.stream(desclocador.getSaida()).boxed().toArray(Integer[]::new));
				}

				if (mir.getSlice(9,9).get(0) == 1){ //rd apareceu
					contRead++;
					if (contRead == 2){
						contRead = 0;
						mbrRead.read();
					}
					
				}

				if (mir.getSlice(10,10).get(0) == 1){ // wr apareceu
					contWrite++;
					if (contWrite == 2){
						contWrite = 0;
						mbrWrite.write();
					}
				}

				//				// fim subciclo 4 ------------------------------
				
				if(pcValueForPause!=null && pausaAutomaticaJaPassou == false) {
					boolean igual = true;
					int a = 0;
					while(igual == true && a<pcValueForPause.length) {
						if(pcValueForPause[a] != gr.get(0).getRegistrador()[a]) {
							igual = false;
						}
						a++;
					}
					if(igual) {
						pause = true;
						pausaAutomaticaJaPassou = true;
					}
				}
				
				long pausedTime = System.currentTimeMillis();
				while(pause) { // gambiarra feia pra adicionar pausa aqui
					sleep(1000);
					
				}
				long endOfPauseTime = System.currentTimeMillis()-pausedTime;
				sleep(sleepInMillis);
				executionTime += System.currentTimeMillis()-cycleStartTime-endOfPauseTime; // tempo do ciclo = tempo atual - tempo do inicio - tempo pausado
				WindowData.executionTime = String.valueOf(executionTime); 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			i++;	
		}
		
	}
	
	private void updateRegisterExibitionValue(){
		WindowData.pc = Conversoes.bitArrayToC2(gr.get(0).getRegistrador());
		WindowData.ac = Conversoes.bitArrayToC2(gr.get(1).getRegistrador());
		WindowData.sp = Conversoes.bitArrayToC2(gr.get(2).getRegistrador());
		WindowData.ir = Conversoes.intArrayToString(gr.get(3).getRegistrador());
		WindowData.tir = Conversoes.intArrayToString(gr.get(4).getRegistrador());
		WindowData.a = Conversoes.bitArrayToC2(gr.get(10).getRegistrador());
		WindowData.b = Conversoes.bitArrayToC2(gr.get(11).getRegistrador());
		WindowData.c = Conversoes.bitArrayToC2(gr.get(12).getRegistrador());
		WindowData.d = Conversoes.bitArrayToC2(gr.get(13).getRegistrador());
		WindowData.e = Conversoes.bitArrayToC2(gr.get(14).getRegistrador());
		WindowData.f = Conversoes.bitArrayToC2(gr.get(15).getRegistrador());
	}
	
	public int getSleepInMillis() {
		return sleepInMillis;
	}
	public void setSleepInMillis(int sleepInMillis) {
		this.sleepInMillis = sleepInMillis;
	}
	
	public void setPause(boolean pause) {
		this.pause = pause;
	}
	
	public boolean isPause() {
		return pause;
	}
	
	public GetRegistrador getGr() {
		return gr;
	}

	public void setGr(GetRegistrador gr) {
		this.gr = gr;
	}

	public MemoriaPrincipal getMemoriaPrincipal() {
		return memoriaPrincipal;
	}

	public void setMemoriaPrincipal(MemoriaPrincipal memoriaPrincipal) {
		this.memoriaPrincipal = memoriaPrincipal;
	}

	public boolean isStop() {
		return stop;
	}
	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public int[] getPcValueForPause() {
		return pcValueForPause;
	}

	//tá feio, mas vale, tô com preguiça
	public void setPcValueForPause(int value) {
		pausaAutomaticaJaPassou = false;
		this.pcValueForPause = Conversoes.integerArrayToIntArray(Conversoes.stringToIntegerArray(Conversoes.conversaoCompleta(value, 16)));
	}
	
}
