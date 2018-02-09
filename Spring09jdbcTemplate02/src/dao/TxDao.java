package dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import model.Job;

//@Component
@Repository		// Trancsaction 사용을 하려면 Dao 클래스에 @Repository 를 작성해야 한다	
public class TxDao {

	@Autowired		// xml 설정파일에서 aurowire = byType 한 것과 동일 / config.xml에서 JdbcTemplate 타입 bean 객체를 찾음
	private JdbcTemplate template;
	
	public void insert(Job job) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO job (job_id, job_title, min_Salary, max_Salary) ");
		sql.append("VALUES (?, ?, ?, ?)");
		
		template.update(sql.toString(), job.getJobId(), job.getJobtitle(), job.getMinSalary(), job.getMaxSalary());
		
	}
	
	public void update(Job job) {
		String sql = "UPDATE job SET job_title = ?, max_Salary = ?, min_Salary = ?"
				+ "WHERE job_Id = ?";
		template.update(sql, job.getJobtitle(), job.getMaxSalary(), job.getMinSalary(), job.getJobId());
	}
	
}
