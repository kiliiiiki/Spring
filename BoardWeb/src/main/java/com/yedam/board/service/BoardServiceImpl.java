package com.yedam.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.board.domain.BoardVO;
import com.yedam.board.persistence.BoardMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

//스프링에서 관리하는 객체: @Control, @Component, @Service

@Service
@Log4j
public class BoardServiceImpl implements BoardService{
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper boardMapper;
	
	@Override
	public void register(BoardVO board) {
		log.info("서비스 구현 ... 등록");
		boardMapper.insertSelectKey(board);	
	}

	@Override
	public BoardVO get(Long bno) {
		log.info("서비스 구현 ... 조회");
		return boardMapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		log.info("서비스 구현 ... 수정");
		int cnt = boardMapper.update(board);
		return cnt == 1 ? true:false;
	}

	@Override
	public boolean remove(Long bno) {
		log.info("서비스 구현 ... 삭제");
		int cnt = boardMapper.delete(bno);
		return cnt == 1 ? true:false;
	}

	@Override
	public List<BoardVO> getList() {
		log.info("서비스 구현 ... 목록");
		return boardMapper.getList();
	}

}
