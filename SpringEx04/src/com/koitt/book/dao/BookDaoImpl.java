package com.koitt.book.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.koitt.book.model.Book;
import com.koitt.book.model.BookException;

@Repository
public class BookDaoImpl implements BookDao{

	@Autowired
	private JdbcTemplate template;
	
	public BookDaoImpl() {}
	
	@Override
	public void insert(Book book) throws BookException {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO book (title, author, publisher, price, description) ");
			sql.append("VALUES (?, ?, ?, ?, ?) ");
			
			template.update(sql.toString(),
					book.getTitle(),
					book.getAuthor(),
					book.getPublisher(),
					book.getPrice(),
					book.getDescription());
			
		} catch(Exception e) {
			throw new BookException(e.getMessage());
		}
		
	}

	@Override
	public void update(Book book) throws BookException {
		try {
			StringBuilder sql = new StringBuilder();
			
			sql.append("UPDATE book SET title = ?, ");
			sql.append("author = ?, ");
			sql.append("publisher = ?, ");
			sql.append("price = ?, ");
			sql.append("description = ? ");
			sql.append("WHERE isbn = ?" );
			
			template.update(sql.toString(),
					book.getTitle(),
					book.getAuthor(),
					book.getPublisher(),
					book.getPrice(),
					book.getDescription(),
					book.getIsbn());
			
		} catch(Exception e) {
			throw new BookException(e.getMessage());
		}
	}

	@Override
	public void delete(String isbn) throws BookException {
		try {
			String sql = "DELETE FROM book WHERE isbn = ? ";
			template.update(sql, isbn);
			
		} catch(Exception e) {
			throw new BookException(e.getMessage());
		}
	}

	@Override
	public List<Book> selectAll() throws BookException {
		List<Book> list = null;
		
		try {
			String sql = "SELECT * FROM book ORDER BY isbn DESC";
			list = template.query(sql, new BeanPropertyRowMapper<Book>(Book.class));
			
		} catch(Exception e) {
			throw new BookException(e.getMessage());
		}
		
		return list;
	}

	@Override
	public Book select(String isbn) throws BookException {
		Book book = null;
		
		try {
			String sql = "SELECT * FROM book WHERE isbn = ?";
			book = template.queryForObject(sql, new BeanPropertyRowMapper<Book>(Book.class), isbn);
			
		} catch(Exception e) {
			throw new BookException(e.getMessage());
		}

		return book;
	}

}
