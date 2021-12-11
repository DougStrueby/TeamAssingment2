package application;

import java.io.FileInputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class EditPollTester extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			
			FXMLLoader loader = new FXMLLoader();
			
	//		System.out.println("About to load 'EditPollView' FXML file");
			root = (BorderPane)loader.load(
					new FileInputStream("src/application/EditPollView.fxml"));
	//		System.out.println("Finished loading 'EditPollView' FXML file");
			
			Scene scene = new Scene(root,525,425);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}
}