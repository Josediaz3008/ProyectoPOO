package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import DAO.ReportDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import model.Expense;
import model.Income;

public class GraphicBalanceController extends BaseExtendedGraphicController implements Initializable{
	
	// Attributes
	
	// Bar Chart
	@FXML
	private BarChart<String, Double> barChart;
	
	// Series
	private XYChart.Series<String, Double> expensesSeries;
	
	private XYChart.Series<String, Double> incomesSeries;
	
	// Totals
	private double totalExpenseAmount;
	
	private double totalIncomeAmount;
	
	// Labels
	@FXML
	private Label labelTotalExpenses;
	
	@FXML
	private Label labelTotalIncomes;
	
	@FXML
	private Label labelTotalBalance;
	
	
	// Constructor
	public GraphicBalanceController() {
		super();
	}
	
	// Methods
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		
		initializeMenu();
						
		initializeBackButton();
		
		initializeIncomesSeries();
		initializeExpensesSeries();
		
		List<XYChart.Series<String, Double>> seriesList = new ArrayList<>();
		seriesList.add(this.expensesSeries);
		seriesList.add(this.incomesSeries);
		this.barChart.getData().addAll(seriesList);

		
		this.labelTotalIncomes.setText(String.valueOf(this.totalIncomeAmount));
		this.labelTotalExpenses.setText(String.valueOf(this.totalExpenseAmount));
		
		initializeTotalLabelBalance();
	}
	
	private void initializeIncomesSeries() {
		this.incomesSeries = new XYChart.Series<String, Double>();
		this.incomesSeries.setName("Incomes");
		
		ReportDAO reportDAO = new ReportDAO();
		ArrayList<Income> incomesAL = reportDAO.getAllIncomes();
		
		this.totalIncomeAmount = 0;
		for (Income income : incomesAL) {
			this.totalIncomeAmount += income.getAmount();
		}
		
		this.incomesSeries.getData().add(new XYChart.Data<String, Double>("Incomes", this.totalIncomeAmount));
	}
	
	private void initializeExpensesSeries() {
		this.expensesSeries = new XYChart.Series<String, Double>();
		this.expensesSeries.setName("Expenses");
		
		ReportDAO reportDAO = new ReportDAO();
		ArrayList<Expense> expensesAL = reportDAO.getAllExpenses();
		
		this.totalExpenseAmount = 0;
		for (Expense expense : expensesAL) {
			this.totalExpenseAmount += expense.getAmount();
		}
		
		this.expensesSeries.getData().add(new XYChart.Data<String, Double>("Expenses", this.totalExpenseAmount));
	}

	private void initializeTotalLabelBalance() {
		double totalBalance = this.totalIncomeAmount - this.totalExpenseAmount;
		
		this.labelTotalBalance.setText(String.valueOf(totalBalance));
		
		if (totalBalance > 0) {
			this.labelTotalBalance.setTextFill(Color.GREEN);
		} else {
			this.labelTotalBalance.setTextFill(Color.RED);
		}
	}
}
