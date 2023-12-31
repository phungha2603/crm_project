package crm_project_02.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_project_02.config.MysqlConfig;
import crm_project_02.entity.Users;
import crm_project_02.repository.UserRepository;
import crm_project_02.service.UserService;

/**
 * Package 
 * - Controller: là nơi chứa toàn bộ file liên quan tới khai báo đường dẫn và xử lý đường dẫn.
 * 
 *
 */

/**
 * Khác nhau giữa Cookie và Session:
 * - Cookie lưu trên client, chỉ lưu giá trị kiểu chuỗi
 * - Session lưu trên server
 * => Session mang tính bảo mật cao hơn
 */

/**
 * Tính năng login:
 * - B1: Lấy dữ liệu người dùng nhập vào ô email và password
 * - B2: Viết câu query kiểm tra email và password người dùng nhập vào có tồn tại trong database hay không
 * - B3: Dùng JDBC kết nối tới CSDL và truyền câu query ở bước 2 cho CSDL thực thi
 * - B4: Kiểm tra dữ liệu. Nếu có dữ liệu thì là đăng nhập thành công và ngược lại là đăng nhập thất bại.
 */

@WebServlet(name="loginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet{
//	UserRepository userRepository = new UserRepository();
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		int maxAge = 8*60*60;
//		Cookie cookie = new Cookie("hoten", URLEncoder.encode("Nguyễn Văn A","UTF-8"));
//		// setMaxAge : đơn vị giây . VD: 8 tiếng => 8*60*60
//		cookie.setMaxAge(maxAge);
//		
//		//yêu cầu client tạo cookie
//		resp.addCookie(cookie);
//		
//		req.getRequestDispatcher("login.html").forward(req, resp);
//	}
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String contextPath =  req.getContextPath();
//		//Bước 1
//		String email = req.getParameter("email");
//		String password = req.getParameter("password");
//		//Bước 2
//		// ?: Đại diện tham số sẽ được truyền vào khi sử dụng JDBC
//		String query ="SELECT * \r\n"
//					+ "FROM Users u \r\n"
//					+ "WHERE u.email = ? and u.pwd  = ?";
//		
//		//Mở kết nối tới CSDL
//		Connection conn = MysqlConfig.getConnection();
//		
//		try {
//			//Chuẩn bị câu query truyền xuống CSDL thông qua PrepareStatement
//			PreparedStatement statement = conn.prepareStatement(query);
//			//Gán giá trị cho tham số trong câu query có dấu (?)
//			statement.setString(1, email);
//			statement.setString(2, password);
//			
//			//Thực thi câu query và lấy kết quả
//			/**
//			 * executeUpdate: Nếu như câu truy vấn khác SELECT => INSERT, UPDATE, DELETE,..
//			 * executeQuery: Nếu như câu truy vấn là câu SELECT
//			 */
//			 ResultSet resultSet = statement.executeQuery();
//			 List<Users> listUser = new ArrayList<Users>();
//			 Users users = new Users();
//			 users = userRepository.findById(Integer.parseInt(req.getParameter("id")));
//			 //Khi nào resultSet mà còn qua dòng tiếp theo được thì làm 
//			 while(resultSet.next()) {
//				 //Duyệt qua từng dòng dữ liệu query được trong database
//				 Users users = new Users();
//				 //Lấy dữ liệu từ cột duyệt được và lưu vào thuộc tính của đối tượng users
////				 users.setId(resultSet.getInt("id"));
////				 users.setEmail(resultSet.getString("email"));
//				 users = userRepository.findById(Integer.parseInt(req.getParameter("id")));
//				 listUser.add(users);
//			 }
//			 if(listUser.size()>0) {
//				 //User tồn tại, đăng nhập thành công
//				 System.out.println("Đăng nhập thành công");
//				 resp.sendRedirect(contextPath+ "/index");
//			 }else {
//				 //User không tồn tại, đăng nhập thất bại
//				 System.out.println("Đăng nhập thất bại");
//				 req.getRequestDispatcher("Login.html").forward(req, resp);
//			 }
//			 
//		} catch (SQLException e) {
//			System.out.println("Lỗi thực thi truy vấn " + e.getLocalizedMessage());
//		}
//	}
	UserService userService = new UserService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String contextPath =  req.getContextPath();
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		List<Users> list = new ArrayList<Users>();
		Users users = userService.login(email, password);
		list.add(users);
		if(list.size()>0) {
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		}else {
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
		
	}
	
	
}
