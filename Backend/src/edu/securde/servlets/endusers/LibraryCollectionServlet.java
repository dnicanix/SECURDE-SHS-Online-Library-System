package edu.securde.servlets.endusers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.securde.beans.Readings;
import edu.securde.beans.RegisteredUser;
import edu.securde.beans.UserType;
import edu.securde.services.ReadingsService;
import edu.securde.services.UsersService;

/**
 * Servlet implementation class LibraryCollectionServlet
 */
@WebServlet("/LibraryCollectionServlet")
public class LibraryCollectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibraryCollectionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		
		//Get the user's name and role
		RegisteredUser user = UsersService.getUser(username);
		request.setAttribute("fullname", user.getFirstname() + " " + user.getMiddleinitial() 
							 + " " + user.getLastname());
		// ?????? for role
		
		//Get the readings to display
		ArrayList<Readings> readings = ReadingsService.getAllReadings();
		request.setAttribute("readings", readings);
		request.setAttribute(Readings.COLUMN_CATEGORYID, 0);
		request.setAttribute("searchfilter", "Title");
		System.out.println("---Library Collection Servlet---");
		System.out.println("# of Readings: " + readings.size());

		request.getRequestDispatcher("LibraryCollection.jsp").forward(request, response);
	}

}
