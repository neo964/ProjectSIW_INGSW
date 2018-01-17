package controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseManager;
import Model.Film;
import Model.FilmPoster;
import Model.Trailer;
import Model.TVSerie;
import Model.TVSeriePoster;;

public class Edit extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idstr = (String) req.getParameter("edit");
		boolean isFilm = (boolean) req.getSession().getAttribute("isFilm");
		String remove = (String) req.getParameter("remove");
		System.out.println(remove);
		
		
		if (remove == null) {
			int id = Integer.parseInt(idstr);
			req.setAttribute("id", id);
			if (isFilm) {
				req.getRequestDispatcher("posterFilm.jsp").forward(req, resp);
			} else {
				req.getRequestDispatcher("posterTVSerie.jsp").forward(req, resp);
			}
		} else {
			if (isFilm) {
				DatabaseManager.getInstance().getDaoFactory().getFilmDAO().delete(new Film(Integer.parseInt(remove), new FilmPoster(), new Trailer(), 0.0, ""));
			} else {
				DatabaseManager.getInstance().getDaoFactory().getTVSerieDAO().delete(new TVSerie(Integer.parseInt(remove), new TVSeriePoster(), new Trailer(), 0.0));
			}
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		}
	}
}
