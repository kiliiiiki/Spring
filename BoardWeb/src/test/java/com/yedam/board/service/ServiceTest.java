package com.yedam.board.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yedam.board.domain.BoardVO;
import com.yedam.board.domain.Criteria;
import com.yedam.board.persistence.MapperTest;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ServiceTest {
	
	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
	@Test
	public void listTest() {
		Criteria cri = new Criteria(1, 30); //5페이지 5건씩
		cri.setType("TCW");
		cri.setKeyword("user03");
		service.getList(cri).forEach(board -> log.info(board));
	}

	//@Test
	public void registerTest() {
		BoardVO board = new BoardVO();
		board.setTitle("새글등록");
		board.setContent("글 본문입니다");
		board.setWriter("user4");
		
		log.info("등록전: " + board);
		service.register(board);
		log.info("등록후: " + board);
	}
	
	//@Test
	public void getTest() {
		service.get(1L);
	}
	
}
