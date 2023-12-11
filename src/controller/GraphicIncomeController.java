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
import model.Income;

public class GraphicIncomeController extends BaseExtendedGraphicController implements Initializable {
	
	// Attributes
	
	// CHARTS
	@FXML
	private PieChart pieChartIncome;
		
	// Observable List
	private ObservableList<PieChart.Data> pieChartData;
		
	// ArrayList
	private ArrayList<Income> incomes;
		
	// Total
	private double totalIncomeAmount;
		
	// Label
	@FXML
	private Label labelTotal;
	
	// Constructor
	public GraphicIncomeController() {
		super();
	}
		
	// Methods
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		
		initializeMenu();
				
		initializeBackButton();
		
		initializeIncomes();
		initializeTotalIncomeAmount();
		initializePieChartData();
		this.pieChartIncome.setData(pieChartData);
		this.labelTotal.setText(String.valueOf(totalIncomeAmount));
	}
	
	private void initializeIncomes() {
		ReportDAO reportDAO = new ReportDAO();
		this.incomes = reportDAO.getAllIncomes();
	}
	
	private void initializeTotalIncomeAmount() {
		this.totalIncomeAmount = 0;
		for (Income income : this.incomes) {
			totalIncomeAmount += income.getAmount();
		}
	}
	
	private void initializePieChartData() {
		this.pieChartData = FXCollections.observableArrayList();
		double percentage;
		
		for (Income income : this.incomes) {
			percentage = (income.getAmount() * 100) / this.totalIncomeAmount;
			this.pieChartData.add(new PieChart.Data(income.getName(), percentage));
		}
	}

}
