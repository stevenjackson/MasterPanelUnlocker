package project.leandog.colorcombination.ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

/**
 * Renders a ComboBox that displays round borders and no arrows.
 */
public class RoundComboBoxUI extends NoArrowComboBoxUI {

	private final static int ROUNDNESS = 24;
	private final static int SPACER = 5;
	
	@Override
    public void update(Graphics g, JComponent c) {

		//Draw round rectangle with background color and thin black border
        g.setColor(c.getBackground());
        g.fillRoundRect(0, 0, c.getWidth(),c.getHeight(), ROUNDNESS, ROUNDNESS);
        
        g.setColor(Color.BLACK);
        g.drawRoundRect(0, 0, c.getWidth()-1 , c.getHeight()-1, ROUNDNESS, ROUNDNESS);

        paint(g, c);
    }
	
	protected void installDefaults() {
        super.installDefaults();
        // Add an empty border to make room for the rounded one we'll draw 
        comboBox.setBorder(BorderFactory.createEmptyBorder(SPACER, SPACER, SPACER, SPACER)); 
    }
 
    

}
