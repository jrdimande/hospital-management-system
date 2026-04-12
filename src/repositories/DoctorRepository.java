package repositories;
import models.data_structures.DoubleLinkedList.DoubleLinkedList;
import models.entities.Doctor;
import services.doctor.DoctorResgisterRequest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class DoctorRepository {
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
}
