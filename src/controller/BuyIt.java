package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseManager;
import Model.User;
import persistenceDAO.UserDAO;

public class BuyIt extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String address = (String) req.getParameter("address");
		String payment = (String) req.getParameter("paymentmethod");
		String iscart = (String) req.getParameter("iscart");
		
		if (iscart != null && iscart.equals("true")) {
			User user = (User)req.getSession().getAttribute("user");
			DatabaseManager.getInstance().getDaoFactory().getCartDao().deleteAll(user.getEmail());
		}
		
		System.out.println(address);
		System.out.println(payment);
		
		if (address == null || payment == null) {
			System.out.println("Not valid");
		}
		req.getRequestDispatcher("myprofile.jsp").forward(req, resp);
		
		
	}

}
