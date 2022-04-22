package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.notices.bean.Notices;

public class NoticesDao {

	private String jdbcURL = "jdbc:mysql://localhost:3306/powergridsystem?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";
	private String jdbcDriver = "com.mysql.jdbc.Driver";
	
	private static final String INSERT_NOTICES_SQL = "insert into notices" + " (Topic, areasAffected, Date, Details	) VALUES " + " (?, ?, ?, ?);";
	private static final String SELECT_NOTICE_BY_ID = "select noticeID, Topic, areasAffected, Date, Details from notices where noticeID =?";
	private static final String SELECT_ALL_NOTICES = "select * from notices";
	private static final String DELETE_NOTICES_SQL = "delete from notices where noticeID = ?;";
	private static final String UPDATE_NOTICES_SQL = "update notices set Topic = ?, areasAffected= ?, Date=?, Details=? where noticeID = ?;";
	
	public NoticesDao() {
		}
	
	//getConnection method
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(jdbcDriver); //load the jdbc
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword); //get the connection
		}catch(SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection; //return the connection
	}
	
	//insert notices 
	public void insertNotice(Notices notices) throws SQLException {
		System.out.println(INSERT_NOTICES_SQL);
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NOTICES_SQL)){
			preparedStatement.setString(1, notices.getTopic());
			preparedStatement.setString(2, notices.getAreasAffected());
			preparedStatement.setString(3, notices.getDate());
			preparedStatement.setString(4, notices.getDetails());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		}catch (SQLException e) {
			printSQLException(e);
		}
		
	}
	
	//select notices by id
	public Notices selectNotices(int noticeID) {
		Notices notices = null;
		//Step 1: Establishing a connection
		try (Connection connection = getConnection();
				//Step 2: Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NOTICE_BY_ID);){
			preparedStatement.setInt(1, noticeID);
			System.out.println(preparedStatement);
			//Step 3: Execute the query or update the query
            ResultSet rs = preparedStatement.executeQuery();
            
            //Step 4: Process the ResultSet object
            while (rs.next()) {
            	String Topic = rs.getString("Topic");
            	String areasAffected = rs.getString("areasAffected");
            	String Date = rs.getString("Date");
            	String Details = rs.getString("Details");
            	notices = new Notices(noticeID, Topic, areasAffected, Date, Details);
            }
		}catch (SQLException e) {
			printSQLException(e);
		}
		return notices;
	}
	
	//select all notices
	public List<Notices> selectAllNotices(){
		//using try-with-resources to avoid closing resources
		List<Notices> notices = new ArrayList<>();
		//Step 1: Establishing a connection
		try (Connection connection = getConnection();
				
				//Step 2: Create a statement using a connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_NOTICES);){
			System.out.println(preparedStatement);
			//Step 3: Execute or update the query
			ResultSet rs = preparedStatement.executeQuery();
			
			//Step 4: Process the ResultSet object
			while (rs.next()) {
				int noticeID = rs.getInt("noticeID");
				String Topic = rs.getString("Topic");
				String areasAffected = rs.getString("areasAffected");
				String Date = rs.getString("Date");
				String Details = rs.getString("Details");
				notices.add(new Notices(noticeID, Topic, areasAffected, Date, Details));
			}
		}catch (SQLException e) {
			printSQLException (e);
		}
		return notices;
	}
	
	//update notices method
	public boolean updateNotices(Notices notices) throws SQLException{
		boolean rowUpdated;
        try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_NOTICES_SQL);){
        	System.out.println("Updated Notice:"+statement);
			statement.setString(1, notices.getTopic());
			statement.setString(2, notices.getAreasAffected());
			statement.setString(3, notices.getDate());
			statement.setString(4, notices.getDetails());
			statement.setInt(4, notices.getNoticeID());
			
			rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
	}
	
	//delete notices from the db
	public boolean deleteNotices(int noticeID) throws SQLException{
		boolean rowDeleted;
		try(Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_NOTICES_SQL);){
			statement.setInt(1, noticeID);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	//printSQLException method
		private void printSQLException(SQLException ex) {
			for(Throwable e: ex) {
				if(e instanceof SQLException) {
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
