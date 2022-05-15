//Hide the alters on page load
$(document).ready(function()
{

	$("#alertSuccess").hide();

 	$("#alertError").hide();

}); 

$(document).on("click", "#btnSave", function(event)
{
	console.log($("#hidCustomerIDSave").val());
	
	// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();

// Form validation-------------------
var status = validateItemForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
 var type = ($("#hidCustomerIDSave").val() == "") ? "POST" : "PUT";
console.log(type); 
 $.ajax(
 {
 url : "CustomersAPI",
 type : type,
 data : $("#formItem").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onCustomerSaveComplete(response.responseText, status);
 }
 });

});

// CLIENT-MODEL================================================================
function validateItemForm()
{
// Customer name
if ($("#cusName").val().trim() == "")
 {
 return "Insert Customer Name.";
 }
// Customer Phone no
if ($("#cusPno").val().trim() == "")
 {
 return "Insert Customer Phone number.";
 }
// is numerical value
var tmpContact = $("#cusPno").val().trim();
if (!$.isNumeric(tmpContact))
 {
 return "Insert a numerical value for Contact number.";
 }

//  Customer Address
if ($("#cusAddress").val().trim() == "")
 {
 return "Insert customer address.";
 }
// Email
if ($("#cusEmail").val().trim() == "")
 {
 return "Insert your Email.";
 }
return true;
}

$(document).on("click", ".btnUpdate", function()
{
 $("#hidCustomerIDSave").val($(this).data("cusid"));
 $("#cusName").val($(this).closest("tr").find('td:eq(0)').text());
 $("#cusPno").val($(this).closest("tr").find('td:eq(1)').text());
 $("#cusAddress").val($(this).closest("tr").find('td:eq(2)').text());
 $("#cusEmail").val($(this).closest("tr").find('td:eq(3)').text());
 
});

function onCustomerSaveComplete(response, status)
{
	if (status == "success")
 	{
 		var resultSet = JSON.parse(response);
 		if (resultSet.status.trim() == "success")
 		{
 			$("#alertSuccess").text("Successfully saved.");
 			$("#alertSuccess").show();
			
 			$("#divItemsGrid").html(resultSet.data);
			
 		} else if (resultSet.status.trim() == "error")
 			{
			 	$("#alertError").text(resultSet.data);
 			 	$("#alertError").show();
 			}	
 	} else if (status == "error")
 		{
 			$("#alertError").text("Error while saving.");
 			$("#alertError").show();
 		} else
 			{
 				$("#alertError").text("Unknown error while saving..");
 				$("#alertError").show();
 			} 
		
 $("#hidCustomerIDSave").val("");
 $("#formItem")[0].reset();
}


$(document).on("click", ".btnRemove", function()
{
	var id = $(this).data("cusid");
	console.log("id is :"+id)
 $.ajax(
 {
 url : "CustomersAPI",
 type : "DELETE",
 data : "cusID=" + id,
 dataType : "text",
 complete : function(response, status)
 {
	console.log(id)
 onCustomerDeleteComplete(response.responseText, status);
 }
 });
});

function onCustomerDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divItemsGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
	$("#hidCustomerIDSave").val(""); 
	$("#formItem")[0].reset(); 
}









