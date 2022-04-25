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

import model.CustomerFeedback;

@Path("/feedback") 
public class CustomerFeedbackService{ 
	
CustomerFeedback feedbackObject=new CustomerFeedback();
	
@GET
@Path("/read") 
@Produces(MediaType.TEXT_PLAIN) 
public String readFeedback() 
{ 
return feedbackObject.ReadFeedback(); 
} 



@POST
@Path("/") 
@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
@Produces(MediaType.TEXT_PLAIN) 
public String insertFeedback(@FormParam("fbType") String fbType, 
@FormParam("fbDesc") String fbDesc, 
@FormParam("fbRate") String fbRate)
{ 
String output = feedbackObject.insertFeedback(fbType, fbDesc, fbRate); 
return output; 
}


@PUT
@Path("/update")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String updateFeedback(String feedbackData) {
	
	//Convert the input string to a JSON object 
	 JsonObject feedbackUpdateObject = new JsonParser().parse(feedbackData).getAsJsonObject();
	 
	//Read the values from the JSON object
	 String feedbackID = feedbackUpdateObject.get("feedbackID").getAsString(); 
	 String fbType = feedbackUpdateObject.get("fbType").getAsString(); 
	 String fbDesc = feedbackUpdateObject.get("fbDesc").getAsString(); 
	 String fbRate = feedbackUpdateObject.get("fbRate").getAsString(); 
	 
	String output= feedbackObject.updateFeedback(feedbackID, fbType, fbDesc, fbRate);
	return output;
}


@DELETE
@Path("/delete")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.APPLICATION_XML)
public String deleteFeedback(String feedbackData) {
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(feedbackData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <feedbackID>
	 String feedbackID = doc.select("feedbackID").text(); 
	 String output = feedbackObject.deleteFeedback(feedbackID); 
	 return output;
}



} 

 
