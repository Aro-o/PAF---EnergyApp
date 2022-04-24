package com;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON
import com.google.gson.*; 

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

import model.CustomerRegister;

@Path("/customer") 
public class CustomerRegisterService{ 
	
CustomerRegister customerObject=new CustomerRegister();
	
@GET
@Path("/readCustomer") 
@Produces(MediaType.TEXT_PLAIN) 
public String readCustomer() 
 { 
 return customerObject.ReadCustomer(); 
 } 



@POST
@Path("/") 
@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
@Produces(MediaType.TEXT_PLAIN) 
public String insertCustomer(@FormParam("NIC") String nic, 
@FormParam("AccNo") String accno, 
@FormParam("Name") String name, 
@FormParam("Address") String address,
@FormParam("Email") String email,
@FormParam("PhoneNo") String phoneno)
{ 
String output = customerObject.insertCustomer(nic, accno, name, address, email, phoneno); 
return output; 
}


@PUT
@Path("/update")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String updateItems(String customerData) {
	
	//Convert the input string to a JSON object 
	 JsonObject customerUpdateObject = new JsonParser().parse(customerData).getAsJsonObject();
	 
	//Read the values from the JSON object
	 String customerID = customerUpdateObject.get("customerID").getAsString(); 
	 String NIC = customerUpdateObject.get("NIC").getAsString(); 
	 String AccNo = customerUpdateObject.get("AccNo").getAsString(); 
	 String Name = customerUpdateObject.get("Name").getAsString(); 
	 String Address = customerUpdateObject.get("Address").getAsString();
	 String Email = customerUpdateObject.get("Email").getAsString();
	 String Phoneno = customerUpdateObject.get("PhoneNo").getAsString();
	 
	String output= customerObject.updateCustomer(customerID, NIC, AccNo, Name, Address, Email, Phoneno);
	return output;
}


@DELETE
@Path("/delete")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.APPLICATION_XML)
public String deleteCustomer(String customerData) {
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(customerData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String customerID = doc.select("customerID").text(); 
	 String output = customerObject.deleteCustomer(customerID); 
	 return output;
}



} 

 
