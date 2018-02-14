package com.koitt.board.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.koitt.board.model.Board;
import com.koitt.board.model.BoardException;

@Repository
public class BoardDaoImpl implements BoardDao{

	@Autowired
	private JdbcTemplate template;
	
	public BoardDaoImpl() {}
	
	@Override
	public void insert(Board board) {
		
	}

	@Override
	public Board select(String no) {
		return null;
	}

	@Override
	public List<Board> selectAll() throws BoardException {
		List<Board> list = null;
		
		// BeanPropertyRowMapper<Board>: 자동으로 필드와 컬럼을 연결시켜준다
		try {
			String sql = "SELECT * FROM board ORDER BY no DESC";
			list = template.query(sql, new BeanPropertyRowMapper<Board>(Board.class));
			
		} catch (Exception e) {
			throw new BoardException(e.getMessage());
		}
		
		return list;
	}

	@Override
	public int boardCounr() {
		return 0;
	}

	@Override
	public void update(Board board) {
		
	}

	@Override
	public void delete(String no) {
		
	}

}
