package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseManager;
import Model.Film;
import persistenceDAO.FilmDAO;

public class GiveMeMultimedia extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		FilmDAO filmDao = DatabaseManager.getInstance().getDaoFactory().getFilmDAO();
		PrintWriter out = resp.getWriter();
		out.println("<table border=\"1\">");
		out.println("<tr>");
		out.println("<th>ID</th>");
		out.println("<th>Title</th>");
		out.println("<th>Category</th>");
		out.println("<th>Year</th>");
		out.println("<th>Director</th>");
		out.println("<th>Plot</th>");
		out.println("<th>Price</th>");
		out.println("<th>Trailer</th>");
		out.println("<th>Streaming</th>");
		out.println("</tr>");
		for (Film film : filmDao.findAll()){
			out.println("<tr>");
			out.println("<td>");
			out.println(film.getId());
			out.println("</td>");
			out.println("<td>");
			out.println(film.getPoster().getTitle());
			out.println("</td>");
			out.println("<td>");
			out.println(film.getPoster().getCategory());
			out.println("</td>");
			out.println("<td>");
			out.println(film.getPoster().getYear());
			out.println("</td>");
			out.println("<td>");
			out.println(film.getPoster().getDirector());
			out.println("</td>");;
			out.println("<td>");
			out.println(film.getPoster().getPlot());
			out.println("</td>");;
			out.println("<td>");
			out.println(film.getPrice());
			out.println("</td>");;
			out.println("<td>");
			out.println(film.getTrailer());
			out.println("</td>");;
			out.println("<td>");
			out.println(film.getVideoOnDemand());
			out.println("</td>");
			out.println("</tr>");
		}
		out.println("</table>");
	}
}
