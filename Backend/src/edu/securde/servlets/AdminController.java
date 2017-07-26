package edu.securde.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.securde.beans.AESencrp;
import edu.securde.beans.RegisteredUser;
import edu.securde.services.UsersService;

/**
 * Servlet implementation class AdminController
 */
@WebServlet(urlPatterns = {"/AdminController", "/A-Dashboard", "/AddUser"

})
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		String servletPath = request.getServletPath();
		switch(servletPath){
			case "/A-Dashboard":
				HttpSession session = request.getSession();
				String username = (String)session.getAttribute("username");
				
				//Get the user's name and role
				RegisteredUser user = UsersService.getUser(username);
				String role = UsersService.getRoleByUsername(username);
				request.setAttribute("fullname", user.getFirstname() + " " + user.getMiddleinitial() 
									 + " " + user.getLastname());
				request.setAttribute("role", role);
				
				request.getRequestDispatcher("A-Dashboard.jsp").forward(request, response);
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
				
				//0 because not active
				user = new RegisteredUser(idnum, 0, usertype, firstname, middleinitial, lastname, username,
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
					request.setAttribute("statusAdd", "Account '" + user.getUsername() + "' is successfully added!");
				}else{
					request.setAttribute("statusAdd", "Failed to add the user! Username/email already exists.");
				}
				
				request.getRequestDispatcher("A-Dashboard").forward(request,response);
				break;
		}
	}

}
