package crm_project_02.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crm_project_02.config.MysqlConfig;
import crm_project_02.entity.Project;

public class ProjectRepository {
	public int insert(String name, String startDate, String endDate) {
		String query = "INSERT INTO Project (name, startDate, endDate)\r\n"
				+ "VALUES (?, ?,?)";
		Connection connection = MysqlConfig.getConnection();
		int count = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setString(2, startDate);
			statement.setString(3, endDate);
			count = statement.executeUpdate();			
			
		} catch (SQLException e) {
			System.out.println("Thêm dự án thất bại " + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Lỗi đóng kết nối " + e.getLocalizedMessage());
			}
		}
		return count;
	}
	public List<Project> findAll(){
		String query = "SELECT id, name, DATE_FORMAT(startDate, '%d/%m/%Y') as startDate, \r\n"
				+ "DATE_FORMAT(endDate, '%d/%m/%Y') as endDate FROM Project";
		Connection connection = MysqlConfig.getConnection();
		List<Project> listProjects = new ArrayList<Project>();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet =  statement.executeQuery();
			while(resultSet.next()) {
				Project project = new Project();
				project.setId(resultSet.getInt("id"));
				project.setName(resultSet.getString("name"));
				project.setStartDate(resultSet.getString("startDate"));
				project.setEndDate(resultSet.getString("endDate"));
				
				listProjects.add(project);
			}
			
		} catch (SQLException e) {
			System.out.println("Lỗi find all Project " +e.getLocalizedMessage());
		}	finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Lỗi đóng kết nối " + e.getLocalizedMessage());
			}
		}
		return listProjects;
	}
	
	public int deleteById(int id) {
		String query = "DELETE FROM Project p WHERE p.id = ?";
		int count = 0;
		Connection connection = MysqlConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			count = statement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Lỗi delete project by id "+ e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Lỗi đóng kết nối " + e.getLocalizedMessage());
			}
		}
		
		return count;
	}
	
	public Project findById(int id) {
		
		String query = "SELECT * FROM Project WHERE id = ?";
		Connection connection = MysqlConfig.getConnection();
		Project project = new Project();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				project.setId(resultSet.getInt("id"));
				project.setName(resultSet.getString("name"));
				project.setStartDate(resultSet.getString("startDate"));
				project.setEndDate(resultSet.getString("endDate"));
			}
		} catch (SQLException e) {
			System.out.println("Lỗi find project by id " + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Lỗi đóng kết nối " + e.getLocalizedMessage());
			}
		}
		
		return project;
	}
	
	public int editById(int id, String name, String startDate, String endDate) {
		
		String query = "UPDATE Project p \r\n"
				+ "SET p.name = ?, p.startDate = ?, p.endDate = ? WHERE p.id = ?";
		int count = 0;
		Connection connection = MysqlConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setString(2, startDate);
			statement.setString(3, endDate);
			statement.setInt(4, id);
			count = statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Lỗi edit project by id " + e.getLocalizedMessage());
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
