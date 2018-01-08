package controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseManager;
import Model.Favourite;
import Model.Friendship;
import Model.User;
import persistenceDAO.FriendshipDAO;
import persistenceDAO.UserDAO;

public class Friends extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String profile = (String) req.getParameter("profile");
		String remove = (String) req.getParameter("remove");
		String add = (String) req.getParameter("add");
		String keyword = (String) req.getParameter("keyword");
		String decline = (String) req.getParameter("decline");
		String accept = (String) req.getParameter("accept");
		User user = (User) req.getSession().getAttribute("user");

		LinkedList<Friendship> friends = new LinkedList<>();
		LinkedList<User> userfriend = new LinkedList<>();
		boolean profilebool = true;
		
		FriendshipDAO friendshipdao = DatabaseManager.getInstance().getDaoFactory().getFriendsip();
		
		if (remove != null) {
			friendshipdao.delete(new Friendship(user.getEmail(), remove, true));
			profilebool = false;

		} else if (decline != null){
			friendshipdao.delete(new Friendship(user.getEmail(), decline, false));
			profilebool = true;
			
		} else if (profile != null) {
			User usertmp = DatabaseManager.getInstance().getDaoFactory().getUserDAO().findByPrimaryKey(profile);
			LinkedList<Favourite> favusertmp = (LinkedList<Favourite>) DatabaseManager.getInstance().getDaoFactory().getFavouriteDAO().findFavouriteUser(usertmp.getEmail());
			req.setAttribute("usertmp", usertmp);
			req.setAttribute("fav", favusertmp);
			req.getRequestDispatcher("consultableProfile.jsp").forward(req, resp);
			return;
			
		} else if (add != null) {
			friendshipdao.save(new Friendship(user.getEmail(), add, false));
			profilebool = false;

		} else if (accept != null) {
			friendshipdao.update(new Friendship(user.getEmail(), accept, true));
			profilebool = true;
		} 
		
		if (keyword != null || !profilebool){ // per la ricerca alla home
			userfriend = (LinkedList<User>) DatabaseManager.getInstance().getDaoFactory().getUserDAO().findByName(keyword);
			for (Iterator iterator = userfriend.iterator(); iterator.hasNext();) {
				User user2 = (User) iterator.next();
				friends.add(DatabaseManager.getInstance().getDaoFactory().getFriendsip().findByPrimaryKey(user.getEmail(), user2.getEmail()));
			}
		}else { // per gli amici nel profilo
			friends.addAll(DatabaseManager.getInstance().getDaoFactory().getFriendsip().findAllMyFriend(user.getEmail()));
			for (Iterator iterator = friends.iterator(); iterator.hasNext();) {
				Friendship friendship = (Friendship) iterator.next();
				System.out.println(friendship.getUser1());
				System.out.println(friendship.getUser2());
				userfriend.add(DatabaseManager.getInstance().getDaoFactory().getUserDAO().findByPrimaryKey(friendship.getUser2()));
			}
		} 
		System.out.println("FINE");
		req.setAttribute("friends", friends);
		req.setAttribute("userfriends", userfriend);
		req.getRequestDispatcher("resultperson.jsp").forward(req, resp);
	}

}
