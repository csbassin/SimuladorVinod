package modelo.registradores;

public class RegistradorMenosUm extends Registrador{
	
	public RegistradorMenosUm() {
		super(16);
		for(int i = 0; i<16; i++) {
			this.registrador[i] = 1;
		}
	}

	
}
