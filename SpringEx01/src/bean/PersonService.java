package bean;

public class PersonService {
	
	// 2-1. 필드 만들기
	private Person person;
	
	// 생성자 - config.xml에서 autowire="constructor" 일 경우 생성자가 있어야 함
	public PersonService(Person person) {
		this.person = person;
	}
	
	// 2-2. person 필드에 대한 setter 	(autowire의 byName을 이용하기 위해)
	public void setPerson(Person person) {
		this.person = person;
	}
	
	// 2-2. person 객체의 getName() 호출하여 그 값을 출력하는 메소드 만들기
	public void sayname() {
		System.out.println(person.getName());
	}
	

}
