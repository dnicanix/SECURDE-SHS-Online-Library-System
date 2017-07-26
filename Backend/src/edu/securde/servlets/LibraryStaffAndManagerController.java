package edu.securde.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.securde.beans.Readings;
import edu.securde.beans.RegisteredUser;
import edu.securde.services.ReadingsService;
import edu.securde.services.UsersService;

/**
 * Servlet implementation class LibraryStaffAndManagerController
 */
@WebServlet(urlPatterns = {"/LibraryStaffAndManagerController", "/SM-LibraryCollection", "/SM-SearchReadings",
							"/SM-FilterByCategory", "/AddReading", "/EditReading", "/DeleteReading"
})
public class LibraryStaffAndManagerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibraryStaffAndManagerController() {
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
		
		Readings reading;
		ArrayList<Readings> readings;
		
		switch(servletPath){
			case "/SM-LibraryCollection":
				
				HttpSession session = request.getSession();
				String username = (String)session.getAttribute("username");
				
				//Get the user's name and role
				RegisteredUser user = UsersService.getUser(username);
				String role = UsersService.getRoleByUsername(username);
				request.setAttribute("fullname", user.getFirstname() + " " + user.getMiddleinitial() 
									 + " " + user.getLastname());
				request.setAttribute("role", role);
				
				//Get the readings to display
				readings = ReadingsService.getAllReadings();
				
				request.setAttribute("filterresults", 0);
				request.setAttribute("searchfilter", "Title");
				request.setAttribute("readings", readings);
				request.setAttribute("readingssize", readings.size());
				
				System.out.println("---Library Collection Servlet---");
				System.out.println("# of Readings: " + readings.size());

				request.getRequestDispatcher("SM-LibraryCollection.jsp").forward(request, response);
				break;
			case "/SM-SearchReadings":
				
				String searchInput = request.getParameter("input_search");
				String searchFilter = request.getParameter("select_searchfilter");

				readings = searchFilterAndSearchInput(searchFilter, searchInput);

				request.setAttribute("filterresults", 0);
				request.setAttribute("searchfilter", searchFilter);
				request.setAttribute("searchinput", searchInput);
				request.setAttribute("readings", readings);
				request.setAttribute("readingssize", readings.size());
				request.getRequestDispatcher("SM-LibraryCollection.jsp").forward(request, response);
				break;

			case "/SM-FilterByCategory":
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
				
				request.getRequestDispatcher("SM-LibraryCollection.jsp").forward(request, response);
				break;
				
			case "/AddReading":
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
				System.out.println("---Adding Reading---");
				System.out.println(readingtitle + " " + categoryid+ " " + author + " "+ publisher + " " + year + location + tags + reading.getStatus() + "");
				
				try{
					ReadingsService.addReading(reading);
					System.out.println("READING MATERIAL ADDED!");
					request.setAttribute("statusAdd", 
										"Reading Material '" + reading.getReadingtitle() + "' is successfully added!");
				}catch(Exception e){
					request.setAttribute("statusAdd", "Failed to add the reading material!");
				}
				
				request.getRequestDispatcher("SM-LibraryCollection").forward(request, response);
				break;
			case "/EditReading":
				int readingid = Integer.parseInt(request.getParameter(Readings.COLUMN_READINGID));
				readingtitle = request.getParameter(Readings.COLUMN_READINGTITLE);
				categoryid = Integer.parseInt(request.getParameter(Readings.COLUMN_CATEGORYID));
				author = request.getParameter(Readings.COLUMN_AUTHOR);
				publisher = request.getParameter(Readings.COLUMN_PUBLISHER);
				year = request.getParameter(Readings.COLUMN_YEAR);
				location = request.getParameter(Readings.COLUMN_LOCATION);
				tags = request.getParameter(Readings.COLUMN_TAGS);
				
				reading = new Readings();
				reading.setReadingid(readingid);
				reading.setReadingtitle(readingtitle);
				reading.setCategoryid(categoryid);
				reading.setAuthor(author);
				reading.setPublisher(publisher);
				reading.setYear(year);
				reading.setLocation(location);
				reading.setTags(tags);
				reading.setStatus("Available");
				System.out.println("---Editing Reading---");
				System.out.println(readingtitle + " " + categoryid+ " " + author + " "+ publisher + " " + year + location + tags + reading.getStatus() + "");
				
				try{
					ReadingsService.updateReading(reading);
					System.out.println("READING MATERIAL UPDATED!");
					request.setAttribute("statusEdit", 
							"Reading Material '" + reading.getReadingtitle() + "' is successfully updated!");					
				}catch(Exception e){
					request.setAttribute("statusEdit", "Failed to update the reading material!");
				}
				
				request.getRequestDispatcher("SM-LibraryCollection").forward(request, response);
				break;
			case "/DeleteReading":
				readingid = Integer.parseInt(request.getParameter(Readings.COLUMN_READINGID));
				reading = ReadingsService.getReadingsByID(readingid);
				
				try{
					ReadingsService.deleteReadings(readingid);
					System.out.println("---Deleting Reading---");
					System.out.println("READING MATERIAL DELETED!");
					request.setAttribute("statusDelete", 
							"Reading Material '" + reading.getReadingtitle() + "' is successfully deleted!");					
				}catch(Exception e){
					request.setAttribute("statusDelete", "Failed to delete the reading material!");
				}
				request.getRequestDispatcher("SM-LibraryCollection").forward(request, response);
				break;
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

}
