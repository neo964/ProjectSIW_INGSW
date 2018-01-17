package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.User;

public class SendUs extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User) req.getSession().getAttribute("user");
		String subject = (String) req.getParameter("subject");
		String object = (String) req.getParameter("object");
		
		SendEmail send = new SendEmail();
		send.sendEmail("pandapmpa@gmail.com", subject, "from " + user.getEmail() + ": " + object);
		req.getRequestDispatcher("aboutUs.jsp").forward(req, resp);
	}
}
