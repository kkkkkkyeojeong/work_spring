package pattern;

import java.util.Date;

public class BuilderPatternTest {
	
	public static void main(String[] args) {
		
		StringBuilder builder01 = new StringBuilder();
		
		builder01
			.append("안녕하세요.")
			.append("반갑습니당");
		
		System.out.println(builder01);
		
		
		UserBuilder builder02 = new UserBuilder()
				.setAge(10)
				.setDob(new Date())
				.setEmail("aaa@gmail.com")
				.setName("길동");
		
		System.out.println(builder02);
		
		
		
		
		
	}

}
