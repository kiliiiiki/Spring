package com.example.demo;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.emp.mapper.EmpMapper;

@SpringBootTest
class OracleApplicationTests {

	@Autowired EmpMapper empMapper;
	
	@Test
	void empList() {
		List<Map<String, Object>> list= empMapper.getEmpList();
		System.out.println(list.get(0));
	}
	
	@Test
	void contextLoads() {
	}

}
