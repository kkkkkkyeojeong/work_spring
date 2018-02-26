package com.koitt.book.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import com.koitt.book.dao.AuthorityDao;
import com.koitt.book.dao.UsersDao;
import com.koitt.book.model.Authority;
import com.koitt.book.model.AuthorityId;
import com.koitt.book.model.Users;
import com.koitt.book.model.UsersException;

@Service
public class UsersServiceImpl implements UsersService{

	@Autowired
	private UsersDao usersdao;
	
	@Autowired
	private AuthorityDao authoritydao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
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
	public void add(Users users) throws UsersException {
		
		String encode = passwordEncoder.encode(users.getPassword());
		users.setPassword(encode);
		
		Authority auth = new Authority(AuthorityId.USER.getAuthrityId(), AuthorityId.USER.name());
		
		Set<Authority> auths = new HashSet<>();
		auths.add(auth);
		users.setAuthorities(auths);
		
		usersdao.insert(users);
		
		Integer no = usersdao.selectlastInsertId();
		
		users.setNo(no);
		
		usersdao.insertAuthority(users);
	
	}

	@Override
	public String remove(Integer no, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String modify(Users users) throws UsersException {
		Users item = usersdao.select(users.getNo());
		String filename = item.getAttachment();
		
		users.setPassword(passwordEncoder.encode(users.getPassword()));
		
		usersdao.update(users);
		
		return filename;
	}

	
	@Override
	public Users detailEmail(String email) throws UsersException {
		return usersdao.selectEmail(email);
	}

	@Override
	public Authority getAuthority(Integer id) throws UsersException {
		return authoritydao.select(id);
	}

	@Override
	public UserDetails getPrincipal() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		Object principal = auth.getPrincipal();
		if(principal instanceof UserDetails) {
			return (UserDetails) principal;
		}
		
		return null;
	}

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse resp) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth != null) {
			new SecurityContextLogoutHandler().logout(request, resp, auth);
		}
		
	}

	@Override
	public boolean isPasswordMatched(String oldPassword) throws UsersException {
		// 현재 로그인한 사용자의 암호화된 비밀번호를 가져온다. 
		String email = this.getPrincipal().getUsername();
		Users users = usersdao.selectEmail(email);
				
		// 입력한 비밀번호와 기존 비밀번호를 비교하여 일치하면 true, 아니면 false 리턴
		return passwordEncoder.matches(oldPassword, users.getPassword());
	}

}
