package edu.securde.servlets;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.securde.services.*;
import edu.securde.beans.*;


/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns = {"/Controller", "/StartServlet", "/LogInServlet", "/LibraryCollectionServlet",
		   "/SearchReadingsServlet", "/ReserveReadingServlet", "/MeetingRoomsServlet",
		   "/SearchByCategoryServlet", "/ViewReadingServlet", "/LibraryCollectionForStaffAndManagerServlet",
		   "/AddReadingServlet", "/DeleteReadingServlet"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
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
		String servletPath = request.getServletPath();
		HttpSession session;
		Cookie[] cookieList;
		RegisteredUser user;
		ArrayList<Readings> readings = new ArrayList<Readings>();
		String username;
		String usertype;
		int readingID;
		switch(servletPath){
		
			case "/StartServlet":
				session = request.getSession();
				
				cookieList = request.getCookies();
				if(cookieList != null){
					for(int i = 0; i < cookieList.length; i++){
						if(cookieList[i].getName().equals("username"))
							session.setAttribute("username", cookieList[i].getValue());
					}
				}
				
				if(session.getAttribute("username") == null){
					System.out.println("NO USER CURRENTLY LOGGED IN");
					request.getRequestDispatcher("LogIn.jsp").forward(request,response);
				}
				else{
					System.out.println("OLD USER LOGGED IN ALREADY");
					
					user = UsersService.getUser((String)session.getAttribute("username"));
					if(user.getUsertype() == 1 || user.getUsertype() == 2){
						request.getRequestDispatcher("LibraryCollectionServlet").forward(request, response);
					}else if(user.getUsertype() == 3 || user.getUsertype() == 4){
						request.getRequestDispatcher("LibraryCollectionForStaffAndManagerServlet").forward(request, response);
					}
				}
				break;
			case "/LogInServlet":
				username = request.getParameter(RegisteredUser.COLUMN_USERNAME);
				String password = request.getParameter(RegisteredUser.COLUMN_PASSWORD);
				
				System.out.println("Username entered:" + username);
				System.out.println("Password entered:" + password);
				
				if(UsersService.validateUser(username, password)){
					Cookie usernameCookie = new Cookie("username", username);
					usernameCookie.setMaxAge(60*60*24);
					usernameCookie.setHttpOnly(true);
					response.addCookie(usernameCookie);
					
					session = request.getSession();
					
					//put the user's info into session's attributes
					user = UsersService.getUser(username);
					System.out.println(user.getUserid());
					if(session.getAttribute("username") == null){
						session.setAttribute(RegisteredUser.COLUMN_USERNAME, username);
						session.setAttribute(RegisteredUser.COLUMN_USERTYPE, user.getUsertype());
						session.setAttribute(RegisteredUser.COLUMN_USERID, user.getUserid());
					}
					System.out.println("Log-In::SUCCESS");
					
					if(user.getUsertype() == 1 || user.getUsertype() == 2){
						request.getRequestDispatcher("LibraryCollectionServlet").forward(request, response);
					}else if(user.getUsertype() == 3 || user.getUsertype() == 4){
						request.getRequestDispatcher("LibraryCollectionForStaffAndManagerServlet").forward(request, response);
					}
				}
				else{
					System.out.println("Log-In::FAILED");
					request.getRequestDispatcher("LogIn.jsp").forward(request,response);
				}
				break;
				
			case "/LibraryCollectionServlet":
				readings = ReadingsService.getAllReadings();
				request.setAttribute("readings", readings);
				request.setAttribute(Readings.COLUMN_CATEGORYID, 0);
				request.setAttribute("searchfilter", "Title");
				System.out.println("# of Readings: " + readings.size());

				request.getRequestDispatcher("LibraryCollection.jsp").forward(request, response);
				break;
			case "/SearchByCategoryServlet":
				int categoryID = Integer.parseInt(request.getParameter(Readings.COLUMN_CATEGORYID));
				if(categoryID != 0){
					readings = ReadingsService.getReadingsByCategory(categoryID);
				}
				else{
					readings = ReadingsService.getAllReadings();
				}

				request.setAttribute("readings", readings);
				request.setAttribute(Readings.COLUMN_CATEGORYID, categoryID);
				request.setAttribute("searchfilter", "Title");
				System.out.println("CategoryID: " + categoryID);
				request.getRequestDispatcher("LibraryCollection.jsp").forward(request, response);
				break;
			case "/SearchReadingsServlet":
				String inputSearch = request.getParameter("input_search");
				String searchFilter = request.getParameter("select_searchfilter");
				
				switch(searchFilter){
					case "Title":	
						readings = ReadingsService.getReadingsByTitle(inputSearch);
						break;	
					case "Author":
						readings = ReadingsService.getReadingsByAuthor(inputSearch);
						break;
					case "Publisher":
						readings = ReadingsService.getReadingsByPublisher(inputSearch);
						break;
				}
				
				request.setAttribute(Readings.COLUMN_CATEGORYID, 0);
				request.setAttribute("searchfilter", searchFilter);
				request.setAttribute("readings", readings);
				request.getRequestDispatcher("LibraryCollection.jsp").forward(request, response);
				break;
			case "/ReserveReadingServlet":
				session = request.getSession();
				
				readingID = Integer.parseInt(request.getParameter(Readings.COLUMN_READINGID));
				username = (String)session.getAttribute("username");
				int userID = UsersService.getUser(username).getUserid();
				
				BorrowedReading borrowedReading = new BorrowedReading(readingID, userID);
				BorrowedReadingService.reserveReading(borrowedReading);
				
				System.out.println("---Borrowing Book---");
				System.out.println("ReadingID: " + readingID);
				System.out.println("UserID: " + userID);
				
				//Insert BorrowReadingServince
				request.getRequestDispatcher("LibraryCollectionServlet").forward(request, response);
				break;
			case "/ViewReadingServlet":
				session = request.getSession();
				
				//Get Reading & Reviews
				readingID = Integer.parseInt(request.getParameter(Readings.COLUMN_READINGID));
				Readings reading = ReadingsService.getReadingsByID(readingID);
				ArrayList<Review> reviews = ReviewService.getReviewsOfBook(readingID);
				
				//Get User
				username = (String)session.getAttribute("username");
				user = UsersService.getUser(username);
				
				request.setAttribute("reading", reading);
				request.setAttribute("reviews", reviews);
				request.setAttribute("user", user);
				request.getRequestDispatcher("ViewBookDetails.jsp").forward(request, response);
				break;
			case "/MeetingRoomsServlet":
				request.getRequestDispatcher("MeetingRooms.jsp").forward(request, response);
				break;
				
			/****** STAFF AND MANAGER SERVLETS ******/
			case "/LibraryCollectionForStaffAndManagerServlet":
				readings = ReadingsService.getAllReadings();
				request.setAttribute("readings", readings);
				request.setAttribute(Readings.COLUMN_CATEGORYID, 0);
				request.setAttribute("searchfilter", "Title");
				System.out.println("# of Readings: " + readings.size());

				request.getRequestDispatcher("LibraryCollectionForStaffAndManager.jsp").forward(request, response);
				break;
				
			case "/AddReadingServlet":
				//INSERT INTO readings(readingid, readingtitle, categoryid, location, author, publisher, year, tags, status)
				String readingtitle = request.getParameter(Readings.COLUMN_READINGTITLE);
				int categoryid = Integer.parseInt(request.getParameter(Readings.COLUMN_CATEGORYID));
				String author = request.getParameter(Readings.COLUMN_AUTHOR);
				String publisher = request.getParameter(Readings.COLUMN_PUBLISHER);
				String year = request.getParameter(Readings.COLUMN_YEAR);
				String location = request.getParameter(Readings.COLUMN_LOCATION);
				String tags = request.getParameter(Readings.COLUMN_TAGS);
				
				reading = new Readings();
				reading.setReadingtitle(readingtitle);
				reading.setCategoryid(categoryid);
				reading.setAuthor(author);
				reading.setPublisher(publisher);
				reading.setYear(year);
				reading.setLocation(location);
				reading.setTags(tags);
				reading.setStatus("Available");
				System.out.println("HELLO WORLD:");
				System.out.println(readingtitle + " " + categoryid+ " " + author + " "+ publisher + " " + year + location + tags + reading.getStatus() + "");
				
				ReadingsService.addReading(reading);
				System.out.println("READING MATERIAL ADDED!");
				request.getRequestDispatcher("LibraryCollectionForStaffAndManagerServlet").forward(request, response);
				break;
			case "/DeleteReadingServlet":
				ReadingsService.deleteReadings(Integer.parseInt(request.getParameter(Readings.COLUMN_READINGID)));
				System.out.println("READING MATERIAL DELETED!");
				request.getRequestDispatcher("LibraryCollectionForStaffAndManagerServlet").forward(request, response);
				break;
		}
				
	}
	
	public void getUserType(){
		
	}

}
