package repositories;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.data_structures.DoubleLinkedList.DoubleLinkedList;
import models.entities.Gender;
import models.entities.Patient;
import services.patient.PatientRegisterRequest;

public class PatientRepository {
	
    public int save(PatientRegisterRequest patient){
        String sql = "INSERT INTO patients(name, age, gender, phone, address) VALUES(?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
            ps.setString(1, patient.getName());
            ps.setInt(2, patient.getAge());
            ps.setString(3, patient.getGender().name());
            ps.setString(4, patient.getPhoneNumber());
            ps.setString(5, patient.getAddress());
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
        String sql = "DELETE from patients WHERE id = ?";
        try(Connection connection = DBConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    public DoubleLinkedList findAll(){
        DoubleLinkedList patients = new DoubleLinkedList();
        String sql = "SELECT * FROM patients";

        try(Connection connection = DBConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()){

            while (rs.next()){
                Patient p = new Patient();

                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setAddress(rs.getString("address"));
                p.setGender(Gender.valueOf(rs.getString("gender")));
                p.setAge(rs.getInt("age"));
                p.setPhoneNumber(rs.getString("phone"));

                patients.add(p);

            }


        }catch (SQLException e){
            e.printStackTrace();
        }

        return patients;
    }

    public void update(Patient patient){
        String sql = "UPDATE SET patients name = ?, age = ?, gender = ?, phone = ?, address = ? where id = ?";

        try(Connection connection = DBConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, patient.getName());
            ps.setInt(2,patient.getAge());
            ps.setString(3, patient.getGender().name());
            ps.setString(4, patient.getPhoneNumber());
            ps.setString(5, patient.getAddress());
            ps.setInt(6, patient.getId());
            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }


}
