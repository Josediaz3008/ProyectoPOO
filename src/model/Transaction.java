package model;

import java.sql.Date;

public abstract class Transaction {
	
	private int sqlId;
	private int id;
	private String name;
	private String description;
	private double amount;
	private Date date;
	
	public Transaction(int sqlId, int id, String name, String description, double amount, Date date) {
		this(name, description, amount, date);
		this.id = id;
		this.sqlId = sqlId;
	}
	
	public Transaction(String name, String description, double amount, Date date) {
		this.name = name;
		this.description = description;
		this.amount = amount;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public double getAmount() {
		return amount;
	}
	
	public int getsqlId() {
		return this.sqlId;
	}
	
	public Date getDate() {
		return this.date;
	}
}
