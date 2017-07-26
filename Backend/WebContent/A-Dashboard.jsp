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
    <title>DLSU-SHS Online Library System</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Gudea:400,400i,700|Open+Sans:400,400i,600,600i,700,700i,800,800i" rel="stylesheet">
    <link href="css/global.css" rel="stylesheet">
    <link href="css/sidenavbar.css" rel="stylesheet">
    <link href="css/modal.css" rel="stylesheet">
    <link href="css/a-accountmanagement.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/css/bootstrap-select.min.css">
  </head>
  <body class = "body_home">
    <!-- Overlay for fixed sidebar -->
    <div class="sidebar-overlay"></div>

    <!-- Material sidebar -->
    <aside id="sidebar" class="sidebar sidebar-default open" role="navigation">
        <!-- Sidebar header -->
        <div class="sidebar-header header-cover">
            <!-- Sidebar brand image -->
            <a href = "LogOut" title = "Logout"><img src = "img/ic_logout.png" style = "float:right;margin-top:10px;margin-right:10px;" width = "30" height = "30"></a>
            <center>
                <div class="sidebar-image">
                    <img src="img/ic_admin.png" class = "img-rounded">
                </div>
            </center>
            <!-- Sidebar brand name -->
            <div class="sidebar-brand">
                ${fullname} <p style = "margin-top:-30px;font-size:12px;">${role}</p>
            </div>
        </div>

        <!-- Sidebar navigation -->
        <ul class="nav sidebar-nav">
            <li class="divider"></li>
            <li class = "active">
                <a href="#">
                    <img src = "img/ic_dashboard.png" class = "img_icons"/>
                    Dashboard
                </a>
            </li>
        </ul>
        <!-- Sidebar divider -->
        <!-- <div class="sidebar-divider"></div> -->

        <!-- Sidebar text -->
        <!--  <div class="sidebar-text">Text</div> -->
    </aside>
    
    <div class="wrapper">
        <!-- Sidebar Constructor -->
        <center>
            <div class="jumbotron">
                <h3> <img src="img/ic_dlsu.png" width = 80; height = 80; style = "margin-right:10px;"/>DLSU Senior High School (SHS) Online Library</h3>      
            </div>
        </center>        
        <div class="constructor">
            <center>
            <div class = "row">
                 <a href="#" class="" data-toggle="modal" data-target=".modal_addaccount">
                    <div class="col-sm-4">
                      <div class="thumbnail">
                        <div class="caption">
                          <h4 class="options-heading">Add an Account</h4>
                        </div>
                        <img src="img/ic_myaccount.png" alt="..." class="">
                      </div>
                    </div>
                  </a>

                  <a href="#" class="" data-toggle="modal" data-target="#daily-modal">
                    <div class="col-sm-4">
                      <div class="thumbnail">
                        <div class="caption">
                          <h4 class="options-heading">Export Status Report</h4>
                        </div>
                        <img src="img/ic_export.png" alt="..." class="">

                      </div>
                    </div>
                  </a>
            </div>
            </center>
        </div>
      </div>
      
     <!-- ADD ACCOUNT MODAL -->
     <div class="modal fade modal_addaccount" id="modal_addaccount" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
      <div class="modal-dialog">
        <div class="addaymodal-container" id="addaccount-container">
            <center>
              <form id = "form_editmeetingroom" class="form form-horizontal" action="AddUser" method="POST">
                <fieldset>
                  <legend style="margin-bottom:-10px;"></legend>
                  <h3><legend class="text-center"><b>ADD ACCOUNT</b></legend></h3>
                  <div class="form-group" >
                       <label class="col-sm-4 control-label" id = "label_title">Type</label>
                           <div class="col-sm-8">
                            <select name = "usertype" class="selectpicker show-tick" data-width="250" 
                            		style = "height:25px;" required = "true">
                             <option value = "1">Student</option>
                             <option value = "2">Faculty</option>
                             <option value = "3">Library Manager</option>
                             <option value = "4">Library Staff</option>
                            </select>
                           </div>
                   </div>
                  <div class="form-group" >
                       <label class="col-sm-4 control-label" id = "label_title">First Name</label>
                           <div class="col-sm-8">
                               <input name = "firstname" type="text" class="form-control" id="input_firstname"
                               		  required = "true">
                           </div>
                   </div>

                  <div class="form-group" >
                       <label class="col-sm-4 control-label" id = "label_title">M.I.</label>
                           <div class="col-sm-8">
                               <input name = "middleinitial" type="text" class="form-control" id="input_mi" 
                               	      required = "true">
                           </div>
                   </div>
                  <div class="form-group" >
                       <label class="col-sm-4 control-label" id = "label_title">Last Name</label>
                           <div class="col-sm-8">
                               <input name = "lastname" type="text" class="form-control" id="input_lastname"
                               		  required = "true">
                           </div>
                   </div>
                  <div class="form-group" >
                       <label class="col-sm-4 control-label" id = "label_title">ID Number</label>
                           <div class="col-sm-8">
                              <input name = "idnum" type="number" class="form-control" id="input_idnum" maxlength="8"  
                              		 oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
                              		 required = "true">
                           </div>
                   </div>
                  <div class="form-group" >
                       <label class="col-sm-4 control-label" id = "label_title">Username</label>
                           <div class="col-sm-8 ">
                              <input name = "username" type="text" class="form-control" id="input_username" data-toggle="tooltip" data-placement="right"
                                      title="Username must be chuchuchu." required = "true">
                           </div>
                   </div>
                  <div class="form-group" >
                       <label class="col-sm-4 control-label" id = "label_title">Password</label>
                           <div class="col-sm-8">
                               <input name = "password" type="password" class="form-control" id="input_password"
                               	      required = "true">
                           </div>
                   </div>
                  <div class="form-group" >
                       <label class="col-sm-4 control-label" id = "label_title"></label>
                           <div class="col-sm-8">
                               <input name = "confirmpassword" type="passord" class="form-control" id="input_confirmpassword"
                                      placeholder = "Re-enter password" required = "true">
                           </div>
                   </div>
                  <div class="form-group" >
                       <label class="col-sm-4 control-label" id = "label_title">Email Address</label>
                           <div class="col-sm-8">
                               <input name = "emailaddress" type="email" class="form-control" id="input_emailaddress">
                           </div>
                   </div>
                  <div class="form-group" >
                       <label class="col-sm-4 control-label" id = "label_title">Birthday</label>
                           <div class="col-sm-8">
                               <input name = "birthday" type="date" class="form-control" id="input_birthday"
                               		  required = "true">
                           </div>
                   </div>
                  <div class="form-group" >
                       <label class="col-sm-4 control-label" id = "label_title">Security Question</label>
                           <div class="col-sm-8">
                               <select name = "secretquestion" id="select_secretquestion" class = "form-control"
                               		   required = "true">
                                  <option disabled = "disabled">---</option>
                                  <option>What was your childhood nickname?</option>
                                  <option>Who was your childhood hero?</option>
                                  <option>What is the name of the city where you got lost?</option>
                               </select>
                           </div>
                           
                   </div>
                  <div class="form-group" >
                       <label class="col-sm-4 control-label" id = "label_title">Security Answer</label>
                       <div class="col-sm-8">
                            <input name = "secretanswer" type="text" class="form-control" id = "input_secretanswer"
                            	   required = "true">
                       </div>
                   </div>
                   <br>
                  <div class="text-center">
                    <button id="button_cancel" type="button" class="btn btn-danger col-xs-4 cancel" data-dismiss="modal" style="
                      margin-left:20px;margin-right:30px;font-size:14px;"> <i class="glyphicon glyphicon-remove"></i> CANCEL </button>
                      
                    <button id="button_addaccount" type="submit" class="btn btn-primary col-xs-4 submit" style="margin-right:30px;font-size:14px;"> <i class="glyphicon glyphicon-plus"></i> ADD </button>
                  </div>

                </fieldset>
              </form>
          </center>
        </div>
      </div>
    </div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/sidenavbar.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script>
  	<!-- ALERT SCRIPTS -->
    <c:if test ="${statusAdd != null}">
    <script>
    	$(document).ready(function(){
    		alert("${statusAdd}");
    	});
    </script>
    </c:if>
  </body>
  
</html>