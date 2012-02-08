package project.leandog.colorcombination;

import java.util.*;

/**
 * UnlockerInput validates the input for Unlocker.
 */
public class UnlockerInput {
	private Object firstColor;
	private Object lastColor;
	List<Chip> chips = new ArrayList<Chip>();
	

	public boolean isValid() {
		return firstColor() != null && lastColor() != null && !chips.isEmpty();
	}

	public Object firstColor() {
		return firstColor;
	}

	public Object lastColor() {
		return lastColor;
	}

	public Collection<Chip> chips() {
		return chips;
	}

	public void setFirstColor(Object color) {
		this.firstColor = color;
	}

	public void setLastColor(Object color) {
		this.lastColor = color;
	}
	
	public void setChips(Chip[] chipArray){
		setChips(Arrays.asList(chipArray));
	}

	public void setChips(Collection<Chip> list) {
		chips = new ArrayList<Chip>(list);
	}

}
