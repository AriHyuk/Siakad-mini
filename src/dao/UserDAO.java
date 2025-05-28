/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Ari Awaludin
 */
import model.User;
import config.Database;
import java.sql.*;

public class UserDAO {
	public boolean login(String username, String password) {
    	try (Connection conn = (Connection) Database.getConnection();
         	PreparedStatement ps = conn.prepareStatement("SELECT * FROM user WHERE username=? AND password=?")) {
        	ps.setString(1, username);
        	ps.setString(2, password);
        	ResultSet rs = ps.executeQuery();
        	return rs.next();
    	} catch (Exception e) { e.printStackTrace(); }
    	return false;
	}
}

