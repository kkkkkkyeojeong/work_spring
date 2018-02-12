package com.koitt.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koitt.model.Board;

@Repository
public class BoardDao {
	
	// 방법1
	@Autowired
	private SqlSessionFactory factory;
	
	public Board getBoard(int no) {
		SqlSession session = factory.openSession();
		Board board = session.selectOne("com.koitt.model.Board.select", no);
		session.close();				
		
		return board;
	}
	
	// 방법2
	@Autowired
	private SqlSession session;
	
	public Board getBoard2(int no) {
		Board board = session.selectOne("com.koitt.model.Board.select", no);
		
		return board;
	}
	

}
