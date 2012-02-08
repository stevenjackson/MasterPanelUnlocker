package project.leandog.colorcombination.ui;

import java.awt.Color;

/**
 * ComboBox for showing colors.  This CombBox has rounded corners.
 */
@SuppressWarnings("serial")
public class RoundColorComboBox extends ColorComboBox {

	public RoundColorComboBox(Color[] colorModel){
		super(colorModel);
		setUI(new RoundComboBoxUI());
	}
	
}
