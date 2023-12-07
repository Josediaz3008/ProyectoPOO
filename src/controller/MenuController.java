package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.AppModel;
import util.SceneManager;

public class MenuController implements Initializable{

	// buttons
	@FXML
	private Button buttonReport;
	
	@FXML
	private Button buttonProyection;
	
	@FXML
	private Button buttonGraphics;
	
	// Label
	@FXML
	private Label labelWelcome;
	
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		this.buttonReport.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SceneManager.changeScene(event, "report.fxml", "Report", 900, 700);
			}
		});
		
		this.labelWelcome.setText("Welcome Back " + AppModel.getCurrentUser() + "!");
	}

}
