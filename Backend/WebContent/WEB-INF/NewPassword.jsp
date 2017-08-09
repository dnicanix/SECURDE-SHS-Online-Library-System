<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Forgot Password || DLSU-SHS Online Library System</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Gudea:400,400i,700|Open+Sans:400,400i,600,600i,700,700i,800,800i" rel="stylesheet">
    <link href="css/global.css" rel="stylesheet">
    <link href="css/forgotpassword.css" rel="stylesheet">
    <link href="css/newpassword.css" rel="stylesheet">
</head>
<body>
    <div class = "container">
        <div class = "main-forgotpassword main-center">
           <div class = "row">
               <p class = "title" style = "text-align:center;"> Senior High School (SHS) Online Library </p>
           </div>
           
           <div class = "row">
               <p class = "subtitle" style="margin-left:10px">
                   <a title="Back to Log In page" href="LoginServlet">
                      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                   </a>

                    NEW PASSWORD
               </p>
           </div>
           
           <div class = "row">
               <div class = "col-md-3"></div>
               <div class = "col-md-6">
                   <form id = "form_newpassword" action="NewPassword" method="POST">
                        <!--  
                        <div class="form-group row">
                            <label for="currentpassword">Current Password</label>
                            <input name = "currentpassword" type="password" type="password" class="form-control" id="currentpassword">
                        </div>  
                        -->     
                        <div class="form-group row">
                            <label for="newpassword">Enter New Password</label>
                            <input name="newpassword" type="password" class="form-control" id="newpassword"
                            	   data-toggle="tooltip" data-placement="right"
                                   minlength = "8" maxlength = "20" title="Password must be atleast 8 characters
                                     										with the strength Medium/Strong." required="true">
                          	<span id="passstrength" style="font-weight:bold;"><b></b></span>
                        </div>
                        <div class="form-group row">
                            <label for="confirmpassword" id = "labelforconfirmpass">Re-enter new password</label>
                            <input name="confirmpassword" type="password" class="form-control" id="confirmpassword"
                            	    minlength = "8" maxlength = "20" required = "true">
                            <c:if test ="${errorMessage != null}">
		                   		<label style="color:red;">${errorMessage}</label>
		                   	</c:if> 
                        </div>
                        <c:if test ="${errorMessage != null}">
                        <button id = "button_newpassword" type="submit" class="btn btn-primary btn-sm"
                        		style = "margin-top:-15px;"> <b> > > </b> </button> 
                        </c:if>
                        <c:if test ="${errorMessage == null}">
                        <button id = "button_newpassword" type="submit" class="btn btn-primary btn-sm"> 
                        		<b> > > </b> </button>   
                        </c:if>
                        
                   </form>
               </div>
           </div>
            
           <div class = "row" style = "margin-top:50px;">
               <p style = "text-align:center">Copyright (c) 2017 All Rights Reserved.</p>
           </div>
           <div class = "row">
               <p style = "text-align:center">De La Salle University, Manila.</p>
           </div>
            
        </div>
    </div>


      
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    	<script>
		$('#newpassword').keyup(function(e) {
		     var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
		     var mediumRegex = new RegExp("^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g");
		     var enoughRegex = new RegExp("(?=.{6,}).*", "g");
		     if (false == enoughRegex.test($(this).val())) {
		    	 	 $('#labelforconfirmpass').css('margin-top', '-10px');
		             $('#passstrength').html('More Characters...');
		             $('#passstrength').css('color', 'red');
		             $('#button_newpassword').prop('disabled', true);
		     } else if (strongRegex.test($(this).val())) {
		    	 	 $('#labelforconfirmpass').css('margin-top', '-10px');
		             $('#passstrength').css('color', 'green');
		             $('#passstrength').html('Strength: Strong!');
		             $('#button_newpassword').prop('disabled', false);
		     } else if (mediumRegex.test($(this).val())) {
		    	 	 $('#labelforconfirmpass').css('margin-top', '-10px');
		    	 	 $('#passstrength').css('color', 'orange');
		             $('#passstrength').html('Strength: Medium!');
		             $('#button_newpassword').prop('disabled', false);
		     } else {
		    	 	 $('#labelforconfirmpass').css('margin-top', '-10px');
		    	 	 $('#passstrength').css('color', 'red');
		             $('#passstrength').html('Strength: Weak!');
		             $('#button_newpassword').prop('disabled', true);
		     }
		     return true;
		});
	</script>
  </body>
</html>