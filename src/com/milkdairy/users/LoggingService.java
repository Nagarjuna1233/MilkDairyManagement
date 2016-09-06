package com.milkdairy.users;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.milkdairy.fileservice.MilkDairyPersistenceManager;
import com.milkdairy.managedobjects.Admin;
import com.milkdairy.managedobjects.User;

public class LoggingService {

	private MilkDairyPersistenceManager persistenceManager;


	public void setPersistenceManager(MilkDairyPersistenceManager persistenceManager) {
		this.persistenceManager = persistenceManager;
	}
	
	
	public void save(Object obj){
		
	}
	
	public boolean update(Object obj){
		
		return false;
	}
	
	public boolean deleteUser(User user){
		
		return false;
	}
	
	public Admin getAdmin(){
		return null;
	}
	
	public List<User> getUsers(){
		return null;
	}
	
	public User getUser(String userName){
		return null;
	}
	
	protected boolean createAdminTable() {
	    PreparedStatement ps;
	    try {
	        ps = persistenceManager.getConnection().prepareStatement("CREATE TABLE Admin (name VARCHAR(256),userName VARCHAR(256),password VARCHAR(256),"
	        		+ "fevPetName VARCHAR(256),phoneNum VARCHAR(256)"
	        		+ ",imageUrl VARCHAR(256),"
	        		+ "email VARCHAR(256),address VARCHAR(256),loggingStatus INTEGER(1))");
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("createAdminTable() error");
	        return false;
	    }
	    return true;
	}
	
	protected boolean createUserTable() {
	    PreparedStatement ps;
	    try {
	        ps = persistenceManager.getConnection().prepareStatement("CREATE TABLE User (name VARCHAR(256),userName VARCHAR(256),password VARCHAR(256),"
	        		+ "fevPetName VARCHAR(256),phoneNum VARCHAR(256)"
	        		+ ",imageUrl VARCHAR(256),"
	        		+ "email VARCHAR(256),address VARCHAR(256),loggingStatus INTEGER(1))");
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("createUserTable() error");
	        return false;
	    }
	    return true;
	}
}
