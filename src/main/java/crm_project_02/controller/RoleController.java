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
import crm_project_02.entity.Role;
import crm_project_02.service.RoleService;

@WebServlet(name = "roleController", urlPatterns = {"/role-add","/role-table","/role-edit"})
public class RoleController extends HttpServlet {
	
	private RoleService roleService = new RoleService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Lấy ra đường dẫn mà người dùng đang gọi
		String path = req.getServletPath();
		//Kiểm tra đường dẫn người dùng đang gọi là đường dẫn nào để hiển thị giao diện tương ứng
		if(path.equals("/role-add")) {
			req.getRequestDispatcher("role-add.jsp").forward(req, resp);
			
		}else if(path.equals("/role-table")){
			List<Role> list = new ArrayList<Role>();
			try {
				list = roleService.findAllRole();
			} catch (Exception e) {
				System.out.println("Lỗi không xuất ra listRole " + e.getLocalizedMessage());
			} 
			req.setAttribute("listRole", list);
			req.getRequestDispatcher("role-table.jsp").forward(req, resp);
				
		}else if(path.equals("/role-edit")) {
			Role role = roleService.findById(Integer.parseInt(req.getParameter("roleId")));
			req.setAttribute("role", role);
			req.getRequestDispatcher("role-edit.jsp").forward(req, resp);
			
		}
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = req.getServletPath();
		switch (path) {
			case "/role-add": {
				String roleName  = req.getParameter("role-name");
				String desc = req.getParameter("desc");
				boolean isSuccess = roleService.addRole(roleName, desc);
				
				req.setAttribute("isSuccess", isSuccess);
				req.getRequestDispatcher("role-add.jsp").forward(req, resp);
				break;
			}
			case "/role-edit":{
				int id = Integer.parseInt(req.getParameter("id"));
				String roleName  = req.getParameter("role-name");
				String desc = req.getParameter("desc");
				boolean isSuccess = roleService.editRoleById(id, roleName, desc);
				req.setAttribute("isSuccess", isSuccess);
				req.getRequestDispatcher("role-edit.jsp").forward(req, resp);
				break;
			}
			
		}
		
		
		
		
				
		
		
		
//		//Nhận tham số nếu có
//		String roleName  = req.getParameter("role-name");
//		String desc = req.getParameter("desc");
//		//Chuẩn bị câu query
//		String query = "INSERT INTO `Role` (name, description)\r\n"
//				+ "VALUES (?,?)";
//		//Mở kết nối CSDL
//		Connection connection = MysqlConfig.getConnection();
//		boolean isSuccess = false;
//		try {
//			//Truyền câu query vào kết nối vừa được mở
//			PreparedStatement statement = connection.prepareStatement(query);
//			statement.setString(1, roleName);
//			statement.setString(2, desc);
//			//Thực thi câu query
//			int count = statement.executeUpdate();
//			if(count>0) {
//				isSuccess = true;
//				System.out.println("Thêm thành công");
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			try {
//				if(connection != null) {
//					connection.close();
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		req.setAttribute("isSuccess", isSuccess);
//		req.getRequestDispatcher("role-add.jsp").forward(req, resp);
	}
	
}
