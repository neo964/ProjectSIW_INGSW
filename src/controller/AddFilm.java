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
import Model.Film;
import Model.Trailer;
import persistenceDAO.ActorInMultimediaDAO;
import persistenceDAO.FilmDAO;
import Model.FilmPoster;
import Model.Actor;

public class AddFilm extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("AddFilm.jsp");
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
		String videoOnDemand = req.getParameter("VideoOnDemand");
		String Plot = req.getParameter("Plot");
		String pricestr = req.getParameter("Price");
		String image = req.getParameter("Image");
		String[] actorstr = req.getParameterValues("Actors");
		int id = Integer.parseInt(idstr);
		int year = Integer.parseInt(yearstr);
		double price = Double.parseDouble(pricestr);
		List<String> actors= new LinkedList ();
		for (String string : actorstr) {
			actors.add(string);
		}
		try {
			Film film = new Film(new FilmPoster(title, category, director, year, actors, Plot, image), new Trailer (trailer), price, videoOnDemand);
			FilmDAO filmdao = DatabaseManager.getInstance().getDaoFactory().getFilmDAO();
			filmdao.save(film);
			ActorInMultimediaDAO actorinmultimedia = DatabaseManager.getInstance().getDaoFactory().getActorInMultimedia ();
			for (String string : actors) {
				actorinmultimedia.save(new Actor (string, id, true));	
			}
			
			req.setAttribute("Film", film);
			RequestDispatcher dispatcher = req.getRequestDispatcher("AddFilm.jsp");
			dispatcher.forward(req, resp);
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	
		
	}
	
}