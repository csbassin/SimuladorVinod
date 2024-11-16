package modelo.registradores;

public class RegistradorUm extends Registrador{
	public RegistradorUm() {
		super(16);
		for(int i = 0; i<15; i++) {
			this.registrador[i] = 0;
		}
		this.registrador[15] = 1;
	}
}
