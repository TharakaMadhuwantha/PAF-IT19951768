package model;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class customer {
	
	public String insertCustomer(String name, String pno, String address, String email) 
	{ 
		String output = ""; 
		try
		{ 
			connection con = new connection();
			Connection con1 = con.connect(); 
			if (con1 == null) 
			{return "Error while connecting to the database for inserting."; } 
			// create a prepared statement
			String query = " insert into customer(`cusID`,`cusName`,`cusPno`,`cusAddress`,`cusEmail`)"
					+ " values (?, ?, ?, ?, ? )"; 
			PreparedStatement preparedStmt = con1.prepareStatement(query); 
			// binding values
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, name); 
			preparedStmt.setString(3, pno);
			preparedStmt.setString(4, address);
			preparedStmt.setString(5, email); 
		
			// execute the statement
			preparedStmt.execute(); 
			con1.close(); 
			String newItems = readCustomer();
			
			System.out.println(newItems);

			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";

		} 
		catch (Exception e) 
		{ 
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the inquiry.\"}";
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
	public String readCustomer() 
	{ 
		System.out.println("came read java");
		String output = ""; 
		try
		{ 
			connection con = new connection();
			Connection con1 = con.connect(); 
			if (con1 == null) 
			{return "Error while connecting to the database for reading."; } 
			// Prepare the html table to be displayed
			output += "<table class='table table-dark table-hover' border='1'><tr><th>Customer Name</th><th>Customer Phone No</th>" 
					+ "<th>Customer Address</th>" 
					+ "<th>Email</th>" 
					+ "<th>Update</th><th>Remove</th></tr>"; 

			String query = "select * from customer"; 
			Statement stmt = con1.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
			// iterate through the rows in the result set
			while (rs.next()) 
			{ 
				String cusID = Integer.toString(rs.getInt("cusID")); 
				String cusName = rs.getString("cusName"); 
				String cusPno = rs.getString("cusPno"); 
				String cusAddress = rs.getString("cusAddress");
				String cusEmail = rs.getString("cusEmail");
				
				// Add into the html table
				output += "<tr><td><input type='hidden' name='hidCustomerIDUpdate' id='hidCustomerIDUpdate' value='"+cusID+"'>" + cusName + "</td>"; 
				
				output += "<td>" + cusPno + "</td>"; 
				output += "<td>" + cusAddress + "</td>";
				output += "<td>" + cusEmail + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-cusid='"+cusID+"'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-secondary' data-cusid='"+cusID+"'></td></tr>"; 
			} 
			con1.close(); 
			// Complete the html table
			output += "</table>"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while reading the inquiries."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
	public String updateCustomer(String id, String name, String pno, String address, String email) 

	{ 
		System.out.println("came update java class");
		String output = ""; 
		try
		{ 
			connection con = new connection();
			Connection con1 = con.connect(); 
			if (con1 == null) 
			{return "Error while connecting to the database for updating."; } 
			// create a prepared statement
			String query = "UPDATE customer SET cusName=?,cusPno=?,cusAddress=?,cusEmail=? WHERE cusID=?"; 
			PreparedStatement preparedStmt = con1.prepareStatement(query); 
			// binding values
			preparedStmt.setString(1, name); 
			preparedStmt.setString(2, pno); 
			preparedStmt.setString(3, address); 
			preparedStmt.setString(4, email);
			preparedStmt.setInt(5, Integer.parseInt(id)); 
			// execute the statement
			preparedStmt.execute(); 
			con1.close(); 
			String newItems = readCustomer();

			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		} 
		catch (Exception e) 
		{ 
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}";
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
	public String deleteCustomer(String cusID) 
	{ 
		String output = ""; 
		try
		{ 
			connection con = new connection();
			Connection con1 = con.connect(); 
			if (con1 == null) 
			{return "Error while connecting to the database for deleting."; } 
			// create a prepared statement
			String query = "delete from customer where cusID=?"; 
			PreparedStatement preparedStmt = con1.prepareStatement(query); 
			// binding values
			preparedStmt.setString(1, cusID); 
			// execute the statement
			preparedStmt.execute(); 
			con1.close(); 
			String newItems = readCustomer();

			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		} 
		catch (Exception e) 
		{ 
			output =  "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}";
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
}
