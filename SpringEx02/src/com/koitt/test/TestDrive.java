package com.koitt.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.koitt.dao.BoardDao;
import com.koitt.model.Board;

public class TestDrive {
	
	public static void main(String[] args) {
		
		ApplicationContext context = 
				new GenericXmlApplicationContext("/com/koitt/config/config.xml");
		
		
		BoardDao dao01 = context.getBean(BoardDao.class);
		Board board01 = dao01.getBoard(1);
		System.out.println(board01);
		
		
		BoardDao dao02 = context.getBean(BoardDao.class);
		Board board02 = dao02.getBoard2(2);
		System.out.println(board02);
		
	}

}
