package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseManager;
import Model.Multimedia;
import Model.Ranking;
import Model.User;

public class Vote extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String rank = (String) req.getParameter("rank");
		int value = Integer.parseInt(rank);
		int id = (int) req.getSession().getAttribute("rankid");
		boolean isFilm = (boolean) req.getSession().getAttribute("isFilm");
		User user = (User) req.getSession().getAttribute("user");
		Multimedia multimedia;
		if (isFilm) {
			multimedia = DatabaseManager.getInstance().getDaoFactory().getFilmDAO().findByPrimaryKey(id);
		}else {
			multimedia = DatabaseManager.getInstance().getDaoFactory().getTVSerieDAO().findByPrimaryKey(id);
		}
		DatabaseManager.getInstance().getDaoFactory().getRankingDAO().save(new Ranking(multimedia, user.getEmail(), value));
		req.setAttribute("YourMultimedia", multimedia);
		req.getRequestDispatcher("MultimediaPage.jsp").forward(req, resp);
	
	}
}
