package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.my.dao.ProductDAO;
import com.my.exception.FindException;
import com.my.vo.Product;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:configuration.xml")
class ProductDAOTest {

	@Autowired
	private ProductDAO productDAO;

	@Test
	void selectByNo() {
		Product product = new Product("C0001", "아메리카노", 1000);
		try {
			assertEquals(productDAO.selectByNo("C0001"), product );
		} catch (FindException e) {
			fail(e.getMessage());
		}
	}
	@Test
	void selectAll() {
		try {
			List<Product> list = productDAO.selectAll();
			assertTrue(list.size() == 43);
		} catch (FindException e) {
			fail(e.getMessage());
		}
	}
}
