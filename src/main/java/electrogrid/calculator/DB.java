package electrogrid.calculator;

import java.sql.*;

public class DB {

	private Connection con;

	public DB() {

		String url = "jdbc:mysql://localhost:3306/electro_grid";
		String username = "electro_grid_user";
		String password = "1qaz@WSX3edc";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ResultSet getquery(String query) throws SQLException {

		Statement st = con.createStatement();
		ResultSet res = st.executeQuery(query);
		return res;
	}

	public void updatequery(String query) throws SQLException {

		PreparedStatement st = con.prepareStatement(query);
		st.executeUpdate();
	}
	
	private void migrate() {
		
		
//		CREATE TABLE electro_grid.devices (
//				id INTEGER auto_increment NOT NULL,
//				estimate_id INTEGER NOT NULL,
//				name varchar(100) NOT NULL,
//				`load` FLOAT NOT NULL,
//				hours INTEGER NOT NULL,
//				units varchar(100) NOT NULL,
//				CONSTRAINT devices_PK PRIMARY KEY (id),
//				CONSTRAINT devices_FK FOREIGN KEY (id) REFERENCES electro_grid.estimates(id) ON DELETE CASCADE
//			)
//			ENGINE=InnoDB
//			DEFAULT CHARSET=utf8mb4
//			COLLATE=utf8mb4_0900_ai_ci;

		
	}

}
