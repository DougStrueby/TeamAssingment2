package application;

/**
 * <h1>PartyView class</h1>
* This class controls what happens the 
* an action is performed of a widget 
* 
* @NolanRuzicki
* @UCID 30132405
*/

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Factory;

public class SetupController {

	@FXML
	private TextField textFieldPolls;

	@FXML
	private Label labelSeats;

	@FXML
	private TextField textFieldParties;

	@FXML
	private Label labelPolls;

	@FXML
	private Label labelParties;

	@FXML
	private TextField textFieldSeats;

	@FXML
	private Label labelTitle;

	@FXML
	private Button buttonSubmit;

	@FXML
	private Button buttonReset;

	/**
	 * This method handles when the reset button is pressed sets all numbers back to
	 * their default value, and makes all text fields empty
	 * 
	 * @param event
	 */

	@FXML
	void buttonResetClicked(ActionEvent event) {
		textFieldSeats.setText("");
		textFieldParties.setText("");
		textFieldPolls.setText("");
		Factory p = Factory.getInstance();
		p.setNumOfPolls(Factory.DEFAULT_NUMBER_OF_POLLS);
		p.setNumOfSeats(Factory.DEFAULT_NUMBER_OF_SEATS);
	}

	/**
	 * This method handles when the submit button is clicked which should set the
	 * number of seats, parties, and polls in the election. Then is will change the
	 * view to the new window.
	 * 
	 * @param event
	 * @throws Exception
	 */

	@FXML
	void buttonSubmitClicked(ActionEvent event) throws Exception {
		Factory p = Factory.getInstance();
		try {
			int numOfSeats = Integer.parseInt(textFieldSeats.getText());
			int numOfParties = Integer.parseInt(textFieldParties.getText());
			int numOfPolls = Integer.parseInt(textFieldPolls.getText());
			p.setNumOfPolls(numOfPolls);
			p.setNumOfSeats(numOfSeats);

			Application partyView = new PartyView(numOfParties);
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			partyView.start(window);

		} catch (NumberFormatException efe) {
			System.out.println("please input a number");
		}

	}

	/**
	 * 
	 * This method handles when something gets typed in the seats text field
	 * 
	 * @param event
	 */
	@FXML
	void keyTypedInTextFieldSeats(KeyEvent event) {

	}

	/**
	 * 
	 * This method handles when something gets typed in the parties text field
	 * 
	 * @param event
	 */
	@FXML
	void keyTypedInTextFieldParties(KeyEvent event) {

	}

	/**
	 * 
	 * This method handles when something gets typed in the polls text field
	 * 
	 * @param event
	 */
	@FXML
	void keyTypedInTextFieldPolls(KeyEvent event) {

	}

}
