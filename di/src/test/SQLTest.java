package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.my.vo.Product;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:configuration.xml")
class SQLTest {
	@Autowired
	SqlSessionFactory sessionFactory;
	
	@Test
	void testProductSelectOne() {
		SqlSession session = sessionFactory.openSession();
		assertNotNull(session);
		
		Product product = session.selectOne("ProductMapper.selectByNo", "C0001");
		assertNotNull(product);
		assertEquals("아메리카노", product.getProd_name());
	}

}
