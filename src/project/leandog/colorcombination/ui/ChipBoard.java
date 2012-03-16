package project.leandog.colorcombination.ui;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import project.leandog.colorcombination.Chip;

@SuppressWarnings("serial")
public class ChipBoard extends JPanel {

	private ChipsModel chips;

	public ChipBoard(ChipsModel chips) {
		this.chips = chips;
	}

	public Color[] getColors() {
		List<Color> colors = new ArrayList<Color>();
		for (ChipModel chip : chips) {
			colors.add(chip.getFirstColor().color());
			colors.add(chip.getSecondColor().color());
		}

		return colors.toArray(new Color[colors.size()]);
	}

	private void drawChip(ChipModel chip) {
		JPanel chipColors = new JPanel(new GridLayout(1, 2));
		chipColors.add(initChipColor(chip.getFirstColor()));
		chipColors.add(initChipColor(chip.getSecondColor()));

		JPanel chipView = new JPanel(new BorderLayout());
		chipView.add(chipColors, BorderLayout.CENTER);
		chipView.add(new JLabel(chip.getOwner()), BorderLayout.SOUTH);
		chipView.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		add(chipView);
	}

	private Component initChipColor(ChipColor firstColor) {
		ColorComboBox box = new ColorComboBox(firstColor.colors());
		box.setSelectedItem(firstColor.color());
		box.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		box.setEnabled(false);
		return box;
	}

	public void updateView() {
		removeAll();
		for(ChipModel chip : chips){
			drawChip(chip);
		}
		revalidate();
	}

}
