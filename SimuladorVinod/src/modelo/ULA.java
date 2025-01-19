package modelo;

public class ULA {
    // A, B, F, R;
    private int[] A, B, F, D, R;

    public int[] getA() {
		return A;
	}
	public int[] getB() {
		return B;
	}
	public int[] getF() {
		return F;
	}
	public ULA(){
        R = new int[16];
        D = new int[2]; //D = NZ
    }
    public void perform(){
        //SOMA
        if (F[0] == 0 && F[1]==0){
            int carry = 0;
            for (int i = 15; i>=0; i--){//comecar a soma dos menos significativos
                int result = A[i] + B[i] + carry;
                switch (result){
                    case 3: // caso 1+1+1, carry 1 e resultado 1
                        carry = 1;
                        R[i]=1;
                        break;
                    case 2: // caso 0+1+1, carry 1 e resultado 0
                        carry = 1;
                        R[i] = 0;
                        break;
                    case 1: // caso 0+1+0, carry 0 e resultado 1
                        carry = 0;
                        R[i] = 1;
                        break;
                    case 0: // caso 0+0+0, carry 0 e resultado 0
                        carry = 0;
                        R[i] = 0;
                        break;
                }
            }

        }

        //AND (A and B)
        else if (F[0] == 0 && F[1]==1){
            for (int c = 0; c < 16; c++){
                if (A[c] == 1 && B[c] == 1)
                    R[c] = 1;
                else
                    R[c] = 0;
            }
        }

        //IDENTIDADE (A)
        else if (F[0] == 1 && F[1]==0){
            System.arraycopy(A, 0, R, 0, 16);
        }

        //INVERTER (inv(A))
        else if (F[0] == 1 && F[1]==1){
            for (int c =0; c<16; c++){
                if (A[c] == 0)
                    R[c]=1;
                else {
                    assert A[c] == 1; //so pra nao dar varios problemas dps //tirar dps
                    R[c] = 0;
                }
            }
        }

        //Atualiza o D (sempre acontece)
        if (R[0] == 1)
            D[0] = 1; //N
        else
            D[0] = 0;

        //Zero flag (sempre acontece)
        boolean tudoZero = true;
        for (int i = 0; i<16; i++){
            if (R[i] != 0){
                tudoZero = false;
                break;
            }
        }
        if (tudoZero)
            D[1] = 1; //zero flag
        else
            D[1] = 0;
    }

    public void setA(int[] a) {
        A = a;
    }
    public void setB(int[] b) {
        B = b;
    }
    public void setF(int[] f) {
        F = f;
    }
    public int[] getD() {
        return D;
    }
    public int[] getR() {
        return R;
    }

}