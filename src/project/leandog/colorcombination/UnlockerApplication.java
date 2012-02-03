package project.leandog.colorcombination;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class UnlockerApplication extends JFrame {

	public static final String WINDOW_NAME = "Master Panel Unlocker";
	public static final String FIRST_COLOR_NAME = "First Color";
	public static final String LAST_COLOR_NAME = "Last Color";
	public static final String UNLOCK_CONTROL_NAME = "Unlock Control";
	public static final String UNLOCK_RESULTS_NAME = "Unlock Results";
	
	
	private JLabel firstColor;
	private JLabel lastColor;
	private JButton unlockButton;
	private JTextArea responseArea;
	
	public UnlockerApplication(){
		super(WINDOW_NAME);
		setName(WINDOW_NAME);
		initLayout();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	
	
	private void initLayout() {
		JPanel p = new JPanel(new BorderLayout());
		
		p.add(initControlPanel(), BorderLayout.NORTH);
		p.add(initResponseArea(), BorderLayout.SOUTH);
		getContentPane().add(p);
		pack();
	}

	private Component initControlPanel() {
		JPanel p = new JPanel();
		
		p.add(initFirstColor());
		p.add(initLastColor());
		p.add(initUnlockControl());
		return p;
	}
	
	private Component initFirstColor() {
		firstColor = new JLabel();
		firstColor.setName(FIRST_COLOR_NAME);
		return firstColor;
	}
	
	private Component initLastColor() {
		lastColor = new JLabel();
		lastColor.setName(LAST_COLOR_NAME);
		return lastColor;
	}
	
	private Component initUnlockControl() {
		unlockButton = new JButton("Unlock");
		unlockButton.setName(UNLOCK_CONTROL_NAME);
		unlockButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				performUnlock();
			}
		});
		return unlockButton;
	}




	private Component initResponseArea() {
		responseArea = new JTextArea();
		responseArea.setName(UNLOCK_RESULTS_NAME);
		return responseArea;
	}

	

	private void performUnlock() {
		Unlocker unlock = new Unlocker();
		unlock.unlock(new UnlockerInput());
		responseArea.setText(unlock.getResult().toString());
	}

	public static void main(String... args) throws Exception {
		startUserInterface();
	}

	private static void startUserInterface() throws Exception {
	    SwingUtilities.invokeAndWait(new Runnable() {
		      public void run() {
		         new UnlockerApplication();
		      }
		    });
	}

}
