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
                   <form id = "form_signup" class = "form-horizontal" action = "SignUpServlet" method = "POST">
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
                                      title="Username must be chuchuchu." required="true">
                            </div>
                        </div>
                       <div class="form-group">
                            <label class="col-sm-3 control-label" id = "label_password" required = "true">Password</label>
                            <div class="col-sm-9">
                              <input name = "password" type="password" class="form-control" id="input_password" data-toggle="tooltip" data-placement="right"
                                      title="Password must be chuchuchuc." required="true">
                            </div>
                       </div>
                       <div class="form-group">
                            <label class="col-sm-3 control-label" id = "label_confirmpassword"></label>
                            <div class="col-sm-7">
                              <input name = "confirmpassword" type="password" class="form-control" id="input_confirmpassword" placeholder="Re-enter password"
                              required="true">          
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function () {
            $('[data-toggle="tooltip"]').tooltip();
        });
    </script>

  </body>
  
</html>