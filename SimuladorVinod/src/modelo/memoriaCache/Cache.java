package modelo.memoriaCache;

import java.util.HashMap;

import main.MicroinstructionRegister;

public class Cache extends HashMap{
	// preciso adicionar novos valores readFinished e writeFinished ao mir, que serão controlados pelo cache
	private MicroinstructionRegister currentMir = null;
	public Cache(MicroinstructionRegister mir) {
		super();
		this.currentMir = mir;
	}
}
