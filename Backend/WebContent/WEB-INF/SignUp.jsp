<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Sign Up | DLSU-SHS Online Library System</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Gudea:400,400i,700|Open+Sans:400,400i,600,600i,700,700i,800,800i" rel="stylesheet">
    <link href="css/global.css" rel="stylesheet">
    <link href="css/signup.css" rel="stylesheet">
  </head>
  
  <body>
    <div class = "container">
        <div class = "main-signup main-center">
           <div class = "row">
               <p class = "title" style = "text-align:center;"> Senior High School (SHS) Online Library </p>
           </div>
           
           <div class = "row">
               <p class = "subtitle" style="margin-left:60px">CREATE AN ACCOUNT</p>
           </div>
           
           <div class = "row">
               <div class = "col-md-5">
                   <img id = "ic_archer" src = "img/ic_archer.png" width = "208" height = "187">
               </div>
               <div class = "col-md-7">
                   <form id = "form_signup" class = "form-horizontal" action = "SignUp" method = "POST">
	                    <c:if test ="${errorMessage != null}">
		                    <center>
		                   		<label style="color:red;">${errorMessage}</label>
		                   	</center>
	                   	</c:if>                    	
                       <div class="form-group">
                            <label class="col-sm-3 control-label" id = "label_name">Name</label>
                            <div class="col-sm-9">
                               <div class="form-inline">
                                    <div class="form-group">
                                        <input name = "firstname" type="text" id = "input_firstname" class="form-control" placeholder="First Name"
                                        required="true"/>
                                    </div>
                                    <div class="form-group">
                                        <input name = "middleinitial" type="text" id = "input_middleinitial" class="form-control" placeholder="MI"
                                        required="true"/>
                                    </div>
                                    <div class="form-group">
                                        <input name = "lastname" type="text" id = "input_lastname" class="form-control" placeholder="Last Name"
                                        required="true"/>
                                    </div>
                                </div>
                            </div>

                       </div>
                       <div class="form-group" style = "text-align:left;">
                            <label class="col-sm-3 control-label" id = "label_idnumber">ID Number</label>
                            <div class="col-sm-9">
                              <input name = "idnum" type="number" class="form-control" id="input_name" maxlength="8"  
                              oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
                              required="true">
                            </div>
                       </div>                     
                       <div class="form-group">
                            <label class="col-sm-3 control-label" id = "label_username">Username</label>
                            <div class="col-sm-9">
                              <input name = "username" type="text" class="form-control" id="input_username" data-toggle="tooltip" data-placement="right"
                                      title="Username must be unique." required="true">
                            </div>
                        </div>
                       <div class="form-group">
                            <label class="col-sm-3 control-label" id = "label_password" required = "true">Password</label>
                            <div class="col-sm-8">
                              <input name = "password" type="password" class="form-control" id="input_password" data-toggle="tooltip" data-placement="right"
                                     minlength = "8" maxlength = "20" title="Password must be atleast 8 characters
                                     										with the strength Medium/Strong." required="true">
                              
                              <span id="passstrength" style="font-weight:bold;"></span>
                            </div>
                       </div>
                       <div class="form-group">
                            <label class="col-sm-3 control-label" id = "label_confirmpassword"></label>
                            <div class="col-sm-7">
                              <input name = "confirmpassword" type="password" class="form-control" id="input_confirmpassword" placeholder="Re-enter password"
                              		 onkeyup = "checkPass(); return false;" required="true">   
                              <span id="confirmMessage"></span>       
                            </div>
                       </div>
                       <div class="form-group">
                            <label class="col-sm-3 control-label" id = "label_email">Email Add.</label>
                            <div class="col-sm-9">
                              <input name = "emailaddress" type="email" class="form-control" id="input_email"
                              required="true">
                            </div>
                        </div>
                       <div class="form-group">
                            <label class="col-sm-3 control-label" id = "label_birthday">Birthday</label>
                            <div class="col-sm-9">
                              <input name = "birthday" type="date" class="form-control" id="input_birthday"
                              required="true">
                            </div>
                       </div>
                       <div class="form-group" style="margin-left:-312px;">
                            <label class="col-sm-5 control-label">Security Question</label>
                            <div class="col-sm-7">
                               <select name = "secretquestion" id="select_securityquestion" class = "form-control">
                                  <option disabled = "disabled">---</option>
                                  <option>What was your childhood nickname?</option>
                                  <option>Who was your childhood hero?</option>
                                  <option>What is the name of the city where you got lost?</option>
                               </select>
                            </div>
                        </div>
                       <div class="form-group" style="margin-left:-310px;">
                            <label class="col-sm-5 control-label" id = "label_securityanswer">Answer</label>
                            <div class="col-sm-7">
                              <input name = "secretanswer" type="text" class="form-control" id="input_securityanswer" 
                              required="true">    
                            </div>
                        </div>

                    <button id = "btn_signup" type="submit" class="btn btn-primary btn-sm">SIGN UP</button> 
                   </form>
               </div>
           </div>
        </div>
    </div>

      
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function () {
            $('[data-toggle="tooltip"]').tooltip();
        });
    </script>
    
    <script>
		function checkPass()
		{
		    //Store the password field objects into variables ...
		    var pass1 = document.getElementById('input_password');
		    var pass2 = document.getElementById('input_confirmpassword');
		    //Store the Confimation Message Object ...
		    var message = document.getElementById('confirmMessage');
		    //Set the colors we will be using ...
		    var goodColor = "transparent";
		    var badColor = "red";
		    //Compare the values in the password field 
		    //and the confirmation field
		    if(pass1.value == pass2.value){
		        //The passwords match. 
		        //Set the color to the good color and inform
		        //the user that they have entered the correct password 
		        //pass2.style.backgroundColor = goodColor;
		        message.style.color = "green";
		        message.innerHTML = "Passwords Match!";
		        document.getElementById("btn_signup").disabled = false;
		    }else{
		        //The passwords do not match.
		        //Set the color to the bad color and
		        //notify the user.
		        //pass2.style.backgroundColor = goodColor;
		        message.style.color = badColor;
		        message.innerHTML = "Passwords Do Not Match!";
		        document.getElementById("btn_signup").disabled = true;
		    }
		}  
	</script>

	<script>
		$('#input_password').keyup(function(e) {
		     var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
		     var mediumRegex = new RegExp("^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g");
		     var enoughRegex = new RegExp("(?=.{6,}).*", "g");
		     if (false == enoughRegex.test($(this).val())) {
		             $('#passstrength').html('More Characters...');
		             $('#passstrength').css('color', 'red');
		             $('#btn_signup').prop('disabled', true);
		     } else if (strongRegex.test($(this).val())) {
		             $('#passstrength').css('color', 'green');
		             $('#passstrength').html('Strength: Strong!');
		             $('#btn_signup').prop('disabled', false);
		     } else if (mediumRegex.test($(this).val())) {
		    	 	 $('#passstrength').css('color', 'orange');
		             $('#passstrength').html('Strength: Medium!');
		             $('#btn_signup').prop('disabled', false);
		     } else {
		    	 	 $('#passstrength').css('color', 'red');
		             $('#passstrength').html('Strength: Weak!');
		             $('#btn_signup').prop('disabled', true);
		     }
		     return true;
		});
	</script>

  </body>
  
</html>