package edu.securde.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.securde.beans.RegisteredUser;
import edu.securde.services.UsersService;

/**
 * Servlet implementation class SecurityQuestionServlet
 */
@WebServlet("/SecurityQuestionServlet")
public class SecurityQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SecurityQuestionServlet() {
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
		
		HttpSession session = request.getSession();
		int userid = (int) session.getAttribute("userid");
		
		RegisteredUser user = UsersService.getUser(userid);
		
		String answer = (String) request.getParameter("securityanswer");
		System.out.println("Security Answer: " + answer);
		
		if(UsersService.checkSecurityAnswer(userid, answer)){
			request.getRequestDispatcher("NewPassword.jsp").forward(request, response);
		}
		else{
			request.getRequestDispatcher("ForgotPasswordServlet").forward(request, response);
		}
		
	}

}
