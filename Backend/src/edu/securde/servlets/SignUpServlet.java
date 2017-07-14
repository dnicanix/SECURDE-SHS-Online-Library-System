package edu.securde.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.User;

import edu.securde.beans.AESencrp;
import edu.securde.beans.RegisteredUser;
import edu.securde.services.UsersService;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpServlet() {
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
		String firstname = request.getParameter(RegisteredUser.COLUMN_FIRSTNAME);
		String middleinitial = request.getParameter(RegisteredUser.COLUMN_MIDDLEINITIAL);	
		String lastname = request.getParameter(RegisteredUser.COLUMN_LASTNAME);
		
		String idnum = request.getParameter(RegisteredUser.COLUMN_IDNUM);
		int usertype = 0;
		String username = request.getParameter(RegisteredUser.COLUMN_USERNAME);
		String password = request.getParameter(RegisteredUser.COLUMN_PASSWORD);
		String email = request.getParameter(RegisteredUser.COLUMN_EMAILADDRESS);
		String birthday = request.getParameter(RegisteredUser.COLUMN_BIRTHDAY);
		String secretquestion = request.getParameter(RegisteredUser.COLUMN_SECRETQUESTION);
		String secretanswer = request.getParameter(RegisteredUser.COLUMN_SECRETANSWER);
		
		//Set if USER or TEACHER
		if(idnum.startsWith("11"))
			usertype = 1; // Student
		else if(idnum.startsWith("99"))
			usertype = 2; //Teacher
		
		RegisteredUser user = new RegisteredUser(idnum, 1, usertype, firstname, middleinitial, lastname, username,
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
		}
		
		request.getRequestDispatcher("LogIn.jsp").forward(request,response);
	}
	
	 

}
