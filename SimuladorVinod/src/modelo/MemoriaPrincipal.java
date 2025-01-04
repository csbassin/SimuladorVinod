package modelo;
import modelo.registradores.Memoria;



public class MemoriaPrincipal extends Memoria {
    private int enderecoSlecionado;


    public MemoriaPrincipal() {
        super(4096, 16);
        for(int i=0; i<4096; i++){
            mem.add(new Integer[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
        }
    }
    public void write(Integer[] conteudo){
        mem.set(enderecoSlecionado, conteudo.clone());
    }
    public Integer[] read(){ //só usa o get normalmente
        return mem.get(enderecoSlecionado);
    }
    public void setEnderecoSlecionado(int enderecoSlecionado) {
        this.enderecoSlecionado = enderecoSlecionado;
    }
}
