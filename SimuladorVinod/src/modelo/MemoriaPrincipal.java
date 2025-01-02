package modelo;
import main.MicroprogramCounter;
import modelo.registradores.Memoria;

import java.util.ArrayList;


public class MemoriaPrincipal extends Memoria {
    private int enderecoSlecionado;



    private boolean saida = true; //saida = true   entrada = false

    public MemoriaPrincipal(MicroprogramCounter mpc) {
        super(4096, 16);
    }
    public void write(Integer[] conteudo){
        mem.set(enderecoSlecionado, conteudo.clone());
    }
    public Integer[] read(){ //só usa o get normalmente
        return mem.get(enderecoSlecionado);
    }

    public void setSaida(boolean saida) {this.saida = saida;}
    public void setEnderecoSlecionado(int enderecoSlecionado) {
        this.enderecoSlecionado = enderecoSlecionado;
    }
}
