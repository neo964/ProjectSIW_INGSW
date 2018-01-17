package controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseManager;
import Model.Film;
import Model.Multimedia;
import Model.PreviewMultimedia;
import persistenceDAO.FilmDAO;
import persistenceDAO.TVSerieDAO;

public class GiveMePage extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		boolean film = (boolean) req.getSession().getAttribute("isFilm");
		int id = 0;
		String str = req.getParameter("giveMultimedia");
		id = Integer.parseInt(str);
		Multimedia multimedia = null;
		if (film) {
			FilmDAO filmDao = DatabaseManager.getInstance().getDaoFactory().getFilmDAO();
			multimedia = filmDao.findByPrimaryKey(id);
		}
		else {
			TVSerieDAO tvseriedao = DatabaseManager.getInstance().getDaoFactory().getTVSerieDAO();
			multimedia = tvseriedao.findByPrimaryKey(id);
		}
		req.setAttribute("YourMultimedia", multimedia);
		req.getRequestDispatcher("MultimediaPage.jsp").forward(req, resp);
	}
}
