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
import persistenceDAO.AddressDAO;
import persistenceDAO.PaymentMethodDAO;

public class UpdateInfo extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String street = (String)req.getParameter("street");
		String country = (String) req.getParameter("country");
		String district = (String) req.getParameter("district");
		String zipcode = (String) req.getParameter("zipcode");
		String cardnumber = (String) req.getParameter("cardnumber");
		String expirationdate = (String) req.getParameter("expirationdate");
		String cvc = (String) req.getParameter("cvc");
		User user = (User) req.getSession().getAttribute("user");
		
		if (street != null) {
			int zip = Integer.parseInt(zipcode);
			AddressDAO addressdao = DatabaseManager.getInstance().getDaoFactory().getAddressDAO();
			addressdao.save(new Address(street, country, district, user.getEmail(), zip));
		} else if (cardnumber != null) {
			int code = Integer.parseInt(cvc);
			DateFormat format = new SimpleDateFormat(expirationdate);
			Date expiration = null;
			try {
				expiration = format.parse(expirationdate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DatabaseManager.getInstance().getDaoFactory().getPaymentMethodDAO().save(new PaymentMethod(cardnumber, user.getEmail(), code, expiration));
		}
		req.setAttribute("buy", "premium");
		Checkout check = new Checkout();
		check.doGet(req, resp);
	}
}
