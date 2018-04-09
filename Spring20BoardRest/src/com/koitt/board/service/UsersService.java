package com.koitt.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.userdetails.UserDetails;

import com.koitt.board.model.Authority;
import com.koitt.board.model.Users;
import com.koitt.board.model.UsersException;

/*
 * 기본적으로 구현해야 할 항목들
 * C Create
 * R Read
 * U Update
 * D Delete
 */

public interface UsersService {
	
	public List<Users> list() throws UsersException;
	
	public Users detail(Integer no) throws UsersException;
	
	// 이메일로 사용자의 모든 정보 가져오기
	public Users detailEmail(String email) throws UsersException;
	
	public void add(Users users) throws UsersException;
	
	public String remove(Integer no, String password) throws UsersException;
	
	public String modify(Users users) throws UsersException;
	
	
	// 사용자 권한 가져오기
	public Authority getAuthority(Integer id) throws UsersException;
	
	/*
	 *  Principal 객체 가져오기
	 *  Principal: 시스템을 사용하려고 하는 사용자 (로그인 한 사용자)
	 */
	public UserDetails getPrincipal();
	
	// 로그아웃
	public void logout(HttpServletRequest request, HttpServletResponse resp);
	
	// 비밀번호 일치 여부 호가인하는 메소드
	public boolean isPasswordMatched(String oldPassword) throws UsersException;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
