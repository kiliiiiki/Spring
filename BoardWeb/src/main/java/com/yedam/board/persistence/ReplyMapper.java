package com.yedam.board.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yedam.board.domain.BoardVO;
import com.yedam.board.domain.Criteria;
import com.yedam.board.domain.ReplyVO;

public interface ReplyMapper {
	
	public int insert(ReplyVO vo);
	public ReplyVO read(Long rno); // 댓글번호 조회
	public int delete(Long rno); // 댓글삭제
	public int update(ReplyVO vo); // 댓글수정
	public List<ReplyVO> getListWithPaging(@Param("bno") Long bno, @Param("cri") Criteria cri); // 글번호에 대한 댓글번호(페이지, 건수)
	public int getCountByBno(Long bno); // 전체 건수
	 
	
	
}
