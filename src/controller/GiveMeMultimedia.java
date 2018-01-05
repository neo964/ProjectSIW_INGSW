package controller;

import java.awt.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseManager;
import Model.Film;
import Model.TVSerie;
import persistenceDAO.FilmDAO;
import persistenceDAO.TVSerieDAO;

public class GiveMeMultimedia extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		FilmDAO filmDao = DatabaseManager.getInstance().getDaoFactory().getFilmDAO();
		TVSerieDAO tvseriedao = DatabaseManager.getInstance().getDaoFactory().getTVSerieDAO();
		
		if (req.getParameter("keyword") != null) {
			LinkedList <Film> films = (LinkedList<Film>) filmDao.findByName(req.getParameter("keyword"));
			LinkedList <TVSerie> tvseries = (LinkedList<TVSerie>) tvseriedao.findByName(req.getParameter("keyword"));
			req.setAttribute("size", films.size() + tvseries.size());
			int i = 0;
			for (Film film2 : films) {
				System.out.println(film2.getPoster().getImage());
				req.setAttribute("film" + i, film2);
				i++;
			}
			req.getSession().setAttribute("isFilm", true);
			req.getRequestDispatcher("research.jsp").forward(req, resp);
		}
		else if (req.getParameter("giveCategory") != null){
			String category = req.getParameter("giveCategory");
				LinkedList <Film> films = (LinkedList<Film>) filmDao.findByCategory(category);
				req.setAttribute("size", films.size());
				int i = 0;
				for (Film film2 : films) {
					System.out.println(film2.getPoster().getImage());
					req.setAttribute("film" + i, film2);
					i++;
				}
				req.getSession().setAttribute("isFilm", true);
				req.getRequestDispatcher("research.jsp").forward(req, resp);
		} else { //else { if (req.getParameter("giveNews") != null){
			String what = req.getParameter("giveNews");
			if (what == null || what.equals("news")) {
				Date date = new Date(System.currentTimeMillis());
				String datestr = date.toString();
				String yearstr = datestr.substring(datestr.length()-4, datestr.length());
				int year = Integer.parseInt(yearstr);
				LinkedList <Film> films = (LinkedList<Film>) filmDao.findByYear(year);
				req.setAttribute("size", films.size());
				int i = 0;
				for (Film film2 : films) {
					System.out.println(film2.getPoster().getImage());
					req.setAttribute("film" + i, film2);
					i++;
				} 
				req.getSession().setAttribute("isFilm", true);
				req.getRequestDispatcher("research.jsp").forward(req, resp);
			}else if (what.equals("film")) {
				System.out.println("film");
				req.getSession().setAttribute("isFilm", true);
				req.getRequestDispatcher("categorypage.jsp").forward(req, resp);
			} else if (what.equals("tvserie")) {
				req.getSession().setAttribute("isFilm", false);
				req.getRequestDispatcher("tvcategorypage.jsp").forward(req, resp);
			} else if (what.equals("friend")) {
				req.getRequestDispatcher("friend.jsp").forward(req, resp);
			}
			
		}
		System.out.println("Here");
		
	}
}
