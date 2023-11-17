package util;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Node;

public abstract class SceneManager {
	
	public static void changeScene(ActionEvent event, String fxmlFile, String title, int width, int height) {
		Parent root = null;
		
		try {
			String pathToFXML = "/view/" + fxmlFile;
			root = FXMLLoader.load(SceneManager.class.getResource(pathToFXML));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle(title);
		stage.setScene(new Scene(root, width, height));		
		stage.show();
	}
	
	public static void createAlert(AlertType alertType, String titleAlert, String textAlert) {
		Alert alert = new Alert(alertType);
		alert.setTitle(titleAlert);
		alert.setHeaderText(textAlert);
		Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
		Image image = new Image("file:resources/Logo.png");
		alertStage.getIcons().add(image);
		alert.show();
	}
	
}