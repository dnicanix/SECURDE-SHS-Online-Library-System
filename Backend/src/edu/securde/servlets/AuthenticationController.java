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

import edu.securde.beans.AESencrp;
import edu.securde.beans.RegisteredUser;
import edu.securde.beans.ReservedRoom;
import edu.securde.services.ReservedRoomService;
import edu.securde.services.UsersService;

/**
 * Servlet implementation class AuthenticationController
 */
@WebServlet(urlPatterns = {"/AuthenticationController", "/StartServlet", "/LogIn", "/SignUp", "/LogOut",
							"/ForgotPassword", "/NewPassword", "/SecurityQuestion"

})
public class AuthenticationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthenticationController() {
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
		
		//Local variables
		int usertype;
		String username;
		String password;
		switch(servletPath){
			case "/StartServlet":
				System.out.println("AuthenticationController");
				HttpSession session;
				Cookie[] cookieList;
				RegisteredUser user;
						
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
					
					if(user.getUsertype() == 1 || user.getUsertype() == 2){ //Student and Teacher
						request.getRequestDispatcher("LibraryCollection").forward(request,response);
					}else if(user.getUsertype() == 3 || user.getUsertype() == 4){ //Library Staff and Manager
						request.getRequestDispatcher("SM-LibraryCollection").forward(request, response);
					}else{
						request.getRequestDispatcher("A-Dashboard").forward(request, response);
					}
										
				}
				
				break;
			case "/LogIn":
				String decryptedPassword = null;
				int readingID;
				
				// TODO Auto-generated method stub
				username = request.getParameter(RegisteredUser.COLUMN_USERNAME);
				password = request.getParameter(RegisteredUser.COLUMN_PASSWORD);
				
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
					
