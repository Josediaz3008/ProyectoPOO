package model;

import java.sql.Date;

public class Income extends Transaction{

	public Income(int sqlId, int id, String name, String description, double amount, Date date) {
		super(sqlId, id, name, description, amount, date);
	}
	
	public Income(String name, String description, double amount, Date date) {
		super(name, description, amount, date);
	}
	
}
