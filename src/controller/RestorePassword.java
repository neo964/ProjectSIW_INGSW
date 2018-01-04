package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseManager;
import Model.User;
import persistenceDAO.UserDAO;
import persistenceDAO.UserReference;

public class RestorePassword extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mail = req.getParameter("mail");
		String fname = req.getParameter("firstname");
		String lname = req.getParameter("lastname");
		
		UserDAO userdao = DatabaseManager.getInstance().getDaoFactory().getUserDAO();
		User user = userdao.findByPrimaryKey(mail);
		
		 if (user == null || !fname.equals(user.getFirstName()) || !lname.equals(user.getLastName())) {
		    	System.out.println("Errore");
		    	reportError(req, resp);
		    }
		 UserReference userref = DatabaseManager.getInstance().getDaoFactory().getUserRefernce(user);
		 String realpass = userref.getPassword();
		 SendEmail send = new SendEmail();
		 send.sendEmail(mail, "Restore Password", "Your password is " + realpass);
		 req.getRequestDispatcher("loginpage.html").forward(req, resp); // Forward to JSP page to redisplay login form with error.
			
		
	}
	
	private void reportError (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//req.setAttribute("message", "Unknown username/password. Please retry."); // Store error message in request scope.
        req.getRequestDispatcher("loginpage.html").forward(req, resp); // Forward to JSP page to redisplay login form with error.
	}
}
