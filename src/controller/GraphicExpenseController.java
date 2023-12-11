package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import DAO.ReportDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import model.Expense;

public class GraphicExpenseController extends BaseExtendedGraphicController implements Initializable{
	
	// Attributes
	
	// CHARTS
	@FXML
	private PieChart pieChartExpense;
	
	// Observable List
	private ObservableList<PieChart.Data> pieChartData;
	
	// ArrayList
	private ArrayList<Expense> expenses;
	
	// Total
	private double totalExpenseAmount;
	
	// Labe
	@FXML
	private Label labelTotal;
	
	// Constructor
	
	public GraphicExpenseController() {
		super();
	}

	// Methods
	
	// Initialize
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		
		initializeMenu();
		
		initializeBackButton();
		
		initializeExpenses();
		initializeTotalExpenseAmount();
		initializePieChartData();
		this.pieChartExpense.setData(this.pieChartData);
		this.labelTotal.setText(String.valueOf(totalExpenseAmount));
	}
	
	private void initializeExpenses() {
		ReportDAO reportDAO = new ReportDAO();
		this.expenses = reportDAO.getAllExpenses();
	}
	
	private void initializeTotalExpenseAmount() {
		this.totalExpenseAmount = 0;
		for (Expense expense : this.expenses) {
			totalExpenseAmount += expense.getAmount();
		}
	}
	
	private void initializePieChartData() {
		this.pieChartData = FXCollections.observableArrayList();
		double percentage;
		
		for (Expense expense : this.expenses) {
			percentage = (expense.getAmount() * 100) / this.totalExpenseAmount;
			this.pieChartData.add(new PieChart.Data(expense.getName(), percentage));
		}
	}
}
