<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
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

  	
  </head>
  <body class = "body_home">
    <!-- Overlay for fixed sidebar -->
    <div class="sidebar-overlay"></div>

    <!-- Material sidebar -->
    <aside id="sidebar" class="sidebar sidebar-default open" role="navigation">
        <!-- Sidebar header -->
        <div class="sidebar-header header-cover">
            <!-- Sidebar brand image -->
            <a href = "LogOutServlet" title = "Logout"><img src = "img/ic_logout.png" style = "float:right;margin-top:10px;margin-right:10px;" width = "30" height = "30"></a>
            <center>
                <div class="sidebar-image">
                    <img src="img/ic_student.png" class = "img-rounded">
                </div>
            </center>
            <!-- Sidebar brand name -->
            <div class="sidebar-brand">
                ${fullname} <p style = "margin-top:-30px;font-size:12px;">Student</p>
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
            <li class = "active">
                <a href="#">
                    <img src = "img/ic_librarycollection.png" class = "img_icons"/>
                    Library Collection
                </a>
            </li>
            <li>
                <a href="#">
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
        <center>
            <div class="jumbotron">
                <h3> <img src="img/ic_dlsu.png" width = 80; height = 80; style = "margin-right:10px;"/>DLSU Senior High School (SHS) Online Library</h3>      
            </div>
        </center>        
        <div class="constructor">

            <div class = "row">
                <p class="pageheader"> <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span> Library Collection</p>
            </div>
            <div class = "row">
                <div class = "col-md-3" style = "float:left;">
                    <select class="selectpicker show-tick" data-width="250">  
                     <option selected>All</option>
                     <option>Filter by Books</option>
                     <option>Filter by Thesis</option>
                     <option>Filter by Magazines</option>
                    </select>
                </div>
                <div class = "col-md-9" style = "float:right;">
                    <form role="search" class = "form-horizontal">
                        <div class="input-group col-md-4" style = "float:right;">
                            <input type="text" class="form-control" placeholder="Type your search here..." name="q">
                            <div class="input-group-btn">
                                <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                            </div>
                        </div>
                        <div class = "input-group col-md-4" style = "float:right;"><label style = "margin-top:5px;margin-left:40px;">Search by:</label>
                            <select class="selectpicker show-tick" data-width="auto">
                             <option>Title</option>
                             <option>Author</option>
                             <option>Publisher</option>
                            </select>
                        </div>
                    </form>
                </div> 
                <!--
                <div class = "col-md-3" style = "float:right;">
                    Search by:
                    <select class="selectpicker show-tick" data-width="auto">
                     <option>Title</option>
                     <option>Author</option>
                     <option>Publisher</option>
                    </select>
                </div>-->
                <!--<div class = "col-md-2" style = "float:right;"></div>-->
            
            </div>
            <div class = "row">
                <p id = "p_resultscount"> ----- Showing 50 results -----</p>
            </div>
            
            <!--- L I B R A R Y   C O L L E C T I O N --->
            <c:forEach var = "c" items = "${readings}" varStatus="loop">
            <div id = "div_readingmaterial" class = "row">
                <div class = "col-md-1"> <p class = "p_number">${loop.index + 1}</p> </div>
                <div id = "div_readingmaterialdetails" class = "col-md-11">
                     <p id = "title"> <a href = "#" id = "${c.readingid}"> ${c.readingtitle} </a>
                         <c:if test ="${c.status == 'Available'}">
                         <button name = "${c.readingid}" id = "button_reserve" type="button" class="button_reserve btn btn-primary btn-sm">
                            <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span> RESERVE
                         </button>
                         </c:if>
                         <c:if test ="${c.status == 'Reserved'}">
                         <button id = "button_reserve" type="button" class="btn btn-primary btn-sm"
                         		 disabled = "disabled">
                            <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span> RESERVE
                         </button>
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
                            <img src=img/ic_thesis.png class = "indent" width = "80" height = "80">
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
                            </p>
                        </div>
                    </div>
                    
                </div>
            </div>
     		</c:forEach>
        </div>
      </div>
      
       <!-- HIDDEN FORMS FOR SIDE NAV BAR -->
	   <form id = "form_meetingrooms" action="#" method="POST">
		    <input type="hidden" name="meetingrooms"/>
	   </form>
	   <form id = "form_librarycollection" action="#" method="POST">
		    <input type="hidden" name="librarycollection"/>
	   </form>
	   
	   <!-- OTHER HIDDEN FORMS -->
	   <form id = "form_reservereading" action = "ReserveReadingServlet" method = "POST">
      	 <input name = "readingid" type="hidden" id="hiddeninput-reading">
       </form>
 
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/sidenavbar.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script>
	  	<script>
	  	$(document).ready(function(){
	  		$("button.button_reserve").click(function(){
	  			var readingID = $(this).attr("name");
	  			alert("readingID: " + readingID);
				$("#hiddeninput-reading").val(readingID);
				$("#form_reservereading").submit();
	  	  	});
	  	});
	  	
  	</script>
  </body>
  
</html>