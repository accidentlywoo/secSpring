package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.my.dao.CustomerDAO;
import com.my.vo.Customer;

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
	private CustomerDAO customerDAO;

	@Test
	@DisplayName("Customer 자동주입")
	void testCustmer() {
		assertNotNull(customer);
	}
	
	@Test
	@DisplayName("CustomerDAO 자동주입")
	void testCustomerDAO() {
		assertNotNull(customerDAO);
	}

}
