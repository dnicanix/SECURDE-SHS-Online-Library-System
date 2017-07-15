package edu.securde.servlets.endusers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.securde.beans.BorrowedReading;
import edu.securde.beans.Readings;
import edu.securde.services.BorrowedReadingService;
import edu.securde.services.UsersService;

/**
 * Servlet implementation class ReserveReadingServlet
 */
@WebServlet("/ReserveReadingServlet")
public class ReserveReadingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReserveReadingServlet() {
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
		
		int readingID = Integer.parseInt(request.getParameter(Readings.COLUMN_READINGID));
		String username = (String)session.getAttribute("username");
		int userID = UsersService.getUser(username).getUserid();
		
		BorrowedReading borrowedReading = new BorrowedReading(readingID, userID);
		BorrowedReadingService.reserveReading(borrowedReading);
		
		System.out.println("---Borrowing Book---");
		System.out.println("ReadingID: " + readingID);
		System.out.println("UserID: " + userID);
		
		request.getRequestDispatcher("LibraryCollectionServlet").forward(request, response);
	}

}
