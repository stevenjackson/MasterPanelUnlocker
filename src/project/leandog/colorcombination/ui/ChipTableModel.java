package project.leandog.colorcombination.ui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import project.leandog.colorcombination.Chip;

/**
 * ChipTableModel holds data for the ChipTable.
 *
 */
@SuppressWarnings("serial")
public class ChipTableModel extends AbstractTableModel {
	
	private List<ChipModel> chips = new ArrayList<ChipModel>();

	//Show the two chip colors then a string for the Chip's owner
	private String[] COLUMNS = new String[] {
			" ",
			" ",
			"Owner"
	};

	@Override
	public int getColumnCount() {
		return COLUMNS.length;
	}

	@Override
	public int getRowCount() {
		return chips.size();
	}

	@Override
	public String getColumnName(int column)
    {
       return COLUMNS[column];
    }

	@Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
       if(chips == null) return "";
       ChipModel chip = chips.get(rowIndex);
       
       if(columnIndex == 0){
    	   return chip.getFirstColor().color();
       } else if(columnIndex == 1){
    	   return chip.getSecondColor().color();
       } else if(columnIndex == 2){
    	   return chip.getOwner();
       }
       
       return "";
    }

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		if(chips == null) return;
		ChipModel chip = chips.get(rowIndex);
		
		if(columnIndex == 0){
    	   chip.setFirstColor(color(value));
       } else if(columnIndex == 1){
    	   chip.setSecondColor(color(value));
       } else if(columnIndex == 2){
    	   chip.setOwner(String.valueOf(value));
       }
	}
	
	private ChipColor color(Object value){
		return ChipColor.fromColor((Color)value);
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if(columnIndex == 0){
			return ChipColor.class;
		} else if(columnIndex == 1){
			return ChipColor.class;
		} else {
			return String.class;
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	public void addChip(){
		chips.add(new ChipModel());
		fireTableRowsInserted(chips.size() - 1, chips.size() - 1);
	}

	public List<Chip> chips() {
		List<Chip> chipList = new ArrayList<Chip>();
		for(ChipModel chip : chips){
			chipList.add(chip.toChip());
		}
		return chipList;
	}
    

}
