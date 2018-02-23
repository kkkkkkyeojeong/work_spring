package com.koitt.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koitt.board.dao.AuthorityDao;
import com.koitt.board.dao.UsersDao;
import com.koitt.board.model.Authority;
import com.koitt.board.model.Users;
import com.koitt.board.model.UsersException;

@Service
@Transactional	// 메소드 단위별로 적용한다는 의미
public class UsersServiceImpl implements UsersService{

	@Autowired
	private UsersDao usersdao;
	
	@Autowired
	private AuthorityDao authoritydao;
	
	public UsersServiceImpl() {}
	
	@Override
	public List<Users> list() throws UsersException {
		return usersdao.selectAll();
	}

	@Override
	public Users detail(Integer no) throws UsersException {
		return usersdao.select(no);
	}
	
	@Override
	public Users detailEmail(String email) throws UsersException {
		return usersdao.selectEmail(email);
	}

	@Override
	public void add(Users users) throws UsersException {
		usersdao.insert(users);
	}

	@Override
	public String remove(Integer no, String password) throws UsersException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String modify(Users users) throws UsersException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Authority getAuthority(Integer id) throws UsersException {
		return authoritydao.select(id);
	}
	
	
	/*
	 *  Principal 객체 가져오기
	 *  Principal: 시스템을 사용하려고 하는 사용자 (로그인 한 사용자)
	 */
	@Override
	public UserDetails getPrincipal(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		Object principal = auth.getPrincipal();
		if(principal instanceof UserDetails) {
			return (UserDetails) principal;
		}
		
		return null;
		
	}

	/*
	 * 아래와 같이 코드 작성하면 스프링에서 로그아웃 처리를 한다.
	 */
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse resp) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth != null) {
			new SecurityContextLogoutHandler().logout(request, resp, auth);
		}
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
