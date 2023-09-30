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
import crm_project_02.entity.Users;
import crm_project_02.service.RoleService;
import crm_project_02.service.UserService;

@WebServlet(name="userController", urlPatterns = {"/user-table","/user-add","/user-edit"})
public class UserController extends HttpServlet {
	
	private UserService userService = new UserService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path =  req.getServletPath();
		List<Users> listUsers =  userService.getAllUsers();
		List<Role> list = new ArrayList<Role>();
		try {
			list = getAllRole();
		} catch (SQLException e) {
			System.out.println("Lỗi get all role " + e.getLocalizedMessage());
		}
		switch (path) {
			case "/user-table":

				req.setAttribute("listUser", listUsers);
				req.getRequestDispatcher("user-table.jsp").forward(req, resp);
				break;
			case "/user-add":
	
				req.setAttribute("listRole", list);
				req.getRequestDispatcher("user-add.jsp").forward(req, resp);
				break;
			case "/user-edit":
				
				Users users = userService.getUserById(Integer.parseInt(req.getParameter("userId")));
				req.setAttribute("users", users);
				req.setAttribute("listRole", list);
				req.getRequestDispatcher("user-edit.jsp").forward(req, resp);
				break;
				
			default:
			
		}	
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = req.getServletPath();
		switch (path) {
			case "/user-add": {
				String firstname = req.getParameter("firstname");
				String lastname = req.getParameter("lastname");
				String fullname = req.getParameter("fullname");
				String username = req.getParameter("username");
				String password = req.getParameter("password");
				String phone = req.getParameter("phone");
				String email = req.getParameter("email");
				int idRole = Integer.parseInt(req.getParameter("role"));
				
				boolean isSuccess = userService.insertUser(firstname, lastname, fullname, username, password, email, phone, idRole);
				
				List<Role> list = new ArrayList<Role>();
				list = userService.getAllRole();		
				
				req.setAttribute("listRole", list);
				req.setAttribute("isSuccess", isSuccess);
				req.getRequestDispatcher("user-add.jsp").forward(req, resp);
				break;
				}
			case "/user-edit":{
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
				List<Role> list = new ArrayList<Role>();
				list = userService.getAllRole();		
				
				req.setAttribute("listRole", list);
				req.setAttribute("isSuccess", isSuccess);
				req.getRequestDispatcher("user-edit.jsp").forward(req, resp);
				break;
			}
		
		
		}
		
		
		
		
		
		
		
		
//		//Lấy tham số từ thẻ form truyền qua khi người dùng click button Submit
//		String fullname = req.getParameter("fullname");
//		String password = req.getParameter("password");
//		String phone = req.getParameter("phone");
//		String email = req.getParameter("email");
//		int idRole = Integer.parseInt(req.getParameter("role"));
//		
//		//Tạo câu query và truyền giá trị vào câu query
//		String query = "INSERT INTO Users (fullName, email, pwd, phone, id_role) \r\n"
//				+ "VALUES (?,?,?,?,?)";
//		
//		//Mở kết nối CSDL
//		Connection connection = MysqlConfig.getConnection();
//		
//		//Truyền câu query vào database đã được kết nối
//		try {
//			PreparedStatement statement = connection.prepareStatement(query);
//			statement.setString(1, fullname);
//			statement.setString(2, email);
//			statement.setString(3, password);
//			statement.setString(4, phone);
//			statement.setInt(5, idRole);
//			
//			int count = statement.executeUpdate();
//			if(count>0) {
//				//insert dữ liệu thành công
//				System.out.println("Thêm thành công");
//			}else {
//				//insert dữ liệu thất bại
//				System.out.println("Thêm thất bại");
//			}
//			
//		} catch (Exception e) {
//			System.out.println("Lỗi thêm dữ liệu user " + e.getLocalizedMessage());
//		} finally {
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				System.out.println("Lỗi đóng kết nối " + e.getLocalizedMessage());
//			}
//		} 
//		
//		List<Role>  list = new ArrayList<Role>();
//		try {
//			list = getAllRole();
//		} catch (SQLException e) {
//			System.out.println("Lỗi get all role " + e.getLocalizedMessage());
//		}
//		req.setAttribute("listRole", list);
//
//		req.getRequestDispatcher("user-add.jsp").forward(req, resp);
	}
	
	private List<Role> getAllRole() throws SQLException {
		//Chuẩn bị câu query
		String query = "SELECT * FROM Role";
		//Mở kết nối CSDL
		Connection connection = MysqlConfig.getConnection();
		//Truyền câu query vào Connection
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		//Thực thi câu truy vấn và được một danh sách dữ liệu
		ResultSet resultSet = preparedStatement.executeQuery();
		List<Role> listRole = new ArrayList<Role>();
		//Duyệt qua từng dòng dữ liệu
		while(resultSet.next()) {
			Role role = new Role();
			role.setId(resultSet.getInt("id"));
			role.setName(resultSet.getString("name"));
			role.setDescription(resultSet.getString("description"));
			
			listRole.add(role);
		}
		return listRole;
	}
	
}
