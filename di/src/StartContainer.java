import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.my.vo.OrderLine;
import com.my.vo.Product;

public class StartContainer {
	public static void main(String[] args) {
		// ������ �����̳ʸ� ����
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("configuration.xml");
		Product p1 = ctx.getBean("p", com.my.vo.Product.class); // 2��° ���ڷ� Down Casting ���ɿ��θ� Ȯ���� �� �ִ�.
//		ctx.getBean("p", com.my.vo.Customer.class);
		Product p2 = ctx.getBean("p", com.my.vo.Product.class); // ������ �����̳ʿ� ���� �����Ǵ� Bean�� �̵������� �����ȴ�.
		System.out.println(p1 == p2);
		System.out.println(p1.getProd_no());
		System.out.println(p1.getProd_name());
		System.out.println(p1.getProd_price());
		
		OrderLine line = ctx.getBean("line", com.my.vo.OrderLine.class);
		System.out.println(line);
	}
}
