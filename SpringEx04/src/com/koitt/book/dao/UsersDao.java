package com.koitt.book.dao;

import java.util.List;

import com.koitt.book.model.Users;
import com.koitt.book.model.UsersException;

public interface UsersDao {
	
	// 사용자 전체 보기
	public List<Users> selectAll() throws UsersException;
	
	// 사용자 한명 자세히 보기
	public Users select(Integer no) throws UsersException;
	
	// 사용자 추가(가입)
	public void insert(Users users) throws UsersException;
	
	// 사용자 삭제
	public void delete(Integer no) throws UsersException;
	
	// ??
	public void deleteUserType(Integer no) throws UsersException;
	
	// 사용자 수정
	public void update(Users users) throws UsersException;

}
