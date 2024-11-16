package modelo.registradores;

public class RegistradorZero extends Registrador{
	public RegistradorZero() {
		super(16);
		for(int i = 0; i<16; i++) {
			this.registrador[i] = 0;
		}
	}
}
