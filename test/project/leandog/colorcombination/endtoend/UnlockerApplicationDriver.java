package project.leandog.colorcombination.endtoend;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JTextArea;

import project.leandog.colorcombination.UnlockerApplication;

import com.objogate.wl.swing.AWTEventQueueProber;
import com.objogate.wl.swing.driver.*;
import com.objogate.wl.swing.gesture.GesturePerformer;

@SuppressWarnings("unchecked")
public class UnlockerApplicationDriver extends JFrameDriver {

	public UnlockerApplicationDriver(int timeoutMillis) {
		super(new GesturePerformer(),
				JFrameDriver.topLevelFrame(
					named(UnlockerApplication.WINDOW_NAME),
					showingOnScreen()),
				new AWTEventQueueProber(timeoutMillis, 100));
						
		hasTitle(UnlockerApplication.WINDOW_NAME);
	}

	public void setFirstColor(Color c) {
		
	}

	public void setLastColor(Color c) {
		
	}

	public void fireUnlock() {
		new JButtonDriver(this, JButton.class, named(UnlockerApplication.UNLOCK_CONTROL_NAME)).click();
	}

	public void showsTextStatus(String text) {
		new JTextComponentDriver<JTextArea>(this, JTextArea.class,
				named(UnlockerApplication.UNLOCK_RESULTS_NAME)).hasText(text);
	}

	

}
