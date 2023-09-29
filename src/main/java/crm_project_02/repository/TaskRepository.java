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
		String query = "SELECT * FROM Job j\r\n"
				+ "JOIN Users u ON j.id_user = u.id \r\n"
				+ "JOIN Project p ON j.id_project = p.id;";
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
				users.setFullName(resultSet.getString("fullName"));
				task.setUsers(users);
				Project project = new Project();
				project.setName(resultSet.getString("name"));
				task.setProject(project);
				Status status = new Status();
				status.setName(resultSet.getString("name"));
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
	
	
	
	
	
	
	
}
