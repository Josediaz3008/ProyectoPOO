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
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import model.Income;
import util.SceneManager;

public class ReportAddIncomeController implements Initializable{
	
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
		private Button buttonAddIncome;
		
		// Textfields
		@FXML
		private TextField tfIncomeName;
		
		@FXML
		private TextField tfIncomeAmount;
		
		// Text Area
		@FXML
		private TextArea taIncomeDescription;

		@Override
		public void initialize(URL url, ResourceBundle resourceBundle) {
			
			// Initialize Back Button
			initializeBackButton();
			
			// Initialize Text Field Amount
			initializeTextFieldAmount();
			
			// Initialize Add Expense Button
			initializeAddIncomeButton();
			
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
		
		private void initializeAddIncomeButton() {
			this.buttonAddIncome.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					double amount = 0;
					String name = tfIncomeName.getText().trim();
					String description = taIncomeDescription.getText().trim();
					
					try {
						amount = Double.parseDouble(tfIncomeAmount.getText());
					} catch (NumberFormatException e) {
						amount = 0;
					}
					
					if (incomeValidation(name, description, amount)) {
						ReportDAO reportDAO = new ReportDAO();
						reportDAO.addIncome(new Income(name, description, amount));
						SceneManager.createAlert(AlertType.INFORMATION, "Add Income", "Income create Succesfully");
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
		        this.tfIncomeAmount.setTextFormatter(textFormatter);
		}
		
		private boolean isValidDoubleInput(String input) {
	        String decimalPattern = "([0-9]*\\.?[0-9]{0,2})";
	        return Pattern.matches(decimalPattern, input);
	    }
		
		private boolean incomeValidation(String name, String description, double amount) {
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
			this.tfIncomeName.setText("");
			this.taIncomeDescription.setText("");
			this.tfIncomeAmount.setText("");
		}
	}
