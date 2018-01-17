package controller;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseManager;
import Model.Cart;
import Model.Multimedia;
import Model.MultimediaInCart;
import Model.User;
import persistenceDAO.CartDAO;
import persistenceDAO.FilmDAO;
import persistenceDAO.TVSerieDAO;
import persistenceDAO.UserDAO;

public class GoToCart extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idstr = (String) req.getParameter("multimedia"); 
		User user = (User) req.getSession().getAttribute("user");
		if (idstr == null) {
			String action = (String) req.getParameter("fremove");
			String action2 = (String) req.getParameter("tremove");
			CartDAO cartdao = DatabaseManager.getInstance().getDaoFactory().getCartDao();
			
			if (action != null) {
				int id = Integer.parseInt(action);
				cartdao.delete(user.getEmail(), id, true);
			} else if (action2 != null) {
				int id = Integer.parseInt(action2);
				cartdao.delete(user.getEmail(), id, false);
			}
			LinkedList<MultimediaInCart> cart = (LinkedList<MultimediaInCart>) cartdao.findByPrimaryKey(user.getEmail()).getCart();
			req.setAttribute("cart", cart);
			RequestDispatcher dispacher = req.getRequestDispatcher("cart.jsp");
			dispacher.forward(req, resp);
		}
		
		int id = Integer.parseInt(idstr);
		/*System.out.println(req.getSession().getAttribute("isfilm") + "String");
		String isFilmstr = (String) req.getSession().getAttribute("isfilm");
		boolean isFilm = true;
		if (isFilmstr.equals("false"))
			isFilm = false;*/
		boolean isFilm = (boolean) req.getSession().getAttribute("isFilm");
		System.out.println(isFilm);
			
		Multimedia multimedia = null;
		if (isFilm) {
			FilmDAO filmdao = DatabaseManager.getInstance().getDaoFactory().getFilmDAO();
			multimedia = filmdao.findByPrimaryKey(id);
		} else {
			TVSerieDAO tvseriedao = DatabaseManager.getInstance().getDaoFactory().getTVSerieDAO();
			multimedia = tvseriedao.findByPrimaryKey(id);
		}
		CartDAO cartDAO = DatabaseManager.getInstance().getDaoFactory().getCartDao();
		Cart cart = new Cart(user);
		cart.addToCart(new MultimediaInCart(multimedia, 1));
		cartDAO.save(cart);
		
		req.setAttribute("YourMultimedia", multimedia);
		req.getRequestDispatcher("MultimediaPage.jsp").forward(req, resp);
	}
}
