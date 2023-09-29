package crm_project_02.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crm_project_02.config.MysqlConfig;
import crm_project_02.entity.Role;
import crm_project_02.entity.Users;

public class UserRepository {
	public int insert(String firstName, String lastName, String fullName, String userName, String email, String pwd, String phone, int id_role) {
		int count =0;
		String query = "INSERT INTO Users (firstName, lastName, fullName, userName, email, pwd, phone, id_role) \r\n"
				+ "VALUES (?,?,?,?,?,?,?,?)";
		Connection connection = MysqlConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			statement.setString(3, fullName);
			statement.setString(4, userName);
			statement.setString(5, email);
			statement.setString(6, pwd);
			statement.setString(7, phone);
			statement.setInt(8, id_role);
			
			count = statement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Lỗi thêm User " + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Lỗi đóng kết nối " + e.getLocalizedMessage());
			}
		}
		 return count;
		
	}
	
	public List<Users> getAllUsers(){
		List<Users> list = new ArrayList<Users>();
		String query = "select u.id, u.firstName, u.lastName, u.userName, u.fullName, r.name\r\n"
				+ "from Users u \r\n"
				+ "join Role r ON u.id_role = r.id";
		Connection connection = MysqlConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Users users = new Users();
				users.setId(resultSet.getInt("id"));
				users.setFirstName(resultSet.getString("firstName"));
				users.setLastName(resultSet.getString("lastName"));
				users.setUserName(resultSet.getString("userName"));
				users.setFullName(resultSet.getString("fullName"));
				
				Role role = new Role();
				role.setName(resultSet.getString("name"));
				users.setRole(role);
				
				list.add(users);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Lỗi get all users " + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Lỗi đóng kết nối " + e.getLocalizedMessage());
			}
		}	
		
		return list;	
		
	}
	
	public int deleteById(int id) {
		
		String query = "DELETE FROM Users u WHERE u.id = ?";
		int count =0;
		Connection connection = MysqlConfig.getConnection();
		try {
			PreparedStatement statement  = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			count = statement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Lỗi delete user by id " + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Lỗi đóng kết nối " + e.getLocalizedMessage());
			}
		}
		
		return count;
	}
	public int editById(int id, String firstname, String lastname, String fullname, String username, String email, String pwd, String phone, int id_role) {
		
		String query = "UPDATE Users u\r\n"
				+ "SET u.firstName = ?, u.lastName = ?, u.fullName = ?, u.userName = ?, u.email = ?, u.pwd = ?, u.phone = ?, u.id_role = ?\r\n"
				+ "WHERE u.id = ?";
		int count = 0;
		Connection connection = MysqlConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, firstname);
			statement.setString(2, lastname);
			statement.setString(3, fullname);
			statement.setString(4, username);
			statement.setString(5, email);
			statement.setString(6, pwd);
			statement.setString(7, phone);
			statement.setInt(8, id_role);
			statement.setInt(9, id);
			
			count = statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Lỗi edit user by id " + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Lỗi đóng kết nối " + e.getLocalizedMessage());
			}
		}
		return count;
	}
	
	public Users findById(int id) {

		String query = "SELECT * FROM Users u\r\n"
				+ "JOIN `Role` r \r\n"
				+ "ON u.id_role = r.id WHERE u.id=?";
		Users users = new Users();
		Connection connection = MysqlConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				users.setId(resultSet.getInt("id"));
				users.setEmail(resultSet.getString("email"));
				users.setPassword(resultSet.getString("pwd"));
				users.setFirstName(resultSet.getString("firstName"));
				users.setLastName(resultSet.getString("lastName"));
				users.setFullName(resultSet.getString("fullName"));
				users.setUserName(resultSet.getString("userName"));
				users.setPhone(resultSet.getString("phone"));
				
				Role role = new Role();
				role.setName(resultSet.getString("name"));
				users.setRole(role);
			}
		} catch (SQLException e) {
			System.out.println("Lỗi find user by id " + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Lỗi đóng kết nối " + e.getLocalizedMessage());
			}
		}
		return users;
		
	}
	
	public Users login(String email, String password) {
		String query = "SELECT * FROM Users u WHERE u.email = ? AND u.pwd = ?";
		Users users = new Users();
		Connection connection = MysqlConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				users.setEmail(resultSet.getString("email"));
				users.setPassword(resultSet.getString("password"));
			}
			
		} catch (SQLException e) {
			System.out.println("Lỗi login " + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Lỗi đóng kết nối " + e.getLocalizedMessage());
			}
		}
		return users;		
	}
		
	
}
