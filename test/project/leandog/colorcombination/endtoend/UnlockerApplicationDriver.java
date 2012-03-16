package project.leandog.colorcombination.endtoend;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import java.awt.Color;
import java.util.Arrays;

import javax.swing.*;

import project.leandog.colorcombination.ui.*;

import com.objogate.wl.swing.AWTEventQueueProber;
import com.objogate.wl.swing.ComponentSelector;
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
		setColor(c, UnlockerApplication.FIRST_COLOR_NAME);
	}

	public void setLastColor(Color c) {
		setColor(c, UnlockerApplication.LAST_COLOR_NAME);
	}
	
	private void setColor(Color c, String componentName){
		JComboBoxDriver comboDriver = new JComboBoxDriver(this, JComboBox.class, named(componentName));
		setColor(c, comboDriver);
	}
	
	private void setColor(Color c, JComboBoxDriver comboDriver){
		//Have to cheat and find index of color to use this driver
		int index = Arrays.asList(ChipColor.colors()).indexOf(c);
		comboDriver.selectItem(index);
		comboDriver.has(backgroundColor(), equalTo(c));
	}

	public void fireUnlock() {
		new JButtonDriver(this, JButton.class, named(UnlockerApplication.UNLOCK_CONTROL_NAME)).click();
	}

	public void showsTextStatus(String text) {
		new JTextComponentDriver<JTextArea>(this, JTextArea.class,
				named(UnlockerApplication.UNLOCK_RESULTS_NAME)).hasText(text);
	}

	public void addChip(int rowIndex, Color c1, Color c2) {
		new JButtonDriver(this, JButton.class, named(UnlockerApplication.ADD_CHIP_NAME)).click();
		
		JTableDriver table = new JTableDriver(this, JTable.class, named(UnlockerApplication.CHIP_TABLE_NAME));
		table.editCell(rowIndex, 0);
		table.component().component().setValueAt(c1, rowIndex, 0);
		table.editCell(rowIndex, 1);
		table.component().component().setValueAt(c2, rowIndex, 1);
	}

	public void showsChipSequence(Color... chipColors) {
		 ComponentSelector<ChipBoard> selector = new JComponentDriver<ChipBoard>(this, ChipBoard.class, named(UnlockerApplication.CHIP_BOARD_NAME)).component();
		 selector.probe();
		 ChipBoard board = selector.component();
		assertEquals(Arrays.asList(chipColors), Arrays.asList(board.getColors()));
	}

	

}
