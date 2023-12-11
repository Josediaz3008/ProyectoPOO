package controller;

import java.net.URL;
import java.time.LocalDate;
import java.sql.Date;
import java.util.ResourceBundle;
import DAO.ReportDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import model.Expense;
import util.SceneManager;

public class ReportAddExpenseController extends BaseExtendedReportController implements Initializable {
	
	// Attributes
	
	@FXML
	private Button buttonAddExpense;
	
	// Constructor
	public ReportAddExpenseController() {
		super();
	}

	// ------------------------------------------------ Methods --------------------------------------------------------------------
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		
		initializeMenu();
		
		// Initialize Back Button
		initializeBackButton();
		
		// Initialize Text Field Amount
		initializeTextFieldAmount();
		
		// Initialize Add Expense Button
		initializeAddExpenseButton();
		
	}
	
	private void initializeAddExpenseButton() {
		this.buttonAddExpense.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				double amount;
				String name = tfName.getText().trim();
				String description = taDescription.getText().trim();
				
				try {
					amount = Double.parseDouble(tfAmount.getText());
				} catch (NumberFormatException e) {
					amount = 0;
					SceneManager.createAlert(AlertType.ERROR, "Amount Error", "Amount is not a valid number");
				}
				
				if (fieldsValidation(name, description, amount)) {
					ReportDAO reportDAO = new ReportDAO();
					Date date = java.sql.Date.valueOf(LocalDate.now());
					if (reportDAO.addExpense(new Expense(name, description, amount, date))) {
						SceneManager.createAlert(AlertType.INFORMATION, "Add Expense", "Expense create Succesfully");
					} else {
						SceneManager.createAlert(AlertType.ERROR, "Add Expense", "Error in Expense Creation");
					}
				}
				
				resetFields();
			}
		});
	}
	
}
