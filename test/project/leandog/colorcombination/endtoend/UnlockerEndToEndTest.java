package project.leandog.colorcombination.endtoend;

import java.awt.Color;

import org.junit.*;

import project.leandog.colorcombination.ui.ChipColor;


public class UnlockerEndToEndTest {
	
	//Sync up the colors in the test and the app.
	private static final Color BLUE = ChipColor.BLUE.color();
	private static final Color RED = ChipColor.RED.color();
	private static final Color GREEN = ChipColor.GREEN.color();
	private static final Color YELLOW = ChipColor.YELLOW.color();
	private static final Color ORANGE = ChipColor.ORANGE.color();
	private static final Color PURPLE = ChipColor.PURPLE.color();
	
	private final ApplicationRunner application = new ApplicationRunner();
	
	@Test public void unlockFailsWithNoInput(){
		application.unlock();
		application.showsUnlockFailed();
	}
		
	@Test public void unlockFailsWithNoChips(){
		application.setEndColors(BLUE, RED);
		application.unlock();
		application.showsUnlockFailed();
	}
	
	@Test public void unlockPassWithGoodChip() {
		application.setEndColors(BLUE, RED);
		application.addChip(0, BLUE, RED);
		application.unlock();
		application.showsUnlockSuccess(BLUE, RED);
	}
	
	@Test public void unlockFailWithBadChip() {
		application.setEndColors(BLUE, GREEN);
		application.addChip(0, RED, BLUE);
		application.unlock();
		application.showsUnlockFailed();
	}
	
	@Test public void unlockPassWithFlipChip() {
		application.setEndColors(BLUE, RED);
		application.addChip(0, RED, BLUE);
		application.unlock();
		application.showsUnlockSuccess(BLUE, RED);
	}
	
	@Test public void exampleOne(){
		application.setEndColors(BLUE, GREEN);
		application.addChips(BLUE, YELLOW,
				RED, ORANGE,
				RED, GREEN,
				YELLOW, RED,
				ORANGE, PURPLE);
		
		application.unlock();
		application.showsUnlockFailed();
	}
	
	@Test public void exampleTwo() {
		application.setEndColors(BLUE, GREEN);
		application.addChips(
				BLUE, YELLOW,
				ORANGE, RED,
				RED, GREEN,
				YELLOW, RED,
				ORANGE, RED);
		
		application.unlock();
		application.showsUnlockSuccess(
				BLUE, YELLOW,
				YELLOW, RED,
				RED, ORANGE,
				ORANGE, RED,
				RED, GREEN);
	}
	
	@Test public void example3(){
		application.setEndColors(BLUE, RED);
		application.addChips(
				RED, BLUE,
				ORANGE, RED,
				GREEN, GREEN,
				ORANGE, YELLOW,
				RED, YELLOW
				);
		
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
