package crm_project_02.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_project_02.entity.Project;
import crm_project_02.entity.Status;
import crm_project_02.entity.Task;
import crm_project_02.entity.Users;
import crm_project_02.service.TaskService;

@WebServlet(name = "taskController", urlPatterns = {"/task-add", "/task-table"})
public class TaskController extends HttpServlet{
	private TaskService taskService = new TaskService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
			case "/task-add": 
				List<Users> listUser = new ArrayList<Users>();
				listUser = taskService.getAllUsers();
				List<Project> listProject = new ArrayList<Project>();
				listProject = taskService.getAllProject();
				req.setAttribute("listUser", listUser);
				req.setAttribute("listProject", listProject);
				req.getRequestDispatcher("task-add.jsp").forward(req, resp);
				break;
			
			case "/task-table": 
				//List<Task> listTask = taskService.get
				req.getRequestDispatcher("task.jsp").forward(req, resp);
				break;
			
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int idProject = Integer.parseInt(req.getParameter("project"));
		String name = req.getParameter("name");
		int idUser = Integer.parseInt(req.getParameter("user"));
		String startDate = req.getParameter("startDate");
		String endDate = req.getParameter("endDate");
		
		boolean isSuccess = taskService.insertTask(idProject, name, idUser, startDate, endDate);
		List<Users> listUser = new ArrayList<Users>();
		listUser = taskService.getAllUsers();
		List<Project> listProject = new ArrayList<Project>();
		listProject = taskService.getAllProject();
		
		req.setAttribute("listUser", listUser);	
		req.setAttribute("listProject", listProject);
		req.setAttribute("isSuccess", isSuccess);
		req.getRequestDispatcher("task-add.jsp").forward(req, resp);
	}
}
