package crm_project_02.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crm_project_02.entity.Role;
import crm_project_02.entity.Users;
import crm_project_02.repository.RoleRepository;
import crm_project_02.repository.UserRepository;

public class UserService {
	
	UserRepository userRepository = new UserRepository();
	RoleRepository roleRepository = new RoleRepository();
	
	public boolean insertUser(String firstName, String lastName, String fullName, String userName, String email, String pwd, String phone, int id_role) {
		int count = userRepository.insert(firstName, lastName, fullName, userName, email, pwd, phone, id_role);
		
		return count>0;
	}

	public List<Role> getAllRole(){
		return roleRepository.findAll();
	}
	
	public List<Users> getAllUsers(){
		return userRepository.getAllUsers();
	}
	
	public boolean deleteUser(int id) {
		int count = userRepository.deleteById(id);
		return count >0;
	}
	
	public boolean editUserById(int id, String firstname, String lastname, String fullname, String username, String email, String pwd, String phone, int id_role) {
		int count = userRepository.editById(id, firstname, lastname, fullname, username, email, pwd, phone, id_role);
		return count>0;
	}
	public Users getUserById(int id) {
		return userRepository.findById(id);
	}
	public Users login(String email, String password) {
		return userRepository.login(email, password);
	}
}
