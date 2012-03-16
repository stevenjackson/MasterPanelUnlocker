package project.leandog.colorcombination.endtoend;

import java.awt.Color;

import project.leandog.colorcombination.Unlocker;
import project.leandog.colorcombination.ui.ChipColor;
import project.leandog.colorcombination.ui.UnlockerApplication;

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

	public void addChip(int rowIndex, Color c1, Color c2) {
		driver.addChip(rowIndex, c1, c2);
	}

	public void showsUnlockSuccess(Color... colors) {
		driver.showsTextStatus(buildResultText(colors));
	}

	private String buildResultText(Color... colors) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < colors.length; i++){
			sb.append(ChipColor.fromColor(colors[i]));
			//figure out separator by position
			if(i % 2 == 0){
				sb.append(",");
			} else {
				sb.append("\n");
			}
		}		
		return sb.toString().trim();
	}

	public void setEndColors(Color start, Color end) {
		setFirstColor(start);
		setLastColor(end);
	}

	public void addChips(Color... colors) {
		int rowIndex = 0;
		int colorIndex = 0;
		while(colorIndex < colors.length){
			addChip(rowIndex++, colors[colorIndex++], colors[colorIndex++]);
		}
	}

	public void showsChipSequence(Color... chipColors) {
		driver.showsChipSequence(chipColors);
	}
}
