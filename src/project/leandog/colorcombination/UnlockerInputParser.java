package project.leandog.colorcombination;

import java.util.ArrayList;
import java.util.List;

/**
 * UnlockerInputParser parses text and converts it to an UnlockerInput.
 */
public class UnlockerInputParser {

	private static final String LINE_SEPARATOR = "\n";
	private static final String COLOR_SEPARATOR = ",";
	
	public static UnlockerInput parse(String input) {
		UnlockerInput parsedInput = new UnlockerInput();
		List<Chip> parsedChips = toChips(String.valueOf(input).split(LINE_SEPARATOR));
		if(parsedChips.size() > 0){
			parsedInput.setFirstColor(parsedChips.get(0).firstColor());
			parsedInput.setLastColor(parsedChips.get(0).lastColor());
			parsedInput.setChips(parsedChips.subList(1, parsedChips.size()));
		}
		
		return parsedInput;
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
}
