package com.koitt.book.dao;

import java.util.List;

import com.koitt.book.model.Book;
import com.koitt.book.model.BookException;

public interface BookDao {

	// 책 추가
	public void insert(Book book) throws BookException;
	
	// 책 수정
	public void update(Book book) throws BookException;
	
	// 책 삭제
	public void delete(String isbn) throws BookException;
	
	// 책 전체 목록 보기
	public List<Book> selectAll() throws BookException;
	
	// 책 하나 불러오기
	public Book select(String isbn) throws BookException;
	
	
	// JUnit Test
	
	// 글 전체 삭제하기
	public void deleteAll() throws BookException;
	
	// 게시글 개수 가져오기
	public Integer BookCount() throws BookException;
	
	// 추가한 게시글 마지막 번호 가져오기
	public Integer BookLastId() throws BookException;
	
	
}
