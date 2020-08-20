import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.my.vo.OrderLine;
import com.my.vo.Product;

public class StartContainer {
	public static void main(String[] args) {
		// 스프링 컨테이너를 구동
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("configuration.xml");
		Product p1 = ctx.getBean("p", com.my.vo.Product.class); // 2번째 인자로 Down Casting 가능여부를 확인할 수 있다.
//		ctx.getBean("p", com.my.vo.Customer.class);
		Product p2 = ctx.getBean("p", com.my.vo.Product.class); // 스프링 컨테이너에 의해 관리되는 Bean은 싱들톤으로 관리된다.
		System.out.println(p1 == p2);
		System.out.println(p1.getProd_no());
		System.out.println(p1.getProd_name());
		System.out.println(p1.getProd_price());
		
		OrderLine line = ctx.getBean("line", com.my.vo.OrderLine.class);
		System.out.println(line);
	}
}
