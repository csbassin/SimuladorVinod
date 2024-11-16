package modelo.registradores;

public class MemoryAdressRegister extends Registrador{
	public MemoryAdressRegister() {
		super(16);
	}
	
	public void habilitarSaida() {
		// aqui aplica-se a máscara e só são copiados os últimos 12 bits
	}
}
