package com.koitt.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class BoardRowMapper implements RowMapper<Board> {

	@Override
	public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Board board = new Board();
		
		board.setNo(rs.getInt("no"));
		board.setTitle(rs.getString("title"));
		board.setContent(rs.getString("content"));
		board.setUser_no(rs.getInt("user_no"));
		board.setRegdate(rs.getDate("regdate"));
		board.setAttachment(rs.getString("attachment"));
		
		return board;
	}

}
