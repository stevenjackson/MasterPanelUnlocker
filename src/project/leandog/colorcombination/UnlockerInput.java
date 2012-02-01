package project.leandog.colorcombination;

import java.util.*;

/**
 * UnlockerInput handles parsing the input for the Unlocker class.
 *
 */
public class UnlockerInput {

	private static final String LINE_SEPARATOR = "\n";
	private static final String COLOR_SEPARATOR = ",";
	private Object firstColor;
	private Object lastColor;
	List<Chip> chips = new ArrayList<Chip>();
	
	public void parse(String input) {
		List<Chip> parsedChips = toChips(String.valueOf(input).split(LINE_SEPARATOR));
		if(parsedChips.size() > 0){
			firstColor = parsedChips.get(0).firstColor();
			lastColor = parsedChips.get(0).lastColor();
			chips = parsedChips.subList(1, parsedChips.size());
		}
	}
	
	private static List<Chip> toChips(String[] lines) {
		List<Chip> chips = new ArrayList<Chip>();
		for(String line : lines){
			chips.add(parseChip(line));
		}
		return chips;
	}

	private static Chip parseChip(String line) {
		String[] parts = line.split(COLOR_SEPARATOR);
		if(parts.length != 2){
			throw new RuntimeException("Could not parse line: [" + line + "]");
		}
		return new Chip(parts[0].trim(), parts[1].trim());
	}

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

}
