package com.company.mvc.emp.mapper;

import java.util.List;
import java.util.Map;

import com.company.mvc.emp.EmpVO;

public interface EmpMapper {
	
	List<DeptVO> getDeptList();
	int deptDelete(DeptVO vo);
	List<EmpVO> getEmpListVO(EmpVO vo);
	List<Map<String, Object>>getEmpList();
	Map<String, Object>getEmp(int value);
	
}
