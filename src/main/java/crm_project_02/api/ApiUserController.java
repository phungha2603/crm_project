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

import crm_project_02.entity.Users;
import crm_project_02.payload.response.BaseResponse;
import crm_project_02.service.UserService;

/**
 * 
 * @author DELL
 *payload:
 * - response: chứa các class liên quan tới format json trả ra cho client
 * - request: chứa các class liên quan tới format json request (tham số) client truyền lên server
 *
 */



@WebServlet(name="apiUserController", urlPatterns = {"/api/users","/api/user/delete","/api/user/edit"})
public class ApiUserController extends HttpServlet{
	private UserService userService = new UserService();
	//Khởi tạo thư viện dạng Gson để sử dụng
	private Gson gson = new Gson();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = req.getServletPath();
		//Kiểm tra người dùng đang gọi đường dẫn nào 
		if(path.equals("/api/users")) {
			List<Users> listUser = userService.getAllUsers();
			
			BaseResponse response = new BaseResponse();
			response.setStatusCode(200);
			response.setMessage("");
			response.setData(listUser);
			
			// Chuyển list hoặc mảng về Json
			String dataJson = gson.toJson(response);
			
			// Trả dữ liệu dạng Gson
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			out.print(dataJson);	
			out.flush();
		}else if(path.equals("/api/user/delete")) {
			
			int id = Integer.parseInt(req.getParameter("id"));
			boolean isSuccess = userService.deleteUser(id);
			
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
		}else if(path.equals("/api/user/edit")) {
			int id = Integer.parseInt(req.getParameter("id"));
			String firstname = req.getParameter("firstname");
			String lastname = req.getParameter("lastname");
			String fullname = req.getParameter("fullname");
			String username = req.getParameter("username");
			String email = req.getParameter("email");
			String pwd = req.getParameter("password");
			String phone = req.getParameter("phone");
			int idRole = Integer.parseInt(req.getParameter("role"));
			
			boolean isSuccess = userService.editUserById(id, firstname, lastname, fullname, username, email, pwd, phone, idRole);
			
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
