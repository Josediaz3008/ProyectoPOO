package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import DAO.ReportDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import model.Expense;
import model.Income;
import util.SceneManager;

public class GraphicBalanceController implements Initializable{

	// Menu Buttons
	@FXML
	private Button buttonReport;
					
	@FXML
	private Button buttonProyection;
	
	// Bar Chart
	@FXML
	private BarChart barChart;
	
	// Series
	private XYChart.Series expensesSeries;
	
	private XYChart.Series incomesSeries;
	
	// Totals
	private double totalExpenseAmount;
	
	private double totalIncomeAmount;
	
	// Back Buttons
	@FXML
	private Button buttonBack;	
	
	// Labels
	@FXML
	private Label labelTotalExpenses;
	
	@FXML
	private Label labelTotalIncomes;
	
	@FXML
	private Label labelTotalBalance;
	
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		
		// -------------------- Initialize Menu Buttons ---------------------------------
		
		// Report
		this.buttonReport.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SceneManager.changeScene(event, "report.fxml", "Report", 900, 700);
			}
		});
						
		// Back button
		this.buttonBack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SceneManager.changeScene(event, "graphics.fxml", "Graphics", 900, 700);
			}
		});
		
		initializeIncomesSeries();
		initializeExpensesSeries();
		
		this.barChart.getData().addAll(this.expensesSeries);
		this.barChart.getData().addAll(this.incomesSeries);
		
		this.labelTotalIncomes.setText(String.valueOf(this.totalIncomeAmount));
		this.labelTotalExpenses.setText(String.valueOf(this.totalExpenseAmount));
		
		initializeTotalLabelBalance();
	}
	
	private void initializeIncomesSeries() {
		this.incomesSeries = new XYChart.Series();
		this.incomesSeries.setName("Incomes");
		
		ReportDAO reportDAO = new ReportDAO();
		ArrayList<Income> incomesAL = reportDAO.getAllIncomes();
		
		this.totalIncomeAmount = 0;
		for (Income income : incomesAL) {
			this.totalIncomeAmount += income.getAmount();
		}
		
		this.incomesSeries.getData().add(new XYChart.Data("Incomes", this.totalIncomeAmount));
	}
	
	private void initializeExpensesSeries() {
		this.expensesSeries = new XYChart.Series();
		this.expensesSeries.setName("Expenses");
		
		ReportDAO reportDAO = new ReportDAO();
		ArrayList<Expense> expensesAL = reportDAO.getAllExpenses();
		
		this.totalExpenseAmount = 0;
		for (Expense expense : expensesAL) {
			this.totalExpenseAmount += expense.getAmount();
		}
		
		this.expensesSeries.getData().add(new XYChart.Data("Expenses", this.totalExpenseAmount));
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
