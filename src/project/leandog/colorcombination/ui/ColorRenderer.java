package project.leandog.colorcombination.ui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.*;

/**
 * ColorRenderer is used to render a single color in a list.
 */
@SuppressWarnings("serial")
public class ColorRenderer extends DefaultListCellRenderer {

	private final JComboBox box;

	public ColorRenderer(JComboBox box){
		this.box = box;
		setOpaque(true);
	}
	
	@Override
	public void setBackground(Color col) 
    {
		//Overriden to avoid the L&F screwing up our paint.
    } 
	
	@Override
	public Component getListCellRendererComponent(JList list, 
            Object value, 
            int index, 
            boolean isSelected,  
            boolean cellHasFocus) {
 		
		setText("      ");
		Color color = (Color)value;
		
		//Set the box background to get the proper color for the selected item.
		box.setBackground(color);
		//Call super (JComponent) to bypass the overriden setBackground in this class.
		//This sets the proper color in the list drop down.
 		super.setBackground(color);
 		
		return this;
	}

}
