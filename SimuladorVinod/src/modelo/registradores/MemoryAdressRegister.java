package modelo.registradores;

import java.util.Arrays;

import modelo.MemoriaPrincipal;
import util.Conversoes;

public class MemoryAdressRegister extends Registrador{
	private MemoriaPrincipal memory;
	public MemoryAdressRegister(MemoriaPrincipal memory) {
		super(16);
		this.memory = memory;
	}


	public void definirEndereco() {
		// aqui aplica-se a máscara e só são copiados os últimos 12 bits
		for (int i = 0; i<4; i++) {registrador[i] = 0;}
		memory.setEnderecoSlecionado(Conversoes.binaryIntToDecimal(registrador));
	}

}