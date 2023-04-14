package com.example.demo.emp;

import java.util.Date;

import lombok.Data;

@Data
public class EmpVO {
	
	String employeeId;
	String firstName;
	String lastName;
	String email;
	String phoneNumber;
	Date hireDate;
	String jobId;
	String salary;
	String commissionPct;
	String managerId;
	String departmentId;
	
}
