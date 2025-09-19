package modelo.registradores;

import modelo.MemoriaPrincipal;

public class MemoryBufferRegisterWrite extends MemoryBufferRegister{
	private static MemoryBufferRegisterWrite mbrWrite = null;
    private MemoryBufferRegisterWrite(){
        super(MemoriaPrincipal.getMemoriaPrincipal());
    }
    public static MemoryBufferRegisterWrite getMbrWrite() {
    	if(mbrWrite == null) {
    		mbrWrite = new MemoryBufferRegisterWrite();
    	}
    	return mbrWrite;
    }
    public void write(){
        Integer[] toWrite = new Integer[16];
        for (int i=0; i <16; i++)
            toWrite[i] = registrador[i];

        memory.write(toWrite);
    }
}