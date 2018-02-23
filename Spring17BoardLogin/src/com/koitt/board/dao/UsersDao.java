package com.koitt.board.dao;

import java.util.List;

import com.koitt.board.model.Users;
import com.koitt.board.model.UsersException;

public interface UsersDao {
	
	public List<Users> selectAll() throws UsersException;
	
	public Users select(Integer no) throws UsersException;
	
	// 이메일로 사용자의 모든 정보 가져오기
	public Users selectEmail(String email) throws UsersException;
	
	public void insert(Users users) throws UsersException;
	
	public void delete(Integer no) throws UsersException;
	
	public void deleteUserType(Integer no) throws UsersException;
	
	public void update(Users users) throws UsersException;

	// users_authority 테이블에 정보 입력
	public void insertAuthority(Users users) throws UsersException;
	
	// 이메일로 사용자의 모든 정보 가져오기
	public Users selectByEmail(String email) throws UsersException;
	
	// 최근 등록한 사용자의 번호 가져오기
	public Integer selectLastInsertId() throws UsersException;
	
	
	
}
