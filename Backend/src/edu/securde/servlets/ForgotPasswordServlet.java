package edu.securde.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.securde.beans.AESencrp;
import edu.securde.beans.RegisteredUser;
import edu.securde.services.UsersService;

/**
 * Servlet implementation class ForgotPasswordServlet
 */
@WebServlet("/ForgotPasswordServlet")
public class ForgotPasswordServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username_idnumber = request.getParameter("input_username_idnumber");
		
		System.out.println(UsersService.validateUsernameIDnum(username_idnumber));
		
		HttpSession session = request.getSession();
		
		//put the user's info into session's attributes
		RegisteredUser user = UsersService.getUserByUsernameID(username_idnumber);
		session.setAttribute(RegisteredUser.COLUMN_USERNAME, user.getUsername());
		session.setAttribute(RegisteredUser.COLUMN_USERID, user.getUserid());
		
		if(UsersService.validateUsernameIDnum(username_idnumber)){
			request.getRequestDispatcher("NewPassword.jsp").forward(request, response);
			System.out.println("VALID USERNAME");
		}
	}
}
