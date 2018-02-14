package com.koitt.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.koitt.board.model.Board;
import com.koitt.board.model.BoardException;
import com.koitt.board.service.BoardService;

@Controller
public class BoardWebController {

	@Autowired
	private BoardService service;
	
	// HTTP Method GET 방식으로 /board-list.do를 클라이언트가 요청하면 아래 메소드 호출
	@RequestMapping("/board-list.do")
	public String list(Model model) {
		List<Board> list = null;
		
		try {
			// service를 이용하여 글 목록 가져오기
			list = service.list();
			
		} catch (BoardException e) {
			// 예외가 발생하면 error키의 값을 이용하여 JSP에 표시
			model.addAttribute("error", "server");
		}
		
		// list키에 list 객체를 추가
		model.addAttribute("list", list);
		
		// ViewResolver에 의해서 /WEB-INF/views/board-list.jsp 페이지로 포워딩하게 됨
		return "board-list";
	}
	
	// 글 상세화면
	@RequestMapping(value="/board-detail.do", method=RequestMethod.GET)
	public String detail(Model model, @RequestParam(value="no", required=true) String no) {
		Board board = null;
		
		try {
			board = service.detail(no);
			
		} catch (BoardException e) {
			model.addAttribute("error", "server");
		}
		
		model.addAttribute("board", board);
		
		return "board-detail";
	}
	
	// 글 작성화면
	@RequestMapping(value="/board-add.do", method=RequestMethod.GET)
	public String add() {
		return "board-add";
	}
	
	// 글 추가 후, 글 목록 화면으로 이동
	@RequestMapping(value="/board-add.do", method=RequestMethod.POST)
	public String add(Model model, Board board) {
		try {
			service.add(board);
			
		} catch (BoardException e) {
			model.addAttribute("error", "server");
		}
		
		// redirect: 뒤에 입력한 주소로 이동
		return "redirect:board-list.do";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
