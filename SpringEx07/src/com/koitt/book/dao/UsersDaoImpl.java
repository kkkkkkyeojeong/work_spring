package com.koitt.book.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koitt.book.model.Users;
import com.koitt.book.model.UsersException;

@Repository
public class UsersDaoImpl implements UsersDao{

	private static final String MAPPER_NS = Users.class.getName();
	
	@Autowired
	private SqlSession session;
	
	public UsersDaoImpl() {}
	
	@Override
	public List<Users> selectAll() throws UsersException {
		List<Users> list = null;
		
		try {
			list = session.selectList(MAPPER_NS + ".selectall-users");
			
		} catch(Exception e) {
			throw new UsersException(e.getMessage());
		}
		
		return list;
	}

	@Override
	public Users select(Integer no) throws UsersException {
		Users users = null;
		
		try {
			users = session.selectOne(MAPPER_NS + ".select-users", no);
			
		} catch(Exception e) {
			throw new UsersException(e.getMessage());
		}
		
		return users;
	}
	
	@Override
	public Users selectEmail(String email) throws UsersException {
		Users users = null;
		
		try {
			users = session.selectOne(MAPPER_NS + ".select-email-users", email); 
			
		} catch(Exception e) {
			throw new UsersException(e.getMessage());
		}
		
		return users;
	}
	

	@Override
	public void insert(Users users) throws UsersException {
		try {
			session.insert(MAPPER_NS + ".insert", users);
			
		} catch(Exception e) {
			throw new UsersException(e.getMessage());
		}
		
	}

	@Override
	public void delete(Integer no) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUserType(Integer no) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Users users) {
		try {
			session.update(MAPPER_NS + ".update-users", users);
			
		} catch(Exception e) {
			
		}
		
	}

	@Override
	public Integer selectlastInsertId() throws UsersException {
		
		Integer lastno = null;
		
		try {
			lastno = session.selectOne(MAPPER_NS + ".select-lastInsert-Id");
			
		} catch(Exception e) {
			throw new UsersException(e.getMessage());
		}
		
		return lastno;
	}

	@Override
	public void insertAuthority(Users users) throws UsersException {
		try {
			session.insert(MAPPER_NS + ".insert-authrity", users);
			
		} catch(Exception e) {
			throw new UsersException(e.getMessage());
		}
		
	}

	

}
