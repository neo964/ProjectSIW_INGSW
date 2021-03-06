package controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseManager;
import Model.Friendship;
import Model.User;

public class SearchUser extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String keyword = (String) req.getParameter("keyword");
		User user = (User) req.getSession().getAttribute("user");
		
		LinkedList<User> userfriend = (LinkedList<User>) DatabaseManager.getInstance().getDaoFactory().getUserDAO().findByName(keyword);
		if (keyword.contains(" ")){
		int space = keyword.indexOf(" ");
		String keyword2 = keyword.substring(0, space);
		userfriend.addAll(DatabaseManager.getInstance().getDaoFactory().getUserDAO().findByName(keyword2));
		keyword2 = keyword.substring(space, keyword.length());
		userfriend.addAll(DatabaseManager.getInstance().getDaoFactory().getUserDAO().findByName(keyword2));
		}
		
		LinkedList<Friendship> friendship = new LinkedList<>();
		for (Iterator iterator = userfriend.iterator(); iterator.hasNext();) {
			User user2 = (User) iterator.next();
			if (user2.getEmail().equals(user.getEmail())) {
				iterator.remove();
			}
			else {
				friendship.add(DatabaseManager.getInstance().getDaoFactory().getFriendsip().findByPrimaryKey(user.getEmail (), user2.getEmail()));
			}
		}
		req.setAttribute("friends", friendship);
		req.setAttribute("userfriends", userfriend);
		req.getRequestDispatcher("resultperson.jsp").forward(req, resp);
	
		
	}
	
}
