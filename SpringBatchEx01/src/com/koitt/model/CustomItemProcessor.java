package com.koitt.model;

import org.springframework.batch.item.ItemProcessor;

public class CustomItemProcessor implements ItemProcessor<Board, Board>{

	@Override
	public Board process(Board item) throws Exception {
		System.out.println("처리중인 항목: " + item);
		return item;
	}

	

}
