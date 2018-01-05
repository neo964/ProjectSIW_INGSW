package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionProfile extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = (String) req.getParameter("actionprofile");
		
		if (action == null) {
			System.out.println("null");
			return;
		}
		
		if (action.equals("subscribe")) {
			RequestDispatcher dispacher = req.getRequestDispatcher("subscribe.jsp");
			dispacher.forward(req, resp);
			
		} else if (action.equals("cart")) {
			RequestDispatcher dispacher = req.getRequestDispatcher("cart.jsp");
			dispacher.forward(req, resp);
			
		} else if (action.equals("settings")) {
			RequestDispatcher dispacher = req.getRequestDispatcher("settings.jsp");
			dispacher.forward(req, resp);
			
		} else if (action.equals("favourite")) {
			RequestDispatcher dispacher = req.getRequestDispatcher("chooseFavourite.jsp");
			dispacher.forward(req, resp);
		}
		
		
	}

}
