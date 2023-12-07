package controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import DAO.ReportDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import model.Expense;
import util.SceneManager;

public class ReportAddExpenseController implements Initializable{
	
	// Menu Buttos
	@FXML
	private Button buttonReport;
	
	@FXML
	private Button buttonGraphics;
	
	@FXML
	private Button buttonProyection;
	
	// Buttons
	@FXML
	private Button buttonBack;
	
	@FXML
	private Button buttonAddExpense;
	
	// Textfields
	@FXML
	private TextField tfExpenseName;
	
	@FXML
	private TextField tfExpenseAmount;
	
	// Text Area
	@FXML
	private TextArea taExpenseDescription;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		
		// Initialize Back Button
		initializeBackButton();
		
		// Initialize Text Field Amount
		initializeTextFieldAmount();
		
		// Initialize Add Expense Button
		initializeAddExpenseButton();
		
	}
	
	// ------------------------------------------------ Methods --------------------------------------------------------------------
	
	private void initializeBackButton() {
		this.buttonBack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SceneManager.changeScene(event, "report.fxml", "Report", 900, 700);
			}
		});
	}
	
	private void initializeAddExpenseButton() {
		this.buttonAddExpense.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				double amount = 0;
				String name = tfExpenseName.getText().trim();
				String description = taExpenseDescription.getText().trim();
				
				try {
					amount = Double.parseDouble(tfExpenseAmount.getText());
				} catch (NumberFormatException e) {
					amount = 0;
				}
				
				if (expenseValidation(name, description, amount)) {
					ReportDAO reportDAO = new ReportDAO();
					if (reportDAO.addExpense(new Expense(name, description, amount))) {
						SceneManager.createAlert(AlertType.INFORMATION, "Add Expense", "Expense create Succesfully");
					} else {
						SceneManager.createAlert(AlertType.ERROR, "Add Expense", "Error in Expense Creation");
					}
				}
				
				resetTextFields();
			}
		});
	}
	
	private void initializeTextFieldAmount() {
		 UnaryOperator<TextFormatter.Change> filter = change -> {
	            String newText = change.getControlNewText();
	            if (isValidDoubleInput(newText)) {
	                return change;
	            } else {
	                return null; 
	            }
	        };

	        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
	        this.tfExpenseAmount.setTextFormatter(textFormatter);
	}
	
	private boolean isValidDoubleInput(String input) {
        String decimalPattern = "([0-9]*\\.?[0-9]{0,2})";
        return Pattern.matches(decimalPattern, input);
    }
	
	private boolean expenseValidation(String name, String description, double amount) {
		if(name.isEmpty()) {
			SceneManager.createAlert(AlertType.ERROR, "Error", "Name cannot be empty");
			return false;
		}
		
		if(description.isEmpty()) {
			SceneManager.createAlert(AlertType.ERROR, "Error", "Description cannot be empty");
			return false;
		}
		
		if(amount == 0) {
			SceneManager.createAlert(AlertType.ERROR, "Error", "Amount cannot be empty or 0");
			return false;
		}
		
		return true;
	}
	
	private void resetTextFields() {
		this.tfExpenseName.setText("");
		this.taExpenseDescription.setText("");
		this.tfExpenseAmount.setText("");
	}
}
