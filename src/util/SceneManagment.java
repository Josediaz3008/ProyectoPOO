package util;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class SceneManagment {
	
	public static void ChangeScene(ActionEvent event, String fxmlFile, String title) {
		Parent root = null;
		
		try {
			String pathToFXML = "/view/" + fxmlFile;
			root = FXMLLoader.load(SceneManagment.class.getResource(pathToFXML));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle(title);
		stage.setScene(new Scene(root, 500, 600));
		stage.show();
	}
}