package modelo;

public class Desclocador {
    private int[] entrada;  //16bits
    private int[] desl_mir; //2bits
    private int[] saida;    //16bits

    public Desclocador(){
        entrada = new int[16];
        saida = new int[16];
        desl_mir = new int[2];
    }

    public void perform(){
        if (desl_mir[0] == 0 && desl_mir[1] == 0)
            System.arraycopy(entrada, 0, saida, 0, 16);

        //desloca direita
        else if (desl_mir[0] == 0 && desl_mir[1] == 1) {
            System.arraycopy(entrada, 1, saida, 1, 15);
            saida[0]=0;
        }

        //desloca esquerda
        else if (desl_mir[0] == 1 && desl_mir[1] == 0) {
            System.arraycopy(entrada, 1, saida, 0, 15);
            saida[15]=0;
        }
        // desl_mir[0] == 1 && desl_mir[1] == 1 Não acontece

    }

    public void setEntrada(int[] entrada) {
        this.entrada = entrada;
    }

    public void setDesl_mir(int[] desl_mir) {
        this.desl_mir = desl_mir;
    }

    public int[] getSaida() {
        return saida;
    }
}
