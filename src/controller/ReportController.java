package controller;

import java.net.URL;
import java.util.ResourceBundle;
import DAO.ReportDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Expense;
import model.Income;
import util.SceneManager;

public class ReportController implements Initializable{
	
	// Menu Buttons
	@FXML
	private Button buttonReport;
	
	@FXML
	private Button buttonProyection;
	
	@FXML
	private Button buttonGraphics;
	
	// -------------------------------------- EXPENSES ------------------------------------------------
	
	// Table
	@FXML
	private TableView<Expense> tbExpenses;
	
	@FXML
	private TableColumn<Expense, Integer> expenseId;
	
	@FXML
	private TableColumn<Expense, String> expenseName;
	
	@FXML
	private TableColumn<Expense, String> expenseDescription;
	
	@FXML
	private TableColumn<Expense, Double> expenseAmount;
	
	@FXML
	private TableColumn<Expense, Void> expenseBtn;
	
	// Observable List
	private ObservableList<Expense> expensesList;
	
	// Buttons
	@FXML
	private Button btnAddExpense;
	
	// Labels
	@FXML
	private Label labelTotalExpenses;
	
	// -------------------------------------- INCOMES ----------------------------------------------------
	
	// Table
	@FXML
	private TableView<Income> tbIncomes;
	
	@FXML
	private TableColumn<Income, Integer> incomeId;
	
	@FXML
	private TableColumn<Income, String> incomeName;
	
	@FXML
	private TableColumn<Income, String> incomeDescription;
	
	@FXML
	private TableColumn<Income, Double> incomeAmount;
	
	@FXML
	private TableColumn<Income, Void> incomeBtn;
	
	// Observable List
	private ObservableList<Income> incomesList;
	
	// Buttons
	@FXML
	private Button btnAddIncome;
	
	// Label
	@FXML
	private Label labelTotalIncomes;
	
	// Initilalize

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		updateExpensesList();
		updateIncomesList();
		
		// ----------------------------------- Expenses ----------------------------------------------------
		
		configureExpensesTable();
		
		// Center Table Cells Info
		
		setStyleExpensesTable();
		
	    // Generate Delete Button <Expense>
		
		generateExpensesTableDeleteButton();
		
		// ADD EXPENSE BUTTON
		
		this.btnAddExpense.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				SceneManager.changeScene(event, "report-add-expenses.fxml", "Add Expense", 900, 700);
				
			}
		});
		
		// ----------------------------------------------- Incomes ------------------------------------------------------------
		configureIncomesTable();
		
		// Center Table Cells Info
		
	    setStyleIncomesTable();
	    
		// Generate Delete Button <Income>
		
	    generateIncomesTableDeleteButton();
		
		// Set Items to Table
		this.tbExpenses.setItems(this.expensesList);
		this.tbIncomes.setItems(this.incomesList);
		
		// Set Total Expenses and Incomes
		setTotalExpenses();
		setTotalIncomes();
		
		// ADD INCOME BUTTON
		
		this.btnAddIncome.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				SceneManager.changeScene(event, "report-add-incomes.fxml", "Add Income", 900, 700);
				
			}
		});
	}
	
	// --------------------------------------------------- Methods ------------------------------------------------------------------------------
	
	// -------------------------------------------------- Expenses -------------------------------------------------------------------
	
	private void configureExpensesTable() {
		this.expenseId.setCellValueFactory(new PropertyValueFactory<Expense, Integer>("Id"));
		this.expenseName.setCellValueFactory(new PropertyValueFactory<Expense, String>("Name"));
		this.expenseDescription.setCellValueFactory(new PropertyValueFactory<Expense, String>("Description"));
		this.expenseAmount.setCellValueFactory(new PropertyValueFactory<Expense, Double>("Amount"));
	}
	
	
	private void setStyleExpensesTable() {
		this.expenseId.setStyle("-fx-alignment: CENTER;");
		this.expenseName.setStyle("-fx-alignment: CENTER;");
	    this.expenseDescription.setStyle("-fx-alignment: CENTER;");
	    this.expenseAmount.setStyle("-fx-alignment: CENTER;");
	    this.expenseBtn.setStyle("-fx-alignment: CENTER;");
		
	}
	
	private void generateExpensesTableDeleteButton() {
		this.expenseBtn.setCellFactory(column -> new TableCell<>() {
			private final Button deleteButton = new Button("Delete");
			
			{
				deleteButton.setStyle("-fx-background-color: #FF0000; -fx-cursor: hand");
				
				deleteButton.setOnAction(event ->{
					SceneManager.createAlert(AlertType.INFORMATION, "Delete Expense", "The selected Expense has been deleted");
					Expense expense = getTableView().getItems().get(getIndex());
					ReportDAO reportDAO = new ReportDAO();
					reportDAO.deleteExpense(expense);
					getTableView().getItems().remove(getIndex());
					updateExpensesList();
					setTotalExpenses();
					
				});
			}
			
			@Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
		});
	}
	
	private void updateExpensesList() {
		ReportDAO reportDAO = new ReportDAO();
		this.expensesList = FXCollections.observableList(reportDAO.getAllExpenses());
	}
	
	
	private void setTotalExpenses() {
		double total = 0;
		for (Expense expense : this.expensesList) {
			total += expense.getAmount();
		}
		this.labelTotalExpenses.setText("$ " + total);
	}
	
	
	// --------------------------------------------------- Incomes -------------------------------------------------------------------
	
	private void configureIncomesTable() {
		this.incomeId.setCellValueFactory(new PropertyValueFactory<Income, Integer>("Id"));
		this.incomeName.setCellValueFactory(new PropertyValueFactory<Income, String>("Name"));
		this.incomeDescription.setCellValueFactory(new PropertyValueFactory<Income, String>("Description"));
		this.incomeAmount.setCellValueFactory(new PropertyValueFactory<Income, Double>("Amount"));
	}
	
	private void setStyleIncomesTable() {
		this.incomeId.setStyle("-fx-alignment: CENTER;");
		this.incomeName.setStyle("-fx-alignment: CENTER;");
	    this.incomeDescription.setStyle("-fx-alignment: CENTER;");
	    this.incomeAmount.setStyle("-fx-alignment: CENTER;");
	    this.incomeBtn.setStyle("-fx-alignment: CENTER;");
	}
	
	private void generateIncomesTableDeleteButton() {
		this.incomeBtn.setCellFactory(column -> new TableCell<>() {
			private final Button deleteButton = new Button("Delete");
			
			{
				deleteButton.setStyle("-fx-background-color: #FF0000; -fx-cursor: hand");
				
				deleteButton.setOnAction(event ->{
					SceneManager.createAlert(AlertType.INFORMATION, "Delete Income", "The selected Income has been deleted");
					Income income = getTableView().getItems().get(getIndex());
					ReportDAO reportDAO = new ReportDAO();
					reportDAO.deleteIncome(income);
					getTableView().getItems().remove(getIndex());
					updateIncomesList();
					setTotalIncomes();
				});
			}
			
			@Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
		});
		
	}
	
	private void updateIncomesList() {
		ReportDAO reportDAO = new ReportDAO();
		this.incomesList = FXCollections.observableList(reportDAO.getAllIncomes());
	}
	
	
	private void setTotalIncomes() {
		double total = 0;
		for (Income income : this.incomesList) {
			total += income.getAmount();
		}
		this.labelTotalIncomes.setText("$ " + total);
	}
}
