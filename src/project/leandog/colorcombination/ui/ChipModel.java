package project.leandog.colorcombination.ui;

import project.leandog.colorcombination.Chip;

/**
 * ChipModel uses ChipColors to model a Chip in the UI.  It also has a field for
 * associating a Chip with a user.
 */
public class ChipModel {

	private ChipColor firstColor;
	private ChipColor secondColor;
	private String owner;
	
	public ChipModel(){
		setFirstColor(ChipColor.values()[0]);
		setSecondColor(ChipColor.values()[0]);
		setOwner("");
	}

	public void setFirstColor(ChipColor firstColor) {
		this.firstColor = firstColor;
	}

	public ChipColor getFirstColor() {
		return firstColor;
	}

	public void setSecondColor(ChipColor secondColor) {
		this.secondColor = secondColor;
	}

	public ChipColor getSecondColor() {
		return secondColor;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getOwner() {
		return owner;
	}

	public Chip toChip() {
		return new Chip(firstColor, secondColor);
	}

	public boolean matches(Chip chip) {
		return toChip().matches(chip);
	}

	public void flipToMatch(Chip chip) {
		if(!matches(chip)) return;
		
		if(!toChip().same(chip)) {
			flip();
		}	
	}

	private void flip() {
		ChipColor temp = firstColor;
		firstColor = secondColor;
		secondColor = temp;
	}
}
