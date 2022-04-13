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


import model.samplebill;
@Path("/bill") 
public class BillService {
	samplebill billObject=new samplebill();
	
	@GET
	@Path("/readBill") 
	@Produces(MediaType.TEXT_PLAIN) 
	public String readItems() 
	 { 
	    return billObject.ReadBill(); 
	 } 
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertBill(@FormParam("category") String category,
	 @FormParam("acno") String acno, 
	 @FormParam("year") String year, 
	 @FormParam("month") String month, 
	 @FormParam("totalunits") String totalunits,
	 @FormParam("fixedcharge") String fixedcharge) 
	{ 
	 String output = billObject.insertBill(category, acno, year, month, totalunits, fixedcharge);
	return output; 
	}


	@DELETE
	@Path("/delete")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_XML)
	public String deleteBill(String itemData) {
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser()); 
		 
		//Read the value from the element <itemID>
		 String acno = doc.select("acno").text();
		 String year = doc.select("year").text();
		 String month = doc.select("month").text();
		 
		 String output = billObject.deleteBill(acno, year, month);
		 return output;
	}


	
	

}
