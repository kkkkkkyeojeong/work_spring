package com.koitt.book.controller;

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

import com.koitt.book.model.Book;
import com.koitt.book.model.BookException;
import com.koitt.book.model.FileException;
import com.koitt.book.service.BookService;
import com.koitt.book.service.FileService;

@Controller
public class BookController {

	@Autowired
	private BookService bookservice;
	
	@Autowired
	private FileService fileservice;
	
	// 책 목록보기
	@RequestMapping(value="/book-list.do", method=RequestMethod.GET)
	public String list(Model model) {
		List<Book> list = null;
		
		try {
			list = bookservice.list();
			
		} catch(BookException e) {
			model.addAttribute("error", "server");
		}
		
		model.addAttribute("list", list);
		
		return "book-list";
	}
	
	// 책 상세보기
	@RequestMapping(value="/book-detail.do", method=RequestMethod.GET)
	public String detail(Model model, HttpServletRequest request,
			@RequestParam(value="isbn", required=true) String isbn) {
		Book book = null;
		String filename = null;
		String imgPath = null;
		
		try {
			book = bookservice.detail(isbn);
			
			filename = book.getAttachment();
			
			if(filename != null && !filename.trim().isEmpty()) {
				filename = URLDecoder.decode(filename, "UTF-8");
			}
			
			imgPath = fileservice.getImgPath(request, filename);
			
		} catch(BookException e) {
			System.out.println(e.getMessage());
			model.addAttribute("error", "server");
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
			model.addAttribute("error", "encoding");
		}
		
		model.addAttribute("book", book);
		model.addAttribute("filename", filename);
		
		if(imgPath != null && !imgPath.trim().isEmpty()) {
			model.addAttribute("imgPath", imgPath);
		}
		
		return "book-detail";
	}
	
	// 책 추가하기 화면
	@RequestMapping(value="/book-add.do", method=RequestMethod.GET)
	public String add() {
		return "book-add";
	}
	
	// 책 추가 후 도서 목록으로 이동
	@RequestMapping(value="/book-add.do", method=RequestMethod.POST)
	public String add(HttpServletRequest request,
			String title, 
			String author,
			String publisher,
			Integer price,
			String description,
			@RequestParam("attachment") MultipartFile attachment) {
		
		Book book = new Book();
		book.setTitle(title);
		book.setAuthor(author);
		book.setPublisher(publisher);
		book.setPrice(price);
		book.setDescription(description);
		
		try {
			String filename = fileservice.add(request, attachment);
			book.setAttachment(filename);
			
			bookservice.add(book);
			
		} catch(BookException e) {
			request.setAttribute("error", "server");
		} catch (FileException e) {
			request.setAttribute("error", "file");
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
	public String remove(Model model, String isbn, HttpServletRequest request) {
		try {
			String toDeleteFilename = bookservice.remove(isbn);
			fileservice.remove(request, toDeleteFilename);
			
		} catch(BookException e) {
			model.addAttribute("error", "server");
		} catch (FileException e) {
			model.addAttribute("error", "file");
		}
		return "redirect:book-list.do";
	}
	
	// 도서 수정하기 화면
	@RequestMapping(value="/book-modify.do", method=RequestMethod.GET)
	public String modify(Model model, 
			@RequestParam(value="isbn", required=true) String isbn) {
		
		Book book = null;
		
		try {
			book = bookservice.detail(isbn);
			
		} catch(BookException e) {
			model.addAttribute("error", "server");
		}
		model.addAttribute("book", book);
		
		return "book-modify";
		
	}
	
	
	// 수정 후 도서목록으로 이동
	@RequestMapping(value="/book-modify.do", method=RequestMethod.POST)
	public String modify(HttpServletRequest request,
			Integer isbn,
			String title,
			String author,
			String publisher,
			Integer price,
			String description,
			@RequestParam("attachment") MultipartFile attachment) {
		
		Book book = new Book();
		book.setIsbn(isbn);
		book.setTitle(title);
		book.setAuthor(author);
		book.setPublisher(publisher);
		book.setPrice(price);
		book.setDescription(description);
		
		try {
			String filename = fileservice.add(request, attachment);
			book.setAttachment(filename);
			
			String toDeleteFilename = bookservice.modify(book);
			
			fileservice.remove(request, toDeleteFilename);
			
		} catch(BookException e) {
			System.out.println(e.getMessage());
			request.setAttribute("error", "server");
		} catch (FileException e) {
			System.out.println(e.getMessage());
			request.setAttribute("error", "file");
		}
		
		return "redirect:book-list.do";
	}
	
	// 파일 다운로드
	@RequestMapping(value="/download.do", method=RequestMethod.GET, params="filename")
	public void download(HttpServletRequest request, HttpServletResponse response, String filename) {
	
		try {
			fileservice.download(request, response, filename);
			
		} catch (FileException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
