package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.my.dao.CustomerDAO;
import com.my.vo.Customer;
import com.my.vo.Product;

@ExtendWith(SpringExtension.class) // @RunWith(SpringJUnitClassRunner.class) : JUnit 4 version
@ContextConfiguration(locations = "classpath:configuration.xml")
/*
 * @ContextConfiguration(locations = "file:C:\수업Spring\di\src\configuration.xml")
 * @ContextConfiguration(classes = com.my.cinfig.Configuration.class)
 */
class SpringContainerTest {
	@Autowired
	private Customer customer;
	
	@Autowired
	@Qualifier(value = "hikarids")
	private DataSource ds;
	
//	@Autowired
//	@Qualifier(value = "myOracle")
	private CustomerDAO customerDAO;

//	@Test
	@DisplayName("Customer 자동주입")
	void testCustmer() {
//		assertNotNull(customer);
	}
	
//	@Test
//	@DisplayName("CustomerDAO 자동주입")
	void testCustomerDAO() {
//		assertNotNull(customerDAO);
	}

//	@Test
//	@DisplayName("HikariDBCP")
	void testHikari() {
//		assertNotNull(ds);
		try {
			Connection con = ds.getConnection();
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}
	@Autowired
	SqlSessionFactory sessionFactory;
	
	@Test
	void testMyBatis() {
		SqlSession session = sessionFactory.openSession();
		Product product = session.selectOne("ProductMapper.selectByNo", "C0001");
		assertEquals("아메리카노", product.getProd_name());
	}
}
