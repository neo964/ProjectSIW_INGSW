package controller;

import java.awt.List;
import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseManager;
import Model.Address;
import Model.PaymentMethod;
import Model.User;

public class Checkout extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String buy = (String) req.getParameter("buy");
		User user = (User) req.getSession().getAttribute("user");
		String iscart = (String) req.getParameter("iscart");
			LinkedList<PaymentMethod> payments = (LinkedList<PaymentMethod>) DatabaseManager.getInstance().getDaoFactory().getPaymentMethodDAO().findAll(user.getEmail());
			LinkedList<Address> addresses = (LinkedList<Address>) DatabaseManager.getInstance().getDaoFactory().getAddressDAO().findMyAddresses(user.getEmail());
			req.setAttribute("addresses", addresses);
			req.setAttribute("payments", payments);
			req.setAttribute("premium", true);
			if (iscart != null)
				req.setAttribute("iscart", iscart);
			else
				req.setAttribute("iscart", iscart);
			System.out.println(iscart);
			req.getRequestDispatcher("checkout.jsp").forward(req, resp);
	}
}
