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
    <link href="css/meetingrooms.css" rel="stylesheet">
    <link href="css/m-meetingrooms.css" rel="stylesheet">
    <link href="css/sidenavbar.css" rel="stylesheet">
    <link href="css/modal.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/daterangepicker.css" />
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
            <a href = "#" title = "Logout"><img src = "img/ic_logout.png" style = "float:right;margin-top:10px;margin-right:10px;" width = "30" height = "30"></a>
            <center>
                <div class="sidebar-image">
                    <img src="img/ic_librarian.png" class = "img-rounded">
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
            <li>
                <a href="#">
                    <img src = "img/ic_myaccount.png" class = "img_icons"/>
                    My Account
                </a>
            </li>
            <li>
                <a href="SM-LibraryCollection">
                    <img src = "img/ic_librarycollection.png" class = "img_icons"/>
                    Library Collection
                </a>
            </li>
            <li class = "active">
                <a href="#">
                    <img src = "img/ic_meetingrooms.png" class = "img_icons"/>
                    Meeting Rooms
                </a>
            </li>
            <li>
                <a href="#">
                    <img src = "img/ic_export.png" class = "img_icons"/>
                    Generate Status Report
                </a>
            </li>
        </ul>
        <!-- Sidebar divider -->
        <!-- <div class="sidebar-divider"></div> -->

        <!-- Sidebar text -->
        <!--  <div class="sidebar-text">Text</div> -->
    </aside>

    <div class="wrapper">
        <center>
            <div class="jumbotron">
                <h3> <img src="img/ic_dlsu.png" width = 80; height = 80; style = "margin-right:10px;"/>DLSU Senior High School (SHS) Online Library</h3>      
            </div>
        </center>  
        <!-- Sidebar Constructor -->
        <div class="constructor">
            <div class = "row">
                <p class="pageheader"> <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span> Meeting Rooms</p>
            </div>
            <center>
            <fieldset class="fieldset_legend" style = "max-width:335px;">
                <legend class="fieldset_legend" id = "title" style = "font-size:16px;"> Legend </legend>
                <div class = "row">
                    <div class = "col-sm-6">
                        <label><div id = "div_availablelegend" style = "float:left;"> </div> Available</label>
                    </div>
                    <div class = "col-sm-6">
                        <label><div id = "div_reservedlegend" style = "float:left;"> </div> Reserved</label>
                    </div>
                </div>
                
            </fieldset>
            <br>
            <form class="form-horizontal">
                <div class="form-group">
                    <div class = "col-sm-4"></div>
                    <div class = "col-sm-2">
                        <label class="col-sm-2 control-label" id = "label_name">Date:</label>                 
                    </div>    
                    <div class="col-sm-6" style = "text-align:left;">
                        <input style="width:150px;" type="text" class="form-control" id="dailydate" name="dailydate" />
                    </div>
                </div>
            </form>

            <table id = "table_meetingrooms" class="table table-bordered">
                <thead>
                    <tr>
                        <th>  </th>
                        <th> 9:00 AM </th>
                        <th> 10:00 AM </th>
                        <th> 11:00 AM </th>
                        <th> 12:00 NN </th>
                        <th> 1:00 PM </th>
                        <th> 2:00 PM </th>
                        <th> 3:00 PM </th>
                        <th> 4:00 PM </th>
                        <th> 5:00 PM </th>
                        <th> 6:00 PM </th>
                        <th> 7:00 PM </th>
                        <th> 8:00 PM </th>
                    </tr>
                </thead>
              <tbody>
                    <tr>
                        <td> 123 </td>
                        
            			<c:forEach var = "c" items = "${rr1}" varStatus="loop">
	            			<c:choose>     
		            			<c:when test ="${(c.starttime == '9:00 AM' || c.starttime == '10:00 AM' || 
		            							c.starttime == '11:00 AM' || c.starttime == '12:00 NN' ||
		            							c.starttime == '1:00 PM' || c.starttime == '2:00 PM' ||
		            							c.starttime == '3:00 PM' || c.starttime == '4:00 PM' ||
		            							c.starttime == '5:00 AM' || c.starttime == '6:00 AM' ||
		            							c.starttime == '7:00 AM' || c.starttime == '8:00 AM')
		            							&& (c.userid != 0)}">                
		                        	<td id = "td_reserved" data-toggle = "modal" data-target = ".modal_delete"
		                        		name = "${c.reservedroomid}"> </td>
		                        </c:when>    
		                        <c:otherwise>
		                        	<td id = "td_available"><a id = "123 ${c.starttime}" class = "a_available">&nbsp;</a> </td>
		                        </c:otherwise>      
	
	                        </c:choose>
                        </c:forEach>
                    </tr>
                    <tr>
                        <td> 456 </td>
            			<c:forEach var = "c" items = "${rr2}" varStatus="loop">
	            			<c:choose>     
		            			<c:when test ="${(c.starttime == '9:00 AM' || c.starttime == '10:00 AM' || 
		            							c.starttime == '11:00 AM' || c.starttime == '12:00 NN' ||
		            							c.starttime == '1:00 PM' || c.starttime == '2:00 PM' ||
		            							c.starttime == '3:00 PM' || c.starttime == '4:00 PM' ||
		            							c.starttime == '5:00 AM' || c.starttime == '6:00 AM' ||
		            							c.starttime == '7:00 AM' || c.starttime == '8:00 AM')
		            							&& (c.userid != 0)}">                
		                        	<td name = "${c.reservedroomid}" id = "td_reserved" data-toggle = "modal" data-target = ".modal_delete"> </td>
		                        </c:when>    
		                        <c:otherwise>
		                        	<td id = "td_available"><a id = "456 ${c.starttime}" class = "a_available">&nbsp;</a> </td>
		                        </c:otherwise>      
	
	                        </c:choose>
                        </c:forEach>
                    </tr>
                    <tr>
                      <tr>
                        <td> 789 </td>
            			<c:forEach var = "c" items = "${rr3}" varStatus="loop">
	            			<c:choose>     
		            			<c:when test ="${(c.starttime == '9:00 AM' || c.starttime == '10:00 AM' || 
		            							c.starttime == '11:00 AM' || c.starttime == '12:00 NN' ||
		            							c.starttime == '1:00 PM' || c.starttime == '2:00 PM' ||
		            							c.starttime == '3:00 PM' || c.starttime == '4:00 PM' ||
		            							c.starttime == '5:00 AM' || c.starttime == '6:00 AM' ||
		            							c.starttime == '7:00 AM' || c.starttime == '8:00 AM')
		            							&& (c.userid != 0)}">                
		                        	<td name = "${c.reservedroomid}" id = "td_reserved" data-toggle = "modal" data-target = ".modal_delete"}'> </td>
		                        </c:when>    
		                        <c:otherwise>
		                        	<td id = "td_available"><a id = "789 ${c.starttime}" class = "a_available">&nbsp;</a> </td>
		                        </c:otherwise>      
	
	                        </c:choose>
                        </c:forEach>
                    </tr>
                      <tr>
                        <td> 901 </td>
            			<c:forEach var = "c" items = "${rr4}" varStatus="loop">
	            			<c:choose>     
		            			<c:when test ="${(c.starttime == '9:00 AM' || c.starttime == '10:00 AM' || 
		            							c.starttime == '11:00 AM' || c.starttime == '12:00 NN' ||
		            							c.starttime == '1:00 PM' || c.starttime == '2:00 PM' ||
		            							c.starttime == '3:00 PM' || c.starttime == '4:00 PM' ||
		            							c.starttime == '5:00 AM' || c.starttime == '6:00 AM' ||
		            							c.starttime == '7:00 AM' || c.starttime == '8:00 AM')
		            							&& (c.userid != 0)}">                
		                        	<td id = "td_reserved" data-toggle = "modal" data-target = ".modal_delete"
		                        		 name = "${c.reservedroomid}"> </td>
		                        </c:when>    
		                        <c:otherwise>
		                        	<td id = "td_available"><a id = "901 ${c.starttime}" class = "a_available">&nbsp;</a> </td>
		                        </c:otherwise>      
	
	                        </c:choose>
                        </c:forEach>
                    </tr>
                    <tr>
                        <td> 234 </td>
            			<c:forEach var = "c" items = "${rr5}" varStatus="loop">
	            			<c:choose>     
		            			<c:when test ="${(c.starttime == '9:00 AM' || c.starttime == '10:00 AM' || 
		            							c.starttime == '11:00 AM' || c.starttime == '12:00 NN' ||
		            							c.starttime == '1:00 PM' || c.starttime == '2:00 PM' ||
		            							c.starttime == '3:00 PM' || c.starttime == '4:00 PM' ||
		            							c.starttime == '5:00 AM' || c.starttime == '6:00 AM' ||
		            							c.starttime == '7:00 AM' || c.starttime == '8:00 AM')
		            							&& (c.userid != 0)}">                
		                        	<td id = "td_reserved" data-toggle = "modal" data-target = ".modal_delete"
		                        	    name = "${c.reservedroomid}"> </td>
		                        </c:when>    
		                        <c:otherwise>
		                        	<td id = "td_available"><a id = "234 ${c.starttime}" class = "a_available">&nbsp;</a> </td>
		                        </c:otherwise>      
	
	                        </c:choose>
                        </c:forEach>
                    </tr>
                </tbody>                
            </table>
            </center>

        </div>
      </div>

    <!-- DELETE RESERVATION MODAL -->
     <div class="modal fade modal_delete" id="modal_delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
      <div class="modal-dialog">
        <div class="addaymodal-container" id="delete-container">
            <center>
                <form id = "form_deletereservation" class="form form-horizontal" action="M-DeleteRoomReservation" method="POST" style = "margin-bottom:70px;" >
                    Do you want to remove this reservation?
                    <div class="text-center" style = "margin-top: 30px;">
                        <input name = "reservedroomid" type="hidden" id="hiddeninput-deleteroomreservation">
                        <input name = "date" type = "hidden" id = "hiddeninput-date">
                        <button id="button_confirmdelete" type="submit" class="btn btn-success col-xs-4 submit" style="margin-left:40px; margin-right:30px;font-size:14px;"> <i class="glyphicon glyphicon-th-list"></i> YES </button>
                        <button type="button" class="btn btn-danger col-xs-4 cancel" data-dismiss="modal" style=" margin-left: 20px;font-size:14px;"> <i class="glyphicon glyphicon-remove"></i> CANCEL </button>

                    </div>
                </form>
            </center>
            
        </div>
       </div>
      </div>
      
    <!-- HIDDEN FORMS -->
    <c:if test = "${role == 'Library Staff'}">
		<form id = "form_viewmeetingrooms_staff" action = "MeetingRooms" method = "POST">
	    	<input name = "date" type="hidden" id="hiddeninput-newdate-staff">
	    </form> 
	</c:if>
    <c:if test = "${role == 'Library Manager'}">
		<form id = "form_viewmeetingrooms_manager" action = "M-MeetingRooms" method = "POST">
	    	<input name = "date" type="hidden" id="hiddeninput-newdate-manager">
	    </form> 
	</c:if>
       
      
    <!-- For datepicker -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/moment.js"></script>
    <script type="text/javascript" src="js/daterangepicker.js"></script>
    <c:if test = "${role == 'Library Staff'}">
    <script>
	    $(function() {
	        var currDate = moment.currDate;
	
	        $('input[name="dailydate"]').daterangepicker({
	          singleDatePicker: true,
	          showDropdowns: true,
	          value: currDate,
	          locale: {
	            format: 'MM-DD-YYYY'
	           }
	        },
	        
	        function(start, end, label) {
	        	var date = start.format('MM-DD-YYYY') 
	        	$("#hiddeninput-newdate-staff").val(date);
	        	$("#form_viewmeetingrooms_staff").submit();
	        });
	        
	    });
    </script>
    </c:if>
    
    <c:if test = "${role == 'Library Manager'}">
    <script>
	    $(function() {
	        var currDate = moment.currDate;
	
	        $('input[name="dailydate"]').daterangepicker({
	          singleDatePicker: true,
	          showDropdowns: true,
	          value: currDate,
	          locale: {
	            format: 'MM-DD-YYYY'
	           }
	        },
	        
	        function(start, end, label) {
	        	var date = start.format('MM-DD-YYYY') 
	        	$("#hiddeninput-newdate-manager").val(date);
	        	$("#form_viewmeetingrooms_manager").submit();
	        });
	        
	    });
    </script>
    </c:if>
    
    <c:if test = "${date != null}">
	    <script type="text/javascript">
	        $(function() {
	        	$('input[name="dailydate"]').data('daterangepicker').setStartDate('${date}');
	        	$('input[name="dailydate"]').data('daterangepicker').setEndDate('${date}');
	    	});
	    </script>
   	</c:if>
   	
   	<script>
   	$(document).ready(function() {
        $("td#td_reserved").click(function(event) {
	    	var reservedroomid = $(this).attr("name");
	    	var date = document.getElementById('dailydate').value
	    	
	    	alert("nyaaaaar" + reservedroomid + date);
            $("#hiddeninput-deleteroomreservation").val(reservedroomid);
            $("#hiddeninput-date").val(date);
        });
   	});
   	</script>
    
    <!--  
    <script type="text/javascript">
        $(document).ready(function() {
            $("td").click(function(event) {
                alert(event.target.id);
                $("#collapse_editmeetingroom").show();

            });
            
            $("#button_cancel").click(function(event) {
                $("#collapse_editmeetingroom").hide();
            });
        });
    </script>
    -->
    
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/sidenavbar.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script>
  	<!-- ALERT SCRIPTS -->
	<c:if test ="${statusDelete != null}">
    <script>
    	$(document).ready(function(){
    		alert("${statusDelete}");
    	});
    </script>
    </c:if> 
	
	  
  </body>
  
</html>