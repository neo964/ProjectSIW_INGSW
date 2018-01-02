package controller;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseManager;
import Model.TVSerie;
import persistenceDAO.TVSerieDAO;

public class AllTVSerie extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TVSerieDAO tvseriedao = DatabaseManager.getInstance().getDaoFactory().getTVSerieDAO();

		LinkedList <TVSerie> tvserie = (LinkedList<TVSerie>) tvseriedao.findAll();
		req.getSession().setAttribute("size", tvserie.size());
		int i = 0;
		for (TVSerie tvserietmp : tvserie) {
			System.out.println(tvserietmp.getPoster().getImage());
			req.setAttribute("film" + i, tvserietmp);
			i++;
		}
		req.getRequestDispatcher("research.jsp").forward(req, resp);
	}
}
