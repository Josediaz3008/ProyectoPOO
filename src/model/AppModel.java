package model;

public abstract class AppModel {
	
	private static String currentUsername;
	private static int currentUserId;
	
	public static String getCurrentUser() {
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
}

