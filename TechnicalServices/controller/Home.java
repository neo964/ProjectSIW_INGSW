package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.*;
import javax.servlet.http.*;

public class Home extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		RequestDispatcher dispacher = req.getRequestDispatcher("index.jsp");
		dispacher.forward(req, resp);
	}
}
