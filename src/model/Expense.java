package model;

public class Expense extends Transaction{

	public Expense(int sqlId, int id, String name, String description, double amount) {
		super(sqlId, id, name, description, amount);
	}
	
	public Expense(String name, String description, double amount) {
		super(name, description, amount);
	}
}
