package controller;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

import Database.DatabaseManager;
import Model.User;
import persistenceDAO.UserDAO;
import persistenceDAO.UserReference;

public class CheckLogin extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		UserDAO userdao = DatabaseManager.getInstance().getDaoFactory().getUserDAO();
		String username = req.getParameter("email");
		String password = req.getParameter("password");
	    User user = userdao.findByPrimaryKey(username);
	    if (user == null) {
	    	reportError(req, resp);
	    }
	    System.out.println(password);
	    UserReference userref = DatabaseManager.getInstance().getDaoFactory().getUserRefernce(user);
	    String realpass = userref.getPassword();
	    System.out.println(realpass);
	    System.out.println("Control");

	    if (realpass.equals(password)) {
	        req.getSession().setAttribute("user", user.getEmail()); // Login user.
	        req.getSession().setAttribute("admin", user.isAdmin());
	        req.getSession().setAttribute("premium", user.isPremium());
	        req.getSession().setAttribute("image", user.getPathToImage());
	        req.getSession().setAttribute("firstName", user.getFirstName());
	        req.getSession().setAttribute("lastName", user.getLastName());
	        resp.sendRedirect("index.jsp"); // Redirect to home page.
	    } else {
	    	reportError(req, resp);
	    }
	}
	
	private void reportError (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//req.setAttribute("message", "Unknown username/password. Please retry."); // Store error message in request scope.
        req.getRequestDispatcher("loginpage.html").forward(req, resp); // Forward to JSP page to redisplay login form with error.
	}
}
