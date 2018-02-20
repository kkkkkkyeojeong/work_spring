package com.koitt.book.service;

import java.util.List;

import com.koitt.book.model.Book;
import com.koitt.book.model.BookException;

public interface BookService {
	
	// 책 추가하기
	public void add(Book book) throws BookException;
	
	// 책 수정하기
	public String modify(Book book) throws BookException;
	
	// 책 삭제하기
	public String remove(String isbn) throws BookException;
	
	// 책 목록 전체 보기
	public List<Book> list() throws BookException;
	
	// 책 하나 불러오기
	public Book detail(String isbn) throws BookException;
	

}
