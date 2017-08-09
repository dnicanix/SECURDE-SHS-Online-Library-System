package edu.securde.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.owasp.esapi.ESAPI;

import edu.securde.beans.RegisteredUser;
import edu.securde.security.AESencrp;
import edu.securde.security.BCrypt;
import edu.securde.security.Log;
import edu.securde.services.UsersService;

/**
 * Servlet implementation class AdminController
 */
@WebServlet(urlPatterns = {"/AdminController", "/A-Dashboard", "/AddUser", "/UnlockAccount"

})

public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOGGER = Logger.getLogger(AuthenticationController.class.getName());      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
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
		HttpSession session = request.getSession();
		String fullname;
		if( session.getAttribute(RegisteredUser.COLUMN_USERTYPE) != null){
			if(session.getAttribute(RegisteredUser.COLUMN_USERTYPE).equals(5)){
			switch(servletPath){
				case "/A-Dashboard":
					session = request.getSession();
					String username = (String)session.getAttribute("username");
					
					//Get the user's name and role
					RegisteredUser user = UsersService.getUser(username);
					String role = UsersService.getRoleByUsername(username);
					ArrayList<RegisteredUser> users = UsersService.getAllUsers();
					
					//Get the user's name and role
					fullname = ESAPI.encoder().encodeForHTML(user.getFirstname() + " " + user.getMiddleinitial() 
							 + " " + user.getLastname());
					
					role = ESAPI.encoder().encodeForHTML(UsersService.getRoleByUsername(username));
					
					request.setAttribute("fullname", fullname);
					request.setAttribute("role", role);
					request.setAttribute("users", users);
					request.getRequestDispatcher("/WEB-INF/A-Dashboard.jsp").forward(request, response);
					break;
				case "/AddUser":
					System.out.println("Add User hello");
					
					int usertype = Integer.parseInt(request.getParameter(RegisteredUser.COLUMN_USERTYPE));
					String firstname = request.getParameter(RegisteredUser.COLUMN_FIRSTNAME);
					String middleinitial = request.getParameter(RegisteredUser.COLUMN_MIDDLEINITIAL);	
					String lastname = request.getParameter(RegisteredUser.COLUMN_LASTNAME);
					String idnum = request.getParameter(RegisteredUser.COLUMN_IDNUM);
					username = request.getParameter(RegisteredUser.COLUMN_USERNAME);
					String password = request.getParameter(RegisteredUser.COLUMN_PASSWORD);
					String email = request.getParameter(RegisteredUser.COLUMN_EMAILADDRESS);
					String birthday = request.getParameter(RegisteredUser.COLUMN_BIRTHDAY);
					String secretquestion = request.getParameter(RegisteredUser.COLUMN_SECRETQUESTION);
					String secretanswer = request.getParameter(RegisteredUser.COLUMN_SECRETANSWER);
					
					//pending user
					user = new RegisteredUser(idnum, usertype, firstname, middleinitial, lastname, username,
							password, email, birthday, secretquestion, secretanswer, "Pending");
					
					//Hash the password
					String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
					String hashedSecurityAnswer = BCrypt.hashpw(secretanswer, BCrypt.gensalt());
					user.setPassword(hashed);
					user.setSecretanswer(hashedSecurityAnswer);
					System.out.println("Hash success!");

					//Check if username and email is already registered
					if(UsersService.isExisting(user.getEmailaddress(), user.getUsername()) == false){
						UsersService.addUser(user);
						System.out.println("USER REGISTERED!!" + user.getUsername());
						log.logger.info("Admin '"+ user.getUsername() + "' ADDED AN ACCOUNT.");
						request.setAttribute("statusAdd", "Account '" + user.getUsername() + "' is successfully added!");
					}else{
						log.logger.warning("Admin '"+ user.getUsername() + "' FAILED TO ADD AN ACCOUNT.");
						request.setAttribute("statusAdd", "Failed to add the user! Username/email already exists.");
					}
					
					request.getRequestDispatcher("A-Dashboard").forward(request,response);
					break;
				case "/UnlockAccount":
					int userid = Integer.parseInt(request.getParameter(RegisteredUser.COLUMN_USERID));
					user = UsersService.getUser(userid);
					if(UsersService.updateStatus(userid, "Activated")){
						request.setAttribute("statusUnlock", "Account '" + user.getUsername() + "' successfully unlocked!");
						log.logger.info("Admin '"+ user.getUsername() + "' UNLOCKED AN ACCOUNT.");
					}else{
						log.logger.warning("Admin '"+ user.getUsername() + "' FAILED TO UNLOCK AN ACCOUNT.");
					}
					
					request.getRequestDispatcher("A-Dashboard").forward(request,response);
					break;
			}//end of switch
			}else{
				request.getRequestDispatcher("/WEB-INF/Forbidden.jsp").forward(request, response);	
			}
		}else{
			request.getRequestDispatcher("/WEB-INF/Forbidden.jsp").forward(request, response);
		}
	}

}
