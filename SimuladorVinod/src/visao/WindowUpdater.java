package visao;

import main.UnidadeControle;

public class WindowUpdater extends Thread{
	private static WindowUpdater wu = null;
	
	public static WindowUpdater getWu(MainWindow mw, MemoriaWindow memw, ProcessadorWindow pw, int sleep) {
		if(wu == null) {
			wu = new WindowUpdater(mw, memw, pw, sleep);
		}
		return wu;
	}
	
	private MainWindow mw;
	private MemoriaWindow memw;
	private ProcessadorWindow pw;
	int sleep;
	private boolean pause;
	private boolean stop = false;
	UnidadeControle uc = UnidadeControle.getUc();
	
	private WindowUpdater(MainWindow mw, MemoriaWindow memw, ProcessadorWindow pw, int sleep) {
		this.mw = mw;
		this.memw = memw;
		this.pw = pw;
		this.sleep = sleep;
	}
	
	public void setSleep(int sleep) {
		this.sleep = sleep;
	}

	@Override
	public void run() {
		while(!stop) {
			//if(!uc.isResetar()) {
				try {
					while(pause) {
						sleep(100);
					}
					mw.update();
					memw.update();
					pw.update();
					sleep(sleep);
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			//}
		}
		
	}

	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}
	
	
}
