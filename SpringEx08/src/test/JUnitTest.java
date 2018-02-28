package test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.koitt.book.dao.BookDao;
import com.koitt.book.dao.UsersDao;
import com.koitt.book.model.Authority;
import com.koitt.book.model.AuthorityId;
import com.koitt.book.model.Book;
import com.koitt.book.model.BookException;
import com.koitt.book.model.Users;
import com.koitt.book.model.UsersException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/test/config/applicationContext.xml")
public class JUnitTest {
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	private Users users1;
	private Users users2;
	private Users users3;
	
	private Authority admin;
	private Authority user;
	
	private Set<Authority> adminSet = new HashSet<>();;
	private Set<Authority> userSet = new HashSet<>();
	private Set<Authority> bothSet = new HashSet<>();
	
	private Book book1;
	
	@Before
	public void setUp() {
		// 비밀번호 인코딩
		String password = passwordEncoder.encode("1234");
		
		// 1. 사용자 
		users1 = new Users(null, "user1@koitt.com", password, "가나다", null);
		users2 = new Users(null, "user2@koitt.com", password, "마바사", null);
		users3 = new Users(null, "user3@koitt.com", password, "아아아", null);
		
		// 2. 권한
		this.admin = new Authority(AuthorityId.ADMIN.getAuthrityId(), AuthorityId.ADMIN.name());
		this.user = new Authority(AuthorityId.USER.getAuthrityId(), AuthorityId.USER.name());
		
		adminSet.add(admin);
		userSet.add(user);
		bothSet.add(admin);
		bothSet.add(user);
		
		this.users1.setAuthorities(adminSet);
		this.users2.setAuthorities(userSet);
		this.users3.setAuthorities(bothSet);
		
		// 3. 게시글
		this.book1 = new Book(null, "제목1", "작가1", "출판사1", 
				5000, "내용1", null, null);
	
		
	}
	
	@Test
	public void addAndGetUsers() throws BookException, UsersException {
		// 삭제 
		// book
		bookDao.deleteAll();
		assertThat(bookDao.BookCount(), is(0));
		
		// 사용자 권한
		usersDao.deleteUsersAuthority(); 
		assertThat(usersDao.countUsersAuthority(), is(0));
		
		// 사용자
		usersDao.deleteAll();
		assertThat(usersDao.usersCount(), is(0));
		
		
		// 사용자 추가
		usersDao.insert(users1);
		users1.setNo(usersDao.selectlastInsertId());
		
		usersDao.insert(users2);
		users2.setNo(usersDao.selectlastInsertId());
		
		usersDao.insert(users3);
		users3.setNo(usersDao.selectlastInsertId());
		
		usersDao.insertAuthority(users1);
		usersDao.insertAuthority(users2);
		usersDao.insertAuthority(users3);
		assertThat(usersDao.countUsersAuthority(), is(4));
		
		
		Users usersget1 = usersDao.selectEmail(users1.getEmail());
		assertThat(usersget1.getName(), is(users1.getName()));
		assertThat(usersget1.getEmail(), is(users1.getEmail()));
		assertTrue(passwordEncoder.matches("1234", usersget1.getPassword()));
		
		Users usersget2 = usersDao.selectEmail(users2.getEmail());
		assertThat(usersget2.getName(), is(users2.getName()));
		assertThat(usersget2.getEmail(), is(users2.getEmail()));
		assertTrue(passwordEncoder.matches("1234", usersget2.getPassword()));
		
		Users usersget3 = usersDao.selectEmail(users3.getEmail());
		assertThat(usersget3.getName(), is(users3.getName()));
		assertThat(usersget3.getEmail(), is(users3.getEmail()));
		assertTrue(passwordEncoder.matches("1234", usersget3.getPassword()));
		
		
	}
	
	
	@Test
	public void addAndGetBook() throws BookException, UsersException {
		// 삭제 
		// book
		bookDao.deleteAll();
		assertThat(bookDao.BookCount(), is(0));
		
		// 사용자 권한
		usersDao.deleteUsersAuthority(); 
		assertThat(usersDao.countUsersAuthority(), is(0));
		
		// 사용자
		usersDao.deleteAll();
		assertThat(usersDao.usersCount(), is(0));		
		
		
		// 사용자 추가
		usersDao.insert(users1);
		Integer userNo = usersDao.selectlastInsertId();
		
		assertThat(usersDao.usersCount(), is(1));
		
		// 책 추가
		book1.setUserNo(userNo);
		bookDao.insert(book1);
		Integer bookNo = bookDao.BookLastId();
		
		assertThat(bookDao.BookCount(), is(1));
		
		// 책 비교
		Book bookget1 = bookDao.select(bookNo.toString());
		assertThat(bookget1.getTitle(), is(book1.getTitle()));
		assertThat(bookget1.getAuthor(), is(book1.getAuthor()));
		
		
	}
	
	
	

}




























