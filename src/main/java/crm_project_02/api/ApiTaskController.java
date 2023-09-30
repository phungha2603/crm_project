package crm_project_02.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import crm_project_02.entity.Role;
import crm_project_02.entity.Task;
import crm_project_02.payload.response.BaseResponse;
import crm_project_02.service.ProjectService;
import crm_project_02.service.TaskService;

@WebServlet(name = "apiTaskController", urlPatterns = {"/api/tasks", "/api/task/delete","/api/task/edit"})
public class ApiTaskController extends HttpServlet {
	private TaskService taskService = new TaskService();
	//Khởi tạo thư viện dạng Gson để sử dụng
	private Gson gson = new Gson();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		//Kiểm tra người dùng đang gọi đường dẫn nào 
		if(path.equals("/api/tasks")) {
			List<Task> listTask = taskService.getAllTask();
			
			BaseResponse response = new BaseResponse();
			response.setStatusCode(200);
			response.setMessage("");
			response.setData(listTask);
			
			// Chuyển list hoặc mảng về Json
			String dataJson = gson.toJson(response);
			
			// Trả dữ liệu dạng Gson
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			out.print(dataJson);	
			out.flush();
		}else if(path.equals("/api/task/delete")) {
			
			int id = Integer.parseInt(req.getParameter("id"));
			boolean isSuccess = taskService.deleteById(id);
			
			BaseResponse response = new BaseResponse();
			response.setStatusCode(200);
			response.setMessage(isSuccess ? "Xóa thành công" : "Xóa thất bại");
			response.setData(isSuccess);
			
			String json = gson.toJson(response);
			
			// Trả dữ liệu dạng Gson
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			out.print(json);
			out.flush();	
		}else if(path.equals("/api/task/edit")) {
			int id = Integer.parseInt(req.getParameter("id"));
			int idProject = Integer.parseInt(req.getParameter("project"));
			String name = req.getParameter("name");
			int idUser = Integer.parseInt(req.getParameter("user"));
			String startDate = req.getParameter("startDate");
			String endDate = req.getParameter("endDate");
			int idStatus = Integer.parseInt(req.getParameter("status"));
			
			boolean isSuccess = taskService.editById(id, name, startDate, endDate, idProject, idUser, idStatus);
			
			BaseResponse response = new BaseResponse();
			response.setStatusCode(200);
			response.setMessage(isSuccess ? "Sửa thành công" : "Sửa thất bại");
			response.setData(isSuccess);
			
			String json = gson.toJson(response);
			
			// Trả dữ liệu dạng Gson
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			out.print(json);
			out.flush();
		}
	}
}
