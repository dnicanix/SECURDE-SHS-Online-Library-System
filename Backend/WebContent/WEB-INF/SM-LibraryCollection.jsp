<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="org.owasp.esapi.ESAPI"%>
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
    <link href="css/ms-librarycollection.css" rel="stylesheet">
    <link href="css/bootstrap-tagsinput.css" rel="stylesheet">
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
                    <img src="img/ic_librarian.png" class = "img-rounded">
                </div>
            </center>
            <!-- Sidebar brand name -->
            <div class="sidebar-brand">
              ${fullname}<p style = "margin-top:-30px;font-size:12px;">${role}</p>
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
                <a href="SM-LibraryCollection">
                    <img src = "img/ic_librarycollection.png" class = "img_icons"/>
                    Library Collection
                </a>
            </li>
            <li>
            	<c:if test = "${role == 'Library Staff'}">
	                <a href="S-MeetingRooms">
	                    <img src = "img/ic_meetingrooms.png" class = "img_icons"/>
	                    Meeting Rooms
	                </a>
                </c:if>
                <c:if test = "${role == 'Library Manager' }">
                	<a href="M-MeetingRooms">
	                    <img src = "img/ic_meetingrooms.png" class = "img_icons"/>
	                    Meeting Rooms
               		 </a>
                </c:if>
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
                <p class="pageheader"> <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span> 
                    Library Collection 
                    <button id = "button_add" type="button" class="btn btn-primary" data-toggle="modal" data-target=".modal_add">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                    </button>
                </p>
            </div>
            <div class = "row">
                <div class = "col-md-3" style = "float:left;">
                    <select id = "select_category" class="selectpicker show-tick" data-width="250"
                    		onchange = "filterSearchByCategory();">  
					<c:if test = "${filterresults == '0' }">
					 <option value = "0" selected>All</option>
                     <option value = "1">Filter by Books</option>
                     <option value = "2">Filter by Thesis</option>
                     <option value = "3">Filter by Magazines</option>
                    </c:if>
                    <c:if test = "${filterresults == '1' }">
					 <option value = "0">All</option>
                     <option value = "1" selected>Filter by Books</option>
                     <option value = "2">Filter by Thesis</option>
                     <option value = "3">Filter by Magazines</option>
                    </c:if>
                    <c:if test = "${filterresults == '2' }">
					 <option value = "0">All</option>
                     <option value = "1">Filter by Books</option>
                     <option value = "2" selected>Filter by Thesis</option>
                     <option value = "3">Filter by Magazines</option>
                     </c:if>
                    <c:if test = "${filterresults == '3' }">
					 <option value = "0">All</option>
                     <option value = "1">Filter by Books</option>
                     <option value = "2">Filter by Thesis</option>
                     <option value = "3" selected>Filter by Magazines</option>
                    </c:if>
                    </select>
                </div>
                <div class = "col-md-9" style = "float:right;">
                    <form role="search" class = "form-horizontal" action = "SM-SearchReadings" method = "POST">
                        <div class="input-group col-md-4" style = "float:right;">
	                        <c:if test ="${empty(searchinput)}">
	                            <input id = "input_search" type="text" class="form-control" placeholder="Type your search here..." 
                            	name="input_search">
	                        </c:if>
	                        <c:if test ="${not empty(searchinput)}">
	                            <input id = "input_search" type="text" class="form-control" placeholder="${searchinput}" 
                            	name="input_search" value = "${searchinput}">
	                        </c:if>
                            <div class="input-group-btn">
                                <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                            </div>
                        </div>
                        <div class = "input-group col-md-4" style = "float:right;"><label style = "margin-top:5px;margin-left:40px;">Search by:</label>
                            <select id = "select_searchfilter" name = "select_searchfilter" class="selectpicker show-tick" data-width="auto">
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
            <div class = "row">
                <p id = "p_resultscount"> ----- Showing ${readingssize} results -----</p>     
            </div>

            <!-- L I B R A R Y   C O L L E C T I O N -->
             <c:forEach var = "c" items = "${readings}" varStatus="loop">
            <div id = "div_readingmaterial" class = "row">
                <div class = "col-md-1"> <p class = "p_number">${loop.index + 1}</p> </div>
                <div id = "div_readingmaterialdetails" class = "col-md-11">
                     <p id = "title"> <a id = "${c.readingid}" class = "a_reading" href = "#"> ${c.readingtitle}</a>
                         <button id = "button_edit" type="button" class="button_edit btn btn-primary" data-toggle = "modal" 
                         		 data-target = ".modal_editreading" 
                         		 data-todo='{"readingid":"${c.readingid}",
                         		 			"title":"${c.readingtitle}","author":"${c.author}", "type":"${c.categoryid}",
                         		 			 "publisher":"${c.publisher}","year":"${c.year}",
                         		 			 "location":"${c.location}","tags":"${c.tags}",
                         		 			 "status":"${c.status}"}'>
                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> EDIT
                         </button>
                     </p>
                     
                     <br>
                    <div class = "row">
                         <div class = "col-md-1 col_1">
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
                         </div>
                         <div class = "col-md-11 col_11">
                            <p class = "indent">
                                <button name = "${c.readingid}" id = "button_delete" type="button" class="button_delete btn btn-danger" 
                                		data-toggle = "modal" data-target = ".modal_delete">
                                <span class="glyphicon glyphicon-trash" aria-hidden="true"></span> DELETE
                                </button>
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
                            Tags: <label id = "tags"> ${c.tags}</label>
                            </p>
                        </div>
                    </div>
                    
                </div>
            </div>
            </c:forEach>
        </div>
    </div>

    <!-- DELETE MODAL -->
     <div class="modal fade modal_delete" id="modal_delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
      <div class="modal-dialog">
        <div class="addaymodal-container" id="delete-container">
            <center>
                <form id = "form_deletereading" class="form form-horizontal" action="DeleteReading" method="POST" style = "margin-bottom:70px;" >
                    Are you sure you want to delete this reading material?
                    <div class="text-center" style = "margin-top: 30px;">
                    	<input name = "readingid" type="hidden" id="hiddeninput-deletereading">
                        <button id="button_confirmdelete" type="submit" class="btn btn-success col-xs-4 submit" style="margin-left:40px; margin-right:30px;font-size:14px;"> <i class="glyphicon glyphicon-th-list"></i> YES </button>

                        <button type="button" class="btn btn-danger col-xs-4 cancel" data-dismiss="modal" style=" margin-left: 20px;font-size:14px;"> <i class="glyphicon glyphicon-remove"></i> CANCEL </button>

                    </div>
                </form>
            </center>
            
        </div>
       </div>
      </div>
    
    <!-- ADD MODAL -->
     <div class="modal fade modal_add" id="modal_add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
      <div class="modal-dialog">
        <div class="addaymodal-container" id="addreading-container">
            <center>
              <form id = "form_addmeetingroom" class="form form-horizontal" action="AddReading" method="POST">
                <fieldset>
                  <legend style="margin-bottom:-10px;"></legend>
                  <h3><legend class="text-center"><b>ADD READING</b></legend></h3>
                  <div class="form-group" >
                       <label class="col-sm-4 control-label" id = "label_title">Title</label>
                           <div class="col-sm-8">
                               <input name = "readingtitle" type="text" class="form-control" id="input_title"
                               		  required="true">
                           </div>
                   </div>
                  <div class="form-group" >
                       <label class="col-sm-4 control-label" id = "label_title">Type</label>
                           <div class="col-sm-8">
                            <select name = "categoryid" class="selectpicker show-tick" data-width="250" style = "height:25px;"
                            		required="true">
                             <option value = "1">Book</option>
                             <option value = "2">Thesis</option>
                             <option value = "3">Magazine</option>
                            </select>
                           </div>
                   </div>
                  <div class="form-group" >
                       <label class="col-sm-4 control-label" id = "label_title">Author</label>
                           <div class="col-sm-8">
                               <input name = "author" type="text" class="form-control" id="input_author" data-role="tagsinput">
                           </div>
                   </div>
                  <div class="form-group" >
                       <label class="col-sm-4 control-label" id = "label_title">Publisher</label>
                           <div class="col-sm-8">
                               <input name = "publisher" type="text" class="form-control" id="input_publisher">
                           </div>
                   </div>
                  <div class="form-group" >
                       <label class="col-sm-4 control-label" id = "label_title">Year</label>
                           <div class="col-sm-8">
                               <input name = "year" type="number" class="form-control" id="input_year"
                                       min="1900" max="2017" step="1" value="2017" required="true">
                           </div>
                   </div>
                  <div class="form-group" >
                       <label class="col-sm-4 control-label" id = "label_title">Location</label>
                           <div class="col-sm-8">
                               <input name = "location" type="text" class="form-control" id="input_location"
                               		  required="true">
                           </div>
                   </div>
                  <div class="form-group" >
                       <label class="col-sm-4 control-label" id = "label_title">Tags</label>
                           <div class="col-sm-8">
                               <input name = "tags" type="text" class="form-control" id="input_tags" data-role="tagsinput"
                               		  required="true">
                           </div>
                   </div>
                   <br>
                  <div class="text-center">
                    <button id="button_addreading" type="submit" class="btn btn-success col-xs-4 submit" style="margin-left:40px; margin-right:30px;font-size:14px;">
                     <i class="glyphicon glyphicon-plus"></i> ADD 
                    </button>

                    <button type="button" class="btn btn-danger col-xs-4 cancel" data-dismiss="modal" style="
                      margin-left: 20px;font-size:14px;"> <i class="glyphicon glyphicon-remove"></i> CANCEL </button>
                  </div>

                </fieldset>
              </form>
          </center>
        </div>
      </div>
    </div>

    <!-- EDIT MODAL -->
     <div class="modal fade modal_editreading" id="modal_editreading" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
      <div class="modal-dialog">
        <div class="addaymodal-container" id="editreading-container">
            <center>
              <form id = "form_editreading" class="form form-horizontal" action="EditReading" method="POST">
                <fieldset>
                  <legend style="margin-bottom:-10px;"></legend>
                  <h3><legend class="text-center"><b>EDIT READING</b></legend></h3>
                  <input name = "readingid" type="hidden" id="hiddeninput-readingid">
                  <div class="form-group" >
                       <label class="col-sm-4 control-label" id = "label_title">Title</label>
                           <div class="col-sm-8">
                               <input name = "readingtitle" type="text" class="form-control" id="input_edittitle"
                               required = "true">
                           </div>
                   </div>
                  <div class="form-group" >
                       <label class="col-sm-4 control-label" id = "label_title">Type</label>
                           <div class="col-sm-8">
                            <select name = "categoryid" class="selectpicker show-tick" data-width="250" style = "height:25px;"
                            		id="select_edittype" required = "true">
                             <option value = "1">Book</option>
                             <option value = "2">Thesis</option>
                             <option value = "3">Magazine</option>
                            </select>
                           </div>
                   </div>
                  <div class="form-group" >
                       <label class="col-sm-4 control-label" id = "label_title">Author</label>
                           <div class="col-sm-8">
                               <input name = "author" type="text" class="form-control" id="input_editauthor" data-role="tagsinput">
                           </div>
                   </div>
                  <div class="form-group" >
                       <label class="col-sm-4 control-label" id = "label_title">Publisher</label>
                           <div class="col-sm-8">
                               <input name = "publisher" type="text" class="form-control" id="input_editpublisher">
                           </div>
                   </div>
                  <div class="form-group" >
                       <label class="col-sm-4 control-label" id = "label_title">Year</label>
                           <div class="col-sm-8">
                               <input name = "year" type="number" class="form-control" id="input_edityear"
                                       min="1900" max="2017" step="1" value="2017" required = "true"> 
                           </div>
                   </div>
                  <div class="form-group" >
                       <label class="col-sm-4 control-label" id = "label_title">Location</label>
                           <div class="col-sm-8">
                               <input name = "location" type="text" class="form-control" id="input_editlocation"
                               required = "true">
                           </div>
                   </div>
                  <div class="form-group" >
                       <label class="col-sm-4 control-label" id = "label_title">Tags</label>
                           <div class="col-sm-8">
                               <input name = "tags" type="text" class="form-control" id="input_edittags" data-role="tagsinput"
                               required = "true">
                           </div>
                   </div>
                    <div class="form-group" >
                       <label class="col-sm-4 control-label" id = "label_title">Status</label>
                           <div class="col-sm-8">
                            <select name = "status" class="selectpicker show-tick" data-width="250" style = "height:25px;"
                            		id="select_editstatus" required = "true">
                             <option value = "Available">Available</option>
                             <option value = "Reserved">Reserved</option>
                             <option value = "Out">Out</option>
                            </select>
                           </div>
                   </div>
                   <br>
                  <div class="text-center">
                    <button id="button_editreading" type="submit" class="btn btn-success col-xs-4 submit" style="margin-left:40px; margin-right:30px;font-size:14px;"> <i class="glyphicon glyphicon-th-ok"></i> SAVE </button>

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
	   <form id = "form_meetingrooms" action="#" method="POST">
		    <input type="hidden" name="meetingrooms"/>
	   </form>
	   <form id = "form_librarycollection" action="#" method="POST">
		    <input type="hidden" name="librarycollection"/>
	   </form>
	   
	   <!-- OTHER HIDDEN FORMS -->
	   <form id = "form_reservereading" action = "ReserveReading" method = "POST">
      	 <input name = "readingid" type="hidden" id="hiddeninput-reading">
       </form>
 	   <form id = "form_searchbycategory" action = "SM-FilterByCategory" method = "POST">
      	 <input name = "categoryid" type="hidden" id="hiddeninput-category">
      	 <input name = "select_searchfilter" type="hidden" id="hiddeninput-searchfilter">
      	 <input name = "input_search" type="hidden" id="hiddeninput-searchinput">
       </form>
        <form id = "form_viewreading" action = "ViewReading" method = "POST">
      	 <input name = "readingid" type="hidden" id="hiddeninput-viewreading">
       </form>
       


    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/sidenavbar.js"></script>
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap-tagsinput.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script>
    <!--  
    <script type="text/javascript">
        $(document).ready(function(){
            $("#form_addreading").submit(function() {
                var authors = $("#input_author").val();
                var tags = $("#input_tags").val();
                alert("Authors" + authors + "/nTags" + tags);
                return false;
            });
        
            $("#form_editreading").submit(function() {
                var authors = $("#input_editauthor").val();
                var tags = $("#input_edittags").val();
                alert("Authors" + authors + "/nTags" + tags);
                return false;
            });
            
        });
    </script>
    -->
    <script>
	    $(document).on("click", ".button_delete", function () {
	    	var readingID = $(this).attr("name");
            $("#hiddeninput-deletereading").val(readingID);
	   	});
	    $(document).on("click", ".button_edit", function () {
			   alert("edit");
			    var id = $(this).data('todo').readingid;
	            var title =  $(this).data('todo').title;
	            var type =  $(this).data('todo').type;
	            var author =  $(this).data('todo').author;
	            var publisher =  $(this).data('todo').publisher;
	            var year =  $(this).data('todo').year;
	            var location =  $(this).data('todo').location;
	            var tags =  $(this).data('todo').tags;
	            var status = $(this).data('todo').status;
	            
	            $("input#hiddeninput-readingid").val(id);
	            $("input#input_edittitle").val(title);
	            $("select#select_edittype").val(type).change();
	            $("input#input_editauthor").tagsinput('removeAll');
	            $("input#input_editauthor").tagsinput('add', author);
	            $("input#input_editpublisher").val(publisher);
	            $("input#input_edityear").val(year);
	            $("input#input_editlocation").val(location);
	            $("input#input_edittags").tagsinput('removeAll');
	            $("input#input_edittags").tagsinput('add', tags);
	            $("select#select_editstatus").val(status).change();
		   });
    </script>
    
  	<script>
	  	function filterSearchByCategory(){
	  		var categoryID = document.getElementById('select_category').value
	  		var searchfilter = document.getElementById('select_searchfilter').value
	  		var searchinput = document.getElementById('input_search').value
	  		$("#hiddeninput-category").val(categoryID);
	  		$("#hiddeninput-searchfilter").val(searchfilter);
	  		$("#hiddeninput-searchinput").val(searchinput);
			$("#form_searchbycategory ").submit();
	  	};
  	</script>
  	
  	<!-- ALERT SCRIPTS -->
    <c:if test ="${statusAdd != null}">
    <script>
    	$(document).ready(function(){
    		alert("${statusAdd}");
    	});
    </script>
    </c:if>
    <c:if test ="${statusEdit != null}">
    <script>
    	$(document).ready(function(){
    		alert("${statusEdit}");
    	});
    </script>
    </c:if>    
    <c:if test ="${statusDelete != null}">
    <script>
    	$(document).ready(function(){
    		alert("${statusDelete}");
    	});
    </script>
    </c:if> 


  </body>
  
</html>