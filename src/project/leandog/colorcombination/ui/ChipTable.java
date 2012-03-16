package project.leandog.colorcombination.ui;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;


/**
 * ChipTable is a controller for the JTable that renders Chip information.
 */
public class ChipTable {

	private JTable table;
	private ChipTableModel tableModel;
	
	public ChipTable(ChipsModel chips){
		tableModel = new ChipTableModel(chips);
		tableModel.addTableModelListener(createModelListener());
		table = new JTable(tableModel);
		setupRenderers();
	}
	
	private TableModelListener createModelListener() {
		return new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				//Tell the rest of the UI
			}
		};
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
	
	public ChipModel addChip(){
		return tableModel.addChip();
	}
	
	public void addChangeListener(TableModelListener l) {
		tableModel.addTableModelListener(l);
	}
}
