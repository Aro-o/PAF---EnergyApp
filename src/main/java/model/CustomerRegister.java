package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class CustomerRegister {
  String NIC;
  String AccNo;
  String Name;
  String Address;
  String Email;
  String PhoneNo;
	
	
   public CustomerRegister() {   
   }
	
   //Connect to DB
   public Connection connect() 
	{ 
	 Connection con = null; 
	 
	 try 
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 
	//Provide the correct details: DBServer/DBName, username, password
	 con= DriverManager.getConnection("jdbc:mysql://localhost:3306/customerdb","root","Rusiru#99"); 
	 
	//For testing
	 System.out.print("Successfully connected-----------------------"); 
	 } 
	 catch(Exception e) 
	 { 
	 e.printStackTrace(); 
	 } 
	 
	 return con; 

	
	}
	
	//Insert Customer details
	public String insertCustomer(String nic, String accno, String name, String address, String email, String phoneno) {
		Connection con = connect(); 
		String output;
		if (con == null) 
		{ 
		return "Error while connecting to the database"; 
		}
		
		else {
			//create prepared statement
			String query="insert into customer(customerID,NIC,AccNo,Name,Address,Email,PhoneNo) values(?,?,?,?,?,?,?)";
		   try {
			PreparedStatement ps=con.prepareStatement(query);
            
			//binding values
			ps.setInt(1, 0);
			ps.setString(2, nic);
			ps.setString(3, accno);
			ps.setString(4, name);
			ps.setString(5, address);
			ps.setString(6, email);
			ps.setString(7, phoneno);
			
			//execute the statement
			ps.execute();
			output="inserted";
			con.close();
			
		} catch (SQLException e) {
			output="not";
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   return output;
		     
		
		}
		
	}
    public String ReadCustomer() {
		
    	
    	String out="";
    	
    	try
    	{ 
    	 Connection con = connect(); 
    	if (con == null) 
    	 { 
    	 return "Error while connecting to the database for reading."; 
    	 }
    	
    	//prepare the html table to be displayed
    	out="<table border='1'>"
    			+ "<tr>"
    			+ "<th>Customer NIC</th><th>Customer AccNo</th><th>Customer Name</th><th>Customer Address</th><th>Customer Email</th><th>Customer ContactNo</th><th>Update</th><th>delete</th>"
    			+ "</tr>";
    	
    	Statement stmt=con.createStatement();
    	String Query="select * from customer";
    	ResultSet rs=stmt.executeQuery(Query);
    	
    	//iterate through the rows in the result set 
    	while(rs.next()) {
    		String customerID= Integer.toString(rs.getInt("customerID"));
    		String NIC=rs.getString("NIC");
    		String AccNo=rs.getString("AccNo");
    	    String Name=rs.getString("Name");
    	    String Address=rs.getString("Address");
    	    String Email=rs.getString("Email");
    	    String PhoneNo=rs.getString("PhoneNo");
    	    
    	    out+="<tr>"
    	    		+ "<td>"+ NIC + "</td><td>"+AccNo +"</td><td>"+Name+"</td><td>"+Address+"</td><td>"+Email+"</td><td>"+PhoneNo+"</td>";
    	    
    		
    		out+="<td><form action='RegisterUpdate.jsp' method='post'> "
    				+ "<input type='submit' name='update' value='update'  class='btn btn-secondary'>"
    				+ "<input type='hidden'   name='customerID' value='"+ customerID +"' > "
    				+ "<input type='hidden'   name='NIC' value='"+ NIC +"' >"
    				+ "<input type='hidden'   name='AccNo' value='"+ AccNo +"' >"
    				+ "<input type='hidden'   name='Name' value='"+ Name +"' >"
    				+ "<input type='hidden'   name='Address' value='"+ Address +"' >"
    				+ "<input type='hidden'   name='Email' value='"+ Email +"' >"
    				+ "<input type='hidden'   name='PhoneNo' value='"+ PhoneNo +"' >"
    				
    				+ "</form> </td>"
    		     
    		      
    				
    				
    				
    				
    				
    				
    				
    				+ "<td><form action='Customer.jsp' method='post'>"
    				+ "<input type='submit' value='delete' name='btnRemove' class='btn btn-danger'>"
    				+ "<input type='hidden'   name='customerID' value='"+ customerID +"' > "
    				+ "</form>"
    				+ "</td>"
    				+ "</tr>";
    		
    	}
    	
    	//complete the html table
    	out+="</table>";
    	con.close();
    	
    	
    	
    	} 
    	catch (Exception e) 
    	{ 
    	 out = "Error while reading the Customer."; 
    	 System.err.println(e.getMessage()); 
    	}
    	
    	
    	
    	return out;
    	
    }
    
    //delete customer
    public String deleteCustomer(String customerID)
    { 
     String output = ""; 
     Connection con = null ;
     try
     { 
       con = connect(); 
     if (con == null) { 
      return "Error while connecting to the database for deleting."; 
      } 
     } 
     catch (Exception e) 
     { 
     output = "Error while deleting the customer."; 
      System.err.println(e.getMessage()); 
     }
     
     
     
     String query="delete from customer where customerID=? ";
     try {
		PreparedStatement ps=connect().prepareStatement(query);
		ps.setInt(1, Integer.parseInt(customerID));
		ps.execute();
		output="deleted success";
		con.close(); 
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		output="delete has error";
		e.printStackTrace();
	}
   
     
     return output;
     
     
     
    }
	
   //update customer
    public String updateCustomer(String customerID,String nic, String accno, String name, String address, String email, String phoneno)
    { 
    	
    String output="";
    Connection con = null ;
    try
    { 
      con = connect(); 
    if (con == null) { 
     return "Error while connecting to the database for update."; 
     } 
     
   
    
    
    String query="Update customer set NIC=?,AccNo=?,Name=?,Address=?,Email=?,PhoneNo=? Where customerID=?";
    PreparedStatement ps =con.prepareStatement(query);
    ps.setString(1, nic); 
    ps.setString(2, accno); 
    ps.setString(3, name);
    ps.setString(4, address);
    ps.setString(5, email);
    ps.setString(6, phoneno);
    ps.setInt(7,Integer.parseInt(customerID)); 
    
    ps.execute();
    output ="update success";
    con.close();
    
    }catch(Exception e){
    	System.out.println("failed id:"+customerID);
    output = "error during update"; 
    System.err.println(e.getMessage());
    }
    
    return output;	
    	
    }
    
 }
	





 
