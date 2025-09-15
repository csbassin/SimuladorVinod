package visao;

public class WindowUpdater extends Thread{
	
	private MainWindow mw;
	public MemoriaWindow memw;
	int sleep;
	private boolean pause;
	
	public WindowUpdater(MainWindow mw, int sleep) {
		this.mw = mw;
		this.sleep = sleep;
	}
	
	public void setSleep(int sleep) {
		this.sleep = sleep;
	}

	@Override
	public void run() {
		while(true) {
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
	
}
