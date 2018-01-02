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
import Model.TVSerie;
import persistenceDAO.FilmDAO;
import persistenceDAO.TVSerieDAO;

public class GiveMeTVSerie extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TVSerieDAO tvseriedao = DatabaseManager.getInstance().getDaoFactory().getTVSerieDAO();
		
		System.out.println(req.getParameter("keyword"));
		System.out.println(req.getParameter("giveCategory"));
		System.out.println(req.getParameter("giveNews"));
		
		if (req.getParameter("keyword") != null) {
			LinkedList <TVSerie> tvseries = (LinkedList<TVSerie>) tvseriedao.findByName(req.getParameter("keyword"));
			req.getSession().setAttribute("size", tvseries.size());
			int i = 0;
			for (TVSerie tvserietmp : tvseries) {
				System.out.println(tvserietmp.getPoster().getImage());
				req.setAttribute("film" + i, tvserietmp);
				i++;
			}
			req.setAttribute("isFilm", false);
			req.getRequestDispatcher("research.jsp").forward(req, resp);
		}
		else if (req.getParameter("giveCategory") != null){
			String categorytv = req.getParameter("giveCategory");
			LinkedList <TVSerie> tvserie = (LinkedList<TVSerie>) tvseriedao.findByCategory(categorytv);
			req.getSession().setAttribute("size", tvserie.size());
			req.getSession().setAttribute("type", false);
			int i = 0;
			for (TVSerie tvserietmp : tvserie) {
				System.out.println(tvserietmp.getPoster().getImage());
				req.setAttribute("film" + i, tvserietmp);
				i++;
			}
			req.setAttribute("isFilm", false);
			System.out.println(req.getAttribute("isFilm"));
			req.getRequestDispatcher("research.jsp").forward(req, resp);
		} else { //else { if (req.getParameter("giveNews") != null){
			Date date = new Date(System.currentTimeMillis());
			String datestr = date.toString();
			String yearstr = datestr.substring(datestr.length()-4, datestr.length());
			int year = Integer.parseInt(yearstr);
			LinkedList <TVSerie> tvseries = (LinkedList<TVSerie>) tvseriedao.findByYear(year);
			req.getSession().setAttribute("size", tvseries.size());
			int i = 0;
			for (TVSerie tvserie : tvseries) {
				System.out.println(tvserie.getPoster().getImage());
				req.setAttribute("film" + i, tvserie);
				i++;
			}
			req.setAttribute("isFilm", false);
			req.getRequestDispatcher("research.jsp").forward(req, resp);
		}
		System.out.println("Here");
		/*for (Film film : films) {
			req.se
		}*/
	}
}
