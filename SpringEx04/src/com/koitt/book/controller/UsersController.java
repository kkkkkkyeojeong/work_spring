package com.koitt.book.controller;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.koitt.book.model.Users;
import com.koitt.book.service.FileService;
import com.koitt.book.service.UsersService;

@Controller
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private FileService<Users> fileService;
	
	
	// 사용자 목록
	@RequestMapping(value="/users-list.do", method=RequestMethod.GET)
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
