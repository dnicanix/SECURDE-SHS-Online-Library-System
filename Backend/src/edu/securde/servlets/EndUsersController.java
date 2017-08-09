package edu.securde.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.owasp.esapi.ESAPI;

import edu.securde.beans.BorrowedReading;
import edu.securde.beans.Readings;
import edu.securde.beans.RegisteredUser;
import edu.securde.beans.ReservedRoom;
import edu.securde.beans.Review;
import edu.securde.beans.ReviewInfo;
import edu.securde.security.Log;
import edu.securde.services.BorrowedReadingService;
import edu.securde.services.ReadingsService;
import edu.securde.services.ReservedRoomService;
import edu.securde.services.ReviewService;
import edu.securde.services.UsersService;

/**
 * Servlet implementation class EndUsersController
 */
@WebServlet(urlPatterns = {"/EndUsersController", "/LibraryCollection", "/ReserveReading", "/SearchReadings",
						   "/FilterByCategory", "/ViewReading", "/AddReview", "/MeetingRooms", "/ReserveMeetingRoom"
		
})
public class EndUsersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOGGER = Logger.getLogger(AuthenticationController.class.getName());
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EndUsersController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		Log log = new Log("C:\\Users\\Danica C. Sevilla\\Documents\\GitHub\\SECURDE-SHS-Online-Library-System\\Backend\\log.txt");
		String servletPath = request.getServletPath();
		HttpSession session = request.getSession();
		String fullname, role;
		RegisteredUser user;
		if( session.getAttribute(RegisteredUser.COLUMN_USERTYPE) != null){
			if(session.getAttribute(RegisteredUser.COLUMN_USERTYPE).equals(1) ||
			   session.getAttribute(RegisteredUser.COLUMN_USERTYPE).equals(2)){
			switch(servletPath){
				case "/LibraryCollection":
					session = request.getSession();
					String username = (String)session.getAttribute("username");
					
					//Get the user's name and role
					user = UsersService.getUser(username);
					fullname = ESAPI.encoder().encodeForHTML(user.getFirstname() + " " + user.getMiddleinitial() 
							 + " " + user.getLastname());
					
					role = ESAPI.encoder().encodeForHTML(UsersService.getRoleByUsername(username));
					request.setAttribute("fullname", fullname);
					request.setAttribute("role", role);
					
					//Get the readings to display
					ArrayList<Readings> readings = ReadingsService.getAllReadings();
					
					request.setAttribute("filterresults", 0);
					request.setAttribute("searchfilter", "Title");
					request.setAttribute("readings", readings);
					request.setAttribute("readingssize", readings.size());
					
					System.out.println("---Library Collection Servlet---");
					System.out.println("# of Readings: " + readings.size());
	
					request.getRequestDispatcher("/WEB-INF/LibraryCollection.jsp").forward(request, response);
					break;
				case "/ReserveReading":
					session = request.getSession();
					
					int readingID = Integer.parseInt(request.getParameter(Readings.COLUMN_READINGID));
					username = (String)session.getAttribute("username");
					int userID = UsersService.getUser(username).getUserid();
					
					BorrowedReading borrowedReading = new BorrowedReading(readingID, userID);
					BorrowedReadingService.reserveReading(borrowedReading);
					
					System.out.println("---Borrowing Book---");
					System.out.println("ReadingID: " + readingID);
					System.out.println("UserID: " + userID);
					
					log.logger.info("User '"+ username + "' RESERVED A BOOK.");
					request.getRequestDispatcher("LibraryCollection").forward(request, response);
					break;
				case "/SearchReadings":
					session = request.getSession();
					username = (String)session.getAttribute("username");
					
					//Get the user's name and role
					user = UsersService.getUser(username);
					fullname = ESAPI.encoder().encodeForHTML(user.getFirstname() + " " + user.getMiddleinitial() 
							 + " " + user.getLastname());
					
					role = ESAPI.encoder().encodeForHTML(UsersService.getRoleByUsername(username));
					request.setAttribute("fullname", fullname);
					request.setAttribute("role", role);
					
					String searchInput = ESAPI.encoder().encodeForHTML(request.getParameter("input_search"));
					String searchFilter = request.getParameter("select_searchfilter");
	
					readings = searchFilterAndSearchInput(searchFilter, searchInput);
	
					request.setAttribute("filterresults", 0);
					request.setAttribute("searchfilter", searchFilter);
					request.setAttribute("searchinput", searchInput);
					request.setAttribute("readings", readings);
					request.setAttribute("readingssize", readings.size());
					
					
					log.logger.info("User '"+ username + "' SEARCHED FOR READINGS.");
					request.getRequestDispatcher("/WEB-INF/LibraryCollection.jsp").forward(request, response);
					break;
				case "/FilterByCategory":
					session = request.getSession();
					username = (String)session.getAttribute("username");
					
					//Get the user's name and role
					user = UsersService.getUser(username);
					fullname = ESAPI.encoder().encodeForHTML(user.getFirstname() + " " + user.getMiddleinitial() 
							 + " " + user.getLastname());
					
					role = ESAPI.encoder().encodeForHTML(UsersService.getRoleByUsername(username));
					request.setAttribute("fullname", fullname);
					request.setAttribute("role", role);
					
					int categoryID = Integer.parseInt(request.getParameter(Readings.COLUMN_CATEGORYID));
					searchInput = request.getParameter("input_search");
					searchFilter = request.getParameter("select_searchfilter");
					ArrayList<Readings> searchReadings = searchFilterAndSearchInput(searchFilter, searchInput);
					readings = new ArrayList<Readings>();
					
					if(categoryID != 0 && searchReadings == null){
						readings = ReadingsService.getAllReadings();
					}else if(categoryID != 0){
						for(int i = 0; i < searchReadings.size(); i++){
							if(searchReadings.get(i).getCategoryid() == categoryID){
								readings.add(searchReadings.get(i));
							}
						}
					}
					else{
						readings = searchReadings;
					}
					
					request.setAttribute("filterresults", categoryID);
					request.setAttribute("searchfilter", searchFilter);
					request.setAttribute("searchinput", searchInput);
					request.setAttribute("readings", readings);
					request.setAttribute("readingssize", readings.size());
					
					log.logger.info("User '"+ username + "' FILTER SEARCH RESULTS BY CATEGORY.");
					request.getRequestDispatcher("/WEB-INF/LibraryCollection.jsp").forward(request, response);
					break;
				case "/ViewReading":
					session = request.getSession();
					
					username = (String)session.getAttribute("username");
					
					//Get the user's name and role
					user = UsersService.getUser(username);
					fullname = ESAPI.encoder().encodeForHTML(user.getFirstname() + " " + user.getMiddleinitial() 
							 + " " + user.getLastname());
					
					role = ESAPI.encoder().encodeForHTML(UsersService.getRoleByUsername(username));
					request.setAttribute("fullname", fullname);
					request.setAttribute("role", role);
					
					int readingid = 0;
					if(request.getAttribute(Readings.COLUMN_READINGID) != null){
						readingid = (int)request.getAttribute(Readings.COLUMN_READINGID);
					}else{
						readingid = Integer.parseInt(request.getParameter(Readings.COLUMN_READINGID));
					}
	
					Readings reading = ReadingsService.getReadingsByID(readingid);
					ArrayList<ReviewInfo> reviews = ReviewService.getReviewsOfBook(readingid);
					
					request.setAttribute("borrowed", BorrowedReadingService.checkIfBorrowedBytheUser(user.getUserid(), readingid));
					request.setAttribute("reading", reading);
					request.setAttribute("reviews", reviews);
					
					log.logger.info("User '"+ username + "' VIEWED A BOOK.");
					request.getRequestDispatcher("/WEB-INF/ViewReadingDetails.jsp").forward(request, response);
					break;
				case "/AddReview":
					System.out.println("AddReviewServlet");
					session = request.getSession();
					int userid = (int) session.getAttribute("userid");
					
					//Get the user's name and role
					user = UsersService.getUser(userid);
					fullname = ESAPI.encoder().encodeForHTML(user.getFirstname() + " " + user.getMiddleinitial() 
							 + " " + user.getLastname());
					
					role = ESAPI.encoder().encodeForHTML(UsersService.getRoleByUsername(user.getUsername()));
					request.setAttribute("fullname", fullname);
					request.setAttribute("role", role);
					
					readingid = Integer.parseInt(request.getParameter("readingid_review"));
					reading = ReadingsService.getReadingsByID(readingid);
					
					String inputreview = request.getParameter("text_review");
	
					Review review = new Review(readingid, userid, inputreview);
					ReviewService.addReview(review);
					
					reviews = ReviewService.getReviewsOfBook(readingid);
					
					request.setAttribute("borrowed", BorrowedReadingService.checkIfBorrowedBytheUser(user.getUserid(), readingid));
					request.setAttribute(Readings.COLUMN_READINGID, readingid);
					request.setAttribute("reading", reading);
					request.setAttribute("reviews", reviews);
					
					log.logger.info("User '"+ user.getUsername() + "' ADDED A REVIEW.");
					request.getRequestDispatcher("ViewReading").forward(request, response);
					break;
				case "/MeetingRooms":
					session = request.getSession();
					username = (String)session.getAttribute("username");
					
					//Get the user's name and role
					user = UsersService.getUser(username);
					fullname = ESAPI.encoder().encodeForHTML(user.getFirstname() + " " + user.getMiddleinitial() 
							 + " " + user.getLastname());
					
					role = ESAPI.encoder().encodeForHTML(UsersService.getRoleByUsername(username));
					request.setAttribute("fullname", fullname);
					request.setAttribute("role", role);
					
					
					//Get Date Today
					String date;
					String newdate = request.getParameter("date");
					if(newdate != null){
						date = newdate;
						request.setAttribute("date", date);
					}else{
						DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
						Date d = new Date();
						date = dateFormat.format(d);
					}
					
					ArrayList<ReservedRoom> rr1 = ReservedRoomService.getReservedRoomsByDateAndRoom(date, 1);
					ArrayList<ReservedRoom> rr2 = ReservedRoomService.getReservedRoomsByDateAndRoom(date, 2);
					ArrayList<ReservedRoom> rr3 = ReservedRoomService.getReservedRoomsByDateAndRoom(date, 3);
					ArrayList<ReservedRoom> rr4 = ReservedRoomService.getReservedRoomsByDateAndRoom(date, 4);
					ArrayList<ReservedRoom> rr5 = ReservedRoomService.getReservedRoomsByDateAndRoom(date, 5);
									
					rr1 = organizeMeetingRooms(rr1);
					rr2 = organizeMeetingRooms(rr2);
					rr3 = organizeMeetingRooms(rr3);
					rr4 = organizeMeetingRooms(rr4);
					rr5 = organizeMeetingRooms(rr5);
					
					request.setAttribute("rr1", rr1);
					request.setAttribute("rr2", rr2);
					request.setAttribute("rr3", rr3);
					request.setAttribute("rr4", rr4);
					request.setAttribute("rr5", rr5);
					
					log.logger.info("User '"+ username + "' RESERVED A BOOK.");
					request.getRequestDispatcher("/WEB-INF/MeetingRooms.jsp").forward(request, response);
					break;
				case "/ReserveMeetingRoom":
					session = request.getSession();
					username = (String)session.getAttribute("username");
					userID = UsersService.getUser(username).getUserid();
					
					date = request.getParameter("date");
					String roomtime = request.getParameter("roomtime");
					String roomname = roomtime.substring(0,roomtime.indexOf(' ')); // "123"
					String starttime = roomtime.substring(roomtime.indexOf(' ')+1); // "11:00 AM"
					int roomid = getRoomId(roomname);
					
					//Construct end time
					int end1 = Integer.parseInt(starttime.substring(0, starttime.indexOf(':'))) + 1;
					String endtime = end1 + ":00 " + AMorNNorPM(end1);
					
					ReservedRoom rr = new ReservedRoom();
					rr.setRoomid(roomid);
					rr.setUserid(userID);
					rr.setDate(date);
					rr.setStarttime(starttime);
					rr.setEndtime(endtime);
					
					try{
						ReservedRoomService.reserveMeetingRoom(rr);
						System.out.println("Meeting room reserved!");
						request.setAttribute("statusReserve", 
											"Room '" + roomtime + "' is successfully reserved!");
					}catch(Exception e){
						request.setAttribute("statusReserve", "Failed to reserve the meeting room!");
					}
					log.logger.info("User '"+ username + "' VIEWED MEETING ROOMS");
					request.getRequestDispatcher("MeetingRooms").forward(request, response);				
					break;
			}//end of switch	
			}else{
				request.getRequestDispatcher("/WEB-INF/Forbidden.jsp").forward(request, response);	
			}
		}else{
			request.getRequestDispatcher("/WEB-INF/Forbidden.jsp").forward(request, response);	
		}
	}
	
	public ArrayList<Readings> searchFilterAndSearchInput(String searchFilter, String searchInput){
		ArrayList<Readings> readings = null;
		
		switch(searchFilter){
		case "Title":	
			readings = ReadingsService.getReadingsByTitle(searchInput);
			break;	
		case "Author":
			readings = ReadingsService.getReadingsByAuthor(searchInput);
			break;
		case "Publisher":
			readings = ReadingsService.getReadingsByPublisher(searchInput);
			break;
		
		}
		return readings;
	}
	
	public ArrayList<ReservedRoom> organizeMeetingRooms(ArrayList<ReservedRoom> rr){
		ArrayList<ReservedRoom> organizedRR= new ArrayList<ReservedRoom>();
		String[] times = {"9:00 AM", "10:00 AM", "11:00 AM", "12:00 NN", "1:00 PM"
						, "2:00 PM", "3:00 PM", "4:00 PM", "5:00 PM", "6:00 PM", "7:00 PM", "8:00 PM"};
		
		boolean found;
		for(int i = 0; i < times.length ; i++){
			found = false;
			for(int j = 0; j < rr.size(); j++){
				if(times[i].equals(rr.get(j).getStarttime())){
					organizedRR.add(rr.get(j));
					found = true;
					break;
				}
			}
			if(!found){
				ReservedRoom room = new ReservedRoom();
				room.setStarttime(times[i]);
				organizedRR.add(room);
			}
		}
		return organizedRR;
	}
	
	public int getRoomId(String roomname){
		switch(roomname){
			case "123":
				return 1;
			case "456":
				return 2;
			case "789":
				return 3;
			case "901":
				return 4;
			case "234":
				return 5;
			default: 
				return 0;
		}
	}
	
	public String AMorNNorPM(int time){
		if(time >=9 && time <= 11){
			return "AM";
		}else if(time == 12){
			return "NN";
		}else if(time >= 1 && time <= 8){
			return "PM";
		}
		return null;
	}

}
