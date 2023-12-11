package controller;

import java.net.URL;
import java.sql.Date;
import java.util.Comparator;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.AppModel;
import model.Expense;
import model.Income;
import util.SceneManager;

public class ReportController extends BaseMenuController implements Initializable{
	
	// Attributes
	
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
	private TableColumn<Expense, Date> expenseDate;
	
	@FXML
	private TableColumn<Expense, Void> expenseDeleteBtn;
	
	@FXML
	private TableColumn<Expense, Void> expenseEditBtn;
	
	// Observable List
	private ObservableList<Expense> expensesList;
	
	// Buttons
	@FXML
	private Button btnAddExpense;
	
	// Labels
	@FXML
	private Label labelTotalExpenses;
	
	// ChoiceBox
	@FXML
	private ChoiceBox<String> expensesFilter;
	
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
	private TableColumn<Income, Date> incomeDate;
	
	@FXML
	private TableColumn<Income, Void> incomeDeleteBtn;
	
	@FXML
	private TableColumn<Income, Void> incomeEditBtn;
	
	// Observable List
	private ObservableList<Income> incomesList;
	
	// Buttons
	@FXML
	private Button btnAddIncome;
	
	// Label
	@FXML
	private Label labelTotalIncomes;
	
	// ChoiceBox
	@FXML
	private ChoiceBox<String> incomesFilter;
	
	// Constructor
	public ReportController() {
		super();
	}
	
	// Methods
	
	// Initialize

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		
		initializeMenu();
				
		updateExpensesList();
		updateIncomesList();
		
		// ----------------------------------------------- Expenses ------------------------------------------------------------
		
		configureExpensesTable();
		
		// Center Table Cells Info
		
		setStyleExpensesTable();
		
	    // Generate Delete Button <Expense>
		
		generateExpensesTableDeleteButton();
		
		generateExpensesTableEditButton();
		
		// ADD EXPENSE BUTTON
		
		initializeAddExpenseButton();
		
		// Filter
		
		initializeExpensesFilter();
		
		// ----------------------------------------------- Incomes ------------------------------------------------------------
		configureIncomesTable();
		
		// Center Table Cells Info
		
	    setStyleIncomesTable();
	    
		// Generate Delete Button <Income>
		
	    generateIncomesTableDeleteButton();
	    
	    generateIncomesTableEditButton();
		
		// Set Items to Table
		this.tbExpenses.setItems(this.expensesList);
		this.tbIncomes.setItems(this.incomesList);
		
		// Set Total Expenses and Incomes
		setTotalExpenses();
		setTotalIncomes();
		
		// ADD INCOME BUTTON
		
		initializeAddIncomeButton();
		
		// Filter
		
