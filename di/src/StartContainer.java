import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.my.vo.MapVO;
import com.my.vo.OrderInfo;
import com.my.vo.OrderLine;
import com.my.vo.Product;

public class StartContainer {
	public static void main(String[] args) throws FileNotFoundException, IOException {
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
		
		OrderInfo info = ctx.getBean("info", com.my.vo.OrderInfo.class);
		System.out.println(info);
		
		MapVO maptest = ctx.getBean("mapvo", MapVO.class);
		System.out.println("maptest : "+maptest);
		
		com.my.vo.PropertiesVO proVo = ctx.getBean("propertiesvo", com.my.vo.PropertiesVO.class);
		System.out.println("proVo : " + proVo.getEnv());
		
		Properties env = proVo.getEnv();
		String fileName = env.getProperty("msg");
		Properties envMsg = new Properties();
		envMsg.load(new FileInputStream(fileName));
		System.out.println(envMsg.getProperty("greeting"));
	}
}
