package project.leandog.colorcombination;

import java.util.ArrayList;
import java.util.List;

/**
 * Unlocker performs the search algorithm to determine if the given input can unlock the panel.
 *
 */
public class Unlocker {

	public static final String UNLOCK_FAILED_MESSAGE = "Cannot unlock master panel";
	private String result = UNLOCK_FAILED_MESSAGE;
	private List<Chip> orderedResults = new ArrayList<Chip>();

	public void unlock(UnlockerInput input) {
		orderedResults = new ArrayList<Chip>();
		
		if(input.isValid()) {
			unlockValidInput(input);
		} else {
			fail();
		}
	}
	
	private void unlockValidInput(UnlockerInput input){
		//Place-holder chips to mark start and end of the pattern
		Chip startChip = new Chip("START", input.firstColor());
		Chip endChip = new Chip(input.lastColor(), "END");
		
		List<Chip> toOrder = new ArrayList<Chip>(input.chips());
		toOrder.add(endChip);
		
		findPatternRec(startChip, toOrder);
		
		boolean foundPattern = isLast(endChip, orderedResults); 
		
		if(foundPattern){
			orderedResults.remove(endChip);
			storeResults(orderedResults);
		} else {
			fail();
		}
		
	}
	
	private boolean fail() {
		orderedResults.clear();
		result = UNLOCK_FAILED_MESSAGE;
		return false;
	}
	
	private void findPatternRec(Chip startChip, List<Chip> toOrder) {
		//Copy to working list so we can modify the passed in list
		List<Chip> workingList = new ArrayList<Chip>(toOrder);
		for(Chip chip : workingList){
			toOrder.remove(chip);
			
			if(completesPath(startChip, chip, toOrder)){
				return;
			} else if(completesPath(startChip, chip.flip(), toOrder)){
				return;
			}
			
			toOrder.add(chip);
		}
	}
	
	private boolean completesPath(Chip prevChip, Chip nextChip, List<Chip> remainingChips){
		return prevChip.precedes(nextChip) && completesPath(nextChip, remainingChips);
	}
	
	private boolean completesPath(Chip nextChip, List<Chip> remainingChips){
		orderedResults.add(nextChip);
		findPatternRec(nextChip, remainingChips);
		if(!remainingChips.isEmpty()){
			orderedResults.remove(nextChip);
		}
		
		return remainingChips.isEmpty();
	}
	
	private boolean isLast(Chip val, List<Chip> list) {
		if(list.isEmpty()) return false;
		
		return val.equals(list.get(list.size() - 1));
	}
	
	private void storeResults(List<Chip> chips) {
		StringBuilder sb = new StringBuilder();
		for(Chip chip : chips){
			sb.append(chip.toString());
			sb.append("\n");
		}
		
		result =  sb.toString().trim();
	}

	public String getResultAsString() {
		return result;
	}
	
	public Chip[] getOrderedChips() {
		return orderedResults.toArray(new Chip[0]);
	}

}
