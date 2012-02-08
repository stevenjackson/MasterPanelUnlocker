package project.leandog.colorcombination.ui;

import javax.swing.JButton;
import javax.swing.plaf.basic.BasicComboBoxUI;

/**
 * ComboBoxUI for hiding the arrow.
 */
public class NoArrowComboBoxUI extends BasicComboBoxUI {

	 @SuppressWarnings("serial")
	protected JButton createArrowButton() {
		 return new JButton() {
	            public int getWidth() {
	                return 0;
	            }
	        };
	 }
}
