<%@page import="edu.securde.beans.Readings"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <link href="css/librarycollectionforstaffandmanager.css" rel="stylesheet">
    <link href="css/sidenavbar.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/css/bootstrap-select.min.css">
  
  	<!-- Scripts -->
  	<script>
	  	function filterSearchByCategory(){
	  		var categoryID = document.getElementById('select_category').value
	  		alert(categoryID);
	  		$("#hiddeninput-category").val(categoryID);
			$("#form_searchbycategory ").submit();
	  	};
	  	
	  	function reserveReading(){
	  		alert("You button was pressed");
			var readingID = document.getElementById("button_reserve").name;
			$("#hiddeninput-reading").val(readingID);
			$("#form_reservereadings").submit();
	
	  	};
	  	
	  	function viewReading(){
	  		alert("Hello");
			var readingID = document.getElementByName("a_reading");
			$("#hiddeninput-viewreading").val(readingID);
			$("#form_viewreading").submit();
	  	};
	  	
	  	function deleteReading(){
	  		var readingID = document.getElementById("button_delete").name;
	  		$("#hiddeninput-deletereading").val(readingID);
	  		$("#form_deletereading").submit();
	  	}
	  	
	  	
  	</script>
  	
  	
  </head>
  <body class = "body_home">
    <!-- Overlay for fixed sidebar -->
    <div class="sidebar-overlay"></div>

    <!-- Material sidebar -->
    <aside id="sidebar" class="sidebar sidebar-default open" role="navigation">
        <!-- Sidebar header -->
        <div class="sidebar-header header-cover">
            <!-- Sidebar brand image -->
            <center>
                <div class="sidebar-image">
                    <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/53474/atom_profile_01.jpg">
                </div>
            </center>
            <!-- Sidebar brand name -->
            <div class="sidebar-brand">
                Danica Sevilla
            </div>
        </div>

        <!-- Sidebar navigation -->
        <ul class="nav sidebar-nav">
            <li class="divider"></li>
            <li>
                <a href = "#" onclick="document.getElementById('form_myaccount').submit();">
                    <img src = "img/ic_myaccount.png" class = "img_icons"/>
                    My Account
                </a>
            </li>
            <li class = "active">
                <a href = "#" onclick="document.getElementById('form_librarycollection').submit();">
                    <img src = "img/ic_librarycollection.png" class = "img_icons"/>
                    Library Collection
                </a>
            </li>
            <li>
                <a href = "#" onclick="document.getElementById('form_meetingrooms').submit();">
                    <img src = "img/ic_meetingrooms.png" class = "img_icons"/>
                    Meeting Rooms
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
        <div class="constructor">
            <center>
                <div class="jumbotron">
                    <h3> <img src="img/ic_dlsu.png" width = 80; height = 80; style = "margin-right:10px;"/>DLSU Senior High School (SHS) Online Library</h3>      
                </div>
            </center>
            <div class = "row">
            	<div class = "col-md-8 col_8">
                   <p class="pageheader">> Library Collection</p>
                </div>
                <div class = "col-md-4 col_4">
                   <button id = "button_add" type="button" class="btn btn-default" data-toggle="modal" data-target="#modal_add">ADD</button>
                </div>
            </div>
            <div class = "row">
                <div class = "col-md-2" style = "float:left;">
                    <select id = "select_category" class="selectpicker show-tick" data-width="250" onchange = "filterSearchByCategory();">
					<c:if test = "${categoryid == '0' }">
					 <option value = "0" selected>All</option>
                     <option value = "1">Filter by Books</option>
                     <option value = "2">Filter by Thesis</option>
                     <option value = "3">Filter by Magazines</option>
                    </c:if>
                    <c:if test = "${categoryid == '1' }">
					 <option value = "0">All</option>
                     <option value = "1" selected>Filter by Books</option>
                     <option value = "2">Filter by Thesis</option>
                     <option value = "3">Filter by Magazines</option>
                    </c:if>
                    <c:if test = "${categoryid == '2' }">
					 <option value = "0">All</option>
                     <option value = "1">Filter by Books</option>
                     <option value = "2" selected>Filter by Thesis</option>
                     <option value = "3">Filter by Magazines</option>
                     </c:if>
                    <c:if test = "${categoryid == '3' }">
					 <option value = "0">All</option>
                     <option value = "1">Filter by Books</option>
                     <option value = "2">Filter by Thesis</option>
                     <option value = "3" selected>Filter by Magazines</option>
                    </c:if>
                    </select>
                </div>
                <div class = "col-md-9" style = "float:right;">
                    <form role="search" class = "form-horizontal" action = "SearchReadingsServlet" method = "POST">
                        <div class="input-group col-md-3" style = "float:right;">
                            <input type="text" class="form-control" placeholder="Type your search here..." name="input_search">
                            <div class="input-group-btn">
                                <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                            </div>
                        </div>
                        <div class = "input-group col-md-3" style = "float:right;"><label style = "margin-top:5px;">Search by:</label>
                            <select  name = "select_searchfilter" class="selectpicker show-tick" data-width="auto">
                             <c:if test = "${empty(searchfilter)}">
	                             <option value = "Title" selected>Title</option>
	                             <option value = "Author">Author</option>
	                             <option value = "Publisher">Publisher</option>
                             </c:if>
                             <c:if test = "${searchfilter == 'Title' }">
	                             <option value = "Title" selected>Title</option>
	                             <option value = "Author">Author</option>
	                             <option value = "Publisher">Publisher</option>
                             </c:if>
                             <c:if test = "${searchfilter == 'Author' }">
	                             <option value = "Title">Title</option>
	                             <option value = "Author" selected>Author</option>
	                             <option value = "Publisher">Publisher</option>
                             </c:if>
                             <c:if test = "${searchfilter == 'Publisher' }">
	                             <option value = "Title">Title</option>
	                             <option value = "Author">Author</option>
	                             <option value = "Publisher" selected>Publisher</option>
                             </c:if>

                            </select>
                        
                        </div>
                    </form>
                </div> 
            </div>

            <!--- L I B R A R Y   C O L L E C T I O N --->
			<c:forEach var = "c" items = "${readings}" varStatus="loop">
 			<div id = "div_readingmaterial" class = "row">
                <div class = "col-md-1"> <p class = "p_number">${loop.index + 1} </p> </div>
                <div id = "div_readingmaterialdetails" class = "col-md-11">
                     <p id = "title"> <a href = "#" id = "${c.readingid}" class = "a_reading"> ${c.readingtitle} </a>
                     </p>
                     
                     <br>
                    <div class = "row">
                    	 <c:if test ="${c.categoryid == '1'}">
                         <div class = "col-md-1 col_1">
                            <img src=img/ic_book.png class = "indent" width = "80" height = "80">
                         </div>
                    	 </c:if>
						 <c:if test ="${c.categoryid == '2'}">
                         <div class = "col-md-1 col_1">
                            <img src=img/ic_magazine.png class = "indent" width = "80" height = "80">
                         </div>
                    	 </c:if>
                    	 <c:if test ="${c.categoryid == '3'}">
                         <div class = "col-md-1 col_1">
                            <img src=img/ic_magazine.png class = "indent" width = "80" height = "80">
                         </div>
                    	 </c:if>
                    	 

                         <div class = "col-md-11 col_11">
                            <p class = "indent">
                               
                                <label id = "author">${c.author}</label> -
                                <label id = "publisher">${c.publisher}</label> - 
                                <label id = "year">${c.year}</label><br>
                                <label id = "location">${c.location}</label><br>
                                <c:if test = "${c.status == 'Available'}">
                                <label id = "available"> AVAILABLE </label>
                                </c:if>
                                <c:if test = "${c.status == 'Reserved'}">
                                <label id = "reserved"> RESERVED </label>
                                </c:if>
                                <c:if test = "${c.status == 'Out'}">
                                <label id = "reserved"> OUT </label>
                                </c:if>
                            </p>

                         </div>
                    </div>
                    <div class = "row">
                        <div class = col-md-1 col_1>
                            <p class = "indent"></p>
                        </div>
                        <div class = col-md-11 col_11>
                            <p class = "indent">
                            Tags: <label id = "tags">${c.tags}</label>
                            <button id = "button_edit" type="button" class="btn btn-default"  data-toggle="modal" data-target="#edit_dialog">EDIT</button>
                            <button id = "button_delete" type="button" name = "${c.readingid}" class="btn btn-default" onclick="deleteReading();">DELETE</button>
                            </p>
                        </div>
                    </div>
                    
                </div>
            </div>			
			
			</c:forEach>
			
		<!-- EDIT MODAL -->
		<!-- the div that represents the modal dialog -->
		<div class="modal" id="edit_dialog" role="dialog">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal">&times;</button>
		                <h4 class="modal-title">Enter your name</h4>
		            </div>
		                <div class="modal-body">
		                    <form id="contact_form" action="/onlinejson/test.php" method="POST">
		                    		Title:&nbsp;&nbsp;&nbsp; <input id = "modal_author" type="text" class="text-line"/> <br>
								 	Type:&nbsp;&nbsp;&nbsp;                                      
								 	<select id = "modal_availability" class="selectpicker show-tick" data-width="auto">
                                        <option>Book</option>
                                        <option>Thesis</option>
                                        <option>Magazine</option>
                                    </select> 
								 	Author:&nbsp;&nbsp;&nbsp; <input id = "modal_author" type="text" class="text-line"/> <br>
                                    Publisher:&nbsp;&nbsp;&nbsp; <input id = "modal_publisher" type="text" class="text-line"/> <br> 
                                    Year Published:&nbsp;&nbsp;&nbsp; <input id = "modal_year" type="text" class="text-line"/> <br>
                                    Location:&nbsp;&nbsp;&nbsp; <input id = "modal_location" type="text" class="text-line"/> <br>
                                    Availability:&nbsp;&nbsp;&nbsp; 
                                    <select id = "modal_availability" class="selectpicker show-tick" data-width="auto">
                                        <option>Available</option>
                                        <option>Reserved</option>
                                        <option>Out ***</option>
                                    </select> 
		                    </form>
		                </div>
		                <div class="modal-footer">
		                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		                    <button type="button" id="submitForm" class="btn btn-default">Send</button>
		                </div>
		            </div>
		        </div>
		    </div>
		    
    <!-- DELETE MODAL -->
     <div class="modal fade modal_delete" id="modal_delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
      <div class="modal-dialog">
        <div class="addaymodal-container" id="delete-container">
            <center>
           		 <legend style="margin-bottom:-10px;"></legend>
                 <h3><legend class="text-center"><b>DELETE READING</b></legend></h3>
                Are you sure you want to delete this book?
            </center><br><br><br><br><br>
            <div class="text-center">
                <button id="button_confirmdelete" type="button" class="btn btn-success col-xs-4 submit" style="margin-left:40px; margin-right:30px;font-size:14px;" onclick = "deleteReading();"> <i class="glyphicon glyphicon-th-list"></i> YES </button>

                <button type="button" class="btn btn-danger col-xs-4 cancel" data-dismiss="modal" style=" margin-left: 20px;font-size:14px;"> <i class="glyphicon glyphicon-remove"></i> CANCEL </button>
                
            </div>
        </div>
       </div>
      </div>
	<!-- EDIT MODAL  -->
    <div class="modal fade modal_edit" id="modal_edit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
      <div class="modal-dialog">
        <div class="addaymodal-container" id="add-container">
            <center>
              <form class="form form-horizontal" action="AddReadingServlet" method="POST">
                <fieldset>
                  <legend style="margin-bottom:-10px;"></legend>
                  <h3><legend class="text-center"><b>EDIT READING</b></legend></h3>
                  <div class="form-group" >
                       <label class="col-sm-3 control-label" id = "label_title">Title</label>
                           <div class="col-sm-9">
                               <input name = "readingtitle" type="text" class="form-control" id="input_title">
                           </div>
                   </div>
                  <div class="form-group" >
                       <label class="col-sm-3 control-label" id = "label_title">Type</label>
                           <div class="col-sm-9">
                            <select name = "categoryid" class="selectpicker show-tick" data-width="250">
                             <option value = "1">Book</option>
                             <option value = "2">Thesis</option>
                             <option value = "3">Magazines</option>
                            </select>
                           </div>
                   </div>
                  <div class="form-group" >
                       <label class="col-sm-3 control-label" id = "label_title">Author</label>
                           <div class="col-sm-9">
                               <input name = "author" type="text" class="form-control" id="input_title">
                           </div>
                   </div>
                  <div class="form-group" >
                       <label class="col-sm-3 control-label" id = "label_title">Publisher</label>
                           <div class="col-sm-9">
                               <input name = "publisher" type="text" class="form-control" id="input_title">
                           </div>
                   </div>
                  <div class="form-group" >
                       <label class="col-sm-3 control-label" id = "label_title">Year</label>
                           <div class="col-sm-9">
                               <input name = "year" type="number" class="form-control" id="input_year" min="1600" max="2017" value="2017">
                           </div>
                   </div>
                  <div class="form-group" >
                       <label class="col-sm-3 control-label" id = "label_title">Location</label>
                           <div class="col-sm-9">
                               <input name = "location" type="text" class="form-control" id="input_title">
                           </div>
                   </div>
                  <div class="form-group" >
                       <label class="col-sm-3 control-label" id = "label_title">Tags</label>
                           <div class="col-sm-9">
                               <input name = "tags" type="text" class="form-control" id="input_title">
                           </div>
                   </div>
                   <br>
                  <div class="text-center">
                    <button id="button_add" type="submit" class="btn btn-success col-xs-4 submit" style="margin-left:40px; margin-right:30px;font-size:14px;"> <i class="glyphicon glyphicon-th-list"></i> ADD </button>

                    <button type="button" class="btn btn-danger col-xs-4 cancel" data-dismiss="modal" style="
                      margin-left: 20px;font-size:14px;"> <i class="glyphicon glyphicon-remove"></i> CANCEL </button>
                  </div>

                </fieldset>
              </form>
          </center>
        </div>
      </div>
    </div>	
	<!-- ADD -->
    <div class="modal fade" id="modal_add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
      <div class="modal-dialog">
        <div class="addaymodal-container" id="add-container">
            <center>
              <form class="form form-horizontal" action="AddReadingServlet" method="POST">
                <fieldset>
                  <legend style="margin-bottom:-10px;"></legend>
                  <h3><legend class="text-center"><b>ADD READING</b></legend></h3>
                  <div class="form-group" >
                       <label class="col-sm-3 control-label" id = "label_title">Title</label>
                           <div class="col-sm-9">
                               <input name = "readingtitle" type="text" class="form-control" id="input_title">
                           </div>
                   </div>
                  <div class="form-group" >
                       <label class="col-sm-3 control-label" id = "label_title">Type</label>
                           <div class="col-sm-9">
                            <select name = "categoryid" class="selectpicker show-tick" data-width="250">
                             <option value = "1">Book</option>
                             <option value = "2">Thesis</option>
                             <option value = "3">Magazines</option>
                            </select>
                           </div>
                   </div>
                  <div class="form-group" >
                       <label class="col-sm-3 control-label" id = "label_title">Author</label>
                           <div class="col-sm-9">
                               <input name = "author" type="text" class="form-control" id="input_title">
                           </div>
                   </div>
                  <div class="form-group" >
                       <label class="col-sm-3 control-label" id = "label_title">Publisher</label>
                           <div class="col-sm-9">
                               <input name = "publisher" type="text" class="form-control" id="input_title">
                           </div>
                   </div>
                  <div class="form-group" >
                       <label class="col-sm-3 control-label" id = "label_title">Year</label>
                           <div class="col-sm-9">
                               <input name = "year" type="number" class="form-control" id="input_year" min="1600" max="2017" value="2017">
                           </div>
                   </div>
                  <div class="form-group" >
                       <label class="col-sm-3 control-label" id = "label_title">Location</label>
                           <div class="col-sm-9">
                               <input name = "location" type="text" class="form-control" id="input_title">
                           </div>
                   </div>
                  <div class="form-group" >
                       <label class="col-sm-3 control-label" id = "label_title">Tags</label>
                           <div class="col-sm-9">
                               <input name = "tags" type="text" class="form-control" id="input_title">
                           </div>
                   </div>
                   <br>
                  <div class="text-center">
                    <button id="button_add" type="submit" class="btn btn-success col-xs-4 submit" style="margin-left:40px; margin-right:30px;font-size:14px;"> <i class="glyphicon glyphicon-th-list"></i> ADD </button>

                    <button type="button" class="btn btn-danger col-xs-4 cancel" data-dismiss="modal" style="
                      margin-left: 20px;font-size:14px;"> <i class="glyphicon glyphicon-remove"></i> CANCEL </button>
                  </div>

                </fieldset>
              </form>
          </center>
        </div>
      </div>
    </div>	
        
       <!-- HIDDEN FORMS FOR SIDE NAV BAR -->
	   <form id = "form_meetingrooms" action="MeetingRoomsServlet" method="POST">
		    <input type="hidden" name="meetingrooms"/>
	   </form>
	   <form id = "form_librarycollection" action="LibraryCollectionServlet" method="POST">
		    <input type="hidden" name="librarycollection"/>
	   </form>
	   
	   <!-- OTHER HIDDEN FORMS -->
	   <form id = "form_reservereadings" action = "ReserveReadingServlet" method = "POST">
      	 <input name = "readingid" type="hidden" id="hiddeninput-reading">
       </form>
 	   <form id = "form_searchbycategory" action = "SearchByCategoryServlet" method = "POST">
      	 <input name = "categoryid" type="hidden" id="hiddeninput-category">
       </form>
       <form id = "form_viewreading" action = "ViewReadingServlet" method = "POST">
      	 <input name = "readingid" type="hidden" id="hiddeninput-viewreading">
       </form>
       <form id = "form_deletereading" action = "DeleteReadingServlet" method = "POST">
      	 <input name = "readingid" type="hidden" id="hiddeninput-deletereading">
       </form>
	   
      </div>
    
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/sidenavbar.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script>
	<script>
	  	$(document).ready(function(){
	  		$("a.a_reading").click(function(){
	  			alert("hello");
	  			var readingID = $(this).attr("id");
				$("#hiddeninput-viewreading").val(readingID);
				$("#form_viewreading").submit();
	  	  	});
	  	});
	  	
  	</script>
  </body>
  
</html>