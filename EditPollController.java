package application;
/* This controller is used in conjunction with the EditPollTester class and the FXML file to control the GUI created that 
 * allows the user to update the names of polls in a poll list, and allows the user the change the projected percentage of votes
 * won and projected number of seats won in the election for the parties in each poll in the poll list.
 * 
 * @author Scott Salmon
 * @UCID 30093320
 * @since 2021-12-05
 * 
 */

import model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
//import javafx.scene.input.KeyEvent;
//import javafx.collections.FXCollections;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class EditPollController {
	
	//this creates a default poll list that is populated with parties and default values from the Factory class.
	Factory p = Factory.getInstance();
	PollList newPollList = p.createEmptyPolls(); 
	
	//this creates a new pollName ArrayList that is used in later methods.
	ArrayList<String> pollNames = new ArrayList<String>();
	
	@FXML
    private TextField textfieldPollName;	
	@FXML
    private TextArea textfieldNumOfSeats;
	@FXML
    private TextArea textfieldPercentageOfVote;
    @FXML
    private Button updatePartyButton;
    @FXML
    private Button clearButton;
    @FXML
    private Button updatePollNameButton;
    @FXML
    private ChoiceBox<String> pollEditBox;
    @FXML
    private ChoiceBox<Party> partyToUpdateChoiceBox;
    @FXML
    private Label totalNumberOfSeatsLabel;

    @FXML
    /* This method is what first runs in the program, it initializes right when the controller is called.
     * 
     * The first thing this method does is set the label that shows the # of seats in the election. Next, it adds all
     * of the poll names to a new ArrayList<String> and then places this ArrayList in the Poll Name Choice Box. After this,
     * there is a listener object that is always waiting for the choice of poll in the Poll Name Choice Box to change. When it changes,
     * the parties listed in the Party Name Choice Box are changed to the correct parties for the selected Poll.
     *
     */
    void initialize() {
   
    	totalNumberOfSeatsLabel.setText(" /" + newPollList.getNumOfSeats());		//this sets label with # of seats
    	
    	for (int i = 0; i< newPollList.toArray().length;i++) {						//this sets initial poll names
    		pollNames.add(newPollList.toArray()[i].getPollName());
    	}
    	pollEditBox.setItems(FXCollections.observableArrayList(pollNames));			
    	
    	//this continually updates the parties shown in the party update choice box, depending on the poll selected
    	pollEditBox.getSelectionModel().selectedIndexProperty().addListener( 
    		     new ChangeListener<Number>() { 
    		          public void changed(ObservableValue observable, Number oldValue, Number newValue) { 
    		        	  int index = newValue.intValue();  
    		              if (index >= 0)  {
    		            	  updatePartyChoiceBox();
    		              }
    		               }}); 
    }
	
    @FXML
    /* This method shows what happens when the "Update Poll Name" Button is clicked. If a Poll is selected and valid input is input,
     * the method makes a new ArrayList<String> and copies every poll name from the original ArrayList<String> that has all of the Poll names.
     * Next it substitutes the new name that is in the poll name text field for the poll that is currently selected in the Poll name choice box.
     * Then it places this new list into the Poll Name Choice Box.
     * 
     * @param ActionEvent event this event is a button press on the GUI. Whenever the button is pressed, the method is called.
     */
    void updatePollNameButtonClicked(ActionEvent event) {
//    	System.out.println("updatePollNameButton clicked");
    	int y = pollEditBox.getSelectionModel().getSelectedIndex();
    	int z = partyToUpdateChoiceBox.getSelectionModel().getSelectedIndex();
    	
    	//this if statement creates an ArrayList with the updated new name substituted in for the old one
    	if (y>=0 && (textfieldPollName.getText().length() != 0)) {
    		newPollList.toArray()[y].setPollName(textfieldPollName.getText());
    		ArrayList<String> newPollNames = new ArrayList<String>();
    		for (int i = 0; i< newPollList.toArray().length;i++) {
        		newPollNames.add(newPollList.toArray()[i].getPollName());
        	}
    		pollEditBox.setItems(FXCollections.observableArrayList(newPollNames));
    		pollEditBox.getSelectionModel().select(y);
    		partyToUpdateChoiceBox.getSelectionModel().select(z);
    		textfieldPollName.setText(null);
    	}
    	
    }
    /* This method shows what happens when the "Update Party" button is clicked. First it checks and makes sure the input for both
     * the projected seat amount and projected percentage is valid. 
     * 
     * If the input is valid, the projected seats and/or projected percentage for the party is changed in the poll in the poll list.
     * If an input is invalid, the selected text field is set to null and the specified value for the party does not change. 
     * 
     * Then the final version of the Poll in the PollList is assigned to the Party Name Choice Box
     * 
     * @param ActionEvent event This event is a button press on the GUI. Whenever the button is pressed, the method is called.
     */
    @FXML
    void updatePartyButtonClicked(ActionEvent event) {
    	
    	int y = pollEditBox.getSelectionModel().getSelectedIndex();
    	int z = partyToUpdateChoiceBox.getSelectionModel().getSelectedIndex();
    	
    	//this if statement sets the projected number of seats if the input is in the correct range/type
    	if(isNum(textfieldNumOfSeats.getText()) == true && textfieldNumOfSeats.getText().length() > 0 &&
    	   Integer.parseInt(textfieldNumOfSeats.getText()) >=0 && (z != -1) &&
	       Integer.parseInt(textfieldNumOfSeats.getText()) <= newPollList.getNumOfSeats()) 
	      {
	         newPollList.toArray()[y].getParties()[z].setProjectedNumberOfSeats(
	         Integer.parseInt(textfieldNumOfSeats.getText()));
	      } else {
	         textfieldNumOfSeats.setText(null); 
	         }	
    	
    	//this if statement sets the projected percentage of votes if the input is in the correct range
    	if(isFloat(textfieldPercentageOfVote.getText()) == true && textfieldPercentageOfVote.getText().length() > 0 &&
    	   Float.parseFloat(textfieldPercentageOfVote.getText())>=0.0f && (z != -1) &&
    	   Float.parseFloat(textfieldPercentageOfVote.getText())<=100.0f) {
    			newPollList.toArray()[y].getParties()[z].setProjectedPercentageOfVotes(
        		Float.parseFloat(textfieldPercentageOfVote.getText()));
    	 } else {
    		textfieldPercentageOfVote.setText(null);
    	 	}
    	
    		updatePartyChoiceBox();
    	
    	//this if statement triggers a change for the listener in the initialize method to ensure the GUI is always updated
    	//it then puts the poll selection back to what it was originally
    	if ((y+1) < newPollList.toArray().length) {
    		pollEditBox.getSelectionModel().select(y+1);
        	pollEditBox.getSelectionModel().select(y);
    	} else {
    		pollEditBox.getSelectionModel().select(0);
        	pollEditBox.getSelectionModel().select(y);
    	}
    	partyToUpdateChoiceBox.getSelectionModel().select(z);
    }
    
    @FXML
    /* This method shows what happens when the "Click" button is pressed. It sets all of the text in the
     * text fields to null.
     * 
     * @param ActionEvent event This event is a button press on the GUI. Whenever the button is pressed, the method is called.
     */
    void clearButtonClicked(ActionEvent event) {
    	textfieldPollName.setText(null);
    	textfieldPercentageOfVote.setText(null);
    	textfieldNumOfSeats.setText(null);
    }
    
 /* This is a private method that updates the Party Name Choice Box. It finds the Poll that we changed the projected values
  * for the party in, and then sets the Party Choice Box to a list of the now updated parties in the Poll. This method is called
  * in the initialize() method, and the updatePartyButtonClicked(ActionEvent) method.
  * 
  */
  	private void updatePartyChoiceBox() {
  		int index = pollEditBox.getSelectionModel().getSelectedIndex();
  		partyToUpdateChoiceBox.setItems(FXCollections.observableArrayList(newPollList.toArray()[index].getParties()));
  	}
  	
 /* This is a private method that checks if a String can be turned into a valid integer or not. This design for this method is inspired by
  * an online discussion I found here: https://stackoverflow.com/questions/14206768/how-to-check-if-a-string-is-numeric
  * 
  * @param String input - this is the string that you want to test if it's an integer or not.
  */
	private boolean isNum(String input) {
		
	    boolean x = true;
	    try {
	        Integer.parseInt(input);
	    }catch (NumberFormatException e) {
	        x = false;
	    }
	    return x;
	}
	
	 /* This is a private method that checks if a String can be turned into a valid float or not. This design for this method is inspired by
	  * an online discussion I found here: https://stackoverflow.com/questions/14206768/how-to-check-if-a-string-is-numeric
	  * 
	  * @param String input - this is the string that you want to test if it's an float or not.
	  */
		private boolean isFloat(String input) {
			
		    boolean x = true;
		    try {
		        Float.parseFloat(input);
		    }catch (NumberFormatException e) {
		        x = false;
		    }
		    return x;
		}
}