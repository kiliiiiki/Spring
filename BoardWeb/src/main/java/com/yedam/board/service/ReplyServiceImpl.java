package com.yedam.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.board.domain.Criteria;
import com.yedam.board.domain.ReplyPageDTO;
import com.yedam.board.domain.ReplyVO;
import com.yedam.board.persistence.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	private ReplyMapper replyMapper;
	
	@Override
	public int register(ReplyVO vo) {
		
		return replyMapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long rno) {

		return replyMapper.read(rno);
	}

	@Override
	public int modify(ReplyVO vo) {
		
		return replyMapper.update(vo);
	}

	@Override
	public int remove(Long rno) {
		
		return replyMapper.delete(rno);
	}

	@Override
	public List<ReplyVO> getList(Long bno, Criteria cri) {
		
		return replyMapper.getListWithPaging(bno, cri);
	}

	@Override
	public ReplyPageDTO getListPage(Criteria cri, Long bno) {
		
		return new ReplyPageDTO(replyMapper.getCountByBno(bno)//
				, replyMapper.getListWithPaging(bno, cri));
	}

}
