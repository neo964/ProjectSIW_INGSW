package controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseManager;
import Model.Actor;
import Model.Film;
import Model.FilmPoster;
import Model.TVSerie;
import Model.TVSeriePoster;
import Model.Trailer;
import persistenceDAO.ActorInMultimediaDAO;
import persistenceDAO.FilmDAO;
import persistenceDAO.TVSerieDAO;

public class AddTVSerie extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("AddTVSerie.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idstr = req.getParameter("ID");
		String title = req.getParameter("Title");
		String category = req.getParameter("Category");
		String yearstr = req.getParameter("Year");
		String director = req.getParameter("Director"); 
		String trailer = req.getParameter("Trailer");
		String Plot = req.getParameter("Plot");
		String pricestr = req.getParameter("Price");
		String image = req.getParameter("Image");
		String[] actorstr = req.getParameterValues("Actors");
		boolean completed;
		if(req.getParameter("completed").equals("true"))
				completed = true;
		else
			completed = false;
		String seasonstr = req.getParameter("seasons");
		int id = 0;
		if (idstr != null)
			id = Integer.parseInt(idstr);
		int year = Integer.parseInt(yearstr);
		double price = Double.parseDouble(pricestr);
		int seasons = Integer.parseInt(seasonstr);
		List<String> actors= new LinkedList<String> ();
		for (String string : actorstr) {
			actors.add(string);
		}
		try {
			TVSerieDAO tvseriedao = DatabaseManager.getInstance().getDaoFactory().getTVSerieDAO();
			if (idstr == null) {
				TVSerie tvSerie = new TVSerie(-1, new TVSeriePoster(title, category, director, year, actors, Plot, image, completed, seasons), new Trailer(trailer), price);
				tvseriedao.save(tvSerie);
			}
			else {
				TVSerie tvSerie = new TVSerie(id, new TVSeriePoster(title, category, director, year, actors, Plot, image, completed, seasons), new Trailer(trailer), price);
				tvseriedao.update(tvSerie);
			}
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("home");
			dispatcher.forward(req, resp);
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
