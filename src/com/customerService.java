package com;

import model.customer;

//for REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//for JSON
import com.google.gson.*;

//for XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Customer")
public class customerService {
	
	customer customerObj = new customer();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readCustomer() {
		
		return customerObj.readCustomer();
		
		
		
	}
	
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String insertCustomer(@FormParam("cusName") String cusName,
							@FormParam("cusPno") String cusPno,
							@FormParam("cusAddress") String cusAddress,
							@FormParam("cusEmail") String cusEmail)
	{
		
		String output = customerObj.insertCustomer(cusName, cusPno, cusAddress, cusEmail);
		return output;
		
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCustomer(String cusData)
	{
		//Convert input string to a JSON object
		JsonObject customerObject = new JsonParser().parse(cusData).getAsJsonObject();
		
		//Read values from JSON object
		String cusID = customerObject.get("cusID").getAsString();
		String cusName = customerObject.get("cusName").getAsString();
		String cusPno = customerObject.get("cusPno").getAsString();
		String cusAddress = customerObject.get("cusAddress").getAsString();
		String cusEmail = customerObject.get("cusEmail").getAsString();
		
		String output = customerObj.updateCustomer(cusID, cusName, cusPno, cusAddress, cusEmail);
		return output;
		
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCustomer(String cusData)
	{
		//Convert input string to a XML object
		Document doc = Jsoup.parse(cusData, "", Parser.xmlParser());
		
		//Read values from XML object
		String cusID = doc.select("cusID").text();
		
		String output = customerObj.deleteCustomer(cusID);
		return output;
		
	}

}

