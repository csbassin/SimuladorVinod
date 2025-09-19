package main;

public class ControladorDeFluxo {
	private static ControladorDeFluxo cf = null;
	private int[]cond = new int[2];
	private int n = 0;
	private int z = 0;
	private ControladorDeFluxo() {
		
	}
	public static ControladorDeFluxo getCf() {
		if(cf == null) {
			cf = new ControladorDeFluxo();
		}
		return cf;
	}
	public int[] getDesvia() {
		if((cond[0] == 0 && cond[1] == 1 && n==1) || (cond[0] == 1 && cond[1] == 0 && z == 1) || (cond[0] == 1 && cond[1] == 1)) {
			return new int[]{1};
		}
		return new int[]{0};
	}
	public void setN(int n) {
		this.n = n;
	}
	public void setZ(int z) {
		this.z = z;
	}
	public void setCond(int[]cond) {
		this.cond = cond;
	}
}
