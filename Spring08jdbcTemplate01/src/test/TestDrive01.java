package test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class TestDrive01 {
	
	public static void main(String[] args) {
		
		// 1-1. 스프링 설정파일 불러오기
		ApplicationContext context = new GenericXmlApplicationContext("/config/config.xml");
		
		// 1-2. JdbcTemplate 객체를 이용하여 SQL query문 실행
		// 클래스의 타입을 이용해서 빈 객체를 가져온 경우
		JdbcTemplate template = context.getBean(JdbcTemplate.class);
		
		// SQL문 : emp 테이블의 행의 개수를 가져오는 SQL문
		String sql = "SELECT COUNT(*) FROM emp";
		
		// int count = template.queryForInt(slq);  -> Spring 3버전에서 사용하던 방식
		// SQL문 실행 : 파라미터 첫번째는 sql문, 두번째는 결과 리턴 타입
		int count = template.queryForObject(sql, Integer.class);
		System.out.println("1: " + count);
	
		
		
		// 2-1.DataSource 객체를 이용하여 SQL query 실행
		DataSource datasource = context.getBean(DataSource.class);
		try{
			Connection conn = datasource.getConnection();
			
			// Connection의 정체를 알아보기 위해 출력
			System.out.println("2: " + conn.getClass().getName());
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				System.out.println("3: " + rs.getInt(1));
			}
			
			rs.close();
			stmt.close();
			conn.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		// 3
		sql = "SELECT COUNT(*) FROM emp WHERE deptno = ?";
									// sql문 , 리턴타입, ? 첫번째 값
		count = template.queryForObject(sql, Integer.class, 20);
		System.out.println("4: " + count);
		
		
		// 4.
		sql = "SELECT ename FROM emp WHERE empno = ?";
		String name = template.queryForObject(sql, String.class, "7900");
		System.out.println("5: " + name);
		
		
		// 5.
		sql = "SELECT hiredate FROM emp WHERE empno = ?";
		Date hireDate = template.queryForObject(sql, Date.class, "7900");
		System.out.println("6: " + hireDate);
		
		
		// 6.
		sql = "SELECT * FROM emp WHERE empno = ?";
		Map<String, Object> emp = template.queryForMap(sql, 7900);
		for (String key : emp.keySet()) {
			System.out.println(key + " : " + emp.get(key));
		}
		System.out.println("---------------");
		
		
		// 7.
		sql = "SELECT * FROM emp WHERE deptno = ?";
		List<Map<String, Object>> emplist = template.queryForList(sql, 20);
		// Map은 각 행을 의미, 각 행을 모두 담고 있어야하기때문에 Map 타입의 List 사용
		// 첫번째 for문은 List 에서 각 행 하나를 꺼내온것
		for (Map<String, Object> item : emplist) {
			
			// 두번째 for문은 key값을 이용해서 각 행의 Map에서 value 값을 꺼내온 것
			for(String key : item.keySet()) {
				System.out.print(key + " : " + item.get(key));
			}
			System.out.println();
		}
		System.out.println();
		
		
		// 8.
		sql = "SELECT sal FROM emp WHERE deptno = ?";
		List<Integer> salary = template.queryForList(sql, Integer.class, 20);
		for (int item : salary) {
			System.out.println(item);
		}
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
