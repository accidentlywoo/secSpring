import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.my.dao.ProductDAO;
import com.my.exception.FindException;
import com.my.service.ProductService;
import com.my.vo.MapVO;
import com.my.vo.OrderInfo;
import com.my.vo.OrderLine;
import com.my.vo.Product;

public class StartContainer {
	public static void main(String[] args) throws FileNotFoundException, IOException, FindException {
		// ������ �����̳ʸ� ����
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("configuration.xml");
//		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(com.my.config.Configuration.class);
		Product p1 = ctx.getBean("product", com.my.vo.Product.class); // 2��° ���ڷ� Down Casting ���ɿ��θ� Ȯ���� �� �ִ�.
//		ctx.getBean("p", com.my.vo.Customer.class);
		Product p2 = ctx.getBean("product", com.my.vo.Product.class); // ������ �����̳ʿ� ���� �����Ǵ� Bean�� �̵������� �����ȴ�.
		System.out.println(p1 == p2);
		System.out.println(p1.getProd_no());
		System.out.println(p1.getProd_name());
		System.out.println(p1.getProd_price());
		
		ProductDAO productDAO = ctx.getBean("productDAO", com.my.dao.ProductDAO.class);
//		productDAO.selectAll();
		System.out.println("========");
		
		ProductService productService = ctx.getBean("productService", com.my.service.ProductService.class);
		productService.findAll();
//		OrderLine line = ctx.getBean("line", com.my.vo.OrderLine.class);
//		System.out.println(line);
////		
//		OrderInfo info = ctx.getBean("info", com.my.vo.OrderInfo.class);
//		System.out.println(info);
////		
//		MapVO maptest = ctx.getBean("mapvo", MapVO.class);
//		System.out.println("maptest : "+maptest);
//		
//		com.my.vo.PropertiesVO proVo = ctx.getBean("propertiesvo", com.my.vo.PropertiesVO.class);
//		System.out.println("proVo : " + proVo.getEnv());
//		
//		Properties env = proVo.getEnv();
//		String fileName = env.getProperty("msg");
//		Properties envMsg = new Properties();
//		envMsg.load(new FileInputStream(fileName));
//		System.out.println(envMsg.getProperty("greeting"));
	}
}
