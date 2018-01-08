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

public class BuyIt extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String address = (String) req.getParameter("address");
		String payment = (String) req.getParameter("paymentmethod");
		String iscart = (String) req.getParameter("iscart");
		User user = (User)req.getSession().getAttribute("user");
		System.out.println(iscart);
		if (iscart != null && iscart.equals("true")) { System.out.println("ISCART");
			DatabaseManager.getInstance().getDaoFactory().getCartDao().deleteAll(user.getEmail());
		} else {
			String pass = DatabaseManager.getInstance().getDaoFactory().getUserRefernce(user).getPassword();
			User newUser = new User(user.getFirstName(), user.getLastName(), user.getEmail(), null, null, true, user.isAdmin(), user.getDateOfBirth(), user.getPathToImage(), pass);
			DatabaseManager.getInstance().getDaoFactory().getUserDAO().update(user);
			System.out.println("Refresh");
			req.getSession().setAttribute("user", newUser);
		}
		if (address == null || payment == null) {
			System.out.println("Not valid");
		}
		System.out.println("Siamoq ui");
		req.getRequestDispatcher("myprofile.jsp").forward(req, resp);
		
		
	}

}
