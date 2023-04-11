package com.yedam.board.service;

import java.util.List;

import com.yedam.board.domain.Criteria;
import com.yedam.board.domain.ReplyPageDTO;
import com.yedam.board.domain.ReplyVO;

public interface ReplyService {
	public int register(ReplyVO vo);
	public ReplyVO get(Long rno);
	public int modify(ReplyVO vo);
	public int remove(Long rno);
	public List<ReplyVO> getList(Long bno, Criteria cri);
	public ReplyPageDTO getListPage(Criteria cri, Long bno);
}
