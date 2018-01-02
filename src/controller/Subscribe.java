package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseManager;
import Model.User;
import persistenceDAO.UserDAO;

public class Subscribe extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = (String) req.getSession().getAttribute("user");
		UserDAO userdao = DatabaseManager.getInstance().getDaoFactory().getUserDAO();
		User user = userdao.findByPrimaryKey(username);
		
		req.setAttribute("utente", user);
		RequestDispatcher dispacher = req.getRequestDispatcher("subscribe.jsp");
		dispacher.forward(req, resp);
	}
}
