package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.entities.Receptionist;
import services.auth.LoginRequestData;

public class ReceptionistRepository {
	
	public Receptionist login(LoginRequestData data) {
	    String sql = "SELECT * FROM receptionist WHERE id = ? AND password = ?";
	        
	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement ps = connection.prepareStatement(sql)) {

	        ps.setInt(1, data.getId());
	        ps.setString(2, data.getPassword());

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	        		Receptionist receptionist = new Receptionist();
	            receptionist.setId(rs.getInt("id"));
	            receptionist.setName(rs.getString("name"));
	            receptionist.setPhone(rs.getString("phone"));
	            receptionist.setPassword(rs.getString("password"));
	            
	            return receptionist;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return null; 
	}	
	
	

}
