package project.leandog.colorcombination.ui;

import java.util.*;

import project.leandog.colorcombination.Chip;

public class ChipComparator implements Comparator<ChipModel> {

	private List<Chip> order;

	public ChipComparator(Chip[] orderedChips) {
		order = Arrays.asList(orderedChips);
	}

	@Override
	public int compare(ChipModel o1, ChipModel o2) {
		return findIndex(o1) - findIndex(o2);
	}

	private int findIndex(ChipModel model) {
		return order.indexOf(findChip(model));
	}

	private Chip findChip(ChipModel model) {
		for(Chip chip : order)
			if(model.matches(chip))
				return chip;
		
		return null;
	}

}
