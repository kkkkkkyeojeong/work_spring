package com.koitt.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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


}
