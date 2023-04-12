package com.company.mvc.emp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.mvc.emp.mapper.DeptVO;
import com.company.mvc.emp.mapper.EmpMapper;

@RestController //@ResponseBody 포함
public class EmpController {
	
	@Autowired
	EmpMapper empMapper;
	
	@GetMapping("/getDept")
	public List<DeptVO> getDept() {
		return empMapper.getDeptList();
	}
	
	@PostMapping("/deptInsert")
	public DeptVO deptInsert(@RequestBody DeptVO vo) {
		return vo;
	}
	
}
