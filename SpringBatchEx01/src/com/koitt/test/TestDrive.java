package com.koitt.test;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDrive {
	
	public static void main(String[] args) {
		
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("com/koitt/config/applicationContext.xml");
		
		JobLauncher launcher = context.getBean(JobLauncher.class);
		
		Job job = context.getBean("csvToXmlJob", Job.class);
		
		try {
			String in = "C:/sample/prac01/board.csv";
			String out = "C:/sample/prac01/board.xml";
			
			JobParameters jobparameters = new JobParametersBuilder()
					.addString("inputFile", in)
					.addString("outputFile", out)
					.addString("date", new Date().toString())
					.toJobParameters();
			
			JobExecution execution = launcher.run(job, jobparameters);
			
			System.out.println("종료 상태 : " + execution.getStatus());
			System.out.println("종료 상태 예외발생 목록 : " + execution.getAllFailureExceptions());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("작업완료");
		
	}

}
