package main;

import java.util.ArrayList;

import modelo.registradores.Memoria;
import util.Conversoes;

public class MemoriaDeControle extends Memoria{
	private MicroprogramCounter mpc;
	public MemoriaDeControle(MicroprogramCounter mpc) {
		super(78, 32);
		this.mem.add(new Integer[]{0,0,0,1,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}); //0
		this.mem.add(new Integer[]{0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0}); //1
		this.mem.add(new Integer[]{1,0,1,1,0,0,0,0,0,0,0,1,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0}); //2
		this.mem.add(new Integer[]{0,0,1,0,0,1,0,0,0,0,0,1,0,1,0,0,0,0,1,1,0,0,1,1,0,0,0,1,0,0,1,1}); //3
		this.mem.add(new Integer[]{0,0,1,1,0,1,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,1,1}); //4
		this.mem.add(new Integer[]{0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,1}); //5
		this.mem.add(new Integer[]{0,0,0,1,0,0,0,0,1,1,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0}); //6
		this.mem.add(new Integer[]{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}); //7
		this.mem.add(new Integer[]{1,1,1,1,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}); //8
		this.mem.add(new Integer[]{0,0,0,1,0,0,0,1,1,0,1,0,0,0,0,0,0,0,1,1,0,0,0,1,0,0,0,0,0,0,0,0}); //9
		this.mem.add(new Integer[]{0,1,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}); //10
		this.mem.add(new Integer[]{0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,1,1,1}); //11
		this.mem.add(new Integer[]{0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0}); //12
		this.mem.add(new Integer[]{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}); //13
		this.mem.add(new Integer[]{1,1,1,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0}); //14
		this.mem.add(new Integer[]{0,0,0,1,0,0,0,0,1,1,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0}); //15
		this.mem.add(new Integer[]{0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,1,0,1,1,0,0,0,0,1,0,0,0,0,0,0,0,0}); //16
		this.mem.add(new Integer[]{1,0,0,1,1,0,0,0,0,0,0,1,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}); //17
		this.mem.add(new Integer[]{0,1,1,0,0,0,0,0,0,0,0,1,0,0,0,1,1,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0}); //18
		this.mem.add(new Integer[]{0,0,1,1,0,1,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,1,1,0,0,1}); //19
		this.mem.add(new Integer[]{0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,1,1,1}); //20
		this.mem.add(new Integer[]{0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0}); //21
		this.mem.add(new Integer[]{0,1,1,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0}); //22
		this.mem.add(new Integer[]{0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,1,1,0}); //23
		this.mem.add(new Integer[]{0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}); //24
		this.mem.add(new Integer[]{0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,1,0,1,1}); //25
		this.mem.add(new Integer[]{0,1,1,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0}); //26
		this.mem.add(new Integer[]{0,1,1,0,1,0,0,0,0,0,0,1,0,0,0,1,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0}); //27
		this.mem.add(new Integer[]{0,0,1,0,0,1,0,0,0,0,0,1,0,1,0,0,0,0,1,1,0,0,1,1,0,0,1,0,1,0,0,0}); //28
		this.mem.add(new Integer[]{0,0,1,1,0,1,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,1,1}); //29
		this.mem.add(new Integer[]{0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1}); //30
		this.mem.add(new Integer[]{0,0,0,0,0,0,0,0,0,0,0,1,1,0,1,0,0,0,1,1,0,0,1,0,0,0,0,0,0,0,0,0}); //31
		this.mem.add(new Integer[]{0,1,1,0,0,0,0,0,1,1,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,1,1,1}); //32
		this.mem.add(new Integer[]{0,0,0,0,0,0,0,0,0,0,0,1,1,0,1,0,0,0,1,1,0,0,1,0,0,0,0,0,0,0,0,0}); //33
		this.mem.add(new Integer[]{0,1,1,1,0,0,0,1,1,0,1,0,0,0,0,0,1,0,1,0,0,0,0,1,0,0,0,0,1,0,1,0}); //34
		this.mem.add(new Integer[]{0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,1,1,0}); //35
		this.mem.add(new Integer[]{0,0,0,0,0,0,0,0,0,0,0,1,1,0,1,0,0,0,1,1,0,0,1,0,0,0,0,0,0,0,0,0}); //36
		this.mem.add(new Integer[]{0,1,1,0,0,0,0,0,1,1,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,1,1,0,1}); //37
		this.mem.add(new Integer[]{0,0,0,0,0,0,0,0,0,0,0,1,1,0,1,0,0,0,1,1,0,0,1,0,0,0,0,0,0,0,0,0}); //38
		this.mem.add(new Integer[]{0,1,1,0,0,0,0,0,1,1,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0}); //39
		this.mem.add(new Integer[]{0,0,1,1,0,1,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,1,0,0,0,0,1,0,1,1,1,0}); //40
		this.mem.add(new Integer[]{0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,1,1,0,0}); //41
		this.mem.add(new Integer[]{0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,1,1,0}); //42
		this.mem.add(new Integer[]{0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}); //43
		this.mem.add(new Integer[]{0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0}); //44
		this.mem.add(new Integer[]{0,1,1,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0}); //45
		this.mem.add(new Integer[]{0,0,1,1,0,1,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,1,0,0,0,0,1,1,0,0,1,0}); //46
		this.mem.add(new Integer[]{0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,1,0,0,1,1,1,0,0,0,0,0,0,0,0}); //47
		this.mem.add(new Integer[]{0,0,0,1,0,0,0,1,1,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0}); //48
		this.mem.add(new Integer[]{0,1,1,0,1,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0}); //49
		this.mem.add(new Integer[]{0,0,1,1,0,1,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,1}); //50
		this.mem.add(new Integer[]{0,0,1,1,0,1,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,1,0,0,0,0,1,1,1,0,1,1}); //51
		this.mem.add(new Integer[]{0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,1,1,0,0,0}); //52
		this.mem.add(new Integer[]{0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0}); //53
		this.mem.add(new Integer[]{0,0,0,0,0,0,0,0,0,1,0,1,0,0,1,0,0,0,1,0,0,1,1,1,0,0,0,0,0,0,0,0}); //54
		this.mem.add(new Integer[]{0,1,1,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,1,0}); //55
		this.mem.add(new Integer[]{0,0,0,0,0,0,0,0,1,1,0,1,0,0,1,0,0,0,1,0,0,1,1,0,0,0,0,0,0,0,0,0}); //56
		this.mem.add(new Integer[]{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}); //57
		this.mem.add(new Integer[]{0,1,1,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,1,0}); //58
		this.mem.add(new Integer[]{0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,1,1,1,1,0}); //59
		this.mem.add(new Integer[]{0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,1,0,0,1,1,1,0,0,0,0,0,0,0,0}); //60
		this.mem.add(new Integer[]{0,1,1,1,0,0,0,1,1,0,1,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,1,0}); //61
		this.mem.add(new Integer[]{0,0,0,0,0,0,0,0,1,1,0,1,0,0,1,0,0,0,1,0,0,1,1,0,0,0,0,0,0,0,0,0}); //62
		this.mem.add(new Integer[]{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}); //63
		this.mem.add(new Integer[]{1,1,1,1,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}); //64
		this.mem.add(new Integer[]{0,0,1,1,0,1,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,1,0,0,0,1,0,0,1,0,0,1}); //65
		this.mem.add(new Integer[]{0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,1,1,0}); //66
		this.mem.add(new Integer[]{0,0,0,0,0,0,0,0,1,1,0,1,0,0,1,0,0,0,1,0,0,1,1,0,0,0,0,0,0,0,0,0}); //67
		this.mem.add(new Integer[]{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}); //68
		this.mem.add(new Integer[]{1,1,1,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}); //69
		this.mem.add(new Integer[]{0,0,0,1,0,0,0,0,0,0,0,1,1,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0}); //70
		this.mem.add(new Integer[]{0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0}); //71
		this.mem.add(new Integer[]{0,1,1,1,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0}); //72
		this.mem.add(new Integer[]{0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,1,1,0,0}); //73
		this.mem.add(new Integer[]{0,0,0,0,1,0,0,0,0,0,0,1,1,0,1,0,0,0,1,1,1,0,0,1,0,0,0,0,0,0,0,0}); //74
		this.mem.add(new Integer[]{0,1,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0}); //75
		this.mem.add(new Integer[]{0,0,0,0,1,0,0,0,0,0,0,1,1,0,1,0,0,0,1,1,1,0,0,1,0,0,0,0,0,0,0,0}); //76
		this.mem.add(new Integer[]{0,0,0,1,1,0,0,0,0,0,0,1,1,0,1,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0}); //77
		this.mem.add(new Integer[]{0,1,1,0,0,0,0,0,0,0,0,1,1,0,1,0,1,0,1,0,0,1,1,0,0,1,0,0,1,0,1,1}); //78
		
		this.mpc = mpc;
	}
	
	public Integer[] getValueAtMPCAddress() {
		ArrayList<Integer> arraylist = new ArrayList<Integer>(mpc.getRegistrador().length);
		for(int i = 0; i<mpc.getRegistrador().length; i++) {
			arraylist.add(mpc.getRegistrador()[i]);
		}
		return this.mem.get(Conversoes.bitArrayToDecimal(arraylist));
	}
}
