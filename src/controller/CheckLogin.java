package controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
		System.out.println("LOGGGG");
		UserDAO userdao = DatabaseManager.getInstance().getDaoFactory().getUserDAO();
		String username = req.getParameter("email");
		String password = req.getParameter("password");
	    User user = userdao.findByPrimaryKey(username);
	    if (user == null) {
	    	System.out.println("Errore");
	    	reportError(req, resp);
	    }
	    UserReference userref = DatabaseManager.getInstance().getDaoFactory().getUserRefernce(user);
	    String realpass = userref.getPassword();
	    System.out.println(realpass);

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
