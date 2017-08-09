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

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.AuthenticationException;

import edu.securde.beans.LoginActivity;
import edu.securde.beans.RegisteredUser;
import edu.securde.beans.ReservedRoom;
import edu.securde.beans.SecurityAnswerActivity;
import edu.securde.security.AESencrp;
import edu.securde.security.BCrypt;
import edu.securde.security.Log;
import edu.securde.services.LoginActivityService;
import edu.securde.services.ReservedRoomService;
import edu.securde.services.SecurityAnswerActivityService;
import edu.securde.services.UsersService;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servlet implementation class AuthenticationController
 */
@WebServlet(urlPatterns = {"/AuthenticationController", "/StartServlet", "/LogIn", "/SignUp", "/LogOut",
							"/ForgotPassword", "/NewPassword", "/SecurityQuestion", 
							"/LoginServlet" , "/ForgotPasswordServlet", "/SignUpServlet"

})
public class AuthenticationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOGGER = Logger.getLogger(AuthenticationController.class.getName());
       
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
		
		Log log = new Log("C:\\Users\\Danica C. Sevilla\\Documents\\GitHub\\SECURDE-SHS-Online-Library-System\\Backend\\log.txt");
		String servletPath = request.getServletPath();
		
		//Local variables
		int usertype;
		String username, password, hashed, hashedSecurityAnswer;
		
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
					request.getRequestDispatcher("/WEB-INF/LogIn.jsp").forward(request,response);
				}
				else{
					System.out.println("OLD USER LOGGED IN ALREADY");
					user = UsersService.getUser((String)session.getAttribute("username"));
					
					log.logger.info("User '"+ user.getUsername() + "' IS ALREADY LOGGED IN.");
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
		
				int readingID;
				
				// TODO Auto-generated method stub
				username = request.getParameter(RegisteredUser.COLUMN_USERNAME);
				password = request.getParameter(RegisteredUser.COLUMN_PASSWORD);
				
				System.out.println("Username entered:" + username);
				System.out.println("Password entered:" + password);
				
				user = UsersService.getUser(username);
				if(user != null){
					if(!(user.getStatus().equals("Locked"))){ //not locked

						//Check if decrypted password matches input password
						if(BCrypt.checkpw(password, UsersService.validateUserbyUsername(username))){
							Cookie usernameCookie = new Cookie("username", username);
							
							//Cookie Attributes
							//usernameCookie.setMaxAge(60*60*24);
							usernameCookie.setHttpOnly(true);
							usernameCookie.setSecure(true);

							response.addCookie(usernameCookie);
							
							session = request.getSession();
							//Session Fixation --- Invalidate & Create New Session ID
							session.invalidate(); 
							try {
								session = ESAPI.httpUtilities().changeSessionIdentifier(request);
							} catch (AuthenticationException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							if(session.getAttribute("username") == null){
								session.setAttribute(RegisteredUser.COLUMN_USERNAME, username);
								session.setAttribute(RegisteredUser.COLUMN_USERTYPE, user.getUsertype());
								session.setAttribute(RegisteredUser.COLUMN_USERID, user.getUserid());
							}
							
							System.out.println("Log-In::SUCCESS");
							log.logger.info("User '"+ user.getUsername() + "' LOGGED IN.");
							
							if(LoginActivityService.searchLoginActivity(user.getUserid()) != null){
								LoginActivityService.deleteLoginActivity(user.getUserid());
							}
							if(SecurityAnswerActivityService.searchSecurityAnswerActivity(user.getUserid()) != null){
								SecurityAnswerActivityService.deleteSecurityAnswerActivity(user.getUserid());
							}

							if(user.getUsertype() == 1 || user.getUsertype() == 2){ //Student and Teacher
								request.getRequestDispatcher("LibraryCollection").forward(request,response);
							}else if(user.getUsertype() == 3 || user.getUsertype() == 4){ //Library Staff and Manager
								request.getRequestDispatcher("SM-LibraryCollection").forward(request, response);
							}else{
								request.getRequestDispatcher("A-Dashboard").forward(request, response);
							}
						}else{ //password mismatch
							
							LoginActivity loginActivity = LoginActivityService.searchLoginActivity(user.getUserid());
							
							if(loginActivity != null){
								if(loginActivity.getLoginattempts() >= 5){
									UsersService.updateStatus(user.getUserid(), "Locked");
									LoginActivityService.deleteLoginActivity(user.getUserid());
									request.setAttribute("lockMessage", "Your account has been locked. Contact admin to unlock.");	
									log.logger.warning("User '"+ user.getUsername() + "' IS LOCKED DUE "
											+ "NUMEROUS LOGIN ATTEMPTS.");
									request.getRequestDispatcher("/WEB-INF/LogIn.jsp").forward(request,response);
									break;
								}else{
									loginActivity.setLoginattempts(loginActivity.getLoginattempts() + 1);
									LoginActivityService.updateLoginAttempts(loginActivity);
									System.out.println("Atempt::+1");
								}	
							}else{
								if(user != null){
									loginActivity = new LoginActivity(user.getUserid(), 1);
									LoginActivityService.addLoginActivity(loginActivity);	
									System.out.println("Atempt::NEW");
								}
							}
							
							System.out.println("Log-In::FAILED");
							request.setAttribute("errorMessage", "Invalid username or password.");
							log.logger.warning("User '"+ user.getUsername() + "' ATTEMPTED TO LOGIN"
									+ "BUT FAILED.");
							request.getRequestDispatcher("/WEB-INF/LogIn.jsp").forward(request,response);
						}
						
					}else{//locked
						request.setAttribute("lockMessage", "Your account has been locked. Contact admin to unlock.");
						log.logger.warning("User '"+ user.getUsername() + "' ATTEMPTED TO LOGIN"
								+ "BUT FAILED.");
						request.getRequestDispatcher("/WEB-INF/LogIn.jsp").forward(request,response);
					}
					
				}else{
					System.out.println("Log-In::FAILED");
					request.setAttribute("errorMessage", "Invalid username or password.");
					log.logger.warning("User '"+ user.getUsername() + "' ATTEMPTED TO LOGIN"
							+ "BUT FAILED.");
					request.getRequestDispatcher("/WEB-INF/LogIn.jsp").forward(request,response);
				}
				
				//Decrypt password
				/*
				try {
					decryptedPassword = AESencrp.decrypt(UsersService.validateUserbyUsername(username));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(password != null && password.equals(decryptedPassword)){
					
					//put the user's info into session's attributes
					user = UsersService.getUser(username);
					System.out.println(user.getUsertype());
					System.out.println(user.getUserid());
					
					if(user.getLocked() == 0){ //not locked
						Cookie usernameCookie = new Cookie("username", username);
						usernameCookie.setMaxAge(60*60*24);
						usernameCookie.setHttpOnly(true);
						response.addCookie(usernameCookie);
						
						session = request.getSession();

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
					}else{ //locked
						request.setAttribute("lockMessage", "Your account has been locked. Try again after 15 mins.");
						request.getRequestDispatcher("LogIn.jsp").forward(request,response);						
					}
				}
				else{
					user = UsersService.getUser(username);
					LoginActivity loginActivity = LoginActivityService.searchLoginActivity(user.getUserid());

					if(loginActivity != null){
						if(loginActivity.getLoginattempts() >= 5){
							UsersService.lockUser(user.getUserid());
							LoginActivityService.deleteLoginActivity(user.getUserid());
						}else{
							loginActivity.setLoginattempts(loginActivity.getLoginattempts() + 1);
							LoginActivityService.updateLoginAttempts(loginActivity);
							System.out.println("Atempt::+1");
						}	
					}else{
						if(user != null){
							loginActivity = new LoginActivity(user.getUserid(), 1);
							LoginActivityService.addLoginActivity(loginActivity);	
							System.out.println("Atempt::NEW");
						}
					}
					
					System.out.println("Log-In::FAILED");
					request.setAttribute("errorMessage", "Invalid username or password.");
					request.getRequestDispatcher("LogIn.jsp").forward(request,response);
				}
				*/
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
				
				user = new RegisteredUser(idnum, usertype, firstname, middleinitial, lastname, username,
						password, email, birthday, secretquestion, secretanswer, "Activated");
				
				//Hash the password & security answer
				hashed = BCrypt.hashpw(password, BCrypt.gensalt());
				hashedSecurityAnswer = BCrypt.hashpw(secretanswer, BCrypt.gensalt());
				user.setPassword(hashed);
				user.setSecretanswer(hashedSecurityAnswer);
				System.out.println("Hash success!");
			
				//Check if username and email is already registered
				if(UsersService.isExisting(user.getEmailaddress(), user.getUsername()) == false){
					UsersService.addUser(user);
					System.out.println("USER REGISTERED!!" + user.getUsername());
					log.logger.warning("User '"+ user.getUsername() + "' IS SUCCESSFULLY REGISTERED.");
					request.setAttribute("statusAdd", "Successfully registered!");
					request.getRequestDispatcher("/WEB-INF/LogIn.jsp").forward(request,response);
				}else{
					request.setAttribute("errorMessage", "Failed to register account! Username/email already exists.");
					log.logger.warning("User '"+ user.getUsername() + "' ATTEMPTED TO REGISTER BUT FAILED.");
					request.getRequestDispatcher("/WEB-INF/SignUp.jsp").forward(request,response);
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
				int userid = (int) session.getAttribute("userid");
				user = UsersService.getUser(userid);
				
				log.logger.info("User '" + user.getUsername() + "' LOGGED OUT.");
				LoginActivityService.deleteLoginActivity((int)session.getAttribute(RegisteredUser.COLUMN_USERID));
				session.invalidate();
				
				System.out.println("---Log Out---");
				
				request.getRequestDispatcher("/WEB-INF/LogIn.jsp").forward(request,response);
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
					
					log.logger.warning("User DO FORGOT PASSWORD.");
					request.setAttribute("securityquestion", user.getSecretquestion());
					request.getRequestDispatcher("/WEB-INF/SecurityQuestion.jsp").forward(request, response);
					System.out.println("VALID USERNAME");
										
				}else{
					System.out.println("Invalid username or id number.");
					log.logger.warning("User ATTEMPTED TO DO FORGOT PASSWORD BUT FAILED.");
					request.setAttribute("errorMessage", "Invalid username or ID number.");
					request.getRequestDispatcher("/WEB-INF/ForgotPassword.jsp").forward(request, response);
				}
				break;
			case "/SecurityQuestion":
				session = request.getSession();
				userid = (int) session.getAttribute("userid");
				
				user = UsersService.getUser(userid);
				
				String answer = (String) request.getParameter("securityanswer");
				
				SecurityAnswerActivity securityAnswerActivity = 
						SecurityAnswerActivityService.searchSecurityAnswerActivity(user.getUserid());
				
				if(!user.getStatus().equals("Locked")){
					if(BCrypt.checkpw(answer, user.getSecretanswer())){
						
						if(LoginActivityService.searchLoginActivity(user.getUserid()) != null){
							LoginActivityService.deleteLoginActivity(user.getUserid());
						}
						
						if(SecurityAnswerActivityService.searchSecurityAnswerActivity(user.getUserid()) != null){
							SecurityAnswerActivityService.deleteSecurityAnswerActivity(user.getUserid());
						}
						
						log.logger.info("User '"+ user.getUsername() + "' ENTERED A CORRECT SECURITY ANSWER");
						request.getRequestDispatcher("/WEB-INF/NewPassword.jsp").forward(request, response);
					}
					else{
						if(securityAnswerActivity != null){
							if(securityAnswerActivity.getAnswer_attempts() >= 5){
								UsersService.updateStatus(user.getUserid(), "Locked");
								SecurityAnswerActivityService.deleteSecurityAnswerActivity(user.getUserid());
								request.setAttribute("lockMessage", "Your account has been locked. Contact admin to unlock.");	
								request.getRequestDispatcher("/WEB-INF/LogIn.jsp").forward(request,response);
								break;
							}else{
								securityAnswerActivity.setAnswer_attempts(securityAnswerActivity.getAnswer_attempts() + 1);
								SecurityAnswerActivityService.updateAnswerAttempts(securityAnswerActivity);
								System.out.println("Atempt::+1");
							}	
						}else{
							if(user != null){
								securityAnswerActivity = new SecurityAnswerActivity(user.getUserid(), 1);
								SecurityAnswerActivityService.addSecurityAnswerActivity(securityAnswerActivity);
								System.out.println("Atempt::NEW");
							}
						}					
						log.logger.warning("User '"+ user.getUsername() + "' ATTEMPTED TO DO FORGOT PASSWORD"
								+ "BUT FAILED.");
						request.setAttribute("securityquestion", user.getSecretquestion());
						request.setAttribute("errorMessage", "Security answer is incorrect!");
						request.getRequestDispatcher("/WEB-INF/SecurityQuestion.jsp").forward(request, response);
					}
				}else{
					log.logger.warning("User '"+ user.getUsername() + "' ATTEMPTED TO DO FORGOT PASSWORD"
							+ "BUT FAILED.");
					request.setAttribute("lockMessage", "Your account has been locked. Contact admin to unlock.");	
					request.getRequestDispatcher("/WEB-INF/LogIn.jsp").forward(request,response);
				}
				
				break;
			case "/NewPassword":
				String newpassword = request.getParameter("newpassword");
				String confirmpassword = request.getParameter("confirmpassword");
				if(newpassword.equals(confirmpassword)){
					session = request.getSession();
					userid = (int) session.getAttribute("userid");
					user = UsersService.getUser(userid);
					
					//Decrypt
					/*
					try {
						decryptedPassword = AESencrp.decrypt(UsersService.validateUserbyUsername(user.getUsername()));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					*/
						try {
							hashed = BCrypt.hashpw(newpassword, BCrypt.gensalt());
							if(UsersService.checkPasswordIfUnique(hashed)){
								System.out.println("Hash success!");
								if(UsersService.changePassword(userid, hashed)){
									System.out.println("Change Password Success!");
									log.logger.info("User '"+ user.getUsername() + "' CHANGED PASSWORD.");
									request.setAttribute("statusChangePassword", "Password changed successfully!");
									request.getRequestDispatcher("/WEB-INF/LogIn.jsp").forward(request, response);
								}
								else{
									System.out.println("Change Password failed!");
									log.logger.warning("User '"+ user.getUsername() + "' ATTEMPTED TO DO FORGOT PASSWORD"
											+ "BUT FAILED.");
									request.setAttribute("errorMessage", "Change of password failed!");
									request.getRequestDispatcher("/WEB-INF/NewPassword.jsp").forward(request, response);
								}
								System.out.println("Encryption success!");
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}else{
					System.out.println("Passwords did not match! Try again.");
					log.logger.warning("User ATTEMPTED TO CHANGE PASSWORD."
							+ "BUT FAILED.");
					request.setAttribute("errorMessage", "Password did not match! Try again.");
					request.getRequestDispatcher("/WEB-INF/NewPassword.jsp").forward(request, response);	
				}
				break;
			case "/LoginServlet":
				request.getRequestDispatcher("/WEB-INF/LogIn.jsp").forward(request, response);	
				break;
			case "/ForgotPasswordServlet":
				request.getRequestDispatcher("/WEB-INF/ForgotPassword.jsp").forward(request, response);	
				break;
			case "/SignUpServlet":
				request.getRequestDispatcher("/WEB-INF/SignUp.jsp").forward(request, response);
				break;
				
		}//end of switch
	}

}
