package project.leandog.colorcombination.ui;

import java.awt.Color;

import javax.swing.JComboBox;

/**
 * ComboBox for showing colors.
 */
@SuppressWarnings("serial")
public class ColorComboBox extends JComboBox {

	public ColorComboBox(Color[] colorModel){
		super(colorModel);
		setRenderer(new ColorRenderer(this));
		setUI(new NoArrowComboBoxUI());
		setOpaque(false);
	}

	public Color getSelectedColor() {
		return (Color) getSelectedItem();
	}

}