					if(user.getUsertype() == 1 || user.getUsertype() == 2){ //Student and Teacher
						request.getRequestDispatcher("LibraryCollection").forward(request,response);
					}else if(user.getUsertype() == 3 || user.getUsertype() == 4){ //Library Staff and Manager
						request.getRequestDispatcher("SM-LibraryCollection").forward(request, response);
					}else{
						request.getRequestDispatcher("A-Dashboard").forward(request, response);
					}
				}
				else{
					System.out.println("Log-In::FAILED");
					request.setAttribute("errorMessage", "Invalid username or password.");
					request.getRequestDispatcher("LogIn.jsp").forward(request,response);
				}
				break;
			case "/SignUp":
				String firstname = request.getParameter(RegisteredUser.COLUMN_FIRSTNAME);
				String middleinitial = request.getParameter(RegisteredUser.COLUMN_MIDDLEINITIAL);	
				String lastname = request.getParameter(RegisteredUser.COLUMN_LASTNAME);
				
				String idnum = request.getParameter(RegisteredUser.COLUMN_IDNUM);
				usertype = 0;
				username = request.getParameter(RegisteredUser.COLUMN_USERNAME);
				password = request.getParameter(RegisteredUser.COLUMN_PASSWORD);
				String email = request.getParameter(RegisteredUser.COLUMN_EMAILADDRESS);
				String birthday = request.getParameter(RegisteredUser.COLUMN_BIRTHDAY);
				String secretquestion = request.getParameter(RegisteredUser.COLUMN_SECRETQUESTION);
				String secretanswer = request.getParameter(RegisteredUser.COLUMN_SECRETANSWER);
				
				//Set if USER or TEACHER
				if(idnum.startsWith("11"))
					usertype = 1; // Student
				else if(idnum.startsWith("99"))
					usertype = 2; //Teacher
				else if(idnum.startsWith("00"))
					usertype = 5; //Admin - to test onlyy
				
				user = new RegisteredUser(idnum, 1, usertype, firstname, middleinitial, lastname, username,
						password, email, birthday, secretquestion, secretanswer);
				
				//Encrypt the password
				try {
					System.out.println("ENCRYPT!");
					user.setPassword(AESencrp.encrypt(password));
					System.out.println("Encryption success!");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Encryption failed!");
				}
				
				//Check if username and email is already registered
				if(UsersService.isExisting(user.getEmailaddress(), user.getUsername()) == false){
					UsersService.addUser(user);
					System.out.println("USER REGISTERED!!" + user.getUsername());
					request.setAttribute("statusAdd", "Successfully registered!");
					request.getRequestDispatcher("LogIn.jsp").forward(request,response);
				}else{
					request.setAttribute("errorMessage", "Failed to register account! Username/email already exists.");
					request.getRequestDispatcher("SignUp.jsp").forward(request,response);
				}
				break;
			case "/LogOut":
				cookieList = request.getCookies();
				
				if(cookieList != null){
					for(int i = 0; i < cookieList.length; i++){
						if(cookieList[i].getName().equals("username")){
							cookieList[i].setMaxAge(0);
							response.addCookie(cookieList[i]); //override the old cookie
						}
					}
				}
				
				session = request.getSession();
				session.invalidate();
				
				System.out.println("---Log Out---");
				request.getRequestDispatcher("LogIn.jsp").forward(request,response);
				break;
			case "/ForgotPassword":
				/*
				String username_idnumber = request.getParameter("input_username_idnumber");		
				session = request.getSession();
				
				if(UsersService.validateUsernameIDnum(username_idnumber)){
					//put the user's info into session's attributes
					user = UsersService.getUserByUsernameID(username_idnumber);
					session.setAttribute(RegisteredUser.COLUMN_USERNAME, user.getUsername());
					session.setAttribute(RegisteredUser.COLUMN_USERID, user.getUserid());
					
					request.getRequestDispatcher("NewPassword.jsp").forward(request, response);
					System.out.println("---Forgot Password---");
					System.out.println("Valid username or id number.");
				}else{
					System.out.println("Invalid username or id number.");
					request.setAttribute("errorMessage", "Invalid username or ID number.");
					request.getRequestDispatcher("ForgotPassword.jsp").forward(request, response);
				}
				*/
				
				String username_idnumber = request.getParameter("input_username_idnumber");
				
				session = request.getSession();
				//put the user's info into session's attributes
				
				if(UsersService.validateUsernameIDnum(username_idnumber)){
					user = UsersService.getUserByUsernameID(username_idnumber);

					session.setAttribute(RegisteredUser.COLUMN_USERID, user.getUserid());
					
					request.setAttribute("securityquestion", user.getSecretquestion());
					request.getRequestDispatcher("SecurityQuestion.jsp").forward(request, response);
					System.out.println("VALID USERNAME");
										
				}else{
					System.out.println("Invalid username or id number.");
					request.setAttribute("errorMessage", "Invalid username or ID number.");
					request.getRequestDispatcher("ForgotPassword.jsp").forward(request, response);
				}
				break;
			case "/SecurityQuestion":
				session = request.getSession();
				int userid = (int) session.getAttribute("userid");
				
				user = UsersService.getUser(userid);
				
				String answer = (String) request.getParameter("securityanswer");
				System.out.println("Security Answer: " + answer);
				
				if(UsersService.checkSecurityAnswer(userid, answer)){
					request.getRequestDispatcher("NewPassword.jsp").forward(request, response);
				}
				else{
					request.setAttribute("securityquestion", user.getSecretquestion());
					request.setAttribute("errorMessage", "Security answer is incorrect!");
					request.getRequestDispatcher("SecurityQuestion.jsp").forward(request, response);
				}
				break;
			case "/NewPassword":
				String newpassword = request.getParameter("newpassword");
				String confirmpassword = request.getParameter("confirmpassword");
				
				if(newpassword.equals(confirmpassword)){
					session = request.getSession();
					userid = (int) session.getAttribute("userid");
					user = UsersService.getUser(userid);
					
					//check if the new password is unique
					try {
						if(UsersService.checkPasswordIfUnique(AESencrp.encrypt(newpassword))){
							
							//Encrypt the password
							try {
								System.out.println("ENCRYPT!");
								if(UsersService.changePassword(userid, AESencrp.encrypt(newpassword))){
									System.out.println("Change Password Success!");
									System.out.println("Password: " + AESencrp.decrypt(user.getPassword()));
									request.setAttribute("statusChangePassword", "Password changed successfully!");
									request.getRequestDispatcher("LogIn.jsp").forward(request, response);
								}
								else{
									System.out.println("Change Password failed!");
									request.setAttribute("errorMessage", "Change of password failed!");
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
							request.setAttribute("errorMessage", "Change of password failed! Try again.");
							request.getRequestDispatcher("NewPassword.jsp").forward(request, response);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else{
					System.out.println("Password did not match! Try again.");
					request.setAttribute("errorMessage", "Password did not match! Try again.");
					request.getRequestDispatcher("NewPassword.jsp").forward(request, response);
				}
				break;
		}//end of switch
	}

}
