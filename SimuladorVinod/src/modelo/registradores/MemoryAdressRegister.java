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

	private static int passAddress(int[] binaryAddress) {
		return Integer.parseInt(Arrays.toString(binaryAddress).replaceAll("[\\[,\\s\\]]", ""),2);
	} //por enquanto usa essa funcao

	public void habilitarSaida() {
		// aqui aplica-se a máscara e só são copiados os últimos 12 bits
		for (int i = 0; i<4; i++) {registrador[i] = 0;}
		memory.setEnderecoSlecionado(passAddress(registrador));
		memory.setSaida(true);
	}

}