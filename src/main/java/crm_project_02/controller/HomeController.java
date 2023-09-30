package crm_project_02.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "homeController", urlPatterns = {"/404","/blank"})
public class HomeController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
			case "/404": {
				req.getRequestDispatcher("404.jsp").forward(req, resp);
				break;
			}
			case "/blank":{
				req.getRequestDispatcher("blank.jsp").forward(req, resp);
				break;
			}
			
		}
	}
}
