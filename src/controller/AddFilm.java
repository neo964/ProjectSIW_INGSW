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
import persistenceDAO.FilmDAO;
import Model.FilmPoster;

public class AddFilm extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idstr = req.getParameter("id");
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
		int id = 0;
		if (idstr != null)
			id = Integer.parseInt(idstr);
		int year = Integer.parseInt(yearstr);
		double price = Double.parseDouble(pricestr);
		List<String> actors= new LinkedList<String> ();
		for (String string : actorstr) {
			actors.add(string);
		}
		try {
			FilmDAO filmdao = DatabaseManager.getInstance().getDaoFactory().getFilmDAO();
			
			if (idstr == null) {
				Film film = new Film(-1, new FilmPoster(title, category, director, year, actors, Plot, image), new Trailer (trailer), price, videoOnDemand);
				filmdao.save(film);
			} else {
				Film film = new Film(id, new FilmPoster(title, category, director, year, actors, Plot, image), new Trailer (trailer), price, videoOnDemand);
				filmdao.update(film);
			}
			RequestDispatcher dispatcher = req.getRequestDispatcher("/Project/home");
			dispatcher.forward(req, resp);
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}