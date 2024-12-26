package modelo;
import main.MicroprogramCounter;
import modelo.registradores.Memoria;

import java.util.ArrayList;


public class MemoriaPrincipal extends Memoria {
    public MemoriaPrincipal(MicroprogramCounter mpc) {
        super(4096, 16);
    }
    public void write(Integer[] conteudo, int endereco){
        mem.set(endereco, conteudo.clone());
    }
    public Integer[] read(int endereco){ //só usa o get normalmente
        return mem.get(endereco);
    }
}
