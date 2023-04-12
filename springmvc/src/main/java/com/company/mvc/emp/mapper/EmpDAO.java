package com.company.mvc.emp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmpDAO {
	
	@Autowired
	SqlSession mybatis;
	
	public List<Map<String, Object>> getEmpList(){
		return mybatis.selectList("com.company.mvc.emp.mapper.EmpMapper.getEmpList");
	}
	
	public Map<String, Object> getEmp(int value){
		return mybatis.selectOne("com.company.mvc.emp.mapper.EmpMapper.getEmp");
	}
	
}
 