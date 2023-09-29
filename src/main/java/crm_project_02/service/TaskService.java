package crm_project_02.service;

import java.util.List;

import crm_project_02.entity.Project;
import crm_project_02.entity.Users;
import crm_project_02.repository.ProjectRepository;
import crm_project_02.repository.TaskRepository;
import crm_project_02.repository.UserRepository;

public class TaskService {
	private TaskRepository taskRepository = new TaskRepository();
	private UserRepository userRepository = new UserRepository();
	private ProjectRepository projectRepository = new ProjectRepository();
	public boolean insertTask(int idProject, String name, int idUser, String startDate, String endDate) {
		int count = taskRepository.insert(idProject, name, idUser, startDate, endDate);
		return count>0 ;
	}
	public List<Users> getAllUsers(){
		return userRepository.getAllUsers();
	}
	public List<Project> getAllProject(){
		return projectRepository.findAll();		
	}

	
}
