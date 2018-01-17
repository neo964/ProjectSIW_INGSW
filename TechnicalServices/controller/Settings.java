package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseManager;
import Model.Address;
import Model.PaymentMethod;
import Model.User;
import persistenceDAO.UserDAO;
import persistenceDAO.UserReference;

public class Settings extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fname = req.getParameter("firstname");
		String lname = req.getParameter("lastname");
		String newpassword = req.getParameter("new");
		String oldpassword = req.getParameter("old");
		String street = req.getParameter("street");
		String country = req.getParameter("country");
		String district = req.getParameter("district");
		String cap = req.getParameter("zipcode");
		String card = req.getParameter("cardnumber");
		String expiration = req.getParameter("expiration");
		String cvc = req.getParameter("cvc");
		User user = (User) req.getSession().getAttribute("user");
		
		UserReference userref = DatabaseManager.getInstance().getDaoFactory().getUserRefernce(user);
	    String realpass = userref.getPassword();
		if (oldpassword.equals(realpass)) {
			DateFormat format = new SimpleDateFormat(expiration);
			Date expirationdate = null;
			try {
				expirationdate = format.parse(expiration);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Address address = null;
			PaymentMethod payement = null;
			
			if (street != null && country != null && district != null && cap != null)
				address = new Address(street, country, district, user.getEmail(), Integer.parseInt(cap));
			if (card != null && expiration != null && cvc != null)
				payement = new PaymentMethod(card, user.getEmail(), Integer.parseInt(cvc), expirationdate);
			User newUser = new User(fname, lname, user.getEmail(), address, payement, user.isPremium(), user.isAdmin(), user.getDateOfBirth(), user.getPathToImage(), newpassword);
			DatabaseManager.getInstance().getDaoFactory().getUserDAO().update(newUser);
			req.getSession().setAttribute("user", newUser);
		}
		req.getRequestDispatcher("settings.html").forward(req, resp);
	}
}
