package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseManager;
import Model.Cart;
import Model.User;
import persistenceDAO.CartDAO;
import persistenceDAO.UserDAO;

public class GoToCart extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = (String) req.getSession().getAttribute("user");
		CartDAO cartdao = DatabaseManager.getInstance().getDaoFactory().getCartDao();
		Cart cart = cartdao.findByPrimaryKey(username);
		
		req.setAttribute("cart", cart);
		RequestDispatcher dispacher = req.getRequestDispatcher("checkout.jsp");
		dispacher.forward(req, resp);
	}

}