		initializeIncomesFilter();
	}
	
	// --------------------------------------------------- Methods -------------------------------------------------------------------
	
	// -------------------------------------------------- Expenses -------------------------------------------------------------------
	
	private void configureExpensesTable() {
		this.expenseId.setCellValueFactory(new PropertyValueFactory<Expense, Integer>("Id"));
		this.expenseName.setCellValueFactory(new PropertyValueFactory<Expense, String>("Name"));
		this.expenseDescription.setCellValueFactory(new PropertyValueFactory<Expense, String>("Description"));
		this.expenseAmount.setCellValueFactory(new PropertyValueFactory<Expense, Double>("Amount"));
		this.expenseDate.setCellValueFactory(new PropertyValueFactory<Expense, Date>("Date"));
	}
	
	private void setStyleExpensesTable() {
		this.expenseId.setStyle("-fx-alignment: CENTER;");
		this.expenseName.setStyle("-fx-alignment: CENTER;");
	    this.expenseDescription.setStyle("-fx-alignment: CENTER;");
	    this.expenseAmount.setStyle("-fx-alignment: CENTER;");
	    this.expenseDate.setStyle("-fx-alignment: CENTER;");
	    this.expenseDeleteBtn.setStyle("-fx-alignment: CENTER;");
	    this.expenseEditBtn.setStyle("-fx-alignment: CENTER;");
	}
	
	private void initializeAddExpenseButton() {
		this.btnAddExpense.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				SceneManager.changeScene(event, "ReportAddExpense.fxml", "Add Expense", 900, 700);
				
			}
		});
	}
	
	private void generateExpensesTableDeleteButton() {
		this.expenseDeleteBtn.setCellFactory(column -> new TableCell<>() {
			private final Button deleteButton = new Button("Delete");
			
			{
				deleteButton.setStyle("-fx-background-color: #FF0000; -fx-cursor: hand");
				
				deleteButton.setOnAction(event ->{
					SceneManager.createAlert(AlertType.INFORMATION, "Delete Expense", "The selected Expense has been deleted");
					Expense expense = getTableView().getItems().get(getIndex());
					ReportDAO reportDAO = new ReportDAO();
					if(reportDAO.deleteExpense(expense)) {
						getTableView().getItems().remove(getIndex());
						updateExpensesList();
						setTotalExpenses();
					} else {
						SceneManager.createAlert(AlertType.ERROR, "Delete Expense", "An error has ocurred, try again later");
					}
					
					
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
	
	private void generateExpensesTableEditButton() {
		this.expenseEditBtn.setCellFactory(column -> new TableCell<>() {
			private final Button editButton = new Button("Edit");
			
			{
				editButton.setStyle("-fx-background-color: #3C65F1; -fx-cursor: hand");
				
				editButton.setOnAction(event ->{
					Expense expense = getTableView().getItems().get(getIndex());
					AppModel.setLastExpense(expense);
					SceneManager.changeScene(event, "ReportEditExpense.fxml", "Edit Expense", 900, 700);
				});
			}
			
			@Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(editButton);
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
	
	private void initializeExpensesFilter() {
		ObservableList<String> options = FXCollections.observableArrayList("None", "Amount", "Date");
		this.expensesFilter.getItems().addAll(options);
		
		this.expensesFilter.setValue("None");
		
		this.expensesFilter.setOnAction(event -> {
			String selectedOption = this.expensesFilter.getValue();
			
			if(selectedOption.equals("Amount")) {
				this.expensesList.sort((expense1, expense2) -> Double.compare(expense1.getAmount(), expense2.getAmount()));
			}
			
			if(selectedOption.equals("Date")) {
				this.expensesList.sort(Comparator.comparing(Expense::getDate));
			}
		});
	}
	
	// --------------------------------------------------- Incomes -------------------------------------------------------------------
	
	private void configureIncomesTable() {
		this.incomeId.setCellValueFactory(new PropertyValueFactory<Income, Integer>("Id"));
		this.incomeName.setCellValueFactory(new PropertyValueFactory<Income, String>("Name"));
		this.incomeDescription.setCellValueFactory(new PropertyValueFactory<Income, String>("Description"));
		this.incomeAmount.setCellValueFactory(new PropertyValueFactory<Income, Double>("Amount"));
		this.incomeDate.setCellValueFactory(new PropertyValueFactory<Income, Date>("Date"));
	}
	
	private void setStyleIncomesTable() {
		this.incomeId.setStyle("-fx-alignment: CENTER;");
		this.incomeName.setStyle("-fx-alignment: CENTER;");
	    this.incomeDescription.setStyle("-fx-alignment: CENTER;");
	    this.incomeAmount.setStyle("-fx-alignment: CENTER;");
	    this.incomeDate.setStyle("-fx-alignment: CENTER;");
	    this.incomeDeleteBtn.setStyle("-fx-alignment: CENTER;");
	    this.incomeEditBtn.setStyle("-fx-alignment: CENTER;");
	}
	
	private void initializeAddIncomeButton() {
		this.btnAddIncome.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				SceneManager.changeScene(event, "ReportAddIncome.fxml", "Add Income", 900, 700);
				
			}
		});
	}
	
	private void generateIncomesTableDeleteButton() {
		this.incomeDeleteBtn.setCellFactory(column -> new TableCell<>() {
			private final Button deleteButton = new Button("Delete");
			
			{
				deleteButton.setStyle("-fx-background-color: #FF0000; -fx-cursor: hand");
				
				deleteButton.setOnAction(event ->{
					SceneManager.createAlert(AlertType.INFORMATION, "Delete Income", "The selected Income has been deleted");
					Income income = getTableView().getItems().get(getIndex());
					ReportDAO reportDAO = new ReportDAO();
					if(reportDAO.deleteIncome(income)) {
						getTableView().getItems().remove(getIndex());
						updateIncomesList();
						setTotalIncomes();
					} else {
						SceneManager.createAlert(AlertType.ERROR, "Delete Income", "An error has ocurred, try again later");
					}
					
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
	
	private void generateIncomesTableEditButton() {
		this.incomeEditBtn.setCellFactory(column -> new TableCell<>() {
			private final Button editButton = new Button("Edit");
			
			{
				editButton.setStyle("-fx-background-color: #3C65F1; -fx-cursor: hand");
				
				editButton.setOnAction(event ->{
					Income income = getTableView().getItems().get(getIndex());
					AppModel.setLastIncome(income);
					SceneManager.changeScene(event, "ReportEditIncome.fxml", "Edit Income", 900, 700);
				});
			}
			
			@Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(editButton);
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

	private void initializeIncomesFilter() {
		ObservableList<String> options = FXCollections.observableArrayList("None", "Amount", "Date");
		this.incomesFilter.getItems().addAll(options);
		
		this.incomesFilter.setValue("None");
		
		this.incomesFilter.setOnAction(event -> {
			String selectedOption = this.incomesFilter.getValue();
			
			if(selectedOption.equals("Amount")) {
				this.incomesList.sort((income1, income2) -> Double.compare(income1.getAmount(), income2.getAmount()));
			}
			
			if(selectedOption.equals("Date")) {
				this.incomesList.sort(Comparator.comparing(Income::getDate));
			}
		});
	}
}
