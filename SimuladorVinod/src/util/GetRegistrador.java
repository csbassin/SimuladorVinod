package util;

import java.util.ArrayList;

import modelo.registradores.Acumulador;
import modelo.registradores.Amask;
import modelo.registradores.InstructionRegister;
import modelo.registradores.ProgramCounter;
import modelo.registradores.Registrador;
import modelo.registradores.RegistradorMenosUm;
import modelo.registradores.RegistradorUm;
import modelo.registradores.RegistradorZero;
import modelo.registradores.Smask;
import modelo.registradores.StackPointer;
import modelo.registradores.TemporaryInstructionRegister;

public class GetRegistrador {
	private ArrayList<Registrador> registradoresVd = new ArrayList<>(16);
	private static GetRegistrador gr = null;
	private GetRegistrador() {
		registradoresVd.add(new ProgramCounter());
		registradoresVd.add(new Acumulador());
		registradoresVd.add(new StackPointer());
		registradoresVd.add(new InstructionRegister());
		registradoresVd.add(new TemporaryInstructionRegister());
		registradoresVd.add(new RegistradorZero());
		registradoresVd.add(new RegistradorUm());
		registradoresVd.add(new RegistradorMenosUm());
		registradoresVd.add(new Amask());
		registradoresVd.add(new Smask());
		for (int i=0;  i<6; i++)
			registradoresVd.add(new Registrador(16));
	}
	public static GetRegistrador getGr() {
		if(gr == null) {
			gr = new GetRegistrador();
		}
		return gr;
	}
	
	public Registrador get(int registrador) {
		return registradoresVd.get(registrador);
	}
	public void reset() {
		// reseta pc, ac, 
		for (int i=0;  i<2; i++) {
			this.get(i).set(new Integer[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
		}
		// reseta sp
		this.get(2).set(new Integer[]{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0});
		
		//reseta a, b, c, d, e, f
		for(int i = 10; i<16; i++) {
			this.get(i).set(new Integer[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
		}
			
	}
}
