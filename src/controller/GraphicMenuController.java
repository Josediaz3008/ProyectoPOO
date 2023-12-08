package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import util.SceneManager;

public class GraphicMenuController implements Initializable{

	// Menu Buttons
	@FXML
	private Button buttonReport;
		
	@FXML
	private Button buttonProyection;
		
	@FXML
	private Button buttonGraphics;
	
	// Buttons 
	@FXML
	private Button buttonExpenses;
	
	@FXML
	private Button buttonIncomes;
	
	@FXML
	private Button buttonBalance;
	
	// Initialize
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		
		// -------------------- Initialize Menu Buttons ---------------------------------
		
		// Report
		this.buttonReport.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SceneManager.changeScene(event, "report.fxml", "Report", 900, 700);
			}
		});
		
		
		// Graphic Buttons
		this.buttonExpenses.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SceneManager.changeScene(event, "graphics-expenses.fxml", "Expense Graphic", 900, 700);
			}
		});
		
		this.buttonIncomes.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SceneManager.changeScene(event, "graphics-incomes.fxml", "Income Graphic", 900, 700);
			}
		});
		
		this.buttonBalance.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SceneManager.changeScene(event, "graphics-balance.fxml", "Balance Graphic", 900, 700);
			}
		});
	}

}
