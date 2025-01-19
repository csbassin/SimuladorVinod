package modelo.registradores;

public class StackPointer extends Registrador{
	public StackPointer() {
		super(16);
		this.registrador = new int[]{0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
	}
}
