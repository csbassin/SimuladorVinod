package modelo.registradores;

import modelo.MemoriaPrincipal;

public class MemoryBufferRegisterRead extends MemoryBufferRegister{
	private static MemoryBufferRegisterRead mbrRead = null;
	
    private MemoryBufferRegisterRead(){
        super(MemoriaPrincipal.getMemoriaPrincipal());
    }
    
    public static MemoryBufferRegisterRead getMbrRead() {
    	if(mbrRead == null) {
    		mbrRead = new MemoryBufferRegisterRead();
    	}
    	return mbrRead;
    }
    
    public void read(){
        Integer[] toRead = memory.read();
        for (int i=0; i <16; i++)
            registrador[i] = toRead[i];
    }
}
