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
import model.Income;
import util.SceneManager;

public class ReportEditIncomeController extends BaseExtendedReportController implements Initializable{
	
	// Attributes
	@FXML
	private Button buttonEditIncome;

	// Constructor
	public ReportEditIncomeController() {
		super();
	}
	
	// Methods
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		
		initializeMenu();
		
		initializeBackButton();
		
		initializeTextFieldAmount();
		
		setTextFieldsInformation();
		
		initializeEditIncomeButton();
	
	}
	
	private void initializeEditIncomeButton() {
		this.buttonEditIncome.setOnAction(new EventHandler<ActionEvent>() {
			
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
					
					if(reportDAO.editIncome(AppModel.getLastIncome(), new Income(name, description, amount, AppModel.getLastIncome().getDate()))) {
						SceneManager.createAlert(AlertType.INFORMATION, "Edit Income", "The selected Income has been edit");
					} else {
						SceneManager.createAlert(AlertType.ERROR, "Edit Income", "An error has ocurred, please try again later");
					}
				}	
			}
		});
	}
	
	private void setTextFieldsInformation() {
		this.tfName.setText(AppModel.getLastIncome().getName());
		this.taDescription.setText(AppModel.getLastIncome().getDescription());
		this.tfAmount.setText(String.valueOf(AppModel.getLastIncome().getAmount()));
	}

}
