package com.company.mvc;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.company.mvc.emp.EmpVO;
import com.company.mvc.emp.mapper.DeptVO;
import com.company.mvc.emp.mapper.EmpDAO;
import com.company.mvc.emp.mapper.EmpMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class EmpMapperClient {
	
	@Autowired EmpMapper empMapper;
	@Autowired EmpDAO dao;
	
	@Test
	public void getDeptList() {
		List<DeptVO> list = empMapper.getDeptList();
		for(DeptVO dept : list) {
			System.out.println("부서: "+dept.getDepartmentName());
			for(EmpVO emp : dept.getEmps()) {
				System.out.println("\t" + emp.getEmployeeId() + "_" + emp.getFirstName());
			}
		}
	}
	
	//@Test
	public void deptDelete() {
		DeptVO vo = new DeptVO();
		vo.setDepartmentIds(Arrays.asList("120", "130"));
		empMapper.deptDelete(vo);
	}
	
	//@Test
	public void VO사원전체조회() {
		EmpVO vo = new EmpVO();
		//vo.setDepartmentId("50");
		vo.setFirstName("vin");
		List<EmpVO> list = empMapper.getEmpListVO(vo);
		System.out.println(list.get(0));
	}
	
	//@Test
	public void DAO사원전체조회() {
		List<Map<String, Object>> list = dao.getEmpList();
		//System.out.println(list.get(0));
		System.out.println("First_name= "+list.get(0).get("firstName")); 
		//<FIRST_NAME> -> sqldeveloper 헤더 이름, Map은 camelcase 적용 안됨
		//camelcase사용하려면 xml에 alias 지정
	}
	
	//@Test
	public void 사원전체조회() {
		List<Map<String, Object>> list = empMapper.getEmpList();
		System.out.println(list.get(0));
	}
	
}
