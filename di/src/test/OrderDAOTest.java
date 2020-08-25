package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.my.dao.CustomerDAO;
import com.my.dao.OrderDAO;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.vo.Customer;
import com.my.vo.OrderInfo;
import com.my.vo.OrderLine;
import com.my.vo.Product;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:com/my/config/configuration.xml")
class OrderDAOTest {

	@Autowired
	private OrderDAO orderDAO;
	
	@Test
	void insert() {
		Customer customer = new Customer("test","test","test");
		Product product = new Product("C0001", "아메리카노", 9999);
		OrderLine line = new OrderLine(24, product, 1);
		List<OrderLine> lines = new ArrayList<OrderLine>();
		lines.add(line);
		OrderInfo orderInfo = new OrderInfo(24, customer, lines, "test");
		try {
			orderDAO.insert(orderInfo);
		} catch ( AddException e) {
			fail(e.getMessage());
		}
	}

	@Test
	void selectById() {
		try {
			assertTrue(orderDAO.selectById("id1").size() == 6);
		} catch (FindException e) {
			fail(e.getMessage());
		}
	}
}
