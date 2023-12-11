package model;

public abstract class AppModel {
	
	// Attributes
	
	private static String currentUsername;
	private static int currentUserId;
	private static Expense lastExpense;
	private static Income lastIncome;
	
	
	// Getters and Setters
	public static String getCurrentUsername() {
		return AppModel.currentUsername;
	}
	
	public static void setCurrentUsername(String username) {
		AppModel.currentUsername = username;
	}
	
	public static int getCurrentUserId() {
		return AppModel.currentUserId;
	}
	
	public static void setCurrentUserId(int userId) {
		AppModel.currentUserId = userId;
	}
	
	public static void setLastExpense(Expense expense) {
		AppModel.lastExpense = expense;
	}
	
	public static Expense getLastExpense() {
		return AppModel.lastExpense;
	}
	
	public static void setLastIncome(Income income) {
		AppModel.lastIncome = income;
	}
	
	public static Income getLastIncome() {
		return AppModel.lastIncome;
	}
	
}

