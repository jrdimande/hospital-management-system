package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import models.data_structures.Stack.Stack;
import models.entities.History;
import models.entities.HistoryType;

public class HistoryRepository {

	public void save(History history) {

		String sql = "INSERT INTO history(history_type, created_at, details) VALUES(?, ?, ?)";

		try (
				Connection connection = DBConnection.getConnection();
				PreparedStatement ps = connection.prepareStatement(
						sql,
						PreparedStatement.RETURN_GENERATED_KEYS)
		) {

			ps.setString(1, history.getHistoryType().name());

			Timestamp time = Timestamp.valueOf(history.getCreatedAt());
			ps.setTimestamp(2, time);

			ps.setString(3, history.getDetails()); // IMPORTANTE

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();

			if (rs.next()) {
				history.setId(rs.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Stack findAll() {
		Stack history = new Stack();
		
		String sql = "SELECT * FROM history";
		
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {	
			
			while(rs.next()) {
				History h = new History();
				
				h.setId(rs.getInt("id"));
				h.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
				h.setDetails(rs.getString("details"));
				
				String type = rs.getString("history_type");
				HistoryType historyType = null;
				
				try {
					historyType = HistoryType.valueOf(type);
					h.setHistoryType(historyType);
					
					history.push(h);
				}catch(IllegalArgumentException | NullPointerException e) {
					System.out.println("" + e.getMessage());
				}
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return history;
	}

}
