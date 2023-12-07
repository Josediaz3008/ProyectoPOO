package model;

public class Income extends Transaction{

	public Income(int sqlId, int id, String name, String description, double amount) {
		super(sqlId, id, name, description, amount);
	}
	
}
