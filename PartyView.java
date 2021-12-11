package application;

/**
 * <h1>PartyView class</h1>
 * This class Creates the new window that is generated when 
 * the submit button is pressed and the widgets in this window 
 * will set the names of the parties
 * 
 * @NolanRuzicki
 * @UCID 30132405
 * 
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PartyView extends Application {

	int numOfParties;

	/**
	 * This constructor sets the number of parties in the elections and will be used
	 * to set the number of widgets
	 * 
	 * @param numOfParties
	 */
	PartyView(int numOfParties) {
		this.numOfParties = numOfParties;
	}

	/**
	 * This method loads the window
	 */
	@Override
	public void start(Stage primaryStage) {

		VBox root = new VBox();

		HBox upperBox = new HBox();
		Button submit = new Button("Submit Party info");

		upperBox.getChildren().add(submit);
		root.getChildren().add(upperBox);

		HBox lowerBox = new HBox();
		VBox LabelBox = new VBox();
		for (int i = 0; i < numOfParties; i++) {
			Label newParties = new Label("Name of party ");
			LabelBox.setSpacing(10);
			LabelBox.getChildren().add(newParties);
		}

		lowerBox.setSpacing(5);
		lowerBox.getChildren().add(LabelBox);

		VBox TextFieldBox = new VBox();
		TextField[] listOfFields = new TextField[numOfParties];
		for (int i = 0; i < numOfParties; i++) {
			TextField newField = new TextField();
			listOfFields[i] = newField;
			TextFieldBox.getChildren().add(newField);
		}

		lowerBox.getChildren().add(TextFieldBox);

		VBox ColorPickerBox = new VBox();
		ColorPicker[] listOfColors = new ColorPicker[numOfParties];
		for (int i = 0; i < numOfParties; i++) {
			ColorPicker newColor = new ColorPicker();
			listOfColors[i] = newColor;
			ColorPickerBox.getChildren().add(newColor);

		}

		lowerBox.getChildren().add(ColorPickerBox);

		root.getChildren().add(lowerBox);
		SubmitHandler handler = new SubmitHandler();
		handler.setColor(listOfColors);
		handler.setNames(listOfFields);
		submit.setOnAction(handler);

		Scene scene = new Scene(root, 500, 200);

		primaryStage.setScene(scene);
		primaryStage.show();

	}

}
