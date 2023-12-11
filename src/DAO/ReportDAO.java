package DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import model.AppModel;
import model.Expense;
import model.Income;

public class ReportDAO extends DataBaseUtils{
	
	// Constructor
	public ReportDAO() {
		super();
	}
	
	// Methods
	public ArrayList<Expense> getAllExpenses() {
		ArrayList<Expense> expenses = new ArrayList<Expense>();
		int counter = 0;
		try {
			initializeDataBaseConnection();
			String query = "Select expense_id, name, description, amount, date FROM expenses WHERE user_id = ?";
			this.preparedStatement = connection.prepareStatement(query);
			this.preparedStatement.setString(1, String.valueOf(AppModel.getCurrentUserId()));
			this.resultSet = this.preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				counter++;
				int sqlId = this.resultSet.getInt("expense_id");
				int id = counter;
				String name = this.resultSet.getString("name");
				String description = this.resultSet.getString("description");
				double amount = this.resultSet.getDouble("amount");
				Date date = this.resultSet.getDate("date");
				
				expenses.add(new Expense(sqlId, id, name, description, amount, date));
			}
			
			return expenses;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
			
		} finally {
			closeResources();
		}
	}
	
	public ArrayList<Income> getAllIncomes(){
		
		ArrayList<Income> incomes = new ArrayList<Income>();
		int counter = 0;
		
		try {
			initializeDataBaseConnection();
			String query = "Select income_id, name, description, amount, date FROM incomes WHERE user_id = ?";
			this.preparedStatement = connection.prepareStatement(query);
			this.preparedStatement.setString(1,  String.valueOf(AppModel.getCurrentUserId()));
			this.resultSet = this.preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				counter++;
				int sqlId = this.resultSet.getInt("income_id");
				int id = counter;
				String name = this.resultSet.getString("name");
				String description = this.resultSet.getString("description");
				double amount = this.resultSet.getDouble("amount");
				Date date = this.resultSet.getDate("date");
				
				incomes.add(new Income(sqlId, id, name, description, amount, date));
			}
			
			return incomes;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
			
		} finally {
			closeResources();
		}
	}
	
	public boolean deleteExpense(Expense expense) {
		try {
			initializeDataBaseConnection();
			int expenseSQLId = expense.getsqlId();
			String query = "DELETE FROM expenses WHERE expense_id = ? AND user_id = ?";
			this.preparedStatement = connection.prepareStatement(query);
			this.preparedStatement.setInt(1, expenseSQLId);
			this.preparedStatement.setInt(2, AppModel.getCurrentUserId());
			this.preparedStatement.executeUpdate();
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}
	
	public boolean deleteIncome(Income income) {
		try {
			initializeDataBaseConnection();
			int incomeSQLId = income.getsqlId();
			String query = "DELETE FROM incomes WHERE income_id = ? AND user_id = ?";
			this.preparedStatement = connection.prepareStatement(query);
			this.preparedStatement.setInt(1, incomeSQLId);
			this.preparedStatement.setInt(2, AppModel.getCurrentUserId());
			
			this.preparedStatement.executeUpdate();
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}
	
	public boolean addExpense(Expense expense) {
		try {
			initializeDataBaseConnection();
			this.preparedStatement = connection.prepareStatement("INSERT INTO expenses (name, description, amount, date, user_id) VALUES (?, ?, ?, ?, ?)");
			this.preparedStatement.setString(1, expense.getName());
			this.preparedStatement.setString(2, expense.getDescription());
			this.preparedStatement.setDouble(3, expense.getAmount());
			this.preparedStatement.setDate(4, expense.getDate());
			this.preparedStatement.setInt(5, AppModel.getCurrentUserId());
			this.preparedStatement.executeUpdate();
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}
	
	public boolean addIncome(Income income) {

		try {
			initializeDataBaseConnection();
			this.preparedStatement = connection.prepareStatement("INSERT INTO incomes (name, description, amount, date, user_id) VALUES (?, ?, ?, ?, ?)");
			this.preparedStatement.setString(1, income.getName());
			this.preparedStatement.setString(2, income.getDescription());
			this.preparedStatement.setDouble(3, income.getAmount());
			this.preparedStatement.setDate(4, income.getDate());
			this.preparedStatement.setInt(5, AppModel.getCurrentUserId());
			this.preparedStatement.executeUpdate();
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}

	public boolean editExpense(Expense oldExpense, Expense modifyExpense) {
		try {
			initializeDataBaseConnection();
			this.preparedStatement = connection.prepareStatement("UPDATE expenses SET name = ?, description = ?, amount = ? WHERE expense_id = ? AND user_id = ?");
			this.preparedStatement.setString(1, modifyExpense.getName());
			this.preparedStatement.setString(2, modifyExpense.getDescription());
			this.preparedStatement.setDouble(3, modifyExpense.getAmount());
			this.preparedStatement.setInt(4, oldExpense.getsqlId());
			this.preparedStatement.setInt(5, AppModel.getCurrentUserId());
			this.preparedStatement.executeUpdate();
			
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}
	
	public boolean editIncome(Income oldIncome, Income modifyIncome) {
		try {
			initializeDataBaseConnection();
			this.preparedStatement = connection.prepareStatement("UPDATE incomes SET name = ?, description = ?, amount = ? WHERE income_id = ? AND user_id = ?");
			this.preparedStatement.setString(1, modifyIncome.getName());
			this.preparedStatement.setString(2, modifyIncome.getDescription());
			this.preparedStatement.setDouble(3, modifyIncome.getAmount());
			this.preparedStatement.setInt(4, oldIncome.getsqlId());
			this.preparedStatement.setInt(5, AppModel.getCurrentUserId());
			this.preparedStatement.executeUpdate();
			
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}
}
