package project.leandog.colorcombination.endtoend;

import java.awt.Color;

import org.junit.*;

public class UnlockerEndToEndTest {
	private final ApplicationRunner application = new ApplicationRunner();
	
	@Test public void unlockFailsWithNoInput(){
		application.unlock();
		application.showsUnlockFailed();
	}
	
	@Ignore
	@Test public void unlockFailsWithNoChips(){
		application.setFirstColor(Color.BLUE);
		application.setLastColor(Color.RED);
		application.unlock();
		application.showsUnlockFailed();
	}
	
	@Before public void startApplicaton() {
		application.start();
	}
	
	@After public void stopApplication() {
		application.stop();
	}
	
	

}
