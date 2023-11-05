package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {

	// Attributes
	private String username;
	private String passwordHash;
	
	// Constructor
	public User(String username, String password) {
		this.username = username;
        this.passwordHash = hashPassword(password);
	}
	
	// Methods
	
	private String hashPassword(String password) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			byte[] passwordBytes = password.getBytes();
			byte[] hashBytes = messageDigest.digest(passwordBytes);
			
			StringBuilder hexHash = new StringBuilder();
			for (byte b: hashBytes) {
				hexHash.append(String.format("%02x", b));
			}
			
			return hexHash.toString();
			
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hash Password Error");
        }
	}
	
	public boolean checkPassword(String inputPassword) {
        String inputPasswordHash = hashPassword(inputPassword);
        return passwordHash.equals(inputPasswordHash);
    }
	
	// Getters
	public String getUsername() {
		return this.username;
	}
	
	public String getPasswordHash() {
		return this.passwordHash;
	}
	
}
