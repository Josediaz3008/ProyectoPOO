package model;

public abstract class AppModel {
	
	private static String currentUsername;
	
	public static String getCurrentUser() {
		return AppModel.currentUsername;
	}
	
	public static void setCurrentUsername(String username) {
		AppModel.currentUsername = username;
	}
}
