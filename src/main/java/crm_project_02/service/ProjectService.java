package crm_project_02.service;

import java.sql.SQLException;
import java.util.List;

import crm_project_02.entity.Project;
import crm_project_02.repository.ProjectRepository;

public class ProjectService {
	private ProjectRepository projectRepository = new ProjectRepository();
	public boolean addProject(String name, String startDate, String endDate) {
		int count = projectRepository.insert(name, startDate, endDate);
		return count>0;
	}
	public List<Project> getAllProject(){
		return projectRepository.findAll();
	}
	public boolean deleteById(int id) {
		int count = projectRepository.deleteById(id);
		return count>0;
	}
	public Project findById(int id) {
		return projectRepository.findById(id);
	}
	public boolean editById(int id, String name, String startDate, String endDate) {
		int count = projectRepository.editById(id, name, startDate, endDate);
		return count>0;
	}
	
	
}
