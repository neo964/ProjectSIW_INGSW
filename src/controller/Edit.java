package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Edit extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idstr = (String) req.getParameter("edit");
		boolean isFilm = (boolean) req.getSession().getAttribute("isFilm");
		int id = Integer.parseInt(idstr);
		req.setAttribute("id", id);
		if (isFilm) {
			req.getRequestDispatcher("posterFilm.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("posterTVSerie.jsp").forward(req, resp);
		}
	}
}
