package model;

public abstract class Transaction {
	
	private int sqlId;
	private int id;
	private String name;
	private String description;
	private double amount;
	
	public Transaction(int sqlId, int id, String name, String description, double amount) {
		this.sqlId = sqlId;
		this.id = id;
		this.name = name;
		this.description = description;
		this.amount = amount;
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
}
