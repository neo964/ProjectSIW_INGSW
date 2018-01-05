package controller;

import javax.servlet.http.HttpServlet;

import Database.DatabaseManager;
import Model.Cart;
import Model.Multimedia;
import Model.MultimediaInCart;
import Model.User;
import persistenceDAO.CartDAO;
import persistenceDAO.UserDAO;

public class CartHandler extends HttpServlet{
	
	protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws javax.servlet.ServletException ,java.io.IOException {
		Multimedia multimedia= (Multimedia)req.getAttribute("multimediaincart");
		CartDAO cartdao = DatabaseManager.getInstance().getDaoFactory().getCartDao();
		
		Cart cart = new Cart ((User)req.getSession().getAttribute("user"));
		cart.addToCart(new MultimediaInCart(multimedia, 1));
		cartdao.save(cart);
	}

}
