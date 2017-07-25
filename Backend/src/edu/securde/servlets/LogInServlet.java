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
 * Servlet implementation class LogInServlet
 */
@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogInServlet() {
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
		HttpSession session;
		Cookie[] cookieList;
		RegisteredUser user;
		String username;
		String decryptedPassword = null;
		String usertype;
		int readingID;
		// TODO Auto-generated method stub
		username = request.getParameter(RegisteredUser.COLUMN_USERNAME);
		String password = request.getParameter(RegisteredUser.COLUMN_PASSWORD);
		
		System.out.println("Username entered:" + username);
		System.out.println("Password entered:" + password);
		
		//Decrypt password
		try {
			decryptedPassword = AESencrp.decrypt(UsersService.validateUserbyUsername(username));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(password != null && password.equals(decryptedPassword)){
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
			
			request.getRequestDispatcher("LibraryCollection").forward(request, response);
			
			/*
			if(user.getUsertype() == 1 || user.getUsertype() == 2){
				request.getRequestDispatcher("LibraryCollectionServlet").forward(request, response);
			}else if(user.getUsertype() == 3 || user.getUsertype() == 4){
				request.getRequestDispatcher("LibraryCollectionForStaffAndManagerServlet").forward(request, response);
			}
			*/
		}
		else{
			System.out.println("Log-In::FAILED");
			request.setAttribute("errorMessage", "Invalid username or password.");
			request.getRequestDispatcher("LogIn.jsp").forward(request,response);
		}
	}

}
