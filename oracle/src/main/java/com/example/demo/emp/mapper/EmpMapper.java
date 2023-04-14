package com.example.demo.emp.mapper;

import java.util.List;
import java.util.Map;

import com.example.demo.emp.DeptVO;
import com.example.demo.emp.EmpVO;

public interface EmpMapper {
	
	List<DeptVO> getDeptList();
	int deptDelete(DeptVO vo);
	List<EmpVO> getEmpListVO(EmpVO vo);
	List<Map<String, Object>>getEmpList();
	Map<String, Object>getEmp(int value);
	
}
