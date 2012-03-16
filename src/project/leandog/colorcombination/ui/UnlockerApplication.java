package project.leandog.colorcombination.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import project.leandog.colorcombination.Unlocker;
import project.leandog.colorcombination.UnlockerInput;

/**
 * UnlockerApplication is the GUI for the Unlocker.
 */
@SuppressWarnings("serial")
public class UnlockerApplication extends JFrame {

	public static final String WINDOW_NAME = "Master Panel Unlocker";
	public static final String FIRST_COLOR_NAME = "First Color";
	public static final String LAST_COLOR_NAME = "Last Color";
	public static final String UNLOCK_CONTROL_NAME = "Unlock Control";
	public static final String UNLOCK_RESULTS_NAME = "Unlock Results";
	public static final String ADD_CHIP_NAME = "Add Chip Button";
	public static final String CHIP_TABLE_NAME = "Chip Table";
	public static final String CHIP_BOARD_NAME = "Chip Board";
	
	private ColorComboBox firstColor;
	private ColorComboBox lastColor;
	private JButton unlockButton;
	private JTextArea responseArea;
	private ChipTable table;
	private ChipBoard chipBoard;
	private ChipsModel chips;
	
	public UnlockerApplication(){
		super(WINDOW_NAME);
		setName(WINDOW_NAME);
		
		chips = new ChipsModel();
		
		initLayout();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void initLayout() {
		JPanel p = new JPanel(new BorderLayout());
		
		p.add(initControlPanel(), BorderLayout.NORTH);
		p.add(initChipTableArea(), BorderLayout.CENTER);
		p.add(initResponseArea(), BorderLayout.SOUTH);
		getContentPane().add(p);
		setSize(1200, 600);
	}

	private Component initControlPanel() {
		JPanel p = new JPanel();
		
		p.add(initFirstColor());
		p.add(initChipBoard());
		p.add(initLastColor());
		p.add(initUnlockControl());
		return p;
	}

	private Component initFirstColor() {
		firstColor = new RoundColorComboBox(ChipColor.colors());
		firstColor.setName(FIRST_COLOR_NAME);
		return firstColor;
	}
	
	private Component initChipBoard() {
		chipBoard = new ChipBoard(chips);
		chipBoard.setName(CHIP_BOARD_NAME);
		return chipBoard;
	}
	
	private Component initLastColor() {
		lastColor = new RoundColorComboBox(ChipColor.colors());
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
	
	private Component initChipTableArea() {
		JPanel p = new JPanel(new BorderLayout());
		p.add(initChipTable(), BorderLayout.CENTER);
		p.add(initChipButtons(), BorderLayout.EAST);
		
		return p;
	}


	private Component initChipTable() {
		table = new ChipTable(chips);
		table.setName(CHIP_TABLE_NAME);
		table.addChangeListener(createTableListener());
		
		return new JScrollPane(table.getComponent());
	}

	private TableModelListener createTableListener() {
		return new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				updateBoard();
			}
		};
	}
	
	private void updateBoard() {
		chipBoard.updateView();
	}

	private Component initChipButtons() {
		Box b = Box.createVerticalBox();
		b.add(initAddButton());
		b.add(Box.createVerticalGlue());
		return b;
	}

	private Component initAddButton() {
		JButton addButton = new JButton("Add");
		addButton.setName(ADD_CHIP_NAME);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addChip();
			}
		});
		return addButton;
	}
	
	private void addChip() {
		table.addChip();
		updateBoard();
	}

	private Component initResponseArea() {
		responseArea = new JTextArea();
		responseArea.setName(UNLOCK_RESULTS_NAME);
		return responseArea;
	}

	private void performUnlock() {
		Unlocker unlock = new Unlocker();
		unlock.unlock(createInput());
		responseArea.setText(unlock.getResultAsString());
		chips.reorder(unlock.getOrderedChips());
		chipBoard.updateView();
	}

	private UnlockerInput createInput() {
		UnlockerInput input = new UnlockerInput();		
		input.setFirstColor(getFirstChipColor());
		input.setLastColor(getLastChipColor());
		input.setChips(chips.getChips());
		return input;
	}

	private ChipColor getFirstChipColor() {
		return ChipColor.fromColor(firstColor.getSelectedColor());
	}

	private Object getLastChipColor() {
		return ChipColor.fromColor(lastColor.getSelectedColor());
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
