package com.koitt.board.service;

import java.util.List;

import com.koitt.board.model.Board;
import com.koitt.board.model.BoardException;

public interface BoardService {
	
	// 글 추가하기
	public void add(Board board) throws BoardException;

	// 글 상세보기
	public Board detail(String no) throws BoardException;
	
	// 글 전체
	public List<Board> list() throws BoardException;
	
	// 글 개수
	public int count() throws BoardException;
	
	// 글 수정하기
	public String modify(Board board) throws BoardException;
	
	// 글 삭제하기
	public String remove(String no) throws BoardException;
	
	
}
