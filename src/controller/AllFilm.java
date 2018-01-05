package controller;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseManager;
import Model.Film;
import persistenceDAO.FilmDAO;

public class AllFilm extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		FilmDAO filmDao = DatabaseManager.getInstance().getDaoFactory().getFilmDAO();

		LinkedList <Film> films = (LinkedList<Film>) filmDao.findAll();
		req.setAttribute("size", films.size());
		int i = 0;
		for (Film film2 : films) {
			System.out.println(film2.getPoster().getImage());
			req.setAttribute("film" + i, film2);
			i++;
		}
		req.getRequestDispatcher("research.jsp").forward(req, resp);
	}

}
