package com.company.mvc.emp;

import java.util.List;

import com.company.mvc.emp.EmpVO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DeptVO {
	String departmentId;
	//@JsonProperty("dname")
	String departmentName;
	String managerId;
	String locationId;
	@JsonIgnore
	List<String> departmentIds;
	@JsonIgnore
	List<EmpVO> emps;
}
