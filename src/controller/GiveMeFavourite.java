package controller;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseManager;
import Model.Favourite;
import persistenceDAO.FavouriteDAO;

public class GiveMeFavourite extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		FavouriteDAO favouritedao = DatabaseManager.getInstance().getDaoFactory().getFavouriteDAO ();
		String user = (String) req.getSession().getAttribute("user");
		LinkedList <Favourite> favourites = (LinkedList<Favourite>) favouritedao.findFavouriteUser(user);
		req.getSession().setAttribute("size", favourites.size());
		int i = 0;
		for (Favourite favouritetmp : favourites) {
			System.out.println(favouritetmp.getMultimedia().getPoster().getTitle());
			req.setAttribute("film" + i, favouritetmp);
			i++;
		}
		req.getRequestDispatcher("research.jsp").forward(req, resp);
	}
}
