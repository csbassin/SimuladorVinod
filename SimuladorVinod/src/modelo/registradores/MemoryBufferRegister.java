package modelo.registradores;

import modelo.MemoriaPrincipal;

public class MemoryBufferRegister extends Registrador{
    protected MemoriaPrincipal memory;
    public MemoryBufferRegister(MemoriaPrincipal memory) {
        super(16);
        this.memory = memory;
    }

}
