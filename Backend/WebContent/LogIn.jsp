 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Log In | DLSU-SHS Online Library System</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Gudea:400,400i,700|Open+Sans:400,400i,600,600i,700,700i,800,800i" rel="stylesheet">
    <link href="css/global.css" rel="stylesheet">
    <link href="css/login.css" rel="stylesheet">
  </head>
  
  <body>
    <div class = "container">
        <div class = "main-login main-center">
           <div class = "row">
               <p class = "title" style = "text-align:center;"> Senior High School (SHS) Online Library </p>
           </div>
           <div class = "row">
               <div class = "col-md-5">
                   <img id = "ic_dlsu" src = "img/ic_dlsu.png" width = "170" height = "170">
               </div>
               <div class = "col-md-7">   
                   <form id = "form_login" action = "LogIn" method = "POST">
                   <c:if test ="${errorMessage != null}">
                   	<label style="color:red;margin-top:-20px;">${errorMessage}</label>
                   </c:if>  
                    <div class="form-group row">
                        <label for="username">Username</label>
                        <input name = "username" type="text" class="form-control" id="input_username" required="true">
                    </div>
                    <div class="form-group row">
                        <label for="password">Password</label>
                        <input name = "password" type="password" class="form-control" id="input_password"
                        required="true">
                    </div>
              
                    <button id = "btn_login" type="submit" class="btn btn-primary btn-sm">LOG IN</button> 
                   </form>
                   
                   <div id = "div_forgotpass_signup">
                       <a href = "ForgotPassword.jsp">Forgot Password?</a> || <a href = "SignUp.jsp">Sign Up</a>
                   </div>
               </div>
           </div>
           
            
           <div class = "row" style = "margin-top:30px;">
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
  	<!-- ALERT SCRIPTS -->
    <c:if test ="${statusAdd != null}">
    <script>
    	$(document).ready(function(){
    		alert("${statusAdd}");
    	});
    </script>
    </c:if>
    <c:if test ="${statusChangePassword!= null}">
    <script>
    	$(document).ready(function(){
    		alert("${statusChangePassword}");
    	});
    </script>
    </c:if>
  </body>
  
</html>