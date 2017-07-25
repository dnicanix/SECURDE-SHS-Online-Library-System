package edu.securde.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.securde.beans.Readings;
import edu.securde.beans.RegisteredUser;
import edu.securde.beans.Review;
import edu.securde.beans.ReviewInfo;
import edu.securde.services.BorrowedReadingService;
import edu.securde.services.ReadingsService;
import edu.securde.services.ReviewService;
import edu.securde.services.UsersService;

/**
 * Servlet implementation class AddReviewServlet
 */
@WebServlet("/AddReviewServlet")
public class AddReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("AddReviewServlet");
		HttpSession session = request.getSession();
		int userid = (int) session.getAttribute("userid");
		
		//Get the user's name and role
		RegisteredUser user = UsersService.getUser(userid);
		String role = UsersService.getRoleByUsername(user.getUsername());
		request.setAttribute("fullname", user.getFirstname() + " " + user.getMiddleinitial() 
							 + " " + user.getLastname());
		request.setAttribute("role", role);
		
		int readingid = Integer.parseInt(request.getParameter("readingid_review"));
		Readings reading = ReadingsService.getReadingsByID(readingid);
		
		String inputreview = request.getParameter("text_review");
		
		/*DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();*/
		
		Review review = new Review(readingid, userid, inputreview);
		ReviewService.addReview(review);
		
		ArrayList<ReviewInfo> reviews = ReviewService.getReviewsOfBook(readingid);
		
		request.setAttribute("borrowed", BorrowedReadingService.checkIfBorrowedBytheUser(user.getUserid(), readingid));
		request.setAttribute("userid", user.getUserid());
		request.setAttribute("name", user.getFirstname() + " " + user.getLastname());
		request.setAttribute("reading", reading);
		request.setAttribute("user", user);
		request.setAttribute("reviews", reviews);
		request.getRequestDispatcher("ViewBookDetails.jsp").forward(request, response);
	}

}
