package modelo.registradores;

public class Amask extends Registrador{
	public Amask() {
		super(16);
		for(int i = 0; i<4; i++) {
			this.registrador[i] = 0;
		}
		for(int i = 4; i<16; i++) {
			this.registrador[i] = 1;
		}
	}
}
