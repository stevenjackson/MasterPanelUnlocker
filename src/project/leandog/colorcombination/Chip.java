package project.leandog.colorcombination;

import java.util.*;

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

	public boolean precedes(Chip chip) {
		return lastColor().equals(chip.firstColor());
	}
	
	public Chip flip() {
		return new Chip(lastColor, firstColor);
	}
	
	public String toString(){
		return firstColor() + "," + lastColor();
	}
	
	public boolean matches(Chip other){
		return colorSet().equals(other.colorSet());
	}
	
	public boolean same(Chip other){
		return colorList().equals(other.colorList());
	}
	
	private Set<Object> colorSet() {
		return new HashSet<Object>(colorList());
	}
	
	private List<Object> colorList() {
		return Arrays.asList(firstColor(), lastColor());
	}

}
