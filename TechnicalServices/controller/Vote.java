package controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Database.DatabaseManager;
import Model.Multimedia;
import Model.Ranking;
import Model.User;

public class Vote extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Ciao a tutti");

		Gson gson = new Gson();
		try {
			StringBuilder sb = new StringBuilder();
			String s;
			while ((s = req.getReader().readLine()) != null) {
				sb.append(s);
			}
			System.out.println("sb: " + sb.toString());
			RankResponse rank = gson.fromJson(sb.toString(), RankResponse.class);
			System.out.println("rank: " + rank.rank);
			
			String ss = rank.rank.replaceAll("Stars", "");
			ss = ss.trim();
			
			System.out.println("ss " + ss);
			int value = Integer.parseInt(ss);
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
			
			String text = "Inviato! Grazie per il tuo feedback! :)"; 
		      resp.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect. 
		      resp.setCharacterEncoding("UTF-8"); // You want world domination, huh? 
		      resp.getWriter().write(text);
			
		} catch (Exception e) {
			System.out.println("ERORRRER " + e.toString());
			String text = "Hai gia' votato!"; 
		      resp.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect. 
		      resp.setCharacterEncoding("UTF-8"); // You want world domination, huh? 
		      resp.getWriter().write(text);
		}
	}
}
