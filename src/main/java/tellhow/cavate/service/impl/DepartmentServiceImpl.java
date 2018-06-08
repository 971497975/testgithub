package tellhow.cavate.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import tellhow.cavate.pojo.Department;
import tellhow.cavate.service.DepartmentService;
@Service
public class DepartmentServiceImpl extends BaseService implements DepartmentService{

	//1.根据部门id查找部门信息
	@Override
	public Department getDepartmentById(String BMBH) {
		Department department=departmentMapper.getDepartmentById(BMBH);
		return department;
	}

	//2.获取所有部门信息
	@Override
	public List<Department> getAllDepartments() {
		List<Department> list=departmentMapper.getAllDepartments();
		return list;
	}

	//3.获取当前用户所在部门的下级所有部门
	@Override
	public List<Department> getAllXJDepartments(String BMBH) {
		//获取所有部门
		List<Department> allDepartments=departmentMapper.getAllDepartments();
		List<Department> suborList = new ArrayList<Department>();
		List<String> uidList = new ArrayList<String>();
		allList.clear();
		allList2.clear();
		suborList = digui(BMBH,allDepartments);
		Department department=departmentMapper.getDepartmentById(BMBH);
		suborList.add(department);
		// 获取当前用户所在单位的下级所有单位的id
		for (int i = 0; i < suborList.size(); i++) {
			uidList.add(suborList.get(i).getBMBH());
		}
		//把集合的查询条件转为map
		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("uidList", uidList);
		param.put("BMBH", BMBH);
        List<Department> list=departmentMapper.getAllXJDepartments(param);
		
		return list;
	}

	
	//递归算法,获取当前用户所在党组织以及下级所有党组织
	public List<Department> digui(String pid,List<Department> alldepList){
		// 获取一级单位信息
 		if (pid != null) {
 			for(int j=0;j<alldepList.size();j++){
 				//查找哪些部门的上级部门是传进来的部门
 				if(alldepList.get(j).getSJBM()!=null){
 					if(alldepList.get(j).getSJBM().equals(pid)){
 	 					allList2.add(alldepList.get(j));
 	 					digui(alldepList.get(j).getBMBH(),alldepList);
 	 				}
 				}
 			}
 		}
 		return allList2;
	}
}
