package edu.securde.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.owasp.esapi.ESAPI;

import edu.securde.beans.Readings;
import edu.securde.beans.RegisteredUser;
import edu.securde.beans.ReservedRoom;
import edu.securde.security.Log;
import edu.securde.services.ReadingsService;
import edu.securde.services.ReservedRoomService;
import edu.securde.services.UsersService;


/**
 * Servlet implementation class LibraryStaffAndManagerController
 */
@WebServlet(urlPatterns = {"/LibraryStaffAndManagerController", "/SM-LibraryCollection", "/SM-SearchReadings",
							"/SM-FilterByCategory", "/AddReading", "/EditReading", "/DeleteReading",
							"/S-MeetingRooms", "/M-MeetingRooms","/M-DeleteRoomReservation"
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
		
		Log log = new Log("C:\\Users\\Danica C. Sevilla\\Documents\\GitHub\\SECURDE-SHS-Online-Library-System\\Backend\\log.txt");
		
		String servletPath = request.getServletPath();
		
		Readings reading;
		ArrayList<Readings> readings;
		String fullname;
		String role;
		
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		if( session.getAttribute(RegisteredUser.COLUMN_USERTYPE) != null){
			if(session.getAttribute(RegisteredUser.COLUMN_USERTYPE).equals(3) ||
			   session.getAttribute(RegisteredUser.COLUMN_USERTYPE).equals(4)){
			switch(servletPath){
				case "/SM-LibraryCollection":
					
					session = request.getSession();
					username = (String)session.getAttribute("username");
					

					RegisteredUser user = UsersService.getUser(username);
				
					//Get the user's name and role
					user = UsersService.getUser(username);
					fullname = ESAPI.encoder().encodeForHTML(user.getFirstname() + " " + user.getMiddleinitial() 
							 + " " + user.getLastname());
					
					role = ESAPI.encoder().encodeForHTML(UsersService.getRoleByUsername(username));
					request.setAttribute("fullname", fullname);
					request.setAttribute("role", role);
					
					//Get the readings to display
					readings = ReadingsService.getAllReadings();
					
					request.setAttribute("filterresults", 0);
					request.setAttribute("searchfilter", "Title");
					request.setAttribute("readings", readings);
					request.setAttribute("readingssize", readings.size());
					
					System.out.println("---Library Collection Servlet---");
					System.out.println("# of Readings: " + readings.size());
	
					request.getRequestDispatcher("/WEB-INF/SM-LibraryCollection.jsp").forward(request, response);
					break;
				case "/SM-SearchReadings":
					session = request.getSession();
					username = (String)session.getAttribute("username");
					
					//Get the user's name and role
					user = UsersService.getUser(username);

					//Get the user's name and role
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
					log.logger.info("User '"+ user.getUsername() + "' SEARCHED FOR A READING.");
					request.getRequestDispatcher("/WEB-INF/SM-LibraryCollection.jsp").forward(request, response);
					break;
	
				case "/SM-FilterByCategory":
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
					
					log.logger.info("User '"+ user.getUsername() + "' FILTER SEARCH RESULTS BY CATEGORY.");
					request.getRequestDispatcher("/WEB-INF/SM-LibraryCollection.jsp").forward(request, response);
					break;
					
				case "/AddReading":
					session = request.getSession();
					username = (String)session.getAttribute("username");
					
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
						log.logger.info("User '" + username + "'ADDED READING MATERIAL.");
						request.setAttribute("statusAdd", 
											"Reading Material '" + reading.getReadingtitle() + "' is successfully added!");
					}catch(Exception e){
						log.logger.warning("User '" + username + "'FAILED TO ADD READING MATERIAL.");
						request.setAttribute("statusAdd", "Failed to add the reading material!");
					}
					
					request.getRequestDispatcher("SM-LibraryCollection").forward(request, response);
					break;
				case "/EditReading":
					session = request.getSession();
					username = (String)session.getAttribute("username");
					
					int readingid = Integer.parseInt(request.getParameter(Readings.COLUMN_READINGID));
					readingtitle = request.getParameter(Readings.COLUMN_READINGTITLE);
					categoryid = Integer.parseInt(request.getParameter(Readings.COLUMN_CATEGORYID));
					author = request.getParameter(Readings.COLUMN_AUTHOR);
					publisher = request.getParameter(Readings.COLUMN_PUBLISHER);
					year = request.getParameter(Readings.COLUMN_YEAR);
					location = request.getParameter(Readings.COLUMN_LOCATION);
					tags = request.getParameter(Readings.COLUMN_TAGS);
					String status = request.getParameter(Readings.COLUMN_STATUS);
					
					reading = new Readings();
					reading.setReadingid(readingid);
					reading.setReadingtitle(readingtitle);
					reading.setCategoryid(categoryid);
					reading.setAuthor(author);
					reading.setPublisher(publisher);
					reading.setYear(year);
					reading.setLocation(location);
					reading.setTags(tags);
					reading.setStatus(status);
					System.out.println("---Editing Reading---");
					System.out.println(readingtitle + " " + categoryid+ " " + author + " "+ publisher + " " + year + location + tags + reading.getStatus() + "");
					
					try{
						ReadingsService.updateReading(reading);
						System.out.println("READING MATERIAL UPDATED!");
						log.logger.info("User '" + username + "' EDITED A READING MATERIAL.");
						request.setAttribute("statusEdit", 
								"Reading Material '" + reading.getReadingtitle() + "' is successfully updated!");					
					}catch(Exception e){
						log.logger.info("User '" + username + "FAILED TO EDIT A READING MATERIAL.");
						request.setAttribute("statusEdit", "Failed to update the reading material!");
					}
					
					request.getRequestDispatcher("SM-LibraryCollection").forward(request, response);
					break;
				case "/DeleteReading":
					session = request.getSession();
					username = (String)session.getAttribute("username");
					
					readingid = Integer.parseInt(request.getParameter(Readings.COLUMN_READINGID));
					reading = ReadingsService.getReadingsByID(readingid);
					
					try{
						ReadingsService.deleteReadings(readingid);
						System.out.println("---Deleting Reading---");
						System.out.println("READING MATERIAL DELETED!");
						log.logger.info("User '" + username + "DELETED A READING MATERIAL.");
						request.setAttribute("statusDelete", 
								"Reading Material '" + reading.getReadingtitle() + "' is successfully deleted!");					
					}catch(Exception e){
						log.logger.info("User DELETED A READING MATERIAL.");
						request.setAttribute("statusDelete", "Failed to delete the reading material!");
					}
					request.getRequestDispatcher("SM-LibraryCollection").forward(request, response);
					break;
				case "/S-MeetingRooms":
					session = request.getSession();
					username = (String)session.getAttribute("username");
					
					//Get the user's name and role
					user = UsersService.getUser(username);
					role = UsersService.getRoleByUsername(username);
					request.setAttribute("fullname", user.getFirstname() + " " + user.getMiddleinitial() 
										 + " " + user.getLastname());
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
					
					request.getRequestDispatcher("/WEB-INF/S-MeetingRooms.jsp").forward(request, response);
					break;
				case "/M-MeetingRooms":
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
					date = null;
					newdate = request.getParameter("date");
					if(newdate != null){
						date = newdate;
						request.setAttribute("date", date);
					}else if(request.getAttribute("date") != null){
						date = (String)request.getAttribute("date");
						request.setAttribute("date", date);
					}else{
						DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
						Date d = new Date();
						date = dateFormat.format(d);
					}
					
					rr1 = ReservedRoomService.getReservedRoomsByDateAndRoom(date, 1);
					rr2 = ReservedRoomService.getReservedRoomsByDateAndRoom(date, 2);
					rr3 = ReservedRoomService.getReservedRoomsByDateAndRoom(date, 3);
					rr4 = ReservedRoomService.getReservedRoomsByDateAndRoom(date, 4);
					rr5 = ReservedRoomService.getReservedRoomsByDateAndRoom(date, 5);
									
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
					
					request.getRequestDispatcher("/WEB-INF/M-MeetingRooms.jsp").forward(request, response);
					break;
				case "/M-DeleteRoomReservation":
					session = request.getSession();
					username = (String)session.getAttribute("username");
					
					int reservedroomid = Integer.parseInt(request.getParameter(ReservedRoom.COLUMN_RESERVEDROOMID));
					date = request.getParameter(ReservedRoom.COLUMN_DATE);
					try{
						ReservedRoomService.deleteReservedRooms(reservedroomid);
						System.out.println("---Deleting Room Reservation---");
						System.out.println("READING MATERIAL DELETED!");
						log.logger.info("User" + username + "OVERRIDE ROOM RESERVATION.");
						request.setAttribute("statusDelete", "Room reservation successfully removed!");	
						
					}catch(Exception e){
						log.logger.info("User" + username + "FAILED TO OVERRIDE ROOM RESERVATION.");
						request.setAttribute("statusDelete", "Failed to delete the reading material!");
					}
					
					request.setAttribute("date", date);
					request.getRequestDispatcher("M-MeetingRooms").forward(request, response);
					break;					
			}//end of switch
			}else{
				log.logger.info("User" + username + "TRIED TO ACCESS UNAUTHORIZED PAGE");
				request.getRequestDispatcher("/WEB-INF/Forbidden.jsp").forward(request, response);
			}	
		}else{
			log.logger.info("User" + username + "TRIED TO ACCESS UNAUTHORIZED PAGE");
			request.getRequestDispatcher("/WEB-INF/Forbidden.jsp").forward(request, response);
		}
		
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
