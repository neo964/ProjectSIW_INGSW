package controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Database.DatabaseManager;
import Model.Favourite;
import Model.Friendship;
import Model.Multimedia;
import Model.Ranking;
import Model.User;
import persistenceDAO.FriendshipDAO;
import persistenceDAO.UserDAO;

public class Friends extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();
		String text = "";
		try {
			StringBuilder sb = new StringBuilder();
			String s;
			while ((s = req.getReader().readLine()) != null) {
				sb.append(s);
			}
			System.out.println("sb: " + sb.toString());
			FriendResponse friendresponse = gson.fromJson(sb.toString(), FriendResponse.class);
			System.out.println("rank: " + friendresponse.action);
			System.out.println(friendresponse.user);
			
			String action = friendresponse.action;
			User user = (User) req.getSession().getAttribute("user");
			System.out.println(action);
			
			LinkedList<Friendship> friends = new LinkedList<>();
			LinkedList<User> userfriend = new LinkedList<>();
			boolean profilebool = true;
			
			FriendshipDAO friendshipdao = DatabaseManager.getInstance().getDaoFactory().getFriendsip();
			
			if (action.equals("remove")) {
				try {
					friendshipdao.delete(new Friendship(user.getEmail(), friendresponse.user, true));
					text = "Eliminato";
				} catch (Exception e) {
					text = "Impossibile eliminare";
				}
				profilebool = false;

			} else if (action.equals("decline")){
				try {
					friendshipdao.delete(new Friendship(user.getEmail(), friendresponse.user, false));
					text = "Richiesta rifiutata";
				} catch (Exception e) {
					text = "Impossibile rifiutare";
				}
				profilebool = true;
				
			} else if (action.equals("add")) {
				try {
					friendshipdao.save(new Friendship(user.getEmail(), friendresponse.user, false));
					text = "Richiesta inviata";
				} catch (Exception e) {
					text = "Richiesta gia inviata";
				} 
			} else if (action.equals("accept")) {
				try {
					friendshipdao.save(new Friendship(user.getEmail(), friendresponse.user, true));
					friendshipdao.update(new Friendship(friendresponse.user, user.getEmail(), true));
					text="Nuovo Amico Aggiunto";
				} catch (Exception e) {
					text="Impossibile aggiungere agli amici";
				}
			} /*else if (action.equals("keyword")){ // per la ricerca alla home
				userfriend = (LinkedList<User>) DatabaseManager.getInstance().getDaoFactory().getUserDAO().findByName(friendresponse.user);
				friends = new LinkedList<>();
				req.setAttribute("keyword", friendresponse.user);
				for (Iterator iterator = userfriend.iterator(); iterator.hasNext();) {
					User user2 = (User) iterator.next();
					friends.add(DatabaseManager.getInstance().getDaoFactory().getFriendsip().findByPrimaryKey(user.getEmail (), user2.getEmail()));
				}
				req.setAttribute("friends", friends);
				req.setAttribute("userfriends", userfriend);
				req.getRequestDispatcher("resultperson.jsp").forward(req, resp);
				return;
			}*/
			
		    resp.setContentType("text/plain"); 
		    resp.setCharacterEncoding("UTF-8");  
		    resp.getWriter().write(text);
			
		} catch (Exception e) {
			System.out.println("ERORRRER " + e.toString());
			text = "Errore inaspettato!"; 
		      resp.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect. 
		      resp.setCharacterEncoding("UTF-8"); // You want world domination, huh? 
		      resp.getWriter().write(text);
		}

	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User) req.getSession().getAttribute("user");
		LinkedList<Friendship> friends = new LinkedList<>();
		LinkedList<User> userfriend = new LinkedList<>();

		String profile = req.getParameter("profile");
		if (profile != null) {
			System.out.println("DOVrebbe proile");
			User usertmp = DatabaseManager.getInstance().getDaoFactory().getUserDAO().findByPrimaryKey(profile);
			LinkedList<Favourite> favusertmp = (LinkedList<Favourite>) DatabaseManager.getInstance().getDaoFactory().getFavouriteDAO().findFavouriteUser(usertmp.getEmail());
			req.setAttribute("usertmp", usertmp);
			req.setAttribute("fav", favusertmp);
			req.getRequestDispatcher("consultableProfile.jsp").forward(req, resp);
			return;
			
		} 
		
		System.out.println("Ricerca");
		friends.addAll(DatabaseManager.getInstance().getDaoFactory().getFriendsip().findAllMyFriend(user.getEmail()));
		for (Iterator iterator = friends.iterator(); iterator.hasNext();) {
			Friendship friendship = (Friendship) iterator.next();
			userfriend.add(DatabaseManager.getInstance().getDaoFactory().getUserDAO().findByPrimaryKey(friendship.getUser2()));
		}
		req.setAttribute("friends", friends);
		req.setAttribute("userfriends", userfriend);
		req.getRequestDispatcher("resultperson.jsp").forward(req, resp);
	}

}
