package crm_project_02.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_project_02.config.MysqlConfig;
import crm_project_02.entity.Project;
import crm_project_02.entity.Role;
import crm_project_02.repository.ProjectRepository;
import crm_project_02.service.ProjectService;

@WebServlet(name = "projectController", urlPatterns = {"/groupwork-table","/groupwork-add","/groupwork-edit"})
public class ProjectController extends HttpServlet {
	private ProjectService projectService = new ProjectService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if(path.equals("/groupwork-table")) {
			List<Project> listProject = new ArrayList<Project>();
			listProject = projectService.getAllProject();
			req.setAttribute("list", listProject);
			req.getRequestDispatcher("groupwork.jsp").forward(req, resp);
		}else if(path.equals("/groupwork-add")) {
			req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
		}else if(path.equals("/groupwork-edit")) {
			Project project = projectService.findById(Integer.parseInt(req.getParameter("projectId")));
			req.setAttribute("project", project);
			req.getRequestDispatcher("/groupwork-edit.jsp").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
			case "/groupwork-add": {
				String name = req.getParameter("project-name");
				String startDate = req.getParameter("startDate");
				String endDate = req.getParameter("endDate");
				boolean isSuccess = projectService.addProject(name, startDate, endDate); 
				req.setAttribute("isSuccess", isSuccess);
				
				req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
				break;
			}
			case "/groupwork-edit":{
				int id = Integer.parseInt(req.getParameter("id"));
				String name = req.getParameter("project-name");
				String startDate = req.getParameter("startDate");
				String endDate = req.getParameter("endDate");
				boolean isSuccess = projectService.editById(id, name, startDate, endDate); 
				req.setAttribute("isSuccess", isSuccess);
				
				req.getRequestDispatcher("groupwork-edit.jsp").forward(req, resp);
				break;
			}
			
		}
	}
	
}
