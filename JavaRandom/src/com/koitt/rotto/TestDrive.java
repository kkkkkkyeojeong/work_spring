package com.koitt.rotto;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class TestDrive {
	
	public static void main(String[] args) {
		
		Random randomNo = new Random();
		
		/*Set<Integer> lotto = new HashSet<Integer>();*/
		
		// TreeSet 숫자 자동 정렬됨
		Set<Integer> lotto = new TreeSet<Integer>();
		
		Integer num = 0;
		
		Integer count = 0;
		
		
		while(true) {
			num = randomNo.nextInt(45) + 1;
			lotto.add(num);
			
			if(lotto.size() == 6) {
				break;
			}
		}
		
		Iterator<Integer> set = lotto.iterator();
		
		while(set.hasNext()) {
			Integer a = set.next();
			System.out.print(a + " ");
		}
		
		/*for (Integer number : lotto) {
			System.out.println(number);
		}*/
		
	}

}








