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

import crm_project_02.entity.Project;
import crm_project_02.entity.Role;
import crm_project_02.payload.response.BaseResponse;
import crm_project_02.service.ProjectService;
import crm_project_02.service.RoleService;

@WebServlet(name = "apiProjectController", urlPatterns = {"/api/projects","/api/project/delete","/api/project/edit"})
public class ApiProjectController extends HttpServlet{
	private ProjectService projectService = new ProjectService();
	//Khởi tạo thư viện dạng Gson để sử dụng
	private Gson gson = new Gson();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		//Kiểm tra người dùng đang gọi đường dẫn nào 
		if(path.equals("/api/projects")) {
			List<Project> listProject = projectService.getAllProject();
			
			BaseResponse response = new BaseResponse();
			response.setStatusCode(200);
			response.setMessage("");
			response.setData(listProject);
			
			// Chuyển list hoặc mảng về Json
			String dataJson = gson.toJson(response);
			
			// Trả dữ liệu dạng Gson
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			out.print(dataJson);	
			out.flush();
		}else if(path.equals("/api/project/delete")) {
			
			int id = Integer.parseInt(req.getParameter("id"));
			boolean isSuccess = projectService.deleteById(id);
			
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
		}else if(path.equals("/api/project/edit")) {
			int id = Integer.parseInt(req.getParameter("id"));
			String name  = req.getParameter("project-name");
			String startDate = req.getParameter("startDate");
			String endDate = req.getParameter("endDate");
			
			boolean isSuccess = projectService.editById(id, name, startDate, endDate);
			
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
