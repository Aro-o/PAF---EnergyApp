package model;

import java.sql.*;

public class Payment {

	int pID;
	int cID;
	int bID;
	String year;
	String month;
	Double total;
	Double paidAmount;
	Double toBePaid;
	int cardID;
	
	public Payment() {
		   //constructor
	   }
	
	public Connection connect() 
	{ 
	 Connection con = null; 
	 
	 try 
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 con= DriverManager.getConnection("jdbc:mysql://localhost:3307/payment", "root", "root123"); 
	 //For testing
	 System.out.print("Successfully connected-----------------------"); 
	 } 
	 catch(Exception e) 
	 { 
	 e.printStackTrace(); 
	 } 
	 
	 return con; 

	
	}
	
	///////////////////////////////////READ DATA////////////////////////////////////////////////////////////
	public String ReadPayment() {


		String out="";

		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{ 
				return "Error while connecting to the database for reading."; 
			}

			Statement stmt=con.createStatement();
			String Query="select * from bill_details order by billID desc limit 1";
			ResultSet rs=stmt.executeQuery(Query);

			while(rs.next()) {
				String year=rs.getString("year");
				String month=rs.getString("month");
				String total=Double.toString(rs.getDouble("total"));

				out = "<h2>Your bill for year " + year + " month " + month + " is " +total+" .</h2><h2>Do you want to proceed to online payment?</h2><form action='' method='post'><table><td><input type='submit' name='Pay Now' value='Pay Now'  class='btn btn-secondary'></td><td></td><td></td><td><input type='submit' name='Pay Now' value='Exit'  class='btn btn-secondary'></td></tr></table>";
			
			}

			
			con.close();

		} 
		catch (Exception e) 
		{ 
			out = "Error while reading the items."; 
			System.err.println(e.getMessage()); 
		}

		return out;
	}
	
	///////////////////////////////PAYMENT////////////////////////////////////////////////////
	
	public String payBill(String bID, Double total, Double paidAmount) 
	 { 
	 String output = ""; 
	 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for inserting."; } 
	 try
	 { 
		
		Double toBePaid = 0.00;

		toBePaid = total - paidAmount;
	 
	 
	 // create a prepared statement
	 String query = " insert into payment (pID,bID,total,paidAmount, toBePaid)"
	 + " values (?, ?, ?, ?,?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, bID); 
	 preparedStmt.setDouble(3, total); 
	 preparedStmt.setDouble(4, paidAmount); 
	 preparedStmt.setDouble(5, toBePaid); 
	 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Successfull Payment"; 
	 output+= "<br><h4>Payment Summery</h4>Bill Amount: " +total+ "<br>Paid Amount: " +paidAmount+ "<br>Remaining Amount to be paid : " +toBePaid+".";
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while paying the bill."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }

	
}
