<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
    <link href="css/sidenavbar.css" rel="stylesheet">
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
                <p class="pageheader">> Meeting Rooms</p>
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
                        <td id = "td_available"><a id = "123 9:00AM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "123 10:00AM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "123 11:00AM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "123 12:00NN"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "123 1:00PM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "123 2:00PM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "123 3:00PM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "123 4:00PM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "123 5:00PM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "123 6:00PM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "123 7:00PM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_reserved"></td>
                    </tr>
                    <tr>
                        <td> 456 </td>
                        <td id = "td_available"><a id = "456 9:00AM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "456 10:00AM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "456 11:00AM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "456 12:00NN"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "456 1:00PM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "456 2:00PM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "456 3:00PM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "456 4:00PM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "456 5:00PM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "456 6:00PM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "456 7:00PM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_reserved"></td>
                    </tr>
                    <tr>
                      <tr>
                        <td> 789 </td>
                        <td id = "td_available"><a id = "789 9:00AM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "789 10:00AM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "789 11:00AM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "789 12:00NN"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "789 1:00PM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "789 2:00PM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "789 3:00PM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "789 4:00PM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "789 5:00PM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "789 6:00PM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "789 7:00PM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_reserved"></td>
                    </tr>
                      <tr>
                        <td> 901 </td>
                        <td id = "td_available"><a id = "901 9:00AM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "901 10:00AM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "901 11:00AM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "901 12:00NN"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "901 1:00PM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "901 2:00PM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "901 3:00PM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "901 4:00PM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "901 5:00PM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "901 6:00PM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "901 7:00PM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_reserved"></td>
                    </tr>
                    <tr>
                        <td> 234 </td>
                        <td id = "td_available"><a id = "234 9:00AM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "234 10:00AM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "234 11:00AM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "234 12:00NN"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "234 1:00PM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "234 2:00PM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "234 3:00PM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "234 4:00PM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "234 5:00PM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "234 6:00PM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_available"><a id = "234 7:00PM"class = "a_available" data-toggle="collapse" href="#collapse_reserve" aria-expanded="false" aria-controls="collapse_reserve">&nbsp;</a> </td>
                        <td id = "td_reserved"></td>
                    </tr>
                </tbody>
            </table>
            <div class="collapse" id="collapse_reserve">
              <div class="well">
                <p id = "p_reserve">Do you want to reserve room?</p><button id = "button_reserve" type="button" class="btn btn-default">YES</button> <button id = "button_cancel" type="button" class="btn btn-default" data-dismiss = "collapse" target="#collapse_reserve" data-dismiss="collapse">CANCEL</button>
              </div>
            </div>
            </center>

        </div>
      <!-- HIDDEN FORMS -->
       <form id = "form_meetingrooms" action="MeetingRoomsServlet" method="POST">
		    <input type="hidden" name="meetingrooms"/>
	   </form>
	   <form id = "form_librarycollection" action="LibraryCollectionServlet" method="POST">
		    <input type="hidden" name="librarycollection"/>
	   </form>
      </div>
      
    <!-- For datepicker -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/moment.js"></script>
    <script type="text/javascript" src="js/daterangepicker.js"></script>
    <script type="text/javascript">
        $(function() {
            var currDate = moment.currDate;

            $('input[name="dailydate"]').daterangepicker({
              singleDatePicker: true,
              showDropdowns: true,
              value: currDate
            });
        });
        
        $(document).ready(function() {
            $("a.a_available").click(function(event) {
                alert(event.target.id);
                $("#collapse_reserve").show();
                $('p#p_reserve').text('Do you want to reserve room ' + event.target.id + " timeslot?");
            });
            
            $("#button_cancel").click(function(event) {
                $("#collapse_reserve").hide();
            });
        });
    </script>
      
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/sidenavbar.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script>
  </body>
  
</html>