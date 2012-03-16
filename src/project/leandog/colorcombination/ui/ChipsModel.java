package project.leandog.colorcombination.ui;

import java.util.*;

import project.leandog.colorcombination.Chip;

public class ChipsModel implements Iterable<ChipModel>{
	private final List<ChipModel> chips = new ArrayList<ChipModel>();

	public int size() {
		return chips.size();
	}

	public ChipModel get(int index) {
		return chips.get(index);
	}

	public void add(ChipModel chipModel) {
		chips.add(chipModel);
	}

	@Override
	public Iterator<ChipModel> iterator() {
		return chips.iterator();
	}

	public Chip[] getChips() {
		List<Chip> chipList = new ArrayList<Chip>();
		for(ChipModel chip : this){
			chipList.add(chip.toChip());
		}
		return chipList.toArray(new Chip[chipList.size()]);
	}

	public void reorder(Chip[] orderedChips) {
		//There may be an invalid result leading to a silly reorder call
		if(orderedChips.length != size()) return;
		
		Collections.sort(chips, new ChipComparator(orderedChips));
		
		//See if the sort took, flip chips as needed
		for(int i = 0; i < chips.size(); i++){
			chips.get(i).flipToMatch(orderedChips[i]);
		}
	}
	
	
}
