package repositories;
import services.doctor.DoctorResgisterRequest;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class DoctorRepository {
    public void save(DoctorResgisterRequest doctor){
        String sql = "INSERT INTO doctors(name, speciality, phone, password) VALUES(?, ?, ?, ?)";
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setString(1, doctor.getName());
            ps.setString(2, doctor.getSpeciality());
            ps.setString(3, doctor.getPhoneNumber());
            ps.setString(4, doctor.getPassword());
            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }


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
}
