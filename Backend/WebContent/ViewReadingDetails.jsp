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
    <link href="css/viewbookdetails.css" rel="stylesheet">

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
                	<c:if test = "${role == 'Student'}">
                    	<img src="img/ic_student.png" class = "img-rounded">
                    </c:if>
                	<c:if test = "${role == 'Faculty'}">
                    	<img src="img/ic_teacher.png" class = "img-rounded">
                    </c:if>
                </div>
            </center>
            <!-- Sidebar brand name -->
            <div class="sidebar-brand">
                ${fullname}  <p style = "margin-top:-30px;font-size:12px;">${role}</p>
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
                <a href="LibraryCollection">
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
                <p class="pageheader">> <a href = "LibraryCollection"> Library Collection </a> > Book Details</p>
            </div>
            
            <!--- B O O K  D E T A I L S --->
            <div id = "div_readingmaterial" class = "row">
                <div class = "col-md-1"> <p class = "p_number"></p> </div>
                <div id = "div_readingmaterialdetails" class = "col-md-11">
                     <p id = "title"> <a href = "#"> ${reading.readingtitle} </a>
                     	<c:if test ="${reading.status == 'Available'}">
                       		<button name = "${reading.readingid}" id = "button_reserve" type="button" class="btn btn-primary">
                            	<span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span> RESERVE
                         	</button>
                         </c:if>
                        <c:if test ="${reading.status == 'Reserved'}">
                         	<button id = "button_reserve" type="button" class="btn btn-default" disabled="disabled">
                         		<span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>RESERVE
                        	</button>
                        </c:if>
                     </p>
                     
                     <br>
                    <div class = "row">
                    	 <c:if test ="${reading.categoryid == '1'}">
	                         <div class = "col-md-1 col_1">
	                            <img src=img/ic_book.png class = "indent" width = "80" height = "80">
	                         </div>
	                    	 </c:if>
							 <c:if test ="${reading.categoryid == '2'}">
	                         <div class = "col-md-1 col_1">
	                            <img src=img/ic_magazine.png class = "indent" width = "80" height = "80">
	                         </div>
	                    	 </c:if>
	                    	 <c:if test ="${reading.categoryid == '3'}">
	                         <div class = "col-md-1 col_1">
	                            <img src=img/ic_magazine.png class = "indent" width = "80" height = "80">
	                         </div>
                    	 </c:if>
                    	 
                         <div class = "col-md-11 col_11">
                            <p class = "indent">
                                <label id = "author">${reading.author}</label> -
                                <label id = "publisher">${reading.publisher}</label> - 
                                <label id = "year">${reading.year}</label><br>
                                <label id = "location">${reading.location}</label><br>
								<c:if test = "${reading.status == 'Available'}">
                                <label id = "available"> AVAILABLE </label>
                                </c:if>
                                <c:if test = "${reading.status == 'Reserved'}">
                                <label id = "reserved"> RESERVED </label>
                                </c:if>
                                <c:if test = "${reading.status == 'Out'}">
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
                            Tags: <label id = "tags"> ${reading.tags}</label>
                            </p>
                        </div>
                    </div>
                    
                </div>
            </div>
            <!--- B O O K  R E V I E W S --->
            <fieldset class="fieldset_reviews" style = "max-width:950px;">
                <legend class="fieldset_reviews" id = "title" style = "font-size:16px;"><img src = "img/ic_reviews.png" class = "img_icons" style = "margin-left:5px;margin-right:5px;"> Reviews </legend>
                
                <!--- W R I T E  Y O U R  O W N  R E V I E W --->
                <div class = "row">
                    <div class = "col-md-3"> </div>
                    <c:if test="${borrowed == true}">
	                    <div class = "col-md-1">
	                        <c:if test = "${role == 'Student'}">
	                        	<img src="img/ic_student.png" id = "img_studentreview" class="img-circle" width = "40" height = "40">
	                    	</c:if>
	                    	<c:if test = "${role == 'Faculty'}">
	                        	<img src="img/ic_teacher.png" id = "img_studentreview" class="img-circle" width = "40" height = "40">
	                    	</c:if>
	                    </div>
	                    <div class = "col-md-6" style = "margin-left:-30px;">
	                        <form action="AddReview" method="POST">
	                            <div class="form-group">
	                                <label for="exampleTextarea">${fullname}</label>
	                                <textarea name="text_review"
	                                		  class="form-control" placeholder = "How's the reading material?" rows="3"></textarea>
	                            </div>
	                            <input name="readingid_review" type="hidden" value="${reading.readingid}">
	                            <button id = "btn_submitreview" type="submit" class="btn btn-primary btn-sm" style="float:right;width:150px;">
	                                SUBMIT
	                            </button> 
	                        </form>
	                    </div>
                    </c:if>
                    <c:if test="${borrowed == false}">
	                    <div class = "col-md-1">
	                        <c:if test = "${role == 'Student'}">
	                        	<img src="img/ic_student.png" id = "img_studentreview" class="img-circle" width = "40" height = "40">
	                    	</c:if>
	                    	<c:if test = "${role == 'Faculty'}">
	                        	<img src="img/ic_teacher.png" id = "img_studentreview" class="img-circle" width = "40" height = "40">
	                    	</c:if>
	                    </div>
	                    <div class = "col-md-6" style = "margin-left:-30px;">
	                        <form action="AddReview" method="POST">
	                            <div class="form-group">
	                                <label for="exampleTextarea">${fullname}</label>
	                                <textarea name="text_review"
	                                		  class="form-control" placeholder = "You cannot add a review since you haven't borrowed this book yet!" rows="3"
	                                		  disabled = "disabled"></textarea>
	                            </div>
	                            <input name="readingid_review" type="hidden" value="${reading.readingid}">
	                            <button id = "btn_submitreview" type="submit" class="btn btn-primary btn-sm" 
	                            		style="float:right;width:150px;" disabled = "disabled">
	                                SUBMIT
	                            </button> 
	                        </form>
	                    </div>
                    </c:if>
                </div>
                
             	<c:forEach var = "c" items = "${reviews}" varStatus="loop">
             	<c:if test="${empty(reviews)}">
               		<p>No reviews yet.</p>
               	</c:if>
                <div class = "row div_reviews">
                    <div class = "col-md-3"> </div>
                    <div class = "col-md-1">
                        <img src="img/ic_student.png" class="img-circle" width = "40" height = "40">
                    </div>
                    <div class = "col-md-6" style = "margin-left:-30px;">
                        <label> ${c.userfullname} </label> <label id = "label_reviewdate"> ${c.reviewdate} </label>
                        <p> ${c.review} </p>
                    </div>

                </div>  
             	</c:forEach>
             	
            </fieldset>
        </div>
      </div>

      
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/sidenavbar.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script>

  </body>
  
</html>