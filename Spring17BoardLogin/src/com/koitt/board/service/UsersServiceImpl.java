package com.koitt.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koitt.board.dao.UsersDao;
import com.koitt.board.model.Users;
import com.koitt.board.model.UsersException;

@Service
@Transactional	// 메소드 단위별로 적용한다는 의미
public class UsersServiceImpl implements UsersService{

	@Autowired
	private UsersDao dao;
	
	public UsersServiceImpl() {}
	
	@Override
	public List<Users> list() throws UsersException {
		return dao.selectAll();
	}

	@Override
	public Users detail(Integer no) throws UsersException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Users users) throws UsersException {
		dao.insert(users);
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

}
