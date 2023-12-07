package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import model.AppModel;
import model.Expense;
import model.Income;

public class ReportDAO extends DataBaseUtils{
	
	public ReportDAO() {
		super();
	}
	
	public ArrayList<Expense> getAllExpenses() {
		ArrayList<Expense> expenses = new ArrayList<Expense>();
		int counter = 0;
		try {
			initializeDataBaseConnection();
			String query = "Select expense_id, name, description, amount FROM expenses WHERE user_id = ?";
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
				
				expenses.add(new Expense(sqlId, id, name, description, amount));
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
			String query = "Select income_id, name, description, amount FROM incomes WHERE user_id = ?";
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
				
				incomes.add(new Income(sqlId, id, name, description, amount));
			}
			
			return incomes;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
			
		} finally {
			closeResources();
		}
	}
	
	public void deleteExpense(Expense expense) {
		try {
			initializeDataBaseConnection();
			int expenseSQLId = expense.getsqlId();
			String query = "DELETE FROM expenses WHERE expense_id = ? AND user_id = ?";
			this.preparedStatement = connection.prepareStatement(query);
			this.preparedStatement.setInt(1, expenseSQLId);
			this.preparedStatement.setInt(2, AppModel.getCurrentUserId());
			
			this.preparedStatement.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
	}
	
	public void deleteIncome(Income income) {
		try {
			initializeDataBaseConnection();
			int incomeSQLId = income.getsqlId();
			String query = "DELETE FROM incomes WHERE income_id = ? AND user_id = ?";
			this.preparedStatement = connection.prepareStatement(query);
			this.preparedStatement.setInt(1, incomeSQLId);
			this.preparedStatement.setInt(2, AppModel.getCurrentUserId());
			
			this.preparedStatement.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
	}
	
}
