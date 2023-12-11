package model;

import java.sql.Date;

public class Expense extends Transaction{

	public Expense(int sqlId, int id, String name, String description, double amount, Date date) {
		super(sqlId, id, name, description, amount, date);
	}
	
	public Expense(String name, String description, double amount, Date date) {
		super(name, description, amount, date);
	}
}
