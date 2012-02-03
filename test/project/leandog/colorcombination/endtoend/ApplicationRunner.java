package project.leandog.colorcombination.endtoend;

import java.awt.Color;

import com.objogate.wl.swing.driver.JButtonDriver;

import project.leandog.colorcombination.Unlocker;
import project.leandog.colorcombination.UnlockerApplication;

public class ApplicationRunner {

	private UnlockerApplicationDriver driver;

	public void start() {
		Thread thread = new Thread("Unlocker Application"){
			public void run() {
				try{
					UnlockerApplication.main();
				} catch (Exception ex){
					ex.printStackTrace();
				}
			}
		};
		thread.setDaemon(true);
		thread.start();
		driver = new UnlockerApplicationDriver(1000);
	}

	public void setFirstColor(Color c) {
		driver.setFirstColor(c);
	}

	public void setLastColor(Color c) {
		driver.setLastColor(c);
	}
	
	public void unlock() {
		driver.fireUnlock();
	}

	public void showsUnlockFailed() {
		driver.showsTextStatus(Unlocker.UNLOCK_FAILED_MESSAGE);
	}

	public void stop() {
		if(driver != null){
			driver.dispose();
		}
	}

}
