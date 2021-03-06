package bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestDrive {
	
	public static void main(String[] args) {
		
		// 1. 스프링 설정파일 불러오기
		ApplicationContext context = new FileSystemXmlApplicationContext("./src/config/config.xml");
		
		// 2. bean 객체 생성방법
		
		// 2-1. 인터페이스를 사용하지 않고 bean 이름만으로 생성하는 방법
		PersonImpl bean01 = (PersonImpl) context.getBean("person");	// 리턴타입 Object
		
		// 2-2. 인터페이스를 사용하지 않고 이름과 클래스 타입으로 생성하는 방법
		PersonImpl bean02 = context.getBean("person", PersonImpl.class);
		
		// 2-3. 인터페이스 타입으로 빈 객체를 생성하는 방법
		// Person을 상속받은 클래스를 찾아서 객체 생성 / 만약 상속받은 클래스가 두개이상이라면 찾지못함
		Person bean03 = context.getBean(Person.class);
		
		// 2-4. 인터페이스와 클래스 정보로 빈 객체를 생성하는 방법
		Person bean04 = context.getBean(PersonImpl.class);
		
		// config.xml의 scope 속성에 따라 결과가 달라진다.
		System.out.println(bean01 == bean02);		// 주소값 비교
		
		bean01.sayHello();
		bean02.sayHello();
		bean03.sayHello();
		bean04.sayHello();
		
		System.out.println(bean01);
		System.out.println(bean02);
		System.out.println(bean03);
		System.out.println(bean04);
		
		
		
	}

}
