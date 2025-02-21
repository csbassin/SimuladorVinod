package util;

import java.util.HashMap;

import visao.MainWindow;

public class Montador extends Thread{
	String code;
	MainWindow janela;
	static final HashMap<String, String> instructions = new HashMap<>(23);
	static final HashMap<String, Integer> operandSize = new HashMap<>(23);
	static {
		instructions.put("LODD", "0000");
		instructions.put("STOD", "0001");
		instructions.put("ADDD", "0010");
		instructions.put("SUBD", "0011");
		instructions.put("JPOS", "0100");
		instructions.put("JZER", "0101");
		instructions.put("JUMP", "0110");
		instructions.put("LOCO", "0111");
		instructions.put("LODL", "1000");
		instructions.put("STOL", "1001");
		instructions.put("ADDL", "1010");
		instructions.put("SUBL", "1011");
		instructions.put("JNEG", "1100");
		instructions.put("JNZE", "1101");
		instructions.put("CALL", "111000");
		instructions.put("PSHI", "111001");
		instructions.put("POPI", "111010");
		instructions.put("PUSH", "1111010000000000");
		instructions.put("POP", "1111011000000000");
		instructions.put("RETN", "1111100000000000");
		instructions.put("SWAP", "1111101000000000");
		instructions.put("INSP", "11111100");
		instructions.put("DESP", "11111110");
		
		operandSize.put("LODD", 12);
		operandSize.put("STOD", 12);
		operandSize.put("ADDD", 12);
		operandSize.put("SUBD", 12);
		operandSize.put("JPOS", 12);
		operandSize.put("JZER", 12);
		operandSize.put("JUMP", 12);
		operandSize.put("LOCO", 12);
		operandSize.put("LODL", 12);
		operandSize.put("STOL", 12);
		operandSize.put("ADDL", 12);
		operandSize.put("SUBL", 12);
		operandSize.put("JNEG", 12);
		operandSize.put("JNZE", 12);
		operandSize.put("CALL", 10);
		operandSize.put("PSHI", 10);
		operandSize.put("POPI", 10);
		operandSize.put("PUSH", 0);
		operandSize.put("POP", 0);
		operandSize.put("RETN", 0);
		operandSize.put("SWAP", 0);
		operandSize.put("INSP", 8);
		operandSize.put("DESP", 8);
	}
	public Montador(String code, MainWindow main) {
		this.code = code;
		this.janela = main;
	}
	@Override 
	public void run() {
		String[] linhas = code.split("\n");
		String montado = "";
		for(String linha:linhas) {
			int firstIndexOfEspaco = linha.length(); //= linha.indexOf(' ');
			for(int i = 0; i<linha.length(); i++) {
				if(linha.charAt(i) == ' ') {
					firstIndexOfEspaco = i;
					break;
				}
			}
			String instructionAsText = linha.substring(0, firstIndexOfEspaco);
			System.out.println(instructionAsText);
			montado += instructions.get(instructionAsText);
			int sizeOperando = operandSize.get(instructionAsText);
			if(sizeOperando > 0) {
				montado += conversaoCompleta(Integer.parseInt(linha.substring(firstIndexOfEspaco+1, linha.length())), sizeOperando);
			}
			montado += "\n";
		}
		janela.setTxtCode(montado);
	}
	
	public String conversaoCompleta(int operando, int size) {
		String binario = Integer.toBinaryString(Math.abs(operando));
		while(binario.length()<size) {
			binario = "0"+binario;
		}
		// acerta o sinal, fazendo a conversão para c2
		if(operando<0) {
			String inverso = "";
			// inverte
			for(int i = binario.length()-1; i>=0; i--) {
				if(binario.charAt(i) == '0') {
					inverso += '1';
				}else {
					inverso += '0';
				}
			}
			// soma 1
			char carry = '1';
			int i = inverso.length()-1;
			binario = ""; // limpa binario para aproveitar a String
			while(i >= 0) {
				if(carry == '1') {
					if(inverso.charAt(i) == '0') {
						binario += '1';
						carry = '0';
					}else {
						binario += '0';
					}
				}else {
					binario += inverso.charAt(i);
				}
				
				i--;
			}
		}
		return binario;
	}
}
