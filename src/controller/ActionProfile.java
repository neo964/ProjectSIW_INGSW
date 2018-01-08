package controller;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseManager;
import Model.MultimediaInCart;
import Model.User;
import persistenceDAO.CartDAO;

public class ActionProfile extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = (String) req.getParameter("actionprofile");
		User user = (User) req.getSession().getAttribute("user");
		
		if (action == null) {
			System.out.println("null");
			return;
		}
		
		if (action.equals("subscribe")) {
			RequestDispatcher dispacher = req.getRequestDispatcher("subscribe.jsp");
			dispacher.forward(req, resp);
			
		} else if (action.equals("cart")) {
			CartDAO cartdao = DatabaseManager.getInstance().getDaoFactory().getCartDao();
			LinkedList<MultimediaInCart> cart = (LinkedList<MultimediaInCart>) cartdao.findByPrimaryKey(user.getEmail()).getCart();
			req.setAttribute("cart", cart);
			RequestDispatcher dispacher = req.getRequestDispatcher("cart.jsp");
			dispacher.forward(req, resp);
			
		} else if (action.equals("settings")) {
			RequestDispatcher dispacher = req.getRequestDispatcher("settings.html");
			dispacher.forward(req, resp);
			
		} else if (action.equals("favourite")) {
			RequestDispatcher dispacher = req.getRequestDispatcher("chooseFavourite.jsp");
			dispacher.forward(req, resp);
		}
		
		
	}

}
