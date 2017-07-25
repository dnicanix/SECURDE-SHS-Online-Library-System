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
 * Servlet implementation class NewPasswordServlet
 */
@WebServlet("/NewPasswordServlet")
public class NewPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewPasswordServlet() {
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
		// TODO Auto-generated method stub
		String newpassword = request.getParameter("newpassword");
		String confirmpassword = request.getParameter("confirmpassword");
		
		if(newpassword.equals(confirmpassword)){
			HttpSession session = request.getSession();
			int userid = (int) session.getAttribute("userid");
			RegisteredUser user = UsersService.getUser(userid);
			
			//check if the new password is unique
			try {
				if(UsersService.checkPasswordIfUnique(AESencrp.encrypt(newpassword))){
					
					//Encrypt the password
					try {
						System.out.println("ENCRYPT!");
						//);
						if(UsersService.changePassword(userid, AESencrp.encrypt(newpassword))){
							System.out.println("Change Password Success!");
							System.out.println("Password: " + AESencrp.decrypt(user.getPassword()));
							
							Cookie usernameCookie = new Cookie("username", user.getUsername());
							usernameCookie.setMaxAge(60*60*24);
							usernameCookie.setHttpOnly(true);
							response.addCookie(usernameCookie);
							
							System.out.println("Log-In::SUCCESS");
							
							request.getRequestDispatcher("LibraryCollection").forward(request, response);;
						}
						else{
							System.out.println("Change Password failed!");
							request.getRequestDispatcher("NewPassword.jsp").forward(request, response);
						}
						System.out.println("Encryption success!");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Encryption failed!");
					}	
				}
				else{
					System.out.println("Change Password failed! Try again.");
					request.getRequestDispatcher("NewPassword.jsp").forward(request, response);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			System.out.println("Password did not match! Try again.");
			request.getRequestDispatcher("NewPassword.jsp").forward(request, response);
		}
		
	}

}
