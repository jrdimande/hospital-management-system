package repositories;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.data_structures.DoubleLinkedList.DoubleLinkedList;
import models.entities.Doctor;
import services.auth.LoginRequestData;
import services.doctor.DoctorResgisterRequest;

public class DoctorRepository {
	
	public Doctor login(LoginRequestData data) {
	    String sql = "SELECT * FROM doctors WHERE id = ? AND password = ?";
	        
	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement ps = connection.prepareStatement(sql)) {

	        ps.setInt(1, data.getId());
	        ps.setString(2, data.getPassword());

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            Doctor doctor = new Doctor();
	            doctor.setId(rs.getInt("id"));
	            doctor.setName(rs.getString("name"));
	            doctor.setPhoneNumber(rs.getString("phone"));
	            doctor.setPassword(rs.getString("password"));
	            doctor.setSpeciality(rs.getString("speciality"));
	            
	            return doctor;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return null; 
	}
	
    public int save(DoctorResgisterRequest doctor){
        String sql = "INSERT INTO doctors(name, speciality, phone, password) VALUES(?, ?, ?, ?)";
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){

            ps.setString(1, doctor.getName());
            ps.setString(2, doctor.getSpeciality());
            ps.setString(3, doctor.getPhoneNumber());
            ps.setString(4, doctor.getPassword());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()){
                int generatedId = rs.getInt(1);
                return generatedId;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;


    }

    public void delete(int id){
        String sql = "DELETE FROM doctors WHERE id = ?";
        try(Connection connection = DBConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public DoubleLinkedList findAll(){
        DoubleLinkedList doctors = new DoubleLinkedList();
        String sql = "SELECT * FROM doctors";

        try (Connection connection = DBConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()){

            while (rs.next()){
                Doctor d = new Doctor();

                d.setId(rs.getInt("id"));
                d.setName(rs.getString("name"));
                d.setPhoneNumber(rs.getString("phone"));
                d.setSpeciality(rs.getString("speciality"));
                d.setPassword(rs.getString("password"));

                doctors.add(d);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return doctors;

    }

    public void update(Doctor doctor){
        String sql = "UPDATE SET doctors name = ?, speciality = ?, phone = ?, password = ? where id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, doctor.getName());
            ps.setString(2, doctor.getSpeciality());
            ps.setString(3, doctor.getPhoneNumber());
            ps.setString(4, doctor.getPassword());
            ps.setInt(5, doctor.getId());

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
