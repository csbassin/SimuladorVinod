package visao;

public class WindowUpdater extends Thread{
	
	private MainWindow mw;
	int sleep;
	
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
			mw.update();
			try {
				sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
