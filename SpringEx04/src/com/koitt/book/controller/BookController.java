package com.koitt.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.koitt.book.model.Book;
import com.koitt.book.model.BookException;
import com.koitt.book.service.BookService;

@Controller
public class BookController {

	@Autowired
	private BookService service;
	
	// 책 목록보기
	@RequestMapping(value="/book-list.do", method=RequestMethod.GET)
	public String list(Model model) {
		List<Book> list = null;
		
		try {
			list = service.list();
			
		} catch(BookException e) {
			model.addAttribute("error", "server");
		}
		
		model.addAttribute("list", list);
		
		return "book-list";
	}
	
	// 책 상세보기
	@RequestMapping(value="/book-detail.do", method=RequestMethod.GET)
	public String detail(Model model, 
			@RequestParam(value="isbn", required=true) String isbn) {
		Book book = null;
		
		try {
			book = service.detail(isbn);
			
		} catch(BookException e) {
			model.addAttribute("error", "server");
		}
		model.addAttribute("book", book);
		
		return "book-detail";
	}
	
	// 책 추가하기 화면
	@RequestMapping(value="/book-add.do", method=RequestMethod.GET)
	public String add() {
		return "book-add";
	}
	
	// 책 추가 후 도서 목록으로 이동
	@RequestMapping(value="/book-add.do", method=RequestMethod.POST)
	public String add(Model model, Book book) {
		try {
			service.add(book);
		} catch(BookException e) {
			model.addAttribute("error", "server");
		}
		
		return "redirect:book-list.do";
	}
	
	// 책 삭제 화면
	@RequestMapping(value="/book-remove.do", method=RequestMethod.GET)
	public String removeconfirm(Model model, 
			 @RequestParam(value="isbn", required=true) String isbn) {
		
		model.addAttribute("isbn", isbn);
		
		return "book-remove-confirm";
	}
	
	
	// 책 삭제 후, 책 목록으로 이동
	@RequestMapping(value="/book-remove.do", method=RequestMethod.POST)
	public String remove(Model model, String isbn) {
		try {
			service.remove(isbn);
			
		} catch(BookException e) {
			model.addAttribute("error", "server");
		}
		return "redirect:book-list.do";
	}
	
	// 도서 수정하기 화면
	@RequestMapping(value="/book-modify.do", method=RequestMethod.GET)
	public String modify(Model model, 
			@RequestParam(value="isbn", required=true) String isbn) {
		
		Book book = null;
		
		try {
			book = service.detail(isbn);
		} catch(BookException e) {
			model.addAttribute("error", "server");
		}
		model.addAttribute("book", book);
		
		return "book-modify";
		
	}
	
	
	// 수정 후 도서목록으로 이동
	@RequestMapping(value="/book-modify.do", method=RequestMethod.POST)
	public String modify(Model model, Book book) {
		try {
			service.modify(book);
			
		} catch(BookException e) {
			model.addAttribute("error", "server");
		}
		
		return "redirect:book-list.do";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
