package com.koitt.service;

import javax.mail.internet.MimeMessage;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class MailService implements Tasklet{

	@Autowired
	private JavaMailSender mailsender;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
		MimeMessagePreparator preparetor = new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {

				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
				helper.setFrom("duwjd6539@gmail.com");		// 보내는 사람 이메일 주소
				helper.setTo("yeok6539@naver.com");			// 받는 사람 이메일 주소
				helper.setSubject("Board 테이블 정보");			// 메일 제목
				helper.setText("안녕하세요", false);			// 메일 내용

				helper.addAttachment("board.csv", new FileSystemResource("C:/sample/prac03/board.csv"));

			}
		};

		try {
			System.err.println("메일 보내는 중 >>> ");
			mailsender.send(preparetor);
			System.out.println("메일 보내기 완료 >>> ");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return RepeatStatus.FINISHED;
	}


}
