package project.leandog.colorcombination.ui;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.table.*;

import project.leandog.colorcombination.Chip;

/**
 * ChipTable is a controller for the JTable that renders Chip information.
 */
public class ChipTable {

	private JTable table;
	private ChipTableModel tableModel;
	
	public ChipTable(){
		tableModel = new ChipTableModel();
		table = new JTable(tableModel);
		setupRenderers();
	}
	
	private void setupRenderers() {
		for(int i = 0; i < table.getColumnCount(); i++){
			TableColumn col = table.getColumnModel().getColumn(i);
			if(table.getColumnClass(i).equals(ChipColor.class)){
				col.setCellRenderer(createColorCellRenderer());
				col.setCellEditor(createColorCellEditor());
			}
		}
	}

	

	private TableCellRenderer createColorCellRenderer() {
		return new TableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value,
		            boolean isSelected, boolean hasFocus, int row, int column) {
				ColorComboBox box = new ColorComboBox(ChipColor.colors());
				box.setSelectedItem(value);
				return box;
			}
		};
	}
	
	private TableCellEditor createColorCellEditor() {
		return new DefaultCellEditor(new ColorComboBox(ChipColor.colors()));
	}

	public void setName(String name){
		table.setName(name);
	}
	
	public Component getComponent() {
		return table;
	}
	
	public void addChip(){
		tableModel.addChip();
	}

	public Chip[] getChips() {
		return tableModel.chips().toArray(new Chip[0]);
	}
}
