package controller;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseManager;
import Model.Favourite;
import Model.Multimedia;
import persistenceDAO.FavouriteDAO;
import persistenceDAO.FilmDAO;
import persistenceDAO.TVSerieDAO;

public class GiveMeFavourite extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idstr = (String) req.getParameter("favourite"); 
		if (idstr == null) {
			FavouriteDAO favouritedao = DatabaseManager.getInstance().getDaoFactory().getFavouriteDAO ();
			String user = (String) req.getSession().getAttribute("user");
			LinkedList <Favourite> favourites = (LinkedList<Favourite>) favouritedao.findFavouriteUser(user);
			req.getSession().setAttribute("size", favourites.size());
			int i = 0;
			for (Favourite favouritetmp : favourites) {
				System.out.println(favouritetmp.getMultimedia().getPoster().getTitle());
				req.setAttribute("film" + i, favouritetmp.getMultimedia());
				i++;
			}
			req.getRequestDispatcher("research.jsp").forward(req, resp);
			return;
		}
		int id = Integer.parseInt(idstr);
		boolean isFilm = (boolean) req.getSession().getAttribute("isFilm");
		Multimedia multimedia = null;
		if (isFilm) {
			FilmDAO filmdao = DatabaseManager.getInstance().getDaoFactory().getFilmDAO();
			multimedia = filmdao.findByPrimaryKey(id);
		} else {
			TVSerieDAO tvseriedao = DatabaseManager.getInstance().getDaoFactory().getTVSerieDAO();
			multimedia = tvseriedao.findByPrimaryKey(id);
		}
		String username = (String)req.getSession().getAttribute("user");
		System.out.println(isFilm);
		System.out.println(id);
		System.out.println(username);
		FavouriteDAO favouritedao = DatabaseManager.getInstance().getDaoFactory().getFavouriteDAO();
		Favourite favourite = new Favourite(username, multimedia, isFilm);
		favouritedao.save(favourite);
		
		req.setAttribute("YourMultimedia", multimedia);
		req.getRequestDispatcher("MultimediaPage.jsp").forward(req, resp);
	}
}
