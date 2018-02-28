package com.koitt.book.controller;

import java.net.URLEncoder;
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

import com.koitt.book.model.FileException;
import com.koitt.book.model.Users;
import com.koitt.book.model.UsersException;
import com.koitt.book.service.FileService;
import com.koitt.book.service.UsersService;

@Controller
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private FileService<Users> fileService;
	
	
	// 사용자 목록
	@RequestMapping(value="/admin/users-list.do", method=RequestMethod.GET)
	public String list(Model model, HttpServletRequest request) {
		
		List<Users> list = null;
		
		try {
			list = usersService.list();
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
			model.addAttribute("error", "server");
		}
		model.addAttribute("list", list);
		model.addAttribute("uploadPath", fileService.getUploadPath(request));
		
		return "admin/users-list";
	}
	
	// 가입화면으로 이동
	@RequestMapping(value="/join.do", method=RequestMethod.GET)
	public String join() {
		return "join";
	}
	
	// 가입 추가
	@RequestMapping(value="/join.do", method=RequestMethod.POST)
	public String join(HttpServletRequest request,
			String email,
			String password,
			String name,
			@RequestParam("attachment") MultipartFile attachment) {
		
		Users users = new Users(null, email, password, name, null);
		
		try {
			String filename = fileService.add(request, attachment);
			
			users.setAttachment(filename);
			
			usersService.add(users);
			
			String encodeName = URLEncoder.encode(users.getName(), "UTF-8");
			
			return "redirect:join-confirm.do?name=" + encodeName;
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
			request.setAttribute("error", "file");
		}
		
		
		return "redirect:index.html";
	}
	
	
	// 가입 확인
	@RequestMapping(value="/join-confirm.do", method=RequestMethod.GET)
	public String joinConfirm(Model model, String name) {
		model.addAttribute("name", name);
		return "join-confirm";
	}
	
	// 로그인
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	// 로그아웃
	@RequestMapping(value="/logout.do", method=RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse resp) {
		// 서비스의 로그아웃 메소드 호출
		usersService.logout(request, resp);
		
		// 로그아웃 한 뒤 로그인 페이지로 이동 후 로그아웃 메시지 출력을 위해 쿼리문자열 사용
		return "redirect:/login.do?logout=true";
	}
	
	
	// 접근 제한 페이지
	@RequestMapping(value="/access-denied.do", method=RequestMethod.GET)
	public String accesslogin(Model model) {
		
		model.addAttribute("email", usersService.getPrincipal().getUsername());
		
		return "access-denied";
	}
	
	
	// 회원정보 변경 페이지로 이동
	@RequestMapping(value="/users-modify.do", method=RequestMethod.GET)
	public String modify(HttpServletRequest request) {
		
		Users users = null;
		String uploadpath = null;
		
		try {
			String email = usersService.getPrincipal().getUsername();
			users = usersService.detailEmail(email);
			uploadpath = fileService.getUploadPath(request);
			
		} catch(UsersException e) {
			request.setAttribute("error", "server");
		}
		
		request.setAttribute("users", users);
		request.setAttribute("uploadpath", uploadpath);
		
		return "users-modify";
	}
	
	
	// 회원정보 변경
	@RequestMapping(value="/users-modify.do", method=RequestMethod.POST)
	public String modify(HttpServletRequest request, 
			String oldPassword,
			String newPassword,
			String name,
			@RequestParam("attachment") MultipartFile attachment) {
		
		
		try {
			// 기존 비밀번호가 일치하는지 확인 
			boolean isMatched = usersService.isPasswordMatched(oldPassword);
			
			// 비밀번호가 일치하면 사용자 정보를 변경한다
			if(isMatched) {
				// 현재 로그인한 사용자의 email 값을 가져오기
				String email = usersService.getPrincipal().getUsername();
				
				// 현재 로그인한 사용자 정봅를 email 값을 이용하여 가져오기
				Users oldUsers = usersService.detailEmail(email);
				
				// 새로 변경할 프로필 사진을 서버에 저장한다.
				String filename = fileService.add(request, attachment);
				
				// 데이터베이스에 저장할 users 객체 생성
				Users users = new Users(oldUsers.getNo(), email, newPassword, name, filename);
				
				// 수정할 정보를 데이터베이스에 전달 
				String toDeleteFile = usersService.modify(users);
				
				// 기존 프로필 사진 삭제
				fileService.remove(request, toDeleteFile);
			}
			// 기존 비밀번호가 일치하지 않은 경우에는 오류메시지를 수정페이지에 출력한다.
			else {
				return "redirect:users-modify.do?error=password";
			}
			
		} catch(UsersException e) {
			System.out.println(e.getMessage());
			request.setAttribute("error", "server");
		} catch (FileException e) {
			System.out.println(e.getMessage());
			request.setAttribute("error", "file");
		} 
		
		return "redirect:users-modify-confirm.do";
			
		}
	
	@RequestMapping(value="users-modify-confirm.do", method=RequestMethod.GET)
	public String modifyConfirm() {
		
		return "users-modify-confirm";
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
