package project.leandog.colorcombination.ui;

import java.awt.Color;

/** 
 * Enum for associating a chip color with a name.
 */
public enum ChipColor {
	RED(Color.RED, "red"),
	BLUE(Color.BLUE, "blue"),
	GREEN(new Color(0, 128, 0), "green"),
	ORANGE(new Color(255, 127, 0), "orange"),
	GRAY(Color.GRAY, "gray"),
	YELLOW(Color.YELLOW, "yellow"), 
	PURPLE(new Color(125, 38, 205), "purple");
	
	
	private Color color;
	private String text;

	ChipColor(Color color, String text){
		this.color = color;
		this.text = text;
	}
	
	public String toString(){
		return text;
	}
	
	public static Color[] colors(){
		Color[] colors = new Color[values().length];
		for(int i = 0; i < colors.length; i++){
			colors[i] = values()[i].color;
		}
		return colors;
	}

	public static ChipColor fromColor(Color value) {
		for(ChipColor color : values()){
			if(color.color().equals(value)){
				return color;
			}
		}
		
		//Didn't find it
		return null;
	}

	public Color color() {
		return color;
	}
} 
