package controller;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import util.SceneManager;

public abstract class BaseExtendedReportController extends BaseMenuController {

	// Buttons
	@FXML
	protected Button buttonBack;
		
	// Textfields
	@FXML
	protected TextField tfName;
		
	@FXML
	protected TextField tfAmount;
		
	// Text Area
	@FXML
	protected TextArea taDescription;
	
	// ----------------------------------------------------------- Methods ----------------------------------------------------------------------
	
	protected void initializeBackButton() {
		this.buttonBack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SceneManager.changeScene(event, "Report.fxml", "Report", 1000, 700);
			}
		});
	}
	
	protected void initializeTextFieldAmount() {
		 UnaryOperator<TextFormatter.Change> filter = change -> {
	            String newText = change.getControlNewText();
	            if (isValidDoubleInput(newText)) {
	                return change;
	            } else {
	                return null; 
	            }
	        };

	        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
	        this.tfAmount.setTextFormatter(textFormatter);
	}
	
	private boolean isValidDoubleInput(String input) {
       String decimalPattern = "([0-9]*\\.?[0-9]{0,2})";
       return Pattern.matches(decimalPattern, input);
    }
	
	protected boolean fieldsValidation(String name, String description, double amount) {
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
	
	protected void resetFields() {
		this.tfName.setText("");
		this.taDescription.setText("");
		this.tfAmount.setText("");
	}
}
