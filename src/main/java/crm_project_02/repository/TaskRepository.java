package crm_project_02.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.User;

import crm_project_02.config.MysqlConfig;
import crm_project_02.entity.Project;
import crm_project_02.entity.Status;
import crm_project_02.entity.Task;
import crm_project_02.entity.Users;

public class TaskRepository {
	public int insert(int idProject, String name, int idUser, String startDate, String endDate) {
		String query = "INSERT INTO Job (id_project, name, id_user, startDate, endDate, id_status)\r\n"
				+ "	VALUES (?,?,?,?,?,1)";
		int count = 0;
		Connection connection = MysqlConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, idProject);
			statement.setString(2, name);
			statement.setInt(3, idUser);
			statement.setString(4, startDate);
			statement.setString(5, endDate);
			count = statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Lỗi insert Task " + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Lỗi đóng kết nối " + e.getLocalizedMessage());
			}
		}
		return count;
	}
	
	public List<Task> findAllTask(){
		String query = "SELECT j.id, j.name, p.name as project_name, u.fullname as user_name, DATE_FORMAT(j.startDate, '%d/%m/%Y') as startDate, DATE_FORMAT(j.endDate, '%d/%m/%Y') as endDate, s.name as status_name FROM Job AS j\r\n"
				+ "JOIN Users AS u ON j.id_user = u.id \r\n"
				+ "JOIN Project AS p ON j.id_project = p.id\r\n"
				+ "JOIN Status AS s ON j.id_status = s.id";
		List<Task> list = new ArrayList<Task>();
		Connection connection = MysqlConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Task task = new Task();
				task.setId(resultSet.getInt("id"));
				task.setName(resultSet.getString("name"));
				task.setStartDate(resultSet.getString("startDate"));
				task.setEndDate(resultSet.getString("endDate"));
				Users users = new Users();
				users.setFullName(resultSet.getString("user_name"));
				task.setUsers(users);
				Project project = new Project();
				project.setName(resultSet.getString("project_name"));
				task.setProject(project);
				Status status = new Status();
				status.setName(resultSet.getString("status_name"));
				task.setStatus(status);
				list.add(task);	
			}
		} catch (SQLException e) {
			System.out.println("Lỗi find all task " + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Lỗi đóng kết nối " + e.getLocalizedMessage());
			}
		}
		
		return list;
		
	}
	
	public int deleteById(int id) {
		
		String query = "DELETE FROM Job j WHERE j.id = ?";
		Connection connection = MysqlConfig.getConnection();
		int count = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			count = statement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Lỗi delete Task by id " + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Lỗi đóng kết nối " + e.getLocalizedMessage());
			}
		}
		
		return count;
		
	}
	
	
	public Task findById(int id) {
		
		String query = "SELECT j.id, j.name, p.name as project_name, u.fullname as user_name, DATE_FORMAT(j.startDate, '%d/%m/%Y') as startDate, DATE_FORMAT(j.endDate, '%d/%m/%Y') as endDate, s.name as status_name FROM Job AS j\r\n"
				+ "JOIN Users AS u ON j.id_user = u.id \r\n"
				+ "JOIN Project AS p ON j.id_project = p.id\r\n"
				+ "JOIN Status AS s ON j.id_status = s.id\r\n"
				+ "WHERE j.id = ?";
		Connection connection = MysqlConfig.getConnection();
		Task task = new Task();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				task.setId(resultSet.getInt("id"));
				task.setName(resultSet.getString("name"));
				task.setStartDate(resultSet.getString("startDate"));
				task.setEndDate(resultSet.getString("endDate"));
				Project project = new Project();
				project.setName(resultSet.getString("project_name"));
				task.setProject(project);
				Users users = new Users();
				users.setFullName(resultSet.getString("user_name"));
				task.setUsers(users);
				Status status = new Status();
				status.setName(resultSet.getString("status_name"));
				task.setStatus(status);
				
			}
		} catch (SQLException e) {
			System.out.println("Lỗi find Task by id " + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Lỗi đóng kết nối " + e.getLocalizedMessage());
			}
		}
			
		return task;
		
	}
	
	public int editById(int id, String name, String startDate, String endDate, int id_project, int id_user, int id_status) {
		
		String query = "UPDATE Job j \r\n"
				+ "SET j.name = ?, j.startDate = ?, j.endDate = ?, j.id_project = ?, j.id_user = ?, j.id_status = ?\r\n"
				+ "WHERE j.id = ?";
		int count = 0;
		Connection connection = MysqlConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setString(2, startDate);
			statement.setString(3, endDate);
			statement.setInt(4, id_project);
			statement.setInt(5, id_user);
			statement.setInt(6, id_status);
			statement.setInt(7, id);
			count = statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Lỗi edit Task by id " + e.getLocalizedMessage());
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
