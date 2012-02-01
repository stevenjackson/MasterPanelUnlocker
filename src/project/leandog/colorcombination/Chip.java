package project.leandog.colorcombination;

/**
 * Chip is a Value Object representing each family member's security chip.
 *
 */
public class Chip {

	private final Object firstColor;
	private final Object lastColor;
	
	public Chip(Object firstColor, Object lastColor){
		this.firstColor = firstColor;
		this.lastColor = lastColor;
	}

	public Object firstColor() {
		return firstColor;
	}

	public Object lastColor() {
		return lastColor;
	}

	public boolean matches(Chip chip) {
		return lastColor().equals(chip.firstColor());
	}
	
	public Chip flip() {
		return new Chip(lastColor, firstColor);
	}
	
	public String toString(){
		return firstColor() + "," + lastColor();
	}

}
