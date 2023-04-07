package com.yedam.board.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yedam.board.domain.BoardVO;
import com.yedam.board.domain.Criteria;
import com.yedam.board.domain.PageDTO;
import com.yedam.board.service.BoardService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board/*")
@Log4j
public class BoardController {
	
	@Setter(onMethod_ = @Autowired)
	private BoardService boardService;

	@RequestMapping("list")
	public void list(Model model, Criteria cri) {
		log.info("컨트롤 .. 목록조회");
		//List<BoardVO> list = boardService.getList();
		List<BoardVO> list = boardService.getList(cri);
		int total = boardService.getTotalCount(cri);
		model.addAttribute("list",list);
		model.addAttribute("pageMaker", new PageDTO(cri, total));
		// /WEB-INF/views/board/list.jsp
	}
	
	@RequestMapping(value="register", method=RequestMethod.POST)
	public String register(BoardVO board, RedirectAttributes model) {
		log.info("컨트롤 .. 등록");
		//등록 처리 후 목록 이동
		boardService.register(board);
		
		model.addFlashAttribute("result", board.getBno());
		
		return "redirect:/board/list"; //response.sendRedirect();
	}
	
	@GetMapping("register")
	public void register() {
		//등록화면
	}
	
	@GetMapping({"get", "modify"})
	public void get(Long bno, @ModelAttribute("cri") Criteria cri,  Model model) {
		log.info("컨트롤 .. 단건조회/수정이동");
		
		model.addAttribute("board", boardService.get(bno));
	}
	
	@PostMapping("modify")
	public String modify(BoardVO board, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("컨트롤 .. 수정");
		if(boardService.modify(board)) {
			rttr.addFlashAttribute("result", "Success");
			
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		
		return "redirect:/board/list"; //response.sendRedirect(); forwarding이 아닐 경우-> RedirectAttribute
									
	}
	
	@PostMapping("remove")
	public String remove(Long bno, Criteria cri, RedirectAttributes rttr) {
		log.info("컨트롤 .. 삭제");
		if(boardService.remove(bno)) {
			rttr.addFlashAttribute("result", "Success");
			
		}
		
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		return "redirect:/board/list"; //response.sendRedirect();
		
	}
	
}
