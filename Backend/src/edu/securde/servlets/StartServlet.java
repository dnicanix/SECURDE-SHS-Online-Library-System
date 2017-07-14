package edu.securde.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.securde.beans.RegisteredUser;
import edu.securde.services.UsersService;

/**
 * Servlet implementation class StartServlet
 */
@WebServlet("/StartServlet")
public class StartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartServlet() {
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
		}
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	
	}

}
