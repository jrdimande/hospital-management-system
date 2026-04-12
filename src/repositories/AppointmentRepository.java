package repositories;

import models.entities.Appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentRepository {

    public void save(Appointment appointment){
        String sql = "INSERT INTO appointments(patient, doctor, date, notes) VALUES(?, ?, ?, ?)";

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
            ps.setString(1, appointment.getPatient().getName());
            ps.setString(2, appointment.getDoctor().getName());
            ps.setString(3, appointment.getDate());
            ps.setString(4, appointment.getNotes());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()){
                int generatedId = rs.getInt(1);
                appointment.setId(generatedId);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(int id){
        String sql = "DELETE FROM appointments WHERE id = ?";

        try(Connection connection = DBConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
