package com.yedam.board.control;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;

import com.yedam.board.domain.BoardVO;
import com.yedam.board.service.ServiceTest;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@WebAppConfiguration //컨트롤 테스트를 할 경우 필요
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class BoardControlTest {
	
	//처리된 결과 페이지, url 패턴 처리 : ApplicationContext 필요
	@Setter(onMethod_ = @Autowired)
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc; //url패턴의 호출 -> control -> .jsp
	
	@Before //테스트클래스 실행될 때 마다 먼저 호출
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build(); //인스턴스 호출
	}
	
	@Test
	public void removeTest() {
		RequestBuilder rb = MockMvcRequestBuilders.post("/board/remove")//
				.param("bno", "1"); //param : 파라미터 값으로 문자열만 가능
		try {
			String vn = mockMvc.perform(rb)//
			.andReturn()//
			.getModelAndView()//
			.getViewName();
			
			log.info(vn);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void modifyTest() {
		RequestBuilder rb = MockMvcRequestBuilders.post("/board/modify")//
				.param("title", "새로운글입니다.")//
				.param("content", "여긴 글내용입니다.")//
				.param("writer", "user7")//
				.param("bno", "1"); //param : 파라미터 값으로 문자열만 가능
		try {
			String vn = mockMvc.perform(rb)//
			.andReturn()//
			.getModelAndView()//
			.getViewName();
			
			log.info(vn);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void registerTest() {
		RequestBuilder rb = MockMvcRequestBuilders.post("/board/register")//
				.param("title", "새로운글입니다.")//
				.param("content", "여긴 글내용입니다.")//
				.param("writer", "user7");
		try {
			String vn = mockMvc.perform(rb)//
			.andReturn()//
			.getModelAndView()//
			.getViewName();
			
			log.info(vn);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void listTest() {
		// /board/list
		try {
			ModelMap map = mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))//
			.andReturn() //처리결과
			.getModelAndView() //model에 저장된 정보를 처리
			.getModelMap();
			
			List<BoardVO> list = (List<BoardVO>) map.get("list");
			for(BoardVO vo : list)
				log.info(vo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
