package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import util.SceneManager;

public abstract class BaseMenuController {
	
	// Attributes

	// Buttons
	@FXML
	private Button buttonReport;
		
	@FXML
	private Button buttonProjection;
		
	@FXML
	private Button buttonGraphics;
	
	@FXML
	private Button buttonSignOut;
	
	// Constructor
	public BaseMenuController() {
	}
	
	// Methods
	
	// Iinitialize Menu
	protected void initializeMenu() {
		initializeReportButton();
		initializeProyectionButton();
		initializeGraphicsButton();
		initializeSignOutButton();
	}
	
	private void initializeReportButton() {
		// Report
		this.buttonReport.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SceneManager.changeScene(event, "Report.fxml", "Report", 1000, 700);
			}
		});
	}
	
	private void initializeGraphicsButton() {
		// Graphics
		this.buttonGraphics.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SceneManager.changeScene(event, "Graphic.fxml", "Graphic", 900, 700);			
			}
		});
	}
	
	private void initializeSignOutButton() {
		// Sign Out
		this.buttonSignOut.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SceneManager.changeScene(event, "Login.fxml", "Login", 500, 600);
			}
		});
	}
	
	private void initializeProyectionButton() {
		// Proyection
		this.buttonProjection.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SceneManager.changeScene(event, "Projection.fxml", "Proyection", 900, 700);
			}
		});
	}
}
