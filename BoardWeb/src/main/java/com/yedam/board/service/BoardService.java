package com.yedam.board.service;

import java.util.List;

import com.yedam.board.domain.BoardVO;

public interface BoardService {
	
	//CRUD등록
	public void register(BoardVO board);
	public BoardVO get(Long bno);
	public boolean modify(BoardVO board);
	public boolean remove(Long bno);
	public List<BoardVO> getList();
}
