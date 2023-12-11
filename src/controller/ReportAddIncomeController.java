package controller;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import DAO.ReportDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import model.Income;
import util.SceneManager;

public class ReportAddIncomeController extends BaseExtendedReportController implements Initializable{
		
		@FXML
		private Button buttonAddIncome;

		@Override
		public void initialize(URL url, ResourceBundle resourceBundle) {
			
			initializeMenu();
			
			// Initialize Back Button
			initializeBackButton();
			
			// Initialize Text Field Amount
			initializeTextFieldAmount();
			
			// Initialize Add Expense Button
			initializeAddIncomeButton();
			
		}
		
		// ------------------------------------------------ Methods --------------------------------------------------------------------
		
		private void initializeAddIncomeButton() {
			this.buttonAddIncome.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					double amount;
					String name = tfName.getText().trim();
					String description = taDescription.getText().trim();
					
					try {
						amount = Double.parseDouble(tfAmount.getText());
					} catch (NumberFormatException e) {
						SceneManager.createAlert(AlertType.ERROR, "Amount Error", "Amount is not a valid number");
						amount = 0;
					}
					
					if (fieldsValidation(name, description, amount)) {
						ReportDAO reportDAO = new ReportDAO();
						Date date = java.sql.Date.valueOf(LocalDate.now());
						reportDAO.addIncome(new Income(name, description, amount, date));
						SceneManager.createAlert(AlertType.INFORMATION, "Add Income", "Income create Succesfully");
					}
					
					resetFields();
				}
			});
		}
	}
