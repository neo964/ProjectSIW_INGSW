package controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
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
		String advisor = (String) req.getParameter("friend");
		System.out.println(advisor);
		boolean isFilm = (boolean) req.getSession().getAttribute("isFilm");
		if (advisor == null) {
			List<Friendship> myfriends = DatabaseManager.getInstance().getDaoFactory().getFriendsip().findAllMyFriend(user.getEmail());
			List<User> frienduser = new LinkedList<>();
			for (Friendship friendship : myfriends) {
				frienduser.add(DatabaseManager.getInstance().getDaoFactory().getUserDAO().findByPrimaryKey(friendship.getUser2()));
			}
			req.setAttribute("friends", frienduser);
			req.setAttribute("id", idstr);
			req.getRequestDispatcher("advice.jsp").forward(req, resp);
		}
		else {
			String realid = (String) req.getSession().getAttribute("afteradvice");
			int id = Integer.parseInt(realid);
			User usertmp = DatabaseManager.getInstance().getDaoFactory().getUserDAO().findByPrimaryKey(advisor);
			Multimedia multimedia = null;
			if (isFilm)
				multimedia = DatabaseManager.getInstance().getDaoFactory().getFilmDAO().findByPrimaryKey(id);
			else
				multimedia = DatabaseManager.getInstance().getDaoFactory().getTVSerieDAO().findByPrimaryKey(id);
				
			System.out.println("DA QUi");
			System.out.println(advisor);
			System.out.println(user);
			System.out.println(usertmp);
			System.out.println(multimedia.getId());
			Advice advice = new Advice (user, usertmp, multimedia);
			DatabaseManager.getInstance().getDaoFactory().getAdviceDAO().save(advice);
			req.setAttribute("YourMultimedia", multimedia);
			req.getRequestDispatcher("MultimediaPage.jsp").forward(req, resp);
		}
		
	}
}
