package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;





public class samplebill {
	
	String msg="hi";
	public samplebill() {
	
	}
	
	public Connection connect() 
	{ 
	 Connection con = null; 
	 
	 try 
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 con= DriverManager.getConnection("jdbc:mysql://localhost:3306/sampleleco", 
	 "root", ""); 
	 //For testing
	 System.out.print("Successfully connected-----------------------"); 
	 } 
	 catch(Exception e) 
	 { 
	 e.printStackTrace(); 
	 } 
	 
	 return con; 

	
	}
	
	public String insertBill(String category,String acno, String year, String month, String totalunits,String fixedcharge) {
		Connection con = connect(); 
		String output;
		if (con == null) 
		{ 
		return "Error while connecting to the database"; 
		}
		
		else {
			
			Double totalunitCharge=0.00;
            Double total=0.00;
            
			if(category.equals("religious")) {
				
			
			if(Double.parseDouble(totalunits)<60) {
            	totalunitCharge=Double.parseDouble(totalunits)* 4.00;
            	total=totalunitCharge+Double.parseDouble(fixedcharge);
			}else if(Double.parseDouble(totalunits)<120) {
				totalunitCharge=60 * 4.00 +(Double.parseDouble(totalunits)-60)* 10.00;
			}else if(Double.parseDouble(totalunits)>120){
				totalunitCharge=(60 * 4.00) + (60 * 10.00) +(Double.parseDouble(totalunits)-120)* 12.00;
			}else {
				System.out.print("enter a valid number of unit");
			}
            
			}else {
				
				if(Double.parseDouble(totalunits)<60) {
	            	totalunitCharge=Double.parseDouble(totalunits)* 6.00;
	            	
				}else if(Double.parseDouble(totalunits)<120) {
					totalunitCharge=60 * 6.00 +(Double.parseDouble(totalunits)-60)* 12.00;
				}else if(Double.parseDouble(totalunits)>120){
					totalunitCharge=(60 * 6.00) + (60 * 12.00) +(Double.parseDouble(totalunits)-120)* 14.00;
				}else {
					System.out.print("enter a valid number of unit");
				}
				total=totalunitCharge+Double.parseDouble(fixedcharge);
			}
			
			
			
			String query="insert into samplebill(id,acno,year,month,totalunits,totalunitcharge,fixedcharge,total) values(?,?,?,?,?,?,?,?)";
		   try {
			PreparedStatement ps=con.prepareStatement(query);
            
			
			ps.setInt(1, 0);
			ps.setString(2, acno);
			ps.setString(3, year);
			ps.setString(4, month);
			ps.setDouble(5,Double.parseDouble(totalunits));
			ps.setDouble(6,totalunitCharge);
			ps.setDouble(7,Double.parseDouble(fixedcharge));
			ps.setDouble(8,total);

			
			ps.execute();
			output="inserted";
			con.close();
			
		} catch (SQLException e) {
			output="not inserted retry and give proper values";
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   return output;
		     
		
		}
		
	}
	
	
	
    public String ReadBill() {
		
    	
    	String out="";
    	
    	try
    	{ 
    	 Connection con = connect(); 
    	if (con == null) 
    	 { 
    	 return "Error while connecting to the database for reading."; 
    	 }
    	
    	out="<table border='1'>"
    			+ "<tr>"
    			+ "<th>AC No</th><th>Year</th><th>Month</th><th>total units</th><th>total units charge</th><th>fixed charge</th><th>total</th><th>Update</th><th>delete</th>"
    			+ "</tr>";
    	
    	Statement stmt=con.createStatement();
    	String Query="select * from samplebill";
    	ResultSet rs=stmt.executeQuery(Query);
    	
    	while(rs.next()) {
    		String ID= Integer.toString(rs.getInt("id"));
    		String AcNo=rs.getString("acno");
    		String Year=rs.getString("year");
    		String month=rs.getString("month");
    		String totalunits=Double.toString(rs.getDouble("totalunits"));
    		String totalunitCharge=Double.toString(rs.getDouble("totalunitcharge"));
    		String fixedcharge=Double.toString(rs.getDouble("fixedcharge"));
    		String total=Double.toString(rs.getDouble("total"));
    	   
    	    
    	    out+="<tr>"
    	    		+ "<td>"+ AcNo + "</td><td>"+Year +"</td><td>"+month+"</td><td>"+totalunits+"</td><td>"+totalunitCharge+"</td><td>"+fixedcharge+"</td><td>"+total+"</td>";
    	    
    		
    		out+="<td><form action='billUpdate.jsp' method='post'>"
    				
    				+ "<input type='hidden'   name='acno' value='"+ AcNo +"' >"
    				+ "<input type='hidden'   name='year' value='"+ Year +"'>"
    				+ "<input type='hidden'   name='month' value='"+month +"'>"
    			    + "<input type='hidden'   name='month' value='"+totalunits +"'>"
    			    + "<input type='hidden'   name='month' value='"+fixedcharge +"'>"
    			    + "<input type='submit' name='update' value='update'></td>"
    				
    				+ "<td><form action='bill.jsp' method='post'>"
    				+ "<input type='hidden'   name='ID' value='"+ ID +"' > "
    				+ "<input type='submit' value='delete' name='btnRemove'>"
    				+ "</form>"
    				+ "</td>"
    				+ "</tr>";
    		
    	}
    	
    	out+="</table>";
    	con.close();
    	
    	
    	
    	} 
    	catch (Exception e) 
    	{ 
    	 out = "Error while reading the bill."; 
    	 System.err.println(e.getMessage()); 
    	}
    	
    	
    	
    	return out;
    	
    }

	
    public String deleteBill(String acno,String year,String month)
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
	 output = "Error while deleting the Bill."; 
	  System.err.println(e.getMessage()); 
	 }
	 
	 
	 
	 String query="delete from samplebill where acno=? and year=? and month=? ";
	 try {
		PreparedStatement ps=connect().prepareStatement(query);
		ps.setString(1, acno);
		ps.setString(2, year);
		ps.setString(3, month);
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
	
	
	
	
	
	
	
	
	
	
	
	
}
