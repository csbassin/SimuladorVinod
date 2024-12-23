package visao;

public class WindowUpdater extends Thread{
	
	private MainWindow mw;
	
	public WindowUpdater(MainWindow mw) {
		this.mw = mw;
	}
	
	@Override
	public void run() {
		while(true) {
			mw.update();	
		}
		
	}
}
