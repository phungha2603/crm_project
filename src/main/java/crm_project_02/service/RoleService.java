package crm_project_02.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crm_project_02.entity.Role;
import crm_project_02.repository.RoleRepository;

/**
 *Service: chứa những class chuyên đi xử lý logic cho controller
 *Cách đặt tên: giống với controller: Ví dụ: RoleController => RoleService 
 *
 *Cách đặt tên hàm: đặt tên hàm ứng với chức năng sẽ làm trên giao diện/ bên controller
 *
 */
public class RoleService {
	
	private RoleRepository roleRepository = new RoleRepository();
	
	public boolean addRole(String name, String desc) {
		int count = roleRepository.insert(name, desc);
		return count>0;
	}
	public boolean deleteRole(int id) {
		int count = roleRepository.deleteById(id);
		return count >0;
	}
	public List<Role> findAllRole(){
		return roleRepository.findAll();
	}
	public Role findById(int id) {
		return roleRepository.findById(id);
	}
	public boolean editRoleById(int id, String name, String desc) {
		int count = roleRepository.editById(id, name, desc);
		return count>0;
	}
}
