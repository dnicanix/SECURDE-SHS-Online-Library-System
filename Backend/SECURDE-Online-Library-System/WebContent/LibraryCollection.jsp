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
    <link href="css/librarycollection.css" rel="stylesheet">
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
	  		alert(Hello);
			var readingID = document.getElementByName("a_reading");
			$("#hiddeninput-viewreading").val(readingID);
			$("#form_viewreading").submit();
	  	};
	  	
	  	
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
                <p class="pageheader">> Library Collection</p>
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
                     	 <c:if test ="${c.status == 'Available'}">
                         	<button name = "${c.readingid}" id = "button_reserve" type="button" class="btn btn-default" onclick = "reserveReading();">RESERVE</button>
                         </c:if>
                     	 <c:if test ="${c.status == 'Reserved'}">
                         	 <button id = "button_reserve" type="button" class="btn btn-default" disabled="disabled">RESERVE</button>
                         </c:if>
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
                                <button id = "button_review" type="button" class="btn btn-default">REVIEW</button>
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
                            </p>
                        </div>
                    </div>
                    
                </div>
            </div>			
			
			</c:forEach>
        
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