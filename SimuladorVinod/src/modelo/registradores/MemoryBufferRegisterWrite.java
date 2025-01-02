package modelo.registradores;

import modelo.MemoriaPrincipal;

public class MemoryBufferRegisterWrite extends MemoryBufferRegister{
    public MemoryBufferRegisterWrite(MemoriaPrincipal memory){
        super(memory);
    };
    public void write(){
        Integer[] toWrite = new Integer[16];
        for (int i=0; i <16; i++)
            toWrite[i] = registrador[i];

        memory.write(toWrite);
    }
}