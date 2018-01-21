package controller;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseManager;
import Model.Favourite;
import Model.Film;
import Model.Multimedia;
import Model.TVSerie;
import Model.User;
import persistenceDAO.FavouriteDAO;
import persistenceDAO.FilmDAO;
import persistenceDAO.TVSerieDAO;

public class GiveMeFavourite extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = -1;
		id = (int) req.getSession().getAttribute("rankid");
		User user = (User) req.getSession().getAttribute("user");
		if (id < 0) {
			String isFilm = (String) req.getParameter("film");
			FavouriteDAO favouritedao = DatabaseManager.getInstance().getDaoFactory().getFavouriteDAO ();
			LinkedList <Favourite> favourites = (LinkedList<Favourite>) favouritedao.findFavouriteUser(user.getEmail());
			int i = 0;

			if (isFilm.equals("film")) {
				for (Favourite favouritetmp : favourites) {
						if(favouritetmp.getMultimedia() instanceof Film) {
						req.setAttribute("film" + i, favouritetmp.getMultimedia());
						i++;
					}
				}
			} else {
				for (Favourite favouritetmp : favourites) {
					if(favouritetmp.getMultimedia() instanceof TVSerie) {
					req.setAttribute("film" + i, favouritetmp.getMultimedia());
					i++;
						}
					}
				}
				req.setAttribute("size", i);
				req.getRequestDispatcher("research.jsp").forward(req, resp);
				return;
			}
		boolean isFilm = (boolean) req.getSession().getAttribute("isFilm");
		Multimedia multimedia = null;
		if (isFilm) {
			FilmDAO filmdao = DatabaseManager.getInstance().getDaoFactory().getFilmDAO();
			multimedia = filmdao.findByPrimaryKey(id);
		} else {
			TVSerieDAO tvseriedao = DatabaseManager.getInstance().getDaoFactory().getTVSerieDAO();
			multimedia = tvseriedao.findByPrimaryKey(id);
		}
		System.out.println(isFilm);
		System.out.println(id);
		try {
			FavouriteDAO favouritedao = DatabaseManager.getInstance().getDaoFactory().getFavouriteDAO();
			Favourite favourite = new Favourite(user.getEmail(), multimedia, isFilm);
			favouritedao.save(favourite);
			
			  String text = "Aggiunto ai preferiti!";
			    resp.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
			    resp.setCharacterEncoding("UTF-8"); // You want world domination, huh?
			    resp.getWriter().write(text);
			
		} catch (Exception e) {
			String text = "E' stato gia' aggiunto ai preferiti precedentemente";
		    resp.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
		    resp.setCharacterEncoding("UTF-8"); // You want world domination, huh?
		    resp.getWriter().write(text);
		}
		}
}
