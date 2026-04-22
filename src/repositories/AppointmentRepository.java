package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.data_structures.Stack.Stack;
import models.entities.Appointment;

public class AppointmentRepository {

    public void save(Appointment appointment){
        String sql = "INSERT INTO appointments(patient, doctor, date, notes) VALUES(?, ?, ?, ?)";

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
            ps.setString(1, appointment.getPatient());
            ps.setString(2, appointment.getDoctor());
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

    public Stack findAll(){
        Stack appointments = new Stack();
        String sql = "SELEC * FROM appointments";

        try(Connection connection = DBConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()){

            while (rs.next()){
                Appointment appointment = new Appointment();

                appointment.setId(rs.getInt("id"));
                appointment.setPatient(rs.getString("patient"));
                appointment.setDoctor(rs.getString("doctor"));
                appointment.setDate(rs.getString("date"));
                appointment.setNotes(rs.getString("notes"));

                appointments.push(appointment);

            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }
}
