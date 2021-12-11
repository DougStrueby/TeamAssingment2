package application;

/**
 * <h1>SubmitHandler class</h1>
 * This class handles what should happen the the button called submit is pressed
 * 
 * @author Nolan Ruzicki
 * @UCID 30132405
 */

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import model.Factory;

public class SubmitHandler implements EventHandler<ActionEvent> {

	private TextField[] names;
	private ColorPicker[] colors;

	/**
	 * This method sets the list of party names in this class to be the same as
	 * those in the "PartyView" class
	 * 
	 * @param theNames
	 */
	public void setNames(TextField[] theNames) {
		this.names = theNames;
	}

	/**
	 * This method sets the list of party colors in this class to be the same as
	 * those in the "PartyView" class
	 * 
	 * @param theColors
	 */
	public void setColor(ColorPicker[] theColors) {
		this.colors = theColors;
	}

	/**
	 * This method handles when the "submit parties info" button is pressed which
	 * will set the names and colors of the parties and update them in the factory
	 * class
	 * 
	 */
	@Override
	public void handle(ActionEvent event) {
		String[] newNames = new String[names.length];
		Color[] newColors = new Color[names.length];
		for (int i = 0; i < names.length; i++) {
			newNames[i] = names[i].getText();
			newColors[i] = colors[i].getValue();

		}

		Factory.getInstance().setPartyIdentifiers(newNames, newColors);
		System.out.println(Factory.getInstance().toString());

	}

}
