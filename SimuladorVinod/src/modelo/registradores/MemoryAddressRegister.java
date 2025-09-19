package modelo.registradores;

import java.util.Arrays;

import modelo.MemoriaPrincipal;
import util.Conversoes;

public class MemoryAddressRegister extends Registrador{
	private MemoriaPrincipal memory;
	private static MemoryAddressRegister mar = null;
	private MemoryAddressRegister() {
		super(16);
		this.memory = MemoriaPrincipal.getMemoriaPrincipal();
	}

	public static MemoryAddressRegister getMar() {
		if(mar == null) {
			mar = new MemoryAddressRegister();
		}
		return mar;
	}
	
	public void definirEndereco() {
		// aqui aplica-se a máscara e só são copiados os últimos 12 bits
		for (int i = 0; i<4; i++) {registrador[i] = 0;}
		memory.setEnderecoSlecionado(Conversoes.binaryIntToDecimal(registrador));
	}

}