package com.yedam.board.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yedam.board.domain.BoardVO;

public interface BoardMapper {
	
	//@Select("select * from tbl_board where bno > 0")
	public List<BoardVO> getList();

	public void insertSelectKey(BoardVO board);

	public BoardVO read(Long bno);
	
	public int delete(Long bno);

	public int update(BoardVO board);
	 
	
	
}
