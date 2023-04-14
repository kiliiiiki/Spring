package com.example.demo.emp;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
