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
		AMux amux = new AMux();
		ULA ula = new ULA();
		Desclocador desclocador = new Desclocador();
		MemoryBufferRegisterRead mbrRead = new MemoryBufferRegisterRead(memoriaPrincipal);
		MemoryBufferRegisterWrite mbrWrite = new MemoryBufferRegisterWrite(memoriaPrincipal);
		MemoryAdressRegister mar = new MemoryAdressRegister(memoriaPrincipal);
		GetRegistrador gr = new GetRegistrador();
		Integer[] zeroValue = new Integer[32];
		for (int i = 0; i < 32; i++) {
			zeroValue[i] = 0;
		}
		mpc.set(zeroValue);
		int i = 0;
		int contRead = 0, contWrite = 0;

		while(!stop) {
			try {
				//adicionar code da simulação aqui
			/*
			System.out.println(i);
			WindowData.txtData = "loop "+i;*/

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
				// fim subciclo 1

				// Subciclo 2 ------------------------------
				WindowData.currentSub = "2";

				//Pegando valores dos registradores
				int[] latchA = gr.get(Conversoes.bitArrayToDecimal(mir.getPieceAs32bit(20, 23))).getRegistrador();
				int[] latchB = gr.get(Conversoes.bitArrayToDecimal(mir.getPieceAs32bit(16, 19))).getRegistrador();

				amux.setControle(mir.getSlice(0,0).stream().mapToInt(Integer::intValue).toArray());
				amux.setEntradas(Arrays.stream(latchA).boxed().toArray(Integer[]::new), Arrays.stream(mbrRead.getRegistrador()).boxed().toArray(Integer[]::new));

				//			if (mir.getPieceAs32bit(0, 0).get(31) == 1) {
//				mbrRead.read();
//				latchA = mbrRead.getRegistrador();
//			}
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

//			// Subciclo 4 ------------------------------
				WindowData.currentSub = "4";


				if (mir.getSlice(8,8).getFirst() == 1) { //mudar valor do MAR
					mar.set(Arrays.stream(latchB).boxed().toArray(Integer[]::new));
					mar.definirEndereco();
				}

				if (mir.getSlice(11,11).getFirst() == 1) { //ENC ligado muda algum registrador
					gr.get(Conversoes.bitArrayToDecimal(mir.getPieceAs32bit(12, 15)))
							.set(Arrays.stream(desclocador.getSaida())
									.boxed()
									.toArray(Integer[]::new));
					switch(Conversoes.bitArrayToDecimal(mir.getPieceAs32bit(12, 15))){
						case 0:
							WindowData.pc = Integer.toString(Conversoes.binaryIntToDecimal(gr.get(0).getRegistrador()));
							break;
						case 1:
							WindowData.ac = Integer.toString(Conversoes.binaryIntToDecimal(gr.get(1).getRegistrador()));
							break;
						case 2:
							WindowData.sp = Integer.toString(Conversoes.binaryIntToDecimal(gr.get(2).getRegistrador()));
							break;
						case 3:
							WindowData.ir = Integer.toString(Conversoes.binaryIntToDecimal(gr.get(3).getRegistrador()));
							break;
						case 4:
							WindowData.tir = Integer.toString(Conversoes.binaryIntToDecimal(gr.get(4).getRegistrador()));
							break;
//					case 5:
//						WindowData.0 = Integer.toString(Conversoes.binaryIntToDecimal(gr.get(5).getRegistrador()));
//						break;
//					case 6:
//						WindowData.+1 = Integer.toString(Conversoes.binaryIntToDecimal(gr.get(6).getRegistrador()));
//						break;
//					case 7:
//						WindowData.-1 = Integer.toString(Conversoes.binaryIntToDecimal(gr.get(7).getRegistrador()));
//						break;
//					case 8:
//						WindowData.Amask = Integer.toString(Conversoes.binaryIntToDecimal(gr.get(8).getRegistrador()));
//						break;
//					case 9:
//						WindowData.Smask = Integer.toString(Conversoes.binaryIntToDecimal(gr.get(9).getRegistrador()));
//						break;
						case 10:
							WindowData.a = Integer.toString(Conversoes.binaryIntToDecimal(gr.get(10).getRegistrador()));
							break;
						case 11:
							WindowData.b = Integer.toString(Conversoes.binaryIntToDecimal(gr.get(11).getRegistrador()));
							break;
						case 12:
							WindowData.c = Integer.toString(Conversoes.binaryIntToDecimal(gr.get(12).getRegistrador()));
							break;
						case 13:
							WindowData.d = Integer.toString(Conversoes.binaryIntToDecimal(gr.get(13).getRegistrador()));
							break;
						case 14:
							WindowData.e = Integer.toString(Conversoes.binaryIntToDecimal(gr.get(14).getRegistrador()));
							break;
						case 15:
							WindowData.f = Integer.toString(Conversoes.binaryIntToDecimal(gr.get(15).getRegistrador()));
							break;
					}
				}


				if (mir.getSlice(7,7).getFirst() == 1){ //mbr ligado entao seta o que sera escrito
					mbrWrite.set(Arrays.stream(desclocador.getSaida())
							.boxed()
							.toArray(Integer[]::new));
				}

				if (mir.getSlice(9,9).getFirst() == 1){ //rd apareceu
					contRead++;
					if (contRead == 2){
						contRead = 0;
						mbrRead.read();
					}
					//MBR é o indice 7
					//rd é o 9
					//wr é o 10
				}

				if (mir.getSlice(10,10).getFirst() == 1){ // wr apareceu
					contWrite++;
					if (contWrite == 2){
						contWrite = 0;
						mbrWrite.write();
					}
				}



				//				//Determinando os valores de entrada do ULA
				//           	 	int valorA = mir.isAMuxEnabled() ? mbr.getValue() : latchA.getValue();
				//            	int valorB = latchB.getValue();
				//
				//				//Executando a operação da ULA
				//            	ula.setA();
				//            	int resultadoULA = ula.operar(valorA, valorB, mir.getULAOperation());
				//
				//				// O resultado da ULA passa pelo deslocador
				//            	int resultadoFinal = deslocador.deslocar(resultadoULA, mir.getShiftType());
				//
				//				//Se a microinstrução pedir, gravamos o valor do latch B no registrador de endereço (MAR)
				//				if (mir.isMARWriteEnabled()) {
				//                	mar.setValue(latchB.getValue());
				//            	}
				//
				//				// fim subciclo 3 ------------------------------
				//
				//				// Subciclo 4 ------------------------------
				//
				//				//Se a microinstrução habilitar o MBR, gravamos o resultado la
				//           	 	if (mir.isMBREnabled()) {
				//                	mbr.setValue(resultadoFinal);
				//            	}
				//
				//				//Gravar o resultado no registrador especificado(Se a microinstrução permitir, claro)
				//            	if (mir.isEnCEnabled()) {
				//                	registradores.set(mir.getBarC(), resultadoFinal);
				//            	}
				//
				//				// fim subciclo 4 ------------------------------

				
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
