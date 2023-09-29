
package crm_project_02.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crm_project_02.config.MysqlConfig;
import crm_project_02.entity.Role;

/**
 * RoleRepository: Chứa toàn bộ câu truy vấn liên quan tới bảng Role
 *
 */
public class RoleRepository {
	
	public int insert(String name, String desc) {
		
		String query = "INSERT INTO `Role` (name, description)\r\n"
				+ "VALUES (?,?)";
		Connection connection = MysqlConfig.getConnection();
		int count = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setString(2, desc);
			count = statement.executeUpdate();	
			
		} catch (SQLException e) {
			System.out.println("Lỗi thêm role " + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Lỗi đóng kết nối " + e.getLocalizedMessage());
			}
		}
		return count;
	}
//	Nguyên tắc đặt tên đối với câu SELECT: tên hàm sẽ bắt đầu bằng chữ find, nếu có điều
//	kiện where : by
	
	public List<Role> findAll() {
		
		List<Role> listRole = new ArrayList<Role>();
		String query = "SELECT * FROM `Role`";		
		Connection connection = MysqlConfig.getConnection();
		//Truyền câu query vào Connection
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			//Duyệt qua từng dòng dữ liệu
			while(resultSet.next()) {
				Role role = new Role();
				role.setId(resultSet.getInt("id"));
				role.setName(resultSet.getString("name"));
				role.setDescription(resultSet.getString("description"));
				
				listRole.add(role);
			}
		} catch (Exception e) {
			System.out.println("Lỗi find all Role " + e.getLocalizedMessage());
		}
		return listRole;	
	}
	
	public int deleteById(int id) {
			
			String query = "DELETE FROM `Role` r WHERE r.id = ?";
			int count =0;
			Connection connection = MysqlConfig.getConnection();
			try {
				PreparedStatement statement  = connection.prepareStatement(query);
				statement.setInt(1, id);
				
				count = statement.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("Lỗi delete role by id " + e.getLocalizedMessage());
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối " + e.getLocalizedMessage());
				}
			}
			
			return count;
	}
	
	public Role findById(int id) {
		
		String query = "SELECT * FROM `Role` r WHERE r.id = ?";
		Role role = new Role();
		Connection connection = MysqlConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				role.setId(resultSet.getInt("id"));
				role.setName(resultSet.getString("name"));
				role.setDescription(resultSet.getString("description"));
			}
		} catch (SQLException e) {
			System.out.println("Lỗi find role by id " +e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Lỗi đóng kết nối " + e.getLocalizedMessage());
			}
		}
		return role;
		
	}
	public int editById(int id, String name, String description) {
		
		String query = "UPDATE `Role` r \r\n"
				+ "SET r.name = ?, r.description = ? WHERE r.id = ?";
		int count = 0;
		Connection connection = MysqlConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setString(2, description);
			statement.setInt(3, id);
			count = statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Lỗi edit role by id " + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Lỗi đóng kết nối " + e.getLocalizedMessage());
			}
		}
		
		return count;
	}
	
	
	
	
	
	
}
	
	
	
	

