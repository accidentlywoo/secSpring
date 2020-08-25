package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.my.dao.CustomerDAO;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.vo.Customer;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:configuration.xml")
class CustomerDAOTest {

	@Autowired
	private CustomerDAO customerDAO;
	
	@Test
	void insert() {
		Customer customer = new Customer("test", "test", "test");
		try {
			customerDAO.insert(customer);
		} catch (FindException | AddException e) {
			fail(e.getMessage());
		}
	}

	@Test
	void selectAll() {
		try {
			assertTrue(customerDAO.selectAll().size() > 0);
		} catch (FindException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void selectById() {
		try {
			assertEquals(customerDAO.selectById("id1").getId(), "id1");
		} catch (FindException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void selectByName() {
		try {
			assertEquals(customerDAO.selectByName("tes"), "test");
		} catch (FindException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void update() {
		Customer customer = new Customer("test", "test2", "test2");
		try {
			customerDAO.update(customer);
		} catch (ModifyException | FindException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void delete() {
		try {
			customerDAO.delete("test");
		} catch ( FindException | RemoveException e) {
			fail(e.getMessage());
		}
	}
}
