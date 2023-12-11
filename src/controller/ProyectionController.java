package controller;

import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import DAO.ReportDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import model.Expense;
import model.Income;

public class ProyectionController extends BaseMenuController implements Initializable{

	// Attributes
	
	// Line Chart
	@FXML
	private LineChart<String, Double> lineChart;
	
	// Series
	private XYChart.Series<String, Double> series;
	
	// Total
	private double total;
	
	// Closest Date
	private Date closestDate;
	
	// Constructor
	public ProyectionController() {
		super();
	}
	
	// Methods
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		initializeMenu();
		getTotal();
		getClosestDate();
		
		initializeSeries();
		
		this.lineChart.getData().add(this.series);
	}
	
	private void initializeSeries() {
		this.series = new XYChart.Series<String, Double>();
		this.series.setName("Total");
		
		series.getData().add(new XYChart.Data<String, Double>(convertDateToString(this.closestDate), this.total));
		series.getData().add(new XYChart.Data<String, Double>(convertDateToString(addMonthToDate(this.closestDate)), this.total*2));
		series.getData().add(new XYChart.Data<String, Double>(convertDateToString(addMonthToDate(addMonthToDate(this.closestDate))), this.total*3));
	}
	
	private void getTotal() {
		ReportDAO reportDAO = new ReportDAO();
		ArrayList<Expense> expenses = reportDAO.getAllExpenses();
		ArrayList<Income> incomes = reportDAO.getAllIncomes();
		
		double totalIncomes = 0;
		for (Income income : incomes) {
			totalIncomes += income.getAmount();
		}
		
		double totalExpenses = 0;
		for (Expense expense : expenses) {
			totalExpenses += expense.getAmount();
		}
		
		this.total = totalIncomes - totalExpenses;
	}
	
	private void getClosestDate() {
		ReportDAO reportDAO = new ReportDAO();
		ArrayList<Expense> expenses = reportDAO.getAllExpenses();
		ArrayList<Income> incomes = reportDAO.getAllIncomes();
		
		LocalDate today = LocalDate.now();
        Date closestDate = null;
        
        for (Expense expense : expenses) {
            Date expenseDate = expense.getDate();
            if (closestDate == null || Math.abs(expenseDate.toLocalDate().until(today).getDays()) < Math.abs(closestDate.toLocalDate().until(today).getDays())) {
                closestDate = expenseDate;
            }
        }

        // Itera sobre las fechas de Income
        for (Income income : incomes) {
            Date incomeDate = income.getDate();
            if (closestDate == null || Math.abs(incomeDate.toLocalDate().until(today).getDays()) < Math.abs(closestDate.toLocalDate().until(today).getDays())) {
                closestDate = incomeDate;
            }
        }
        
        this.closestDate = closestDate;
	}

	private Date addMonthToDate(Date currentDate) {
		
        LocalDate localDate = currentDate.toLocalDate();
        LocalDate newLocalDate = localDate.plusMonths(1);
        Date newDate = Date.valueOf(newLocalDate);

        return newDate;
    }
	
	private String convertDateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Format the Date to String
        return dateFormat.format(date);
    }
}
