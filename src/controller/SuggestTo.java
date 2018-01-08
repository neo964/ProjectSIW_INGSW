package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseManager;
import Model.Advice;
import Model.Friendship;
import Model.Multimedia;
import Model.User;

public class SuggestTo extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User) req.getSession().getAttribute("user");
		String idstr = (String) req.getParameter("multimedia");
		String advisor = (String) req.getParameter("advisor");
		boolean isFilm = (boolean) req.getSession().getAttribute("isFilm");
		if (advisor == null) {
			List<Friendship> myfriends = DatabaseManager.getInstance().getDaoFactory().getFriendsip().findAllMyFriend(user.getEmail());
			req.setAttribute("friends", myfriends);
			req.setAttribute("id", idstr);
			req.getRequestDispatcher("adivsorpage.jsp").forward(req, resp);
		}
		else {
			String realid = (String) req.getSession().getAttribute("idtmp");
			int id = Integer.parseInt(realid);
			User usertmp = DatabaseManager.getInstance().getDaoFactory().getUserDAO().findByPrimaryKey(advisor);
			Multimedia multimedia = DatabaseManager.getInstance().getDaoFactory().getFilmDAO().findByPrimaryKey(id);
			Advice advice = new Advice (user, usertmp, multimedia);
			DatabaseManager.getInstance().getDaoFactory().getAdviceDAO().save(advice);
			req.setAttribute("YourMultimedia", multimedia);
			req.getRequestDispatcher("MultimediaPage.jsp").forward(req, resp);
		}
		
	}
}
