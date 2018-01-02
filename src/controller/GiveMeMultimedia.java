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
		
		System.out.println(req.getParameter("keyword"));
		System.out.println(req.getParameter("giveCategory"));
		System.out.println(req.getParameter("giveNews"));
		
		if (req.getParameter("keyword") != null) {
			LinkedList <Film> films = (LinkedList<Film>) filmDao.findByName(req.getParameter("keyword"));
			LinkedList <TVSerie> tvseries = (LinkedList<TVSerie>) tvseriedao.findByName(req.getParameter("keyword"));
			req.getSession().setAttribute("size", films.size() + tvseries.size());
			int i = 0;
			for (Film film2 : films) {
				System.out.println(film2.getPoster().getImage());
				req.setAttribute("film" + i, film2);
				i++;
			}
			req.getRequestDispatcher("research.jsp").forward(req, resp);
		}
		else if (req.getParameter("giveCategory") != null){
			String category = req.getParameter("giveCategory");
			if (category.charAt(0) == 'T') {
				String categorytv = category.substring(1, category.length());
				LinkedList <TVSerie> tvserie = (LinkedList<TVSerie>) tvseriedao.findByCategory(categorytv);
				req.getSession().setAttribute("size", tvserie.size());
				req.getSession().setAttribute("type", false);
				int i = 0;
				for (TVSerie tvserietmp : tvserie) {
					System.out.println(tvserietmp.getPoster().getImage());
					req.setAttribute("film" + i, tvserietmp);
					i++;
				}
				req.getRequestDispatcher("research.jsp").forward(req, resp);
			} else {
				LinkedList <Film> films = (LinkedList<Film>) filmDao.findByCategory(category);
				req.getSession().setAttribute("size", films.size());
				int i = 0;
				for (Film film2 : films) {
					System.out.println(film2.getPoster().getImage());
					req.setAttribute("film" + i, film2);
					i++;
				}
				req.getRequestDispatcher("research.jsp").forward(req, resp);
			}
		} else { //else { if (req.getParameter("giveNews") != null){
			String what = req.getParameter("giveNews");
			if (what == null || what.equals("news")) {
				Date date = new Date(System.currentTimeMillis());
				String datestr = date.toString();
				String yearstr = datestr.substring(datestr.length()-4, datestr.length());
				int year = Integer.parseInt(yearstr);
				LinkedList <Film> films = (LinkedList<Film>) filmDao.findByYear(year);
				req.getSession().setAttribute("size", films.size());
				int i = 0;
				for (Film film2 : films) {
					System.out.println(film2.getPoster().getImage());
					req.setAttribute("film" + i, film2);
					i++;
				} 
					req.getRequestDispatcher("research.jsp").forward(req, resp);
			}else if (what.equals("film")) {
				System.out.println("film");
				req.setAttribute("isFilm", true);
				req.getRequestDispatcher("categorypage.jsp").forward(req, resp);
			} else if (what.equals("tvserie")) {
				System.out.println("tv");
				req.setAttribute("isFilm", false);
				req.getRequestDispatcher("tvcategorypage.jsp").forward(req, resp);
			} else if (what.equals("friend")) {
				System.out.println("friend");
				req.getRequestDispatcher("friend.jsp").forward(req, resp);
			} else {/*
				Date date = new Date(System.currentTimeMillis());
				String datestr = date.toString();
				String yearstr = datestr.substring(datestr.length()-4, datestr.length());
				int year = Integer.parseInt(yearstr);
				LinkedList <Film> films = (LinkedList<Film>) filmDao.findByYear(year);
				req.getSession().setAttribute("size", films.size());
				int i = 0;
				for (Film film2 : films) {
					System.out.println(film2.getPoster().getImage());
					req.setAttribute("film" + i, film2);
					i++;
					req.getRequestDispatcher("research.jsp").forward(req, resp);*/
				}
			
		}
		System.out.println("Here");
		
	}
}
