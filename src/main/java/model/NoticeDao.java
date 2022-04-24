package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NoticeDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/powergridsystem?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";

	private static final String INSERT_NOTICES_SQL = "INSERT INTO notices" + "  (topic, areasAffected, date, details) VALUES "
			+ " (?, ?, ?, ?);";

	private static final String SELECT_NOTICE_BY_ID = "select id,topic,areasAffected,date,details from users where id =?";
	private static final String SELECT_ALL_NOTICES = "select * from notices";
	private static final String DELETE_NOTICES_SQL = "delete from notices where id = ?;";
	private static final String UPDATE_NOTICES_SQL = "update notices set topic = ?,areasAffected= ?, date =?, details=? where id = ?;";

	public NoticeDao() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertNotice(Notice notice) throws SQLException {
		System.out.println(INSERT_NOTICES_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NOTICES_SQL)) {
			preparedStatement.setString(1, notice.getTopic());
			preparedStatement.setString(2, notice.getAreasAffected());
			preparedStatement.setString(3, notice.getDate());
			preparedStatement.setString(4, notice.getDetails());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Notice selectNotice(int id) {
		Notice notice = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NOTICE_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String topic = rs.getString("topic");
				String areasAffected = rs.getString("areasAffected");
				String date = rs.getString("date");
				String details = rs.getString("details");
				notice = new Notice(id, topic, areasAffected, date, details);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return notice;
	}

	public List<Notice> selectAllNotices() {

		List<Notice> notices = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_NOTICES);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String topic = rs.getString("topic");
				String areasAffected = rs.getString("areasAffected");
				String date = rs.getString("date");
				String details = rs.getString("details");
				notices.add(new Notice(id, topic, areasAffected, date, details));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return notices;
	}

	public boolean deleteNotice(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_NOTICES_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateNotice(Notice notice) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_NOTICES_SQL);) {
			System.out.println("updated Notice:"+statement);
			statement.setString(1, notice.getTopic());
			statement.setString(2, notice.getAreasAffected());
			statement.setString(3, notice.getDate());
			statement.setString(4, notice.getDetails());
			statement.setInt(5, notice.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}