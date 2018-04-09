package com.koitt.board.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.koitt.board.model.Board;
import com.koitt.board.model.BoardException;
import com.koitt.board.model.FileException;
import com.koitt.board.model.Users;
import com.koitt.board.model.UsersException;
import com.koitt.board.service.BoardService;
import com.koitt.board.service.FileService;
import com.koitt.board.service.UsersService;

@Controller
@RequestMapping("/board")	// 하위의 RequestMapping의 value 앞에 공통으로 /board 값 추가됨
public class BoardWebController {

	@Autowired
	private BoardService boardservice;
	
	@Autowired
	private FileService fileservice;
	
	@Autowired
	private UsersService usersService;
	
	// HTTP Method GET 방식으로 /board-list.do를 클라이언트가 요청하면 아래 메소드 호출
	@RequestMapping("/board-list.do")
	public String list(Model model) {
		List<Board> list = null;
		
		try {
			// service를 이용하여 글 목록 가져오기
			list = boardservice.list();
			
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
	public String detail(Model model, HttpServletRequest request, @RequestParam(value="no", required=true) String no) {
		Board board = null;
		String filename = null;
		String imgPath = null;
		String uploadPath = null;
		
		try {
			board = boardservice.detail(no);
			
			filename = board.getAttachment();
			
			if(filename != null && !filename.trim().isEmpty()) {
				// URL Encoding 된 파일명을 다시 Decoding 해서 포워딩 
				filename = URLDecoder.decode(filename, "UTF-8");
			}
			
			imgPath = fileservice.getImgPath(request, filename);
			
			uploadPath = fileservice.getUploadPath(request);
			
		} catch (BoardException e) {
			System.out.println(e.getMessage());
			model.addAttribute("error", "server");
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
			model.addAttribute("error", "encoding");
		}
		
		model.addAttribute("board", board);
		model.addAttribute("filename", filename);
		
		if(imgPath != null && !imgPath.trim().isEmpty()) {
			model.addAttribute("imgPath", imgPath);
		}
		
		model.addAttribute("uploadpath", uploadPath);
		
		return "board-detail";
	}
	
	// 글 작성화면
	@RequestMapping(value="/board-add.do", method=RequestMethod.GET)
	public String add(Model model) {
		
		// 현재 로그인한 사용자의 사용자의 email 값을 가져온다.
		String email = usersService.getPrincipal().getUsername();
		model.addAttribute("email", email);
		try {
			// 가져온 이메일 값을 이용하여 사용자 정보를 불러온다.
			Users users = usersService.detailEmail(email);
			
			// 비밀번호는 클라리언트에 제공하지 않기 위해 null 값 설정
			users.setPassword(null);
			
			// 비밀번호 제외한 사용자 정보를 클라이언트에게 전달
			model.addAttribute("users", users);
			
		} catch (UsersException e) {
			System.out.println(e.getMessage());
			model.addAttribute("error", "server");
		}
		
		return "board-add";
	}
	
	// 글 추가 후, 글 목록 화면으로 이동
	@RequestMapping(value="/board-add.do", method=RequestMethod.POST)
	public String add(HttpServletRequest request,
				Integer userNo,
				String title,
				String content,
				@RequestParam("attachment") MultipartFile attachment) {
		
		Board board = new Board();
		board.setUserNo(userNo);
		board.setTitle(title);
		board.setContent(content);
		
		try {
			// 파일 서비스로부터 전달받은 파일명을 VO 객체에 담는다
			String filename = fileservice.add(request, attachment);
			board.setAttachment(filename);
			
			// 모든 내용을 저장한 VO 객체를 데이터베이스로 전달
			boardservice.add(board);
			
		} catch (BoardException e) {
			request.setAttribute("error", "server");
		} catch (FileException e) {
			request.setAttribute("error", "file");
		}
		
		// redirect: 뒤에 입력한 주소로 이동
		return "redirect:board-list.do";
	}
	
	// 글 삭제 확인 화면 (board-detail 에서 삭제하기 버튼을 누르면 글 번호 정보가 이곳으로 넘어옴)
	@RequestMapping(value="/board-remove.do", method=RequestMethod.GET)
	public String removeconfirm(Model model, @RequestParam(value="no", required=true) String no) {
		
		model.addAttribute("no", no);
		
		return "board-remove-confirm";
	}
	
	// 글 삭제 후, 글 목록 화면으로 이동
	@RequestMapping(value="/board-remove.do", method=RequestMethod.POST)
	public String remove(Model model, String no, HttpServletRequest request) {
		try {
			String toDeleteFilename = boardservice.remove(no);
			fileservice.remove(request, toDeleteFilename);
			
		} catch (BoardException e) {
			model.addAttribute("error", "server");
		} catch (FileException e) {
			model.addAttribute("error", "file");
		}
		return "redirect:board-list.do";
	}
	
	// 글 수정하기 화면
	@RequestMapping(value="/board-modify.do", method=RequestMethod.GET)
	public String modify(Model model, @RequestParam(value="no", required=true) String no) {
		Board board = null;
		
		try {
			/*
			 * 수정하고자 하는 글의 정보를 가져와서 
			 * 글 수정하기 화면에 출력하기 위해 아래와 같이 호출
			 */
			board = boardservice.detail(no);
			
		} catch(BoardException e) {
			model.addAttribute("error", "server");
		}
		
		model.addAttribute("board", board);
		
		return "board-modify";
	}
	
	// 글 수정한 후, 글 목록 화면으로 이동
	@RequestMapping(value="/board-modify.do", method=RequestMethod.POST)
	public String modify(HttpServletRequest request, 
			Integer no,
			String title,
			String content,
			@RequestParam("attachment") MultipartFile attachment) {
		
		Board board = new Board();
		board.setNo(no);
		board.setTitle(title);
		board.setContent(content);
		
		try {
			// 새롭게 수정할 파일을 서버에 저장
			String filename = fileservice.add(request, attachment);
			board.setAttachment(filename);
			
			// 기존 파일명을 가져온다
			String toDeleteFilename = boardservice.modify(board);
			
			// 기존에 있던 파일 삭제
			fileservice.remove(request, toDeleteFilename);
			
		} catch(BoardException e) {
			System.out.println(e.getMessage());
			request.setAttribute("error", "server");
		} catch (FileException e) {
			System.out.println(e.getMessage());
			request.setAttribute("error", "file");
		}
		
		return "redirect:board-list.do";
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
