package controller;

import java.net.URL;
import java.util.ResourceBundle;

import DAO.ReportDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Expense;
import model.Income;

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
	
	// Initilalize

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		updateExpensesList();
		updateIncomesList();
		
		// ----------------------------------- Expenses ----------------------------------------------------
		this.expenseId.setCellValueFactory(new PropertyValueFactory<Expense, Integer>("Id"));
		this.expenseName.setCellValueFactory(new PropertyValueFactory<Expense, String>("Name"));
		this.expenseDescription.setCellValueFactory(new PropertyValueFactory<Expense, String>("Description"));
		this.expenseAmount.setCellValueFactory(new PropertyValueFactory<Expense, Double>("Amount"));
		
		// Center Table Cells Info
		this.expenseId.setStyle("-fx-alignment: CENTER;");
		this.expenseName.setStyle("-fx-alignment: CENTER;");
	    this.expenseDescription.setStyle("-fx-alignment: CENTER;");
	    this.expenseAmount.setStyle("-fx-alignment: CENTER;");
	    this.expenseBtn.setStyle("-fx-alignment: CENTER;");
		
	    // Generate Delete Button <Expense>
		this.expenseBtn.setCellFactory(column -> new TableCell<>() {
			private final Button deleteButton = new Button("Delete");
			
			{
				deleteButton.setStyle("-fx-background-color: #FF0000; -fx-cursor: hand");
				
				deleteButton.setOnAction(event ->{
					Expense expense = getTableView().getItems().get(getIndex());
					ReportDAO reportDAO = new ReportDAO();
					reportDAO.deleteExpense(expense);
					
					getTableView().getItems().remove(getIndex());
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
		
		// ----------------------------------------------- Incomes ------------------------------------------------------------
		this.incomeId.setCellValueFactory(new PropertyValueFactory<Income, Integer>("Id"));
		this.incomeName.setCellValueFactory(new PropertyValueFactory<Income, String>("Name"));
		this.incomeDescription.setCellValueFactory(new PropertyValueFactory<Income, String>("Description"));
		this.incomeAmount.setCellValueFactory(new PropertyValueFactory<Income, Double>("Amount"));
		
		// Center Table Cells Info
		this.incomeId.setStyle("-fx-alignment: CENTER;");
		this.incomeName.setStyle("-fx-alignment: CENTER;");
	    this.incomeDescription.setStyle("-fx-alignment: CENTER;");
	    this.incomeAmount.setStyle("-fx-alignment: CENTER;");
	    this.incomeBtn.setStyle("-fx-alignment: CENTER;");
	    
		// Generate Delete Button <Income>
		this.incomeBtn.setCellFactory(column -> new TableCell<>() {
			private final Button deleteButton = new Button("Delete");
			
			{
				deleteButton.setStyle("-fx-background-color: #FF0000; -fx-cursor: hand");
				
				deleteButton.setOnAction(event ->{
					Income expense = getTableView().getItems().get(getIndex());
					ReportDAO reportDAO = new ReportDAO();
					reportDAO.deleteIncome(expense);
					
					getTableView().getItems().remove(getIndex());
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
		
		
		// Set Items to Table
		this.tbExpenses.setItems(this.expensesList);
		this.tbIncomes.setItems(this.incomesList);
	}
	
	// Methods
	
	private void updateExpensesList() {
		ReportDAO reportDAO = new ReportDAO();
		this.expensesList = FXCollections.observableList(reportDAO.getAllExpenses());
	}
	
	private void updateIncomesList() {
		ReportDAO reportDAO = new ReportDAO();
		this.incomesList = FXCollections.observableList(reportDAO.getAllIncomes());
	}
}
