package controller;

import java.net.URL;
import java.util.ResourceBundle;

import DAO.ReportDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import model.AppModel;
import model.Expense;
import util.SceneManager;

public class ReportEditExpenseController extends BaseExtendedReportController implements Initializable{
	
	// Attributes
	
	@FXML
	private Button buttonEditExpense;
	
	// Constructor
	public ReportEditExpenseController() {
		super();
	}

	// Methods
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		
		initializeMenu();
		
		initializeBackButton();
		
		initializeTextFieldAmount();
		
		setTextFieldsInformation();
		
		initializeEditExpenseButton();
	
	}
	
	private void initializeEditExpenseButton() {
		this.buttonEditExpense.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				double amount;
				String name = tfName.getText();
				String description = taDescription.getText();
				try {
					amount = Double.parseDouble(tfAmount.getText());
				} catch(NumberFormatException e) {
					amount = 0;
					SceneManager.createAlert(AlertType.ERROR, "Amount Error", "Amount is not a valid number");
				}
				
				if(fieldsValidation(name, description, amount)) {
					ReportDAO reportDAO = new ReportDAO();
					
					if(reportDAO.editExpense(AppModel.getLastExpense(), new Expense(name, description, amount, AppModel.getLastExpense().getDate()))) {
						SceneManager.createAlert(AlertType.INFORMATION, "Edit Expense", "The selected Expense has been edit");
					} else {
						SceneManager.createAlert(AlertType.ERROR, "Edit Expense", "An error has ocurred, please try again later");
					}
				}	
			}
		});
	}
	
	private void setTextFieldsInformation() {
		this.tfName.setText(AppModel.getLastExpense().getName());
		this.taDescription.setText(AppModel.getLastExpense().getDescription());
		this.tfAmount.setText(String.valueOf(AppModel.getLastExpense().getAmount()));
	}

}
