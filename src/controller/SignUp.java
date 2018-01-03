package controller;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseManager;
import Model.Address;
import Model.PaymentMethod;
import Model.User;
import persistenceDAO.UserDAO;

public class SignUp extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fname = req.getParameter("firstname");
		String lname = req.getParameter("lastname");
		String username = req.getParameter("emailreg");
		String password = req.getParameter("passwordreg");
		String birth = req.getParameter("birth");
		String street = req.getParameter("street");
		String country = req.getParameter("country");
		String district = req.getParameter("district");
		String cap = req.getParameter("cap");
		String card = req.getParameter("card");
		String expiration = req.getParameter("expiration");
		String cvc = req.getParameter("cvc");
		
		if (fname == null || lname == null || username == null || password == null || street == null || country == null|| district == null|| cap == null || card == null || cvc == null || expiration == null ) {
			reportError (req, resp);
		}
		if (!sendRegEmail(username)) {
			reportError (req, resp);
		}
		
		UserDAO userdao = DatabaseManager.getInstance().getDaoFactory().getUserDAO();
		User user = new User(fname, lname, username, new Address(street, country, district, username, Integer.parseInt(cap)), new PaymentMethod(card, username, Integer.parseInt(cvc), new Date (Long.parseLong(expiration))), false, false, new Date (Long.parseLong(birth)), password);
		userdao.save(user);
		req.getSession().setAttribute("user", user); // Login user.
        resp.sendRedirect("index.jsp"); // Redirect to home page.
    
	}

	public boolean sendRegEmail (String to) throws IOException {
		final String username = "blackperrycs@gmail.com";
		final String password = "Dobrowitz4";
		final String subject = "Welcome";
		final String text = "Welcome to our comunity";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(username, password);
			}
		  });
		System.out.println("QUI");

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(text);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private void reportError (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//req.setAttribute("message", "Unknown username/password. Please retry."); // Store error message in request scope.
        req.getRequestDispatcher("loginpage.html").forward(req, resp); // Forward to JSP page to redisplay login form with error.
	}

}
