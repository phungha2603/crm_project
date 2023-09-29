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
import crm_project_02.entity.Users;
import crm_project_02.payload.response.BaseResponse;
import crm_project_02.service.RoleService;
import crm_project_02.service.UserService;

@WebServlet(name = "apiRoleController", urlPatterns = {"/api/roles", "/api/role/delete", "/api/role/edit"})
public class ApiRoleController extends HttpServlet{
	private RoleService roleService = new RoleService();
	//Khởi tạo thư viện dạng Gson để sử dụng
	private Gson gson = new Gson();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		//Kiểm tra người dùng đang gọi đường dẫn nào 
		if(path.equals("/api/roles")) {
			List<Role> listRole = roleService.findAllRole();
			
			BaseResponse response = new BaseResponse();
			response.setStatusCode(200);
			response.setMessage("");
			response.setData(listRole);
			
			// Chuyển list hoặc mảng về Json
			String dataJson = gson.toJson(response);
			
			// Trả dữ liệu dạng Gson
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			out.print(dataJson);	
			out.flush();
		}else if(path.equals("/api/role/delete")) {
			
			int id = Integer.parseInt(req.getParameter("id"));
			boolean isSuccess = roleService.deleteRole(id);
			
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
		}else if(path.equals("/api/role/edit")) {
			int id = Integer.parseInt(req.getParameter("id"));
			String roleName  = req.getParameter("role-name");
			String desc = req.getParameter("desc");
			
			boolean isSuccess = roleService.editRoleById(id, roleName, desc);
			
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
