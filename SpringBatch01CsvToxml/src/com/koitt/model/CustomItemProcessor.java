package com.koitt.model;

import org.springframework.batch.item.ItemProcessor;

/*
 * ItemReader에서 읽은 데이터를 전달받아
 * 중간에서 비지니스 로직이 필요할 경우 작성
 * (필요 없다면 ItemProcessor는 생략 가능)
 * 
 * 처리가 끝나면 ItemWriter에게 처리할 데이터를 전달 
 * (단, 처리할 항목 개수가 commit-interval 만큼 쌓이면 전달)
 * 
 * ItemProcessor<Report, Report>: 첫번째는 리턴받은 타입, 두번째는 리턴할 타입
 */
public class CustomItemProcessor implements ItemProcessor<Report, Report>{

	@Override
	public Report process(Report item) throws Exception {
		
		// 여기서는 따로 데이터를 가공/처리하지 않고 콘솔 출력만 하는 예시
		System.out.println("처리 중인 항목: " + item);

		return item;
	}

	

}
