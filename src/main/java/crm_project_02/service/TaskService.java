package crm_project_02.service;

import java.util.List;

import crm_project_02.entity.Project;
import crm_project_02.entity.Status;
import crm_project_02.entity.Task;
import crm_project_02.entity.Users;
import crm_project_02.repository.ProjectRepository;
import crm_project_02.repository.StatusRepository;
import crm_project_02.repository.TaskRepository;
import crm_project_02.repository.UserRepository;

public class TaskService {
	private TaskRepository taskRepository = new TaskRepository();
	private UserRepository userRepository = new UserRepository();
	private StatusRepository statusRepository = new StatusRepository();
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
	public List<Status> getAllStatus(){
		return statusRepository.findAll();
	}
	public List<Task> getAllTask(){
		return taskRepository.findAllTask();
	}
	public boolean deleteById(int id) {
		int count = taskRepository.deleteById(id);
		return count > 0;
	}
	public Task findById(int id) {
		return taskRepository.findById(id);
	}
	public boolean editById(int id, String name, String startDate, String endDate, int id_project, int id_user, int id_status) {
		int count = taskRepository.editById(id, name, startDate, endDate, id_project, id_user, id_status);
		return count>0;
	}
	
}
