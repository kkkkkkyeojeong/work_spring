package com.koitt.model;

import java.text.SimpleDateFormat;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

/*
 * ItemReader
 */
public class BoardFieldSetMapper implements FieldSetMapper<Board>{

	private SimpleDateFormat dateFormat;
	
	public BoardFieldSetMapper() {
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	}
	
	
	@Override
	public Board mapFieldSet(FieldSet fieldSet) throws BindException {
		
		Board item = new Board();
		
		item.setNumber(fieldSet.readInt(0));
		item.setTitle(fieldSet.readString(1));
		item.setContent(fieldSet.readString(2));
		item.setWriter(fieldSet.readString(3));
		
		String date = fieldSet.readString(4);
		
		try {
			item.setDate(dateFormat.parse(date));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return item;
	}

	

}
