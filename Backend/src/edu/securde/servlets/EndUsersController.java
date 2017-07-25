package edu.securde.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.securde.beans.BorrowedReading;
import edu.securde.beans.Readings;
import edu.securde.beans.RegisteredUser;
import edu.securde.beans.Review;
import edu.securde.beans.ReviewInfo;
import edu.securde.services.BorrowedReadingService;
import edu.securde.services.ReadingsService;
import edu.securde.services.ReviewService;
import edu.securde.services.UsersService;

/**
 * Servlet implementation class EndUsersController
 */
@WebServlet(urlPatterns = {"/EndUsersController", "/LibraryCollection", "/ReserveReading", "/SearchReadings",
						   "/FilterByCategory", "/ViewReading"
		
})
public class EndUsersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		String servletPath = request.getServletPath();
		switch(servletPath){
			case "/LibraryCollection":
				HttpSession session = request.getSession();
				String username = (String)session.getAttribute("username");
				
				//Get the user's name and role
				RegisteredUser user = UsersService.getUser(username);
				String role = UsersService.getRoleByUsername(username);
				request.setAttribute("fullname", user.getFirstname() + " " + user.getMiddleinitial() 
									 + " " + user.getLastname());
				request.setAttribute("role", role);
				
				//Get the readings to display
				ArrayList<Readings> readings = ReadingsService.getAllReadings();
				
				request.setAttribute("filterresults", 0);
				request.setAttribute("searchfilter", "Title");
				request.setAttribute("readings", readings);
				request.setAttribute("readingssize", readings.size());
				
				System.out.println("---Library Collection Servlet---");
				System.out.println("# of Readings: " + readings.size());

				request.getRequestDispatcher("LibraryCollection.jsp").forward(request, response);
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
				
				request.getRequestDispatcher("LibraryCollection").forward(request, response);
				break;
			case "/SearchReadings":
				String searchInput = request.getParameter("input_search");
				String searchFilter = request.getParameter("select_searchfilter");

				readings = searchFilterAndSearchInput(searchFilter, searchInput);

				request.setAttribute("filterresults", 0);
				request.setAttribute("searchfilter", searchFilter);
				request.setAttribute("searchinput", searchInput);
				request.setAttribute("readings", readings);
				request.setAttribute("readingssize", readings.size());
				
				request.getRequestDispatcher("LibraryCollection.jsp").forward(request, response);
				break;
			case "/FilterByCategory":
				session = request.getSession();
				username = (String)session.getAttribute("username");
				
				//Get the user's name and role
				user = UsersService.getUser(username);
				role = UsersService.getRoleByUsername(username);
				request.setAttribute("fullname", user.getFirstname() + " " + user.getMiddleinitial() 
									 + " " + user.getLastname());
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
				
				request.getRequestDispatcher("LibraryCollection.jsp").forward(request, response);
				break;
			case "/ViewReading":
				session = request.getSession();
				username = (String)session.getAttribute("username");
				
				//Get the user's name and role
				user = UsersService.getUser(username);
				role = UsersService.getRoleByUsername(username);
				request.setAttribute("fullname", user.getFirstname() + " " + user.getMiddleinitial() 
									 + " " + user.getLastname());
				request.setAttribute("role", role);
				
				int readingid = Integer.parseInt(request.getParameter(Readings.COLUMN_READINGID));
				
				request.setAttribute("borrowed", BorrowedReadingService.checkIfBorrowedBytheUser(user.getUserid(), readingid));
				
				Readings reading = ReadingsService.getReadingsByID(readingid);
				
				ArrayList<ReviewInfo> reviews = ReviewService.getReviewsOfBook(readingid);
				
				request.setAttribute("userid", user.getUserid());
				request.setAttribute("name", user.getFirstname() + " " + user.getLastname());
				request.setAttribute("reading", reading);
				request.setAttribute("user", user);
				request.setAttribute("reviews", reviews);
				request.getRequestDispatcher("ViewBookDetails.jsp").forward(request, response);
				
				break;
		}//end of switch
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

}
