package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import util.SceneManager;

public class GraphicController extends BaseMenuController implements Initializable{
	
	// Attributes
	
	// Buttons 
	@FXML
	private Button buttonExpenses;
	
	@FXML
	private Button buttonIncomes;
	
	@FXML
	private Button buttonBalance;
	
	// Constructor
	
	public GraphicController() {
		super();
	}
	
	// Methods
	
	// Initialize
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		
		initializeMenu();
		
		
		// Graphic Buttons
		this.buttonExpenses.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SceneManager.changeScene(event, "GraphicExpense.fxml", "Expense Graphic", 900, 700);
			}
		});
		
		this.buttonIncomes.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SceneManager.changeScene(event, "GraphicIncome.fxml", "Income Graphic", 900, 700);
			}
		});
		
		this.buttonBalance.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SceneManager.changeScene(event, "GraphicBalance.fxml", "Balance Graphic", 900, 700);
			}
		});
	}

}
