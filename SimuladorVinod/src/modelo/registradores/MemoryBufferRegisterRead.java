package modelo.registradores;

import modelo.MemoriaPrincipal;

public class MemoryBufferRegisterRead extends MemoryBufferRegister{
    public MemoryBufferRegisterRead(MemoriaPrincipal memory){
        super(memory);
    };
    public void read(){
        Integer[] toRead = memory.read();
        for (int i=0; i <16; i++)
            registrador[i] = toRead[i];
    }
}
