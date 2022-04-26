package com;
import java.sql.SQLException;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Card;
import model.Payment;

@Path("/cards")
public class CardService {

	Card cardObj = new Card();
	Payment payObj = new Payment();
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertItem(@FormParam("cardno") String cardno, 
	 @FormParam("name") String name, 
	 @FormParam("cvv") String cvv, 
	 @FormParam("exDate") String exDate) 
	{ 
	 String output = cardObj.insertCard(cardno, name, cvv, exDate); 
	return output; 
	}
	
	@GET
	@Path("/readCard") 
	@Produces(MediaType.TEXT_PLAIN) 
	public String readItems() 
	 { 
	 return cardObj.ReadCard(); 
	 }
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayments(String cardData) {
		
		//Convert the input string to a JSON object 
		 JsonObject pymentUpdateObject = new JsonParser().parse(cardData).getAsJsonObject();
		 
		//Read the values from the JSON object
		 String cardID = pymentUpdateObject.get("cardID").getAsString(); 
		 String cardno = pymentUpdateObject.get("cardno").getAsString(); 
		 String name = pymentUpdateObject.get("name").getAsString(); 
		 String cvv = pymentUpdateObject.get("cvv").getAsString(); 
		 String exDate = pymentUpdateObject.get("exDate").getAsString();
		 
		String output= cardObj.updateCard(cardID, cardno, name, cvv, exDate);
		return output;
	}
	
	@DELETE
	@Path("/delete")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_XML)
	public String deletePayments(String cardData) {
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(cardData, "", Parser.xmlParser()); 
		 
		//Read the value from the element <paymentID>
		 String cardID = doc.select("cardID").text(); 
		 String output = cardObj.deleteCard(cardID); 
		 return output;
	}
	
	////////////////////////////////Payment/////////////////////////////////////////
	
	@GET
	@Path("/readPayment") 
	@Produces(MediaType.TEXT_PLAIN) 
	public String readPay() 
	 { 
	 return payObj.ReadPayment(); 
	 }
	
	@POST
	@Path("/pay") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String payNow(@FormParam("bID") String bID,
						@FormParam("total") Double total,
						@FormParam("paidAmount") Double paidAmount)throws SQLException
	{ 
	 String output = payObj.payBill(bID, total, paidAmount); 
	return output; 
	}
}
