package com.koitt.model;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component		
public class RunScheduler {

	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private Job job;
	
	// 매일 오후 4시마다 Job 실행
	@Scheduled(cron="0 0 16 ? * *")
	public void run() {
		try {
			JobParameters param = new JobParametersBuilder()
					// Job이 실행한 시간을 출력하기 위해 (콘솔창 빨간색 메시지 부분 확인)
					.addString("date", new Date().toString())
					.toJobParameters();
			
			JobExecution execution = jobLauncher.run(job, param);
			System.out.println("종료상태 : " + execution.getStatus());
			System.out.println("발생한 예외들 : " + execution.getAllFailureExceptions());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
	
	
	

}




















