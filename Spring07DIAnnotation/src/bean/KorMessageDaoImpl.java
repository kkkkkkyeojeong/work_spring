package bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("korDao")	// bean 이름의 "korDao"라고 정의한 것과 같다. 
public class KorMessageDaoImpl implements MessageDao{

	@Override
	public String getMessage() {
		return "안녕";
	}

}
