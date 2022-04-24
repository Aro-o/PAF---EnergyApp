package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerFeedback {
	  String fbType;
	  String fbDesc;
	  Double fbRate;
		
		
		
	   public CustomerFeedback() {   
	   }
		
	   //Connect to DB
	   public Connection connect() 
		{ 
		 Connection con = null; 
		 
		 try 
		 { 
		 Class.forName("com.mysql.jdbc.Driver"); 
		 
		//Provide the correct details: DBServer/DBName, username, password
		 con= DriverManager.getConnection("jdbc:mysql://localhost:3306/customerdb", "root", "Rusiru#99"); 
		 
		//For testing
		 System.out.print("Successfully connected-----------------------"); 
		 } 
		 catch(Exception e) 
		 { 
		 e.printStackTrace(); 
		 } 
		 
		 return con; 

		
		}
		
		
		public String insertFeedback(String type, String desc, String rate) {
			Connection con = connect(); 
			String output;
			if (con == null) 
			{ 
			return "Error while connecting to the database"; 
			}
			
			else {
				//create prepared statement
				String query="insert into feedback(feedbackID,fbType,fbDesc,fbRate) values(?,?,?,?)";
			   try {
				PreparedStatement ps=con.prepareStatement(query);
	            
				//binding values
				ps.setInt(1, 0);
				ps.setString(2, type);
				ps.setString(3, desc);
				ps.setDouble(4, Double.parseDouble(rate));
				
				//execute the statement
				ps.execute();
				output="Data inserted successfully";
				con.close();
				
			} catch (SQLException e) {
				output="not";
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   return output;
			     
			
			}
			
		}
	    public String ReadFeedback() {
			
	    	
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
	    			+ "<th>Feedback Type</th><th>Feedback Description</th><th>Feedback Rate</th><th>Update</th><th>delete</th>"
	    			+ "</tr>";
	    	
	    	Statement stmt=con.createStatement();
	    	String Query="select * from feedback";
	    	ResultSet rs=stmt.executeQuery(Query);
	    	
	    	//iterate through the rows in the result set 
	    	while(rs.next()) {
	    		String feedbackID= Integer.toString(rs.getInt("feedbackID"));
	    		String fbType=rs.getString("fbType");
	    		String fbDesc=rs.getString("fbDesc");
	    		String fbRate=Double.toString(rs.getDouble("fbRate"));
	    	     
	    	    
	    	    out+="<tr>"
	    	    		+ "<td>"+ fbType + "</td><td>"+fbDesc +"</td><td>"+fbRate+"</td>";
	    	    
	    		
	    		out+="<td><form action='FeedbackUpdate.jsp' method='post'> "
	    				+ "<input type='submit' name='update' value='update'  class='btn btn-secondary'>"
	    				+ "<input type='hidden'   name='feedbackID' value='"+ feedbackID +"' > "
	    				+ "<input type='hidden'   name='fbType' value='"+ fbType +"' >"
	    				+ "<input type='hidden'   name='fbDesc' value='"+ fbDesc +"' >"
	    				+ "<input type='hidden'   name='fbRate' value='"+ fbRate+"' >"
	    				
	    				+ "</form> </td>"
	    		     
	    		      
	    				
	    				
	    				
	    				
	    				
	    				
	    				
	    				+ "<td><form action='Item.jsp' method='post'>"
	    				+ "<input type='submit' value='delete' name='btnRemove' class='btn btn-danger'>"
	    				+ "<input type='hidden'   name='feedbackID' value='"+ feedbackID +"' > "
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
	    	 out = "Error while reading the feedback."; 
	    	 System.err.println(e.getMessage()); 
	    	}
	    	
	    	
	    	
	    	return out;
	    	
	    }
	    
	    //delete item
	    public String deleteFeedback(String feedbackID)
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
	     output = "Error while deleting the feedback."; 
	      System.err.println(e.getMessage()); 
	     }
	     
	     
	     
	     String query="delete from feedback where feedbackID=? ";
	     try {
			PreparedStatement ps=connect().prepareStatement(query);
			ps.setInt(1, Integer.parseInt(feedbackID));
			ps.execute();
			output="Successfully Deleted";
			con.close(); 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			output="delete has error";
			e.printStackTrace();
		}
	   
	     
	     return output;
	     
	     
	     
	    }
		
	   //update feedback
	    public String updateFeedback(String feedbackID,String fbType, String fbDesc, String fbRate)
	    { 
	    	
	    String output="";
	    Connection con = null ;
	    try
	    { 
	      con = connect(); 
	    if (con == null) { 
	     return "Error while connecting to the database for update."; 
	     } 
	     
	   
	    
	    
	    String query="Update feedback set fbType=?,fbDesc=?,fbRate=? Where feedbackID=?";
	    PreparedStatement ps =con.prepareStatement(query);
	    ps.setString(1, fbType); 
	    ps.setString(2, fbDesc); 
	    ps.setDouble(3, Double.parseDouble(fbRate)); 
	    ps.setInt(4,Integer.parseInt(feedbackID)); 
	    
	    ps.execute();
	    output ="updated successfully";
	    con.close();
	    
	    }catch(Exception e){
	    	System.out.println("failed id:"+feedbackID);
	    output = "error during update"; 
	    System.err.println(e.getMessage());
	    }
	    
	    return output;	
	    	
	    }
	    
	 }
		