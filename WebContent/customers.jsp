<%@page import="model.customer" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Management</title>
<link rel="stylesheet" href="Views/bootstrap.css">

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
			    <div class="col-3"></div>
				<h1>Customer Management</h1>
				<form id="formItem" name="formItem">
					Customer name: 
					    <input id="cusName"
						name="cusName" type="text" class="form-control form-control-sm"> 
					<br> Customer Phone no: 
					    <input id="cusPno" name="cusPno"
						type="text" class="form-control form-control-sm"> 
					<br> Customer Address: 
						<input id="cusAddress" name="cusAddress"
						type="text" class="form-control form-control-sm"> 
					<br> Email:
					    <input id="cusEmail" name="cusEmail" type="text"
						class="form-control form-control-sm"> 
					
					<br> 
					<input id="btnSave" name="btnSave" type="button" value="Save" 
					          class="btn btn-primary"> 
					<input type="hidden" id="hidCustomerIDSave" name="hidCustomerIDSave" value="">
				</form>
				<br>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divCustomersGrid">
				<%
				customer customerObj = new customer();
 					out.print(customerObj.readCustomer());
 					%>
				
	
				</div>
			</div>
		</div>
	</div>
</body>
<script src="Components/jquery.min.js" type="text/javascript"></script>
<script src="Components/customers.js" type="text/javascript"></script>
</html>