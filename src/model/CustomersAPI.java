package model;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Servlet implementation class CustomersAPI
 */
@WebServlet("/CustomersAPI")
public class CustomersAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	customer customerObj = null;
    
    public CustomersAPI() {
        super();
        
    }
    
    

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("come to post");
		customer Customer = new customer();
		String output = Customer.insertCustomer(request.getParameter("cusName"),
				request.getParameter("cusPno"),
				request.getParameter("cusAddress"),
				request.getParameter("cusEmail"));
				response.getWriter().write(output);
				
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("came update");
		customer Customer = new customer();
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		 String output = Customer.updateCustomer(paras.get("hidCustomerIDSave").toString(),
		 paras.get("cusName").toString(),
		paras.get("cusPno").toString(),
		paras.get("cusAddress").toString(),
		paras.get("cusEmail").toString()
		);
		response.getWriter().write(output);
		
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		customer Customer = new customer();
		Map paras = getParasMap(request);
		 String output = Customer.deleteCustomer(paras.get("cusID").toString());
		response.getWriter().write(output);
	}
	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request)
		{
			Map<String, String> map = new HashMap<String, String>();
	try
	 	{
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ?
			scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			for (String param : params)
				{ 
					String[] p = param.split("=");
					map.put(p[0], p[1]);
				 }
	 	}
		catch (Exception e)
			 {
			 	}
		return map;
				}

}