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

@WebServlet(name = "taskController", urlPatterns = {"/task-add", "/task-table", "/task-edit"})
public class TaskController extends HttpServlet{
	private TaskService taskService = new TaskService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
			case "/task-add": {
				List<Users> listUser = new ArrayList<Users>();
				listUser = taskService.getAllUsers();
				List<Project> listProject = new ArrayList<Project>();
				listProject = taskService.getAllProject();
				req.setAttribute("listUser", listUser);
				req.setAttribute("listProject", listProject);
				req.getRequestDispatcher("task-add.jsp").forward(req, resp);
				break;
			}
			case "/task-table": {
				List<Task> listTask = new ArrayList<Task>();
				listTask = taskService.getAllTask();
				req.setAttribute("listTask", listTask);
				req.getRequestDispatcher("task.jsp").forward(req, resp);
				break;
			}
			case "/task-edit": {
				List<Users> listUser = taskService.getAllUsers();
				req.setAttribute("listUser", listUser);
				List<Project> listProject = taskService.getAllProject();
				req.setAttribute("listProject", listProject);
				List<Status> listStatus = taskService.getAllStatus();
				req.setAttribute("listStatus", listStatus);
				Task task = taskService.findById(Integer.parseInt(req.getParameter("taskId")));
				req.setAttribute("task", task);
				req.getRequestDispatcher("task-edit.jsp").forward(req, resp);
				break;
			}
				
			
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = req.getServletPath();
		switch (path) {
			case "/task-add": {
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
				break;
			}
			case "/task-edit":{
				int id = Integer.parseInt(req.getParameter("id"));
				int idProject = Integer.parseInt(req.getParameter("project"));
				String name = req.getParameter("name");
				int idUser = Integer.parseInt(req.getParameter("user"));
				String startDate = req.getParameter("startDate");
				String endDate = req.getParameter("endDate");
				int idStatus = Integer.parseInt(req.getParameter("status"));
				
				boolean isSuccess = taskService.editById(id, name, startDate, endDate, idProject, idUser, idStatus);
				List<Users> listUser = new ArrayList<Users>();
				listUser = taskService.getAllUsers();
				List<Project> listProject = new ArrayList<Project>();
				listProject = taskService.getAllProject();
				List<Status> listStatus = new ArrayList<Status>();
				listStatus = taskService.getAllStatus();
				
				req.setAttribute("listStatus", listStatus);
				req.setAttribute("listUser", listUser);	
				req.setAttribute("listProject", listProject);
				req.setAttribute("isSuccess", isSuccess);
				req.getRequestDispatcher("task-edit.jsp").forward(req, resp);
				break;
			}
		
		}
		
		
	}
}
