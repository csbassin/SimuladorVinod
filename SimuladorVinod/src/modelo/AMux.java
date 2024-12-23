package modelo;

import modelo.registradores.Multiplexador;

public class AMux extends Multiplexador{
    public AMux() {
        super(2,2);
    }

    public void setControle(int[]controle) {
        this.controle = controle;
    }
    
    public Integer[] getSaida() {
        if(controle[0] == 1) {
            return entradas.get(1);
        }
        return entradas.get(0);
    }
    
    public void setEntradas(Integer[] mpc, Integer[] addr) {
    	this.entradas.set(0, mpc);
    	this.entradas.set(1, addr);
    }
}
