package util;

import java.util.HashMap;
import java.util.ArrayList;
import visao.MainWindow;

public class Montador extends Thread{
	String code;
	MainWindow janela;
	static final HashMap<String, String> instructions = new HashMap<>(23);
	static final HashMap<String, Integer> operandSize = new HashMap<>(23);
	HashMap<String, Integer> vars = new HashMap<>(); // vai guardar os nomes de variàves e respectivos endereços
	HashMap<String, Integer> flags = new HashMap<>(); // vai guardar flags e respectivos endereços
	ArrayList<Integer> firstIndexesOfEspaco = new ArrayList<>();
	int currentVariableAddress = 0;
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
		ArrayList<String> tratado = new ArrayList<>(linhas.length);
		// primeira primeira passada: Remove espaços no início e no fim e remove comentários. Tá feio mas tá mais fácil
		for(String linha:linhas) {
			if(!(linha.startsWith("/"))) {
				if(linha.startsWith(" ")) {
					linha = linha.trim();
				}
				tratado.add(linha);
			}
		}
		currentVariableAddress = tratado.size();
		// primeira passada, procura por flags
		int i = 0;
		for(String linha: tratado) {
			int firstIndexOfColon = 0;
			
			for(int a = 0; a<linha.length(); a++) {
				if(linha.charAt(a) == ':') {
					firstIndexOfColon = a;
					break;
				}
			}
			if(firstIndexOfColon != 0) {
				flags.put(linha.substring(0, firstIndexOfColon), i);
				linha = linha.substring(firstIndexOfColon+1); //corta a flag
			}
			tratado.set(i, linha);
			i++;
		}
		// segunda passada, troca flags e variáveis por números
		i = 0;
		for(String linha:tratado) {
			int firstIndexOfEspaco = linha.length(); //= linha.indexOf(' ');
			for(int a = 0; a<linha.length(); a++) {
				if(linha.charAt(a) == ' ') {
					firstIndexOfEspaco = a;
					break;
				}
			}
			firstIndexesOfEspaco.add(firstIndexOfEspaco);
			String instruction = linha.substring(0, firstIndexOfEspaco);
			if(firstIndexOfEspaco != linha.length()) { // implica que tem argumento
				String value = linha.substring(firstIndexOfEspaco+1);
				// Se for um desvio, o tratamento é diferente
				if(instruction.equals("JUMP") || instruction.equals("JPOS") || instruction.equals("JZER") || instruction.equals("JNEG") || instruction.equals("JNZE") ||instruction.equals("CALL")) {
					linha = linha.substring(0, firstIndexOfEspaco) + " " + flags.get(value);
				}else {
					 // pega o argumento
					// trata o argumento
					try {
						int v2 = Integer.parseInt(value);
						
						// se for um número, ele não faz nada mesmo
					}catch(Exception e) {
						Integer addr = vars.get(value);
						if(addr == null) {
							vars.put(value, currentVariableAddress); //guarda a correspondência entre variável e endereço no HashMap
							linha = linha.substring(0, firstIndexOfEspaco) + " "+currentVariableAddress;  //troca o nome pelo endereço da variável
							currentVariableAddress+=1; // a próxima variável será gravada no endereço seguinte
						}else {
							linha = linha.substring(0, firstIndexOfEspaco) + " "+addr.toString(); // troca pelo endereço já registrado
						}
						
					}
				}
			}
			tratado.set(i, linha);
			i++;
		}
		String montado = "";
		// terceira passada, monta a linha
		i = 0;
		for(String linha:tratado) {
			if(!linha.isEmpty()) {
				int firstIndexOfEspaco = firstIndexesOfEspaco.get(i); // desde que eu peguei o índice, só alterei pra frente, então vale
				String instructionAsText = linha.substring(0, firstIndexOfEspaco);
				System.out.println(instructionAsText);
				montado += instructions.get(instructionAsText);
				int sizeOperando = operandSize.get(instructionAsText);
				if(sizeOperando > 0) {
					montado += conversaoCompleta(Integer.parseInt(linha.substring(firstIndexOfEspaco+1, linha.length())), sizeOperando);
				}
				montado += "\n";
			}
			i++;
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