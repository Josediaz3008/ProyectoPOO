package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DAO.ReportDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Expense;
import util.SceneManager;

public class GraphicExpenseController implements Initializable{
	
	// Menu Buttons
	@FXML
	private Button buttonReport;
			
	@FXML
	private Button buttonProyection;
	
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
	
	// Back Buttons
	@FXML
	private Button buttonBack;

	// Initialize
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