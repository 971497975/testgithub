package tellhow.cavate.mapper;

import java.util.HashMap;
import java.util.List;

import tellhow.cavate.pojo.Department;

public interface DepartmentMapper {

	//1.根据部门id查找部门信息
	public Department getDepartmentById(String id);
	//2.获取所有部门信息
	public List<Department> getAllDepartments();
	//3.获取当前用户所在部门的下级所有部门
	public List<Department> getAllXJDepartments(HashMap<String, Object> param);
}
