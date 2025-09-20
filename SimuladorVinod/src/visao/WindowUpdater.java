package visao;

public class WindowUpdater extends Thread{
	private static WindowUpdater wu = null;
	
	public static WindowUpdater getWu(MainWindow mw, int sleep) {
		if(wu == null) {
			wu = new WindowUpdater(mw, sleep);
		}
		return wu;
	}
	
	private MainWindow mw;
	public MemoriaWindow memw;
	int sleep;
	private boolean pause;
	private boolean stop = false;
	
	private WindowUpdater(MainWindow mw, int sleep) {
		this.mw = mw;
		this.sleep = sleep;
	}
	
	public void setSleep(int sleep) {
		this.sleep = sleep;
	}

	@Override
	public void run() {
		while(!stop) {
			try {
				while(pause) {
					sleep(1000);
				}
				mw.update();
				memw.update();
				sleep(sleep);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
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
